package com.itllp.barleylegalhomebrewers.ontap;
public enum SortType {
    NAME(0),
    STYLE(1),
    ABV(2),
    IBU(3),
    SRM(4);

    private final int sortId;
    
    SortType(int id) {
        this.sortId = id;
    }
    
    public int getId() { 
    	return sortId; 
    }
    
    public static SortType getSortType(int id) {
    	if (STYLE.getId() == id) {
    		return STYLE;
    	}
    	if (ABV.getId() == id) {
    		return ABV;
    	}
    	if (IBU.getId() == id) {
    		return IBU;
    	}
    	if (SRM.getId() == id) {
    		return SRM;
    	}
    	return NAME;
    }
}