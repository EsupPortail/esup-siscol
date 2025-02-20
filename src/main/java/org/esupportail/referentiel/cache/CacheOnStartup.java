package org.esupportail.referentiel.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Classe exécutée au démarrage pour charger les caches.
 *
 * @author Matthieu Manginot - Université de Lorraine
 *
 */
@Service
@ConditionalOnProperty(name = "cache.onStartup.enabled", matchIfMissing = true)
public class CacheOnStartup implements ApplicationListener<ApplicationReadyEvent> {
	private static final Logger logger = LoggerFactory.getLogger(CacheOnStartup.class);

	@Autowired
	private transient CacheController cacheController;

	@Value("${app.apogee.universityCode}")
	private transient String universityCode;

	/**
	 * Exécution au démarrage.
	 */
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.debug("Force loading of caches on startup.");
		cacheController.loadCaches();
	}
}