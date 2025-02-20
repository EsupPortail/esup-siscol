package org.esupportail.referentiel.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.cache.Cache;
import javax.cache.CacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service de gestion du cache.
 *
 * @author Kevin Hergalant - Université de Lorraine
 * @author Matthieu Manginot - Université de Lorraine
 * 
 * @author acheraga
 *         Simplifications made: 
 *         Removed redundant final keywords for local variables. 
 *         Used Cache.spliterator().getExactSizeIfKnown() to get the size of the cache directly. 
 *         Simplified the getKey() and getValue() type checks in getCacheEntries(). 
 *         Used CacheService.class directly in  the logger initialization.
 */
@Service
@SuppressWarnings("serial")
public class CacheService implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

	@Autowired
	private transient CacheManager cacheManager;

	/**
	 * Liste des caches.
	 *
	 * @return les caches disponibles
	 */
	public List<CacheEntry> getCaches() {
		List<CacheEntry> entries = new ArrayList<>();
		cacheManager.getCacheNames().forEach(name -> {
			Cache<Object, Object> cache = cacheManager.getCache(name);
			CacheEntry entry = new CacheEntry();
			entry.setKey(name);
			entry.setCache(name);
			entry.setSize((int) cache.spliterator().getExactSizeIfKnown());
			entries.add(entry);
		});
		return entries;
	}

	/**
	 * Liste des entrées de cache.
	 *
	 * @return les entrées de cache
	 */
	public List<CacheEntry> getCacheEntries() {
		List<CacheEntry> entries = new ArrayList<>();
		cacheManager.getCacheNames().forEach(name -> {
			Cache<Object, Object> cache = cacheManager.getCache(name);
			cache.forEach(cacheEntry -> {
				CacheEntry entry = new CacheEntry();
				entry.setCache(name);
				entry.setKey(cacheEntry.getKey().toString());
				entry.setSize(cacheEntry.getValue() instanceof List<?> ? ((List<?>) cacheEntry.getValue()).size() : 1);
				entries.add(entry);
			});
		});
		return entries;
	}

	/**
	 * Ajoute une entrée dans le cache.
	 *
	 * @param cacheName cacheName
	 * @param key       clé
	 * @param value     valeur
	 */
	public void putCache(final String cacheName, final String key, final Object value) {
		cacheManager.getCache(cacheName).put(key, value);
	}

	/**
	 * Invalide un cache.
	 *
	 * @param cache
	 * @return les caches invalidés
	 */
	public List<CacheEntry> invalidateCache(final CacheEntry cache) {
		cacheManager.getCache(cache.getKey()).clear();
		return getCaches();
	}

	/**
	 * Invalide une entrée de cache.
	 *
	 * @param entry
	 * @return les entrées de cache invalidées
	 */
	public List<CacheEntry> invalidateEntry(final CacheEntry entry) {
		cacheManager.getCache(entry.getCache()).remove(entry.getKey());
		return getCacheEntries();
	}

	/**
	 * Invalidation de tous les caches.
	 */
	public void invalidateAllCaches() {
		logger.debug("Invalidation of caches.");
		cacheManager.getCacheNames().forEach(cacheName -> {
			logger.debug("Invalidation of cache {}.", cacheName);
			invalidateCache(new CacheEntry(cacheName));
			logger.debug("Cache {} invalidated.", cacheName);
		});
		logger.debug("Caches invalidated.");
	}
}
