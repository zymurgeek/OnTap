package com.itllp.barleylegalhomebrewers.ontap.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.itllp.barleylegalhomebrewers.ontap.SortType;

public class SortTypeToActionId {

	public SortTypeToActionId() {
		sortTypeActionId = new HashMap<SortType, Integer>();
	}
	
	public void add(SortType sortType, int actionId) {
		sortTypeActionId.put(sortType, Integer.valueOf(actionId));
	}

	public int getActionId(SortType sortType) {
		Integer intId = sortTypeActionId.get(sortType);
		int result = 0;
		if (intId != null) {
			result = intId.intValue();
		}
		return result;
	}
	
	public SortType getSortType(int actionId) {
		Set<SortType> keys = sortTypeActionId.keySet();
		
		for (SortType key : keys) {
			if (getActionId(key) == actionId) {
				return key;
			}
		}
		
		return SortType.NAME;
	}
	
	
	private Map<SortType, Integer> sortTypeActionId;
}

