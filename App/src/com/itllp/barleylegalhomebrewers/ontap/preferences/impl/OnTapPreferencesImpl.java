// Copyright 2014 David A. Greenbaum

package com.itllp.barleylegalhomebrewers.ontap.preferences.impl;

import com.itllp.barleylegalhomebrewers.ontap.SortType;
import com.itllp.barleylegalhomebrewers.ontap.preferences.OnTapPreferences;


public class OnTapPreferencesImpl implements OnTapPreferences {

	public OnTapPreferencesImpl() {
		sortType = SortType.NAME;
	}
	
	
	/** Gets sort type.
	 * 
	 * @return Preferred beer sort type.  @see SortType
	 */
	public SortType getSortType() {
		return sortType;
	}

	/** Sets the preferred beer sorting type.
	 * 
	 * @param sortType - @see SortType
	 */
	public void setSortType(SortType newSortType) {
		sortType = newSortType;
	}

	
	public String toString() {
		return sortType.toString();
	};
	

	private SortType sortType;
}