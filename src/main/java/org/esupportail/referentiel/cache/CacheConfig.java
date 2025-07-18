package org.esupportail.referentiel.cache;

import java.util.concurrent.TimeUnit;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.ModifiedExpiryPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.jcache.config.JCacheConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * Configuration du cache.
 *
 * @author Kevin Hergalant - Université de Lorraine
 * @author Matthieu Manginot - Université de Lorraine
 */
@EnableCaching
@Configuration
@Service
public class CacheConfig implements JCacheConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

	public static final String TMP = "temporaire";

	public static final String PERMANENT = "permanent";

	@Value("${cache.ttl-tmp:60}")
	private long ttlTmp;

	@Value("${cache.ttl-permanent:0}")
	private long ttlPermanent;

	/**
	 * Instanciation du manager de caches.
	 *
	 * @return manager
	 */
	@Bean
	JCacheManagerCustomizer jCacheManagerCustomizer() {
		return cacheManager -> {
			logger.info("Initialize cache levels...");
			cacheManager.createCache(TMP, new MutableConfiguration<>()
					.setExpiryPolicyFactory(ModifiedExpiryPolicy.factoryOf(new Duration(TimeUnit.MINUTES, ttlTmp))));
			if (ttlPermanent > 0) {
				cacheManager.createCache(PERMANENT, new MutableConfiguration<>().setExpiryPolicyFactory(
						ModifiedExpiryPolicy.factoryOf(new Duration(TimeUnit.HOURS, ttlPermanent))));
			} else {
				cacheManager.createCache(PERMANENT, new MutableConfiguration<>());
			}
			logger.info("Cache levels initialized.");
		};
	}

	/**
	 * @see org.springframework.cache.annotation.CachingConfigurerSupport#keyGenerator()
	 */
	@Override
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> {
			StringBuilder sbKey = new StringBuilder(target.getClass().getName()).append("#").append(method.getName());
			for (Object param : params) {
				if (param != null) {
					sbKey.append("#").append(param.toString());
				}
			}
			return sbKey.toString();
		};
	}
}
