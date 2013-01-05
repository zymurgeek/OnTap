package com.itllp.beerfestival.dateconverter;

import java.util.Date;

public class JsonDateToJavaDate {

	/**
	 * Converts a JSON date to a java Date.
	 * @param jsonDate A date formatted for JSON in Microsoft Ajax format,
	 * e.g. "\/@Date(1352001600000-0400)\/".
	 * @return Date corresponding to input jsonDate.
	 */
	public Date getJavaDate(String jsonDate) {
		String milliPlusTzString = removeJsonDateWrapper(jsonDate);
		
		int timezoneStartOffset = getTimezoneStartOffset(milliPlusTzString);
		if (-1 == timezoneStartOffset) {
			timezoneStartOffset = milliPlusTzString.length();
		}
		String millisString = milliPlusTzString.substring(0, timezoneStartOffset);
		String timezoneOffsetString = milliPlusTzString.substring(timezoneStartOffset);
		
		long millis = parseLong(millisString);
		long timezoneOffset = parseLong(timezoneOffsetString);
		
		long hourTimezoneOffset = getTzHours(timezoneOffset);
		long minuteTimezoneOffset = getTzMinutes(timezoneOffset);
		millis += getMillisFromHours(hourTimezoneOffset);
		millis += getMillisFromMinutes(minuteTimezoneOffset);
		
		Date result = new Date(millis);

		return result;
	}


	/**
	 * Returns the number of milliseconds are in a number of minutes.
	 * @param minutes 
	 * @return The number of milliseconds in the given number of minutes.
	 */
	long getMillisFromMinutes(long minutes) {
		return minutes * 60 * 1000;
	}


	/**
	 * Returns the number of milliseconds in a number of hours.
	 * @param hours
	 * @return The number of milliseconds in the given number of hours.
	 */
	long getMillisFromHours(long hours) {
		return hours * 60 * 60 * 1000;
	}


	/**
	 * Returns the minutes in a given timezone offset.
	 * @param timezoneOffset Numeric equivalent of a timezone offset in format "HHMM",
	 * e.g. "0530" would be 530. 
	 * @return The minute portion of the input.  For example, for 530, the result
	 * would be 30.
	 */
	long getTzMinutes(long timezoneOffset) {
		long minuteTimezoneOffset = timezoneOffset % 100;
		return minuteTimezoneOffset;
	}


	/**
	 * Returns the hours in a given timezone offset.
	 * @param timezoneOffset Numeric equivalent of a timezone offset in format "HHMM",
	 * e.g. "0530" would be 530. 
	 * @return The hours portion of the input.  For example, for 530, the result
	 * would be 5.
	 */
	long getTzHours(long timezoneOffset) {
		long hourTimezoneOffset = timezoneOffset / 100;
		return hourTimezoneOffset;
	}


	/**
	 * Removes the text portion of a JSON date.
	 * @param jsonDate JSON formatted date, 
	 * e.g. "\/Date(1352001600000-0400)\/".
	 * @return The millisecond and TZ portion in between the parenthesis or an
	 * empty string if the input is not formatted as a JSON date. 
	 */
	String removeJsonDateWrapper(String jsonDate) {
		if (null == jsonDate) {
			return "";
		}
		String dateString = jsonDate.replace("\\/Date(", "");
		dateString = dateString.replace(")\\/", "");
		return dateString;
	}
	
	
	/**
	 * Returns the starting index of the timezone offset.
	 * @param dateString The millisecond and timezone portion
	 * of a JSON date, i.e. the part inside the parenthesis.
	 * @return The character index or -1 if there is no timezone.
	 */
	int getTimezoneStartOffset(String dateString) {
		if (null == dateString) {
			return -1;
		}
		int timezoneStartOffset = dateString.indexOf('-');
		if (-1 == timezoneStartOffset) {
			timezoneStartOffset = dateString.indexOf('+');
		}
		return timezoneStartOffset;
	}

	/**
	 * Returns a long.
	 * This mmthod uses the Long.parseLong method except a leading "+" is 
	 * permitted.
	 * @param longString A long integer in string format.
	 * @return The long contained in the string or 0 if the input does not
	 * contain a long integer.
	 */
	long parseLong(String longString) {
		long longNumber = 0;
		
		if (null == longString) {
			longString = "";
		}
		try {
			if (longString.startsWith("+")) {
				longString = longString.substring(1);
			}
			longNumber = Long.parseLong(longString);
		} catch (NumberFormatException e) {
			longNumber = 0;
		}
		
		return longNumber;
	}


}
