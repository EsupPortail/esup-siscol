package org.esupportail.referentiel.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class CacheOnStartup implements ApplicationListener<ApplicationReadyEvent> {
	final transient Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private transient CacheController cacheController;

	@Value("${app.apogee.universityCode}")
	private transient String universityCode;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.debug("Force loading of caches on startup.");
		cacheController.loadCaches();
	}
}