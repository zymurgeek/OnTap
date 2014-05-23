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

	
	/** Tells if the beta server (as opposed to the production
	 * server) is in use.
	 * 
	 * @return True if the beta server is in use; false otherwise.
	 */
	public Boolean useBetaServer() {
		return useBetaServer;
	}

	/** Sets if the beta server (as opposed to the production
	 * server) is in use.
	 * 
	 * @param True if the beta server is in use; false otherwise.
	 */
	public void useBetaServer(Boolean useBeta) {
		useBetaServer = useBeta;
	}

	public String toString() {
		return "SortType=" + sortType.toString() +
				", BetaServer=" + useBetaServer.toString();
	};
	

	private SortType sortType;
	private Boolean useBetaServer;
}