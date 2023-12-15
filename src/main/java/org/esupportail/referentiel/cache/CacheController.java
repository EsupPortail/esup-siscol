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

	@GetMapping("/reload")
	public ResponseEntity<Object> reload() {
		invalidateCaches();
		loadCaches();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@GetMapping("/load")
	public ResponseEntity<Object> load() {
		loadCaches();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

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
		/* Ajout en cache des étapes */
		cacheService.putCache(CacheConfig.PERMANENT,
				"org.esupportail.referentiel.services.impl.StudentComponentRepositoryDaoWS#getEtapesRef#@Value("
						+ universityCode + ")",
				studentComponentRepositoryDao.getEtapesRef(universityCode));
		logger.debug("Cache for apogeeController/EtapesRef loaded.");
	}

	/**
	 * Invalidation des caches.
	 */
	public void invalidateCaches() {
		logger.debug("Invalidating cache {}.", CacheConfig.TMP);
		cacheService.invalidateCache(new CacheEntry(CacheConfig.TMP));
		logger.debug("Cache {} invalidated.", CacheConfig.TMP);
		logger.debug("Invalidating cache {}.", CacheConfig.PERMANENT);
		cacheService.invalidateCache(new CacheEntry(CacheConfig.PERMANENT));
		logger.debug("Cache {} invalidated.", CacheConfig.PERMANENT);
	}
}