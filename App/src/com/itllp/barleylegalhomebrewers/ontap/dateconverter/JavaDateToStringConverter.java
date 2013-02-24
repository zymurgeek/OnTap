package com.itllp.barleylegalhomebrewers.ontap.dateconverter;

import java.util.Date;

public interface JavaDateToStringConverter {

	/**
	 * Converts a java.util.Date to a String.
	 */
	public abstract String getString(Date javaDate);

}