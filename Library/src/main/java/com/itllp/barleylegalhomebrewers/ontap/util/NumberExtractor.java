package com.itllp.barleylegalhomebrewers.ontap.util;

public class NumberExtractor {

	
	public Float extractNumber(String input) {
		String numeric = input.replaceAll("[^\\d.-]", "");
		if (numeric.length() < 1) {
			return null;
		}
		numeric = extractUpToSecondCharacter(numeric, '.');
		numeric = extractUpToSecondCharacter(numeric, '-');
		Float result = null;
		try {
			result = Float.valueOf(numeric);
		} catch (NumberFormatException e) {
			// result = null
		}
		return result;
	}

	
	private String extractUpToSecondCharacter(String numeric, char character) {
		int firstDecimalPointIndex = numeric.indexOf(character);
		if (firstDecimalPointIndex >= 0 && (firstDecimalPointIndex+1 < numeric.length())) {
			int secondDecimalPointIndex = numeric.indexOf(character, firstDecimalPointIndex+1);
			if (secondDecimalPointIndex >= 0) {
				numeric = numeric.substring(0, secondDecimalPointIndex);
			}
		}
		return numeric;
	}

}
