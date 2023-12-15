package org.esupportail.referentiel.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.cache.Cache;
import javax.cache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service de gestion du cache.
 *
 * @author Kevin Hergalant - Université de Lorraine
 * @author Matthieu Manginot - Université de Lorraine
 */
@Service
@SuppressWarnings("serial")
public class CacheService implements Serializable {

	@Autowired
	private transient CacheManager cacheManager;

	/**
	 * @return les caches disponibles
	 */
	public List<CacheEntry> getCaches() {
		final List<CacheEntry> entries = new ArrayList<>();
		cacheManager.getCacheNames().forEach(e -> {
			final Cache<Object, Object> cache = cacheManager.getCache(e);
			final CacheEntry entry = new CacheEntry();
			entry.setKey(e);
			entry.setCache(e);
			final AtomicInteger count = new AtomicInteger(0);
			cache.forEach(cacheEntry -> {
				count.getAndIncrement();
			});
			entry.setSize(count.get());
			entries.add(entry);

		});
		return entries;
	}

	/**
	 * @return les entrées de cache
	 */
	public List<CacheEntry> getCacheEntries() {
		final List<CacheEntry> entries = new ArrayList<>();
		cacheManager.getCacheNames().forEach(e -> {
			final Cache<Object, Object> cache = cacheManager.getCache(e);
			cache.forEach(cacheEntry -> {
				final CacheEntry entry = new CacheEntry();
				entry.setCache(e);
				if (cacheEntry.getKey() instanceof String) {
					entry.setKey(((String) cacheEntry.getKey()));
				} else {
					entry.setKey(cacheEntry.getKey().toString());
				}
				if (cacheEntry.getValue() instanceof ArrayList<?>) {
					entry.setSize(((ArrayList<?>) cacheEntry.getValue()).size());
				} else {
					entry.setSize(1);
				}
				entries.add(entry);
				System.out.println(entry);
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
	 * @param cache
	 * @return les caches invalidés
	 */
	public List<CacheEntry> invalidateCache(final CacheEntry cache) {
		cacheManager.getCache(cache.getKey()).clear();
		return getCaches();
	}

	/**
	 * @param entry
	 * @return les entrées de cache invalidées
	 */
	public List<CacheEntry> invalidEntry(final CacheEntry entry) {
		cacheManager.getCache(entry.getCache()).remove(entry.getKey());
		return getCacheEntries();
	}

}
