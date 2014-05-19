// Copyright 2014 David A. Greenbaum
package com.itllp.barleylegalhomebrewers.ontap.persistence.impl;

import com.itllp.barleylegalhomebrewers.ontap.SortType;
import com.itllp.barleylegalhomebrewers.ontap.persistence.Persister;
import com.itllp.barleylegalhomebrewers.ontap.persistence.PreferencesPersister;
import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferences;

import android.content.Context;
import android.widget.Toast;


public class PreferencesPersisterImpl implements PreferencesPersister {
	private Persister persister;
	
	public PreferencesPersisterImpl(Persister persister) {
		this.persister = persister;
	}


	/* (non-Javadoc)
	 * @see com.itllp.barleylegalhomebrewers.ontap.preferences.persistence.impl.PreferencesPersister#saveState(com.itllp.tipOnDiscount.model.DataModel, com.itllp.barleylegalhomebrewers.ontap.persistence.Persister, android.content.Context)
	 */
	@Override
	public void saveState(OnTapPreferences preferences, Context context) {
		persister.beginSave(context);
		try {
			persister.save(PreferencesPersister.SORT_TYPE_KEY, preferences.getSortType().getId());
			persister.endSave();
		} catch (Exception e) {
			Toast.makeText(context,
           		 "Failed to save preferences", Toast.LENGTH_LONG).show();
		}
	}

	
	/* (non-Javadoc)
	 * @see com.itllp.barleylegalhomebrewers.ontap.preferences.persistence.impl.PreferencesPersister#saveState(com.itllp.tipOnDiscount.model.DataModel, com.itllp.barleylegalhomebrewers.ontap.persistence.Persister, android.content.Context)
	 */
	@Override
	public void restoreState(OnTapPreferences preferences, Context context) {
		Integer sortTypeId = persister.retrieveInteger(context, 
				PreferencesPersister.SORT_TYPE_KEY);
		if (null != sortTypeId) {
			SortType sortType = SortType.getSortType(sortTypeId.intValue());
			preferences.setSortType(sortType);
		}
	}
}
