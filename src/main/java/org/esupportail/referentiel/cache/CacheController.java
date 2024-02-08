package org.esupportail.referentiel.cache;

import org.esupportail.referentiel.services.StudentComponentRepositoryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de gestion des caches.
 *
 * @author Kevin Hergalant - Université de Lorraine
 * @author Matthieu Manginot - Université de Lorraine
 *
 */
@RestController
@RequestMapping("cache")
public class CacheController {
	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private transient StudentComponentRepositoryDao studentComponentRepositoryDao;
	@Autowired
	private transient CacheService cacheService;

	@Value("${app.apogee.universityCode}")
	private transient String universityCode;

	/**
	 * API de rechargement complet des caches (invalidation puis chargement).
	 *
	 * @return {@link HttpStatus#OK}
	 */
	@GetMapping("/reload")
	public ResponseEntity<Object> reload() {
		invalidateCaches();
		loadCaches();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * API de chargement des caches.
	 *
	 * @return {@link HttpStatus#OK}
	 */
	@GetMapping("/load")
	public ResponseEntity<Object> load() {
		loadCaches();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * API d'invalidation des caches.
	 *
	 * @return {@link HttpStatus#OK}
	 */
	@GetMapping("/invalidate")
	public ResponseEntity<Object> invalid() {
		invalidateCaches();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * Chargement des caches.
	 */
	public void loadCaches() {
		logger.debug("Loading caches.");
		loadCacheEtapes();
	}

	/**
	 * Chargement du cache Etapes.
	 */
	public void loadCacheEtapes() {
		logger.debug("Loading cache for apogeeController/EtapesRef.");
		/* Appel à getEtapesRef pour mise en cache des étapes */
		studentComponentRepositoryDao.getEtapesRef(universityCode);
		logger.debug("Cache for apogeeController/EtapesRef loaded.");
	}

	/**
	 * Invalidation des caches.
	 */
	public void invalidateCaches() {
		cacheService.invalidateAllCaches();
	}
}