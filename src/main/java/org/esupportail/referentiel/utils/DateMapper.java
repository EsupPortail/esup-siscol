package org.esupportail.referentiel.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMapper {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public String asString(Date date) {
        return date != null ? DATE_FORMAT.format(date) : null;
    }
}
