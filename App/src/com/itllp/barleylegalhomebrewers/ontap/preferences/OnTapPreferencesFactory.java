// Copyright 2014 David A. Greenbaum
package com.itllp.barleylegalhomebrewers.ontap.preferences;

public class OnTapPreferencesFactory {
	private static OnTapPreferences preferences;

	
	public static void setPreferences(OnTapPreferences newPreferences) {
		preferences = newPreferences;
	}

	
	public static OnTapPreferences getPreferences() {
		return preferences;
	}
	

}
