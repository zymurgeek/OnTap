package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import android.content.ContentValues;

public class ContentValuesComparator {
	public boolean areEqual(ContentValues a, ContentValues b) {
		if (a==null && b==null) {
			return true;
		}
		if (a==null || b==null) {
			return false;
		}
		if (a.size() != b.size()) {
			return false;
		}
		if (!a.toString().equals(b.toString())) {
			return false;
		}
		return true;
	}
	
	public boolean areBeersEqual(ContentValues a, ContentValues b) {
		if (a==null && b==null) {
			return true;
		}
		if (a==null || b==null) {
			return false;
		}
		if (a.size() != b.size()) {
			return false;
		}
		if (!areIntegersEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ID_COLUMN)) {
			return false;
		}
		if (!areStringsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.NAME_COLUMN)) {
			return false;
		}
		if (!areIntegersEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.EVENT_ID_COLUMN)) {
			return false;
		}
		if (!areStringsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.BREWER_NAME_COLUMN)) {
			return false;
		}
		if (!areStringsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_CODE_COLUMN)) {
			return false;
		}
		if (!areStringsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_NAME_COLUMN)) {
			return false;
		}
		if (!areStringsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_OVERRIDE_COLUMN)) {
			return false;
		}
		if (!areIntegersEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.IS_KICKED_COLUMN)) {
			return false;
		}
		if (!areIntegersEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.TAP_NUMBER_COLUMN)) {
			return false;
		}
		if (!areStringsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.PACKAGING_COLUMN)) {
			return false;
		}
		if (!areStringsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.DESCRIPTION_COLUMN)) {
			return false;
		}
		if (!areFloatsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ORIGINAL_GRAVITY_COLUMN)) {
			return false;
		}
		if (!areFloatsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.FINAL_GRAVITY_COLUMN)) {
			return false;
		}
		if (!areFloatsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ALCOHOL_BY_VOLUME_COLUMN)) {
			return false;
		}
		if (!areFloatsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.INTERNATIONAL_BITTERNESS_UNITS_COLUMN)) {
			return false;
		}
		//TODO Change SRM to REAL
		if (!areStringsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STANDARD_REFERENCE_METHOD_COLUMN)) {
			return false;
		}
		if (!areIntegersEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.IS_EMAIL_SHOWN)) {
			return false;
		}
		if (!areStringsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.EMAIL_ADDRESS)) {
			return false;
		}
		if (!areStringsEqual(a, b, com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.UNTAPPD_BEER_ID)) {
			return false;
		}
		return true;
	}

	
	public boolean areIntegersEqual(ContentValues a, ContentValues b, String key) {
		Integer intA = a.getAsInteger(key);
		Integer intB = b.getAsInteger(key);
		if (intA == null && intB == null) {
			return true;
		}
		if (intA == null || intB == null) {
			return false;
		}
		return intA.equals(intB);
	}

	public boolean areStringsEqual(ContentValues a, ContentValues b, String key) {
		String stringA = a.getAsString(key);
		String stringB = b.getAsString(key);
		if (stringA == null && stringB == null) {
			return true;
		}
		if (stringA == null || stringB == null) {
			return false;
		}
		return stringA.equals(stringB);
	}

	public boolean areFloatsEqual(ContentValues a, ContentValues b,	String key) {
		Float floatA = a.getAsFloat(key);
		Float floatB = b.getAsFloat(key);
		if (floatA == null && floatB == null) {
			return true;
		}
		if (floatA == null || floatB == null) {
			return false;
		}
		return floatA.equals(floatB);
	}

	
}
