package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;

import android.database.Cursor;

public class BeerCursorUtility {

	
	public String getBeerName(Cursor beer) {
		int nameColIndex = beer.getColumnIndex(BeerTableMetadata.NAME_COLUMN);
		String name = beer.getString(nameColIndex);
		return name;
	}

	
	public String getBrewerName(Cursor beer) {
		int nameColIndex = beer.getColumnIndex(BeerTableMetadata.BREWER_NAME_COLUMN);
		String name = beer.getString(nameColIndex);
		return name;
	}

	
	public int getTapNumber(Cursor beer) {
		int tapColIndex = beer.getColumnIndex(BeerTableMetadata.TAP_NUMBER_COLUMN);
		int tapNumber = beer.getInt(tapColIndex);
		return tapNumber;
	}


	public String getPackaging(Cursor beer) {
		int packagingColIndex = beer.getColumnIndex(BeerTableMetadata.PACKAGING_COLUMN);
		String packaging = beer.getString(packagingColIndex);
		return packaging;
	}
	
	
	public String getStyleCode(Cursor beer) {
		int styleCodeColIndex = beer.getColumnIndex(BeerTableMetadata.STYLE_CODE_COLUMN);
		String styleCode = beer.getString(styleCodeColIndex);
		return styleCode;
	}
	
	
	public String getStyleName(Cursor beer) {
		int styleNameColIndex = beer.getColumnIndex(BeerTableMetadata.STYLE_NAME_COLUMN);
		String styleName = beer.getString(styleNameColIndex);
		return styleName;
	}
	
	
	public String getStyleOverride(Cursor beer) {
		int overrideColIndex = beer.getColumnIndex(BeerTableMetadata.STYLE_OVERRIDE_COLUMN);
		String override = beer.getString(overrideColIndex);
		return override;
	}
	
	
	public boolean isKicked(Cursor beer) {
		int kickedColIndex = beer.getColumnIndex(BeerTableMetadata.IS_KICKED_COLUMN);
		int isKicked = beer.getInt(kickedColIndex);
		return (isKicked != 0);
	}


	public boolean isPouring(Cursor beer) {
		if ((getTapNumber(beer) != 0) && !isKicked(beer)) {
			return true;
		}
		return false;
	}

	
	public String getOriginalGravity(Cursor beer) {
		int ogColIndex = beer.getColumnIndex(BeerTableMetadata.ORIGINAL_GRAVITY_COLUMN);
		String ogString = "";
		try {
			double og = beer.getDouble(ogColIndex);
			ogString = String.valueOf(og);
		} catch (Exception e) {}
		return ogString;
	}
	
	

}
