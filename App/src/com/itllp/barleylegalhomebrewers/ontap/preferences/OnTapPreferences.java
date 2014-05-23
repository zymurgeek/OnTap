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

	/** Tells if the beta server (as opposed to the production
	 * server) is in use.
	 * 
	 * @return True if the beta server is in use; false otherwise.
	 */
	public Boolean useBetaServer();

	/** Sets if the beta server (as opposed to the production
	 * server) is in use.
	 * 
	 * @param True if the beta server is in use; false otherwise.
	 */
	public void useBetaServer(Boolean useBeta);

	public String toString();
}