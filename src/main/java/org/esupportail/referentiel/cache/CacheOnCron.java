package org.esupportail.referentiel.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Cron pour la gestion des caches.
 *
 * @author Matthieu Manginot - Université de Lorraine
 *
 */
@Service
@EnableScheduling
@ConditionalOnProperty(name = "cache.scheduling.enabled", matchIfMissing = true)
public class CacheOnCron {
	private static final Logger logger = LoggerFactory.getLogger(CacheOnCron.class);

	@Autowired
	private transient CacheController cacheController;

	@Scheduled(cron = "${cache.scheduling.cron}")
	public void onCron() {
		logger.debug("Invaliding caches from cron.");
		cacheController.invalidateCaches();
		logger.debug("Force loading of caches on cron.");
		cacheController.loadCaches();
	}
}