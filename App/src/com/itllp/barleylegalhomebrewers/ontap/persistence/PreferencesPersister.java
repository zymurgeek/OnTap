package com.itllp.barleylegalhomebrewers.ontap.persistence;

import android.content.Context;

import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferences;

public interface PreferencesPersister {
	public static final String SORT_TYPE_KEY = "SortType";

	public abstract void saveState(OnTapPreferences preferences, Context context);

	public abstract void restoreState(OnTapPreferences preferences, Context context);

}