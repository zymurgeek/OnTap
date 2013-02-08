package com.itllp.barleylegalhomebrewers.ontap.dateconverter;

import java.util.Date;

public interface StringToJavaDate {

	/**
	 * Converts a String to a java Date.
	 * @return Date corresponding to input stringDate or Date(0) if the
	 * input string cannot be converted to a Date.
	 */
	public abstract Date getJavaDate(String stringDate);

}