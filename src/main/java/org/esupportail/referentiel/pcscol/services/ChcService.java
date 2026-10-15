package org.esupportail.referentiel.pcscol.services;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import org.esupportail.referentiel.beans.ElementPedagogique;
import org.esupportail.referentiel.pcscol.api.chc.ArbresApi;
import org.esupportail.referentiel.pcscol.api.chc.CursusDcaApi;
import org.esupportail.referentiel.pcscol.api.chc.ObjetsMaquetteApiCHC;
import org.esupportail.referentiel.pcscol.api.odf.EspacesExterneApi;
import org.esupportail.referentiel.pcscol.api.odf.ObjetsMaquetteExterneApi;
import org.esupportail.referentiel.pcscol.chcv6.model.ArbreLecture;
import org.esupportail.referentiel.pcscol.chcv6.model.ArbreObjetFormation;
import org.esupportail.referentiel.pcscol.chcv6.model.CursusDCA;
import org.esupportail.referentiel.pcscol.chcv6.model.LignePedagogiqueDCA;
import org.esupportail.referentiel.pcscol.chcv6.model.ObjetMaquette;
import org.esupportail.referentiel.pcscol.invoker.ApiException;
import org.esupportail.referentiel.pcscol.odf.model.DescripteursObjetFormation;
import org.esupportail.referentiel.pcscol.odf.model.Espace;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteDetail;
import org.esupportail.referentiel.pcscol.odf.model.ObjetMaquetteSummary;
import org.esupportail.referentiel.pcscol.odf.model.Pageable;
import org.esupportail.referentiel.pcscol.odf.model.PagedEspaces;
import org.esupportail.referentiel.pcscol.odf.model.PagedObjetMaquetteSummaries;
import org.esupportail.referentiel.pcscol.odf.model.TypeObjetMaquette;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.mode_pegase")
public class ChcService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CursusDcaApi cursusDcaApi;
	@Autowired
	private ArbresApi arbresApi;
	
	@Autowired
	private ObjetsMaquetteApiCHC objetsMaquetteApiCHC;
	@Autowired
	private EspacesExterneApi espacesApi;

	@Autowired
	private OffreFormationService offreFormationService;

	

	public ArbreLecture arbrePourUneFormation(String codeStructure, String codePeriode, String codeFormation) {
		try {
			return arbresApi.arbrePourUneFormation(codeStructure, codePeriode, codeFormation);
		} catch (ApiException e) {
			logger.error("Erreur lors de la lecture de l'arbre pour la formation {} : {}", codeFormation,
					e.getMessage());
			return null;
		}
	}

	public List<ArbreObjetFormation> listeObjetFormationFromArbreObjetFormation(String codeStructure,
			String codePeriode, String codeFormation) {
		ArbreLecture arbreLecture = arbrePourUneFormation(codeStructure, codePeriode, codeFormation);
		return arbreLecture.getFormationArbre().getListeObjetFormation();
	}

	/**
	 * TODO erreur de lecture
	 * 
	 * @param codeStructure
	 * @param codePeriode
	 * @return
	 */

	public List<ObjetMaquette> lireListeFormationAll(String codeStructure, String codePeriode) {
		try {
			return objetsMaquetteApiCHC.lireListeFormationAll(codeStructure, codePeriode);
		} catch (ApiException e) {
			logger.error("Erreur lors de la lecture des formations pour la structure {} et la période {} : {}",
					codeStructure, codePeriode, e.getMessage());
			return Collections.emptyList();
		}
	}

	/**
	 * Retourne la liste des {@link ElementPedagogique} de type <b>stage</b>
	 * associés à un apprenant, en parcourant toute la chaîne d'appel suivante :
	 *
	 * <pre>
	 * 1. CursusDcaApi.lireCusrsuApprenant(codeApprenant)
	 *       → GET /cursus-dca?codeApprenant={codeApprenant}
	 *       → Retourne List&lt;CursusDCA&gt;
	 *         (chaque CursusDCA contient : codeStructure, periode, formation,
	 *          racinePedagogique : LignePedagogiqueDCA [arbre récursif d'enfants])
	 *
	 * 2. Filtre optionnel sur cursus.getPeriode().getCode() == codeEspace
	 *
	 * 3. Pour chaque CursusDCA retenu :
	 *    ChcService.listeElementPedagogiqueStageFromCursus(cursus, codeStructure)
	 *       → ChcService.listeElementPedagogiqueFromCursus(cursus, codeStructure)
	 *           → ChcService.lireCursus(cursus)
	 *               a. EspacesApi.rechercherEspaces(codeStructure, pageable, codePeriode, null, null)
	 *                     → GET /etablissement/{codeStructure}/espaces?r={codePeriode}
	 *                     → Retourne PagedEspaces  (recherche l'UUID de l'espace)
	 *
	 *               b. ChcService.lignePedagogiqueDCADynamic(codeStructure, racinePeda,
	 *                                                         lignes, items, espaceUUID)
	 *                     → Parcours itératif DFS de l'arbre LignePedagogiqueDCA
	 *                     → Pour chaque enfant non encore visité :
	 *                         ObjetsMaquetteApi.rechercherObjetMaquette(
	 *                             codeStructure, pageable, codeEnfant, espace, ...)
	 *                           → GET /etablissement/{codeStructure}/objets-maquette
	 *                                 ?r={codeEnfant}&espace={espaceUUID}&...
	 *                           → Retourne PagedObjetMaquetteSummaries
	 *                           → Filtre les items dont le code == codeEnfant
	 *                           → Accumule dans itemsObjetMaquetteSummaries
	 *
	 *               c. OffreFormationService.recherchDescripteurMaquettes(
	 *                       codeStructure, [UUID des ObjetMaquetteSummary])
	 *                     → Pour chaque UUID :
	 *                         ObjetsMaquetteApi.lireObjetMaquette(codeStructure, id)
	 *                           → GET /etablissement/{codeStructure}/objets-maquette/{id}
	 *                           → Retourne ObjetMaquetteDetail
	 *                     → Retourne List&lt;ObjetMaquetteDetail&gt;
	 *
	 *           → Pour chaque ObjetMaquetteDetail : construit un ElementPedagogique
	 *             (codElp, codEtp, codePeriode, libElp, temElpTypeStage, nbrCrdElp,
	 *              libNatureElp, codVrsVet = codeStructure)
	 *
	 *       → Filtre les ElementPedagogique dont temElpTypeStage == "true"
	 *
	 * 4. Retourne List&lt;ElementPedagogique&gt; (stages uniquement)
	 * </pre>
	 *
	 * @param codeApprenant code unique de l'apprenant dans Pégase
	 * @param codeStructure code de l'établissement (ex. "ETAB00")
	 * @param codeEspace    code de l'espace/période à filtrer ; si {@code null} ou
	 *                      vide, tous les cursus de l'apprenant sont traités
	 * @return liste (possiblement vide) des éléments pédagogiques de type stage
	 * @throws ApiException en cas d'erreur lors de l'appel à
	 *                      {@code CursusDcaApi.lireCusrsuApprenant}
	 */
	public List<ElementPedagogique> lirelisteElementPedagogiqueStageApprenant(String codeApprenant,
			String codeStructure, String codeEspace) throws ApiException {
		List<CursusDCA> response = cursusDcaApi.lireCusrsuApprenant(codeApprenant);

		if (codeEspace != null && !codeEspace.isBlank()) {
			response = response.stream()
					.filter(cursus -> cursus.getPeriode() != null && codeEspace.equals(cursus.getPeriode().getCode()))
					.toList();
		}

		/**
		 */
		List<ElementPedagogique> listeElementPedagogique = new ArrayList<>();
		response.forEach(cursus -> {
			List<ElementPedagogique> lep = listeElementPedagogiqueStageFromCursus(cursus, codeStructure);
			listeElementPedagogique.addAll(lep);
		});
		return listeElementPedagogique;

	}

	/**
	 * Délègue directement à {@link CursusDcaApi#lireCusrsuApprenant(String)}.
	 * <p>
	 * Appel HTTP : {@code GET /cursus-dca?codeApprenant={codeApprenant}}
	 * </p>
	 * Retourne la liste brute de tous les cursus de l'apprenant sans aucun
	 * filtrage. Chaque {@link CursusDCA} contient :
	 * <ul>
	 * <li>{@code codeStructure} – code de l'établissement</li>
	 * <li>{@code periode}
	 * ({@link org.esupportail.referentiel.pcscol.chcv6.model.PeriodeDCA}) – période
	 * du cursus</li>
	 * <li>{@code formation}
	 * ({@link org.esupportail.referentiel.pcscol.chcv6.model.FormationDCA}) –
	 * formation associée</li>
	 * <li>{@code racinePedagogique}
	 * ({@link org.esupportail.referentiel.pcscol.chcv6.model.LignePedagogiqueDCA})
	 * – racine de l'arbre pédagogique récursif</li>
	 * </ul>
	 *
	 * @param codeApprenant code unique de l'apprenant dans Pégase (obligatoire)
	 * @return liste de {@link CursusDCA} (peut être vide, jamais {@code null})
	 * @throws ApiException si l'appel HTTP échoue (4xx / 5xx) ou si
	 *                      {@code codeApprenant} est {@code null}
	 */
	public List<CursusDCA> lireCusrsuApprenant(String codeApprenant) throws ApiException {
		return cursusDcaApi.lireCusrsuApprenant(codeApprenant);
	}

	/**
	 * Construit la liste des {@link ElementPedagogique} correspondant à
	 * <em>tous</em> les objets maquette trouvés dans un {@link CursusDCA}.
	 * <p>
	 * Chaîne d'appel interne :
	 * </p>
	 * 
	 * <pre>
	 * ChcService.lireCursus(cursus)
	 *   → EspacesApi.rechercherEspaces(codeStructure, pageable, codePeriode, null, null)
	 *         GET /etablissement/{codeStructure}/espaces?r={codePeriode}
	 *         → PagedEspaces  (résolution de l'UUID de l'espace)
	 *   → ChcService.lignePedagogiqueDCADynamic(...)
	 *         → ObjetsMaquetteApi.rechercherObjetMaquette(...) [1 appel / enfant]
	 *               GET /etablissement/{codeStructure}/objets-maquette?r={code}&espace={uuid}&...
	 *               → PagedObjetMaquetteSummaries
	 *   → OffreFormationService.recherchDescripteurMaquettes(codeStructure, uuids)
	 *         → ObjetsMaquetteApi.lireObjetMaquette(codeStructure, id) [1 appel / UUID]
	 *               GET /etablissement/{codeStructure}/objets-maquette/{id}
	 *               → ObjetMaquetteDetail
	 * Pour chaque ObjetMaquetteDetail :
	 *   - codElp         ← objetMaquette.getCode()
	 *   - codEtp         ← cursus.getRacinePedagogique().getCodeObjetMaquette()
	 *   - codePeriode    ← cursus.getPeriode().getCode()
	 *   - libElp         ← descripteursObjetMaquette.getLibelle()
	 *   - temElpTypeStage← String.valueOf(DescripteursObjetFormation.getStage())
	 *   - nbrCrdElp      ← DescripteursObjetFormation.getEcts()
	 *   - libNatureElp   ← DescripteursObjetFormation.getType().getCode()
	 *   - codVrsVet      ← codeStructure
	 * </pre>
	 *
	 * @param cursus        cursus de l'apprenant (jamais {@code null})
	 * @param codeStructure code de l'établissement (ex. "ETAB00")
	 * @return liste (possiblement vide) de tous les {@link ElementPedagogique} du
	 *         cursus, tous types confondus (stages et non-stages)
	 */
	public List<ElementPedagogique> listeElementPedagogiqueFromCursus(CursusDCA cursus, String codeStructure) {
		List<ElementPedagogique> listeElementPedagogique = new ArrayList<>();
		List<ObjetMaquetteDetail> lomd;
		try {
			lomd = lireCursus(cursus);
			lomd.forEach(objetMaquette -> {

				try {
					ElementPedagogique elemePeda = new ElementPedagogique();

					DescripteursObjetFormation dem = (DescripteursObjetFormation) objetMaquette
							.getDescripteursObjetMaquette();

					elemePeda.setCodElp(objetMaquette.getCode());
					/**
					 * TODO diplome formation
					 */
					// elemePeda.setCodEtp(cursus.getFormation().getCode());
					elemePeda.setCodePeriode(cursus.getPeriode().getCode());

					elemePeda.setCodEtp(cursus.getRacinePedagogique().getCodeObjetMaquette());
					elemePeda.setCodVrsVet(codeStructure);
					elemePeda.setLibElp(objetMaquette.getDescripteursObjetMaquette().getLibelle());
					elemePeda.setTemElpTypeStage(String.valueOf(dem.getStage()));
					elemePeda.setNbrCrdElp(dem.getEcts());
					if (dem.getType() != null)
						elemePeda.setLibNatureElp(dem.getType().getCode());
					listeElementPedagogique.add(elemePeda);

				} catch (Exception e) {
					logger.error("Erreur lors de la lecture de l'élément pédagogique pour l'objet maquette : {}",
							objetMaquette.getCode(), e);
				}

			});
		} catch (ApiException e) {
			logger.error("Erreur lors de la lecture du cursus : {}", cursus.getCodeStructure(), e);
		}

		return listeElementPedagogique;
	}

	/**
	 * Filtre les {@link ElementPedagogique} d'un {@link CursusDCA} pour ne
	 * conserver que ceux dont {@code temElpTypeStage} vaut {@code "true"}
	 * (insensible à la casse).
	 * <p>
	 * Délègue d'abord à
	 * {@link #listeElementPedagogiqueFromCursus(CursusDCA, String)} pour construire
	 * la liste complète, puis applique le filtre :
	 * </p>
	 * 
	 * <pre>
	 * listeElementPedagogiqueFromCursus(cursus, codeStructure)
	 *   → (voir Javadoc de cette méthode pour la chaîne d'appel complète)
	 *
	 * stream().filter(e -> e.getTemElpTypeStage().equalsIgnoreCase("true"))
	 *   → Retourne List&lt;ElementPedagogique&gt; (stages uniquement)
	 * </pre>
	 *
	 * @param cursus        cursus de l'apprenant (jamais {@code null})
	 * @param codeStructure code de l'établissement (ex. "ETAB00")
	 * @return liste (possiblement vide) des {@link ElementPedagogique} dont le type
	 *         est stage
	 */
	public List<ElementPedagogique> listeElementPedagogiqueStageFromCursus(CursusDCA cursus, String codeStructure) {
		List<ElementPedagogique> listeElementPedagogique = listeElementPedagogiqueFromCursus(cursus, codeStructure);

		try {
			Stream<ElementPedagogique> resultFiltre = listeElementPedagogique.stream()
					.filter(e -> e.getTemElpTypeStage().equalsIgnoreCase("true"));

			listeElementPedagogique = resultFiltre.toList();

		} catch (Exception e) {
			logger.error("Erreur lors du filtrage des éléments pédagogiques de type stage : {}", e.getMessage());
		}
		logger.debug("listeElementPedagogiqueStageFromCursus : {}", listeElementPedagogique.size());
		return listeElementPedagogique;
	}

	/**
	 * Résout l'arbre pédagogique d'un {@link CursusDCA} en une liste de
	 * {@link ObjetMaquetteDetail} exploitables, en trois étapes :
	 *
	 * <ol>
	 * <li><b>Résolution de l'espace (période)</b> :
	 * 
	 * <pre>
	 * EspacesApi.rechercherEspaces(cursus.getCodeStructure(), pageable,
	 *                              cursus.getPeriode().getCode(), null, null)
	 *   → GET /etablissement/{codeStructure}/espaces
	 *         ?r={codePeriode}
	 *   → PagedEspaces
	 *   → Filtre l'Espace dont e.getCode() == cursus.getPeriode().getCode()
	 *   → espaceUUID (UUID de l'espace)
	 * </pre>
	 * 
	 * Si aucun espace ne correspond, retourne une liste vide.</li>
	 *
	 * <li><b>Parcours de l'arbre pédagogique (DFS itératif)</b> :
	 * 
	 * <pre>
	 * ChcService.lignePedagogiqueDCADynamic(codeStructure, racinePedagogique,
	 *                                        lignes, itemsObjetMaquetteSummaries,
	 *                                        espaceUUID.toString())
	 *   → Pour chaque code enfant non encore visité :
	 *       ObjetsMaquetteApi.rechercherObjetMaquette(
	 *           codeStructure, pageable, codeEnfant, espaceUUID,
	 *           typeObjetMaquette, racine, typeObjetFormation,
	 *           ids, piaSeulement, piaActif, valideSeulement, mutualise)
	 *         → GET /etablissement/{codeStructure}/objets-maquette
	 *               ?r={codeEnfant}&espace={espaceUUID}&...
	 *         → PagedObjetMaquetteSummaries
	 *         → Filtre objet.getCode() == codeEnfant
	 *         → Accumule dans itemsObjetMaquetteSummaries
	 * </pre>
	 * 
	 * </li>
	 *
	 * <li><b>Lecture des détails des objets maquette</b> :
	 * 
	 * <pre>
	 * OffreFormationService.recherchDescripteurMaquettes(codeStructure, uuids)
	 *   → Pour chaque UUID dans la liste :
	 *       ObjetsMaquetteApi.lireObjetMaquette(codeStructure, id)
	 *         → GET /etablissement/{codeStructure}/objets-maquette/{id}
	 *         → ObjetMaquetteDetail
	 *   → Retourne List&lt;ObjetMaquetteDetail&gt;
	 * </pre>
	 * 
	 * </li>
	 * </ol>
	 *
	 * @param cursus le cursus à traiter (ne doit pas être {@code null}, ni avoir
	 *               une période ou une formation {@code null})
	 * @return liste (possiblement vide) des {@link ObjetMaquetteDetail} trouvés
	 *         dans l'arbre du cursus
	 * @throws ApiException si l'appel à {@code EspacesApi.rechercherEspaces} échoue
	 */
	public List<ObjetMaquetteDetail> lireCursus(CursusDCA cursus) throws ApiException {

		logger.debug("lireCodeEspaceCursus : {} {}", cursus.getCodeStructure(), cursus.getPeriode().getCode());
		Pageable pageable=new Pageable();
		// rechecher espaces pour la structure et la période du cursus
		PagedEspaces espaceDCA = espacesApi.rechercherEspaces(cursus.getCodeStructure(), pageable,
				cursus.getPeriode().getCode(), null, null);

		UUID espaceUUID = null;

		for (Espace e : espaceDCA.getItems()) {
			if (e.getCode().equals(cursus.getPeriode().getCode())) {
				espaceUUID = e.getId();
				break;
			}
			logger.warn("Code espace {} non trouvé pour le cursus (code formation) {}", cursus.getPeriode().getCode(),
					cursus.getFormation().getCode());
		}

		logger.debug("espaceUUID : {}", espaceUUID);

		if (espaceUUID == null) {
			logger.warn("Aucun espace trouvé pour le cursus (code formation) {}", cursus.getFormation().getCode());
			return Collections.emptyList();
		}
		LignePedagogiqueDCA racinePeda = cursus.getRacinePedagogique();
		List<String> lignes = new ArrayList<>();
		List<ObjetMaquetteSummary> itemsObjetMaquetteSummaries = new ArrayList<>();
		// Récupération des lignes pédagogiques et des objets maquette associés
		lignePedagogiqueDCADynamic(cursus.getCodeStructure(), racinePeda, lignes, itemsObjetMaquetteSummaries,
				espaceUUID.toString());
		logger.info("nbr itemsObjetMaquetteSummaries {} pour le cursus (code formation) {}",
				itemsObjetMaquetteSummaries.size(), cursus.getFormation().getCode());

		List<UUID> uuidsObjetMaquetteSummaries = new ArrayList<>();
		itemsObjetMaquetteSummaries.forEach(e -> uuidsObjetMaquetteSummaries.add(e.getId()));
		logger.debug("uuidsObjetMaquetteSummaries : {}", uuidsObjetMaquetteSummaries);

		return offreFormationService.recherchDescripteurMaquettes(cursus.getCodeStructure(),
				uuidsObjetMaquetteSummaries);
	}

	/**
	 * Variante <b>itérative</b> (pile explicite) du parcours DFS de l'arbre
	 * {@link LignePedagogiqueDCA}, conçue pour éviter les débordements de pile et
	 * limiter les appels API redondants grâce à deux ensembles de codes déjà
	 * visités.
	 * <p>
	 * Algorithme :
	 * </p>
	 * 
	 * <pre>
	 * pile ← [racinePeda]
	 * codesLignesVisites    ← {} (évite de remettre en liste un même code)
	 * codesObjetsDejaCharges← {} (évite de rappeler l'API pour un même code enfant)
	 *
	 * TANT QUE pile non vide :
	 *   courante ← pile.pop()
	 *   SI courante.codeObjetMaquette ∉ codesLignesVisites :
	 *       lignes.add(code)
	 *   POUR CHAQUE enfant de courante (ordre inverse pour préserver l'ordre original) :
	 *       SI enfant.codeObjetMaquette ∉ codesObjetsDejaCharges :
	 *           ObjetsMaquetteApi.rechercherObjetMaquette(
	 *               codeStructure, pageable, codeEnfant, espace,
	 *               typeObjetMaquette, racine, typeObjetFormation,
	 *               ids, piaSeulement, piaActif, valideSeulement, mutualise)
	 *             → GET /etablissement/{codeStructure}/objets-maquette
	 *                   ?r={codeEnfant}&espace={espace}&...
	 *             → PagedObjetMaquetteSummaries
	 *             → Filtre objet.getCode() == codeEnfant
	 *             → Accumule dans itemsObjetMaquetteSummaries
	 *       pile.push(enfant)
	 * </pre>
	 *
	 * @param codeStructure               code de l'établissement (ex. "ETAB00")
	 * @param racinePeda                  nœud racine de l'arbre pédagogique du
	 *                                    cursus ; si {@code null}, la méthode
	 *                                    retourne immédiatement
	 * @param lignes                      liste (accumulée) des codes des nœuds
	 *                                    visités
	 * @param itemsObjetMaquetteSummaries liste (accumulée) des
	 *                                    {@link ObjetMaquetteSummary} trouvés pour
	 *                                    chaque enfant unique
	 * @param espace                      UUID de l'espace (sous forme
	 *                                    {@code String}) utilisé comme critère de
	 *                                    filtrage dans l'appel à
	 *                                    {@code ObjetsMaquetteApi.rechercherObjetMaquette}
	 */
	public void lignePedagogiqueDCADynamic(String codeStructure, LignePedagogiqueDCA racinePeda, List<String> lignes,
			List<ObjetMaquetteSummary> itemsObjetMaquetteSummaries, String espace) {
		if (racinePeda == null) {
			return;
		}

		Deque<LignePedagogiqueDCA> pile = new ArrayDeque<>();
		Set<String> codesLignesVisites = new HashSet<>();
		Set<String> codesObjetsDejaCharges = new HashSet<>();
		pile.push(racinePeda);

		while (!pile.isEmpty()) {
			LignePedagogiqueDCA courante = pile.pop();
			if (courante == null) {
				continue;
			}

			String codeCourant = courante.getCodeObjetMaquette();
			if (codeCourant != null && codesLignesVisites.add(codeCourant)) {
				lignes.add(codeCourant);
			}

			List<LignePedagogiqueDCA> enfants = courante.getEnfants();
			if (enfants == null || enfants.isEmpty()) {
				continue;
			}

			for (int i = enfants.size() - 1; i >= 0; i--) {
				LignePedagogiqueDCA enfant = enfants.get(i);
				if (enfant == null) {
					continue;
				}

				String codeEnfant = enfant.getCodeObjetMaquette();
				if (codeEnfant != null && codesObjetsDejaCharges.add(codeEnfant)) {
					try {
						Boolean piaSeulement = null;
						Boolean piaActif = null;

						PagedObjetMaquetteSummaries om = offreFormationService.rechercheObjetMaquette(codeStructure,
								codeEnfant, espace, piaSeulement, piaActif);

						if (om.getItems() != null && !om.getItems().isEmpty()) {
							for (ObjetMaquetteSummary objet : om.getItems()) {
								if (codeEnfant.equals(objet.getCode())) {
									itemsObjetMaquetteSummaries.add(objet);
								}
							}
						}
					} catch (Exception e) {
						logger.error("Erreur lors de la recherche de l'objet maquette {} : {}", codeEnfant,
								e.getMessage());
					}
				}

				pile.push(enfant);
			}
		}
	}

}