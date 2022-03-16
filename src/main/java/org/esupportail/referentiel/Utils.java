package org.esupportail.referentiel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Utils {

	public static Date dateFromLocalDateTime(LocalDateTime ldt) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		ZonedDateTime t = ldt.atZone(defaultZoneId);
		return (Date.from(t.toInstant()));

	}

}
