package com.itllp.barleylegalhomebrewers.ontap.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UntappdBeerIdParser {

	/**
	 Extracts an Untappd Beer ID from a given string.  The beer ID is identified
	 by the phrase "Untappd [...] ####" where:
	 <ul>
	 <li>"Untappd" is case insensitive</li>
	 <li>[...] is anything other than a number</li>
	 <li>#### is the numeric beer ID</li>
	 </ul>
	 For example, calling this method with the text 
	 "This beer's Untappd ID is 123456" would return "123456". 
	 @param String to search
	 @return Untappd Beer ID or empty string if no ID found. 
	 */
	public String getUntappdBeerId(String text) {
		String regEx = "Untappd[^0-9]*([0-9]+)";
		String untappdBeerId; // = text.replaceAll(regEx, "$1"); 
		final Pattern regExPattern = Pattern.compile(
			    regEx, Pattern.CASE_INSENSITIVE );
		Matcher m = regExPattern.matcher(text); 
		untappdBeerId = m.find() ? m.group(1) : "";
		
		return untappdBeerId;
	}

}
