// Copyright 2014 David A. Greenbaum

package com.itllp.barleylegalhomebrewers.ontap.preferences.persistence;

import com.itllp.barleylegalhomebrewers.ontap.persistence.PreferencesPersister;

public class PreferencesPersisterFactory {
	private static PreferencesPersister preferencesPersister;

	
	public static void setPreferencesPersister
	(PreferencesPersister newPreferencesPersister) {
		preferencesPersister = newPreferencesPersister;
	}


	public static PreferencesPersister getPreferencesPersister() {
		return preferencesPersister;
	}
	

}
