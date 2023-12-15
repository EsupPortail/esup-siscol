package org.esupportail.referentiel.cache;

/**
 * Classe représentant une entrée du cache.
 *
 * @author Kevin Hergalant - Université de Lorraine
 */
public class CacheEntry {

	private String key;
	private String cache;
	private int size;

	public CacheEntry() {
		super();
	}

	public CacheEntry(String key) {
		super();
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCache() {
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "CacheEntry [key=" + key + ", cache=" + cache + ", size=" + size + "]";
	}
}
