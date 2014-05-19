// Copyright 2014 David A. Greenbaum

package com.itllp.barleylegalhomebrewers.ontap.preferences;

import com.itllp.barleylegalhomebrewers.ontap.SortType;


public interface OnTapPreferences {

	/** Gets sort type.
	 * 
	 * @return Preferred beer sort type.  @see SortType
	 */
	public SortType getSortType();

	/** Sets the preferred beer sorting type.
	 * 
	 * @param sortType - @see SortType
	 */
	public void setSortType(SortType sortType);

	public String toString();
}