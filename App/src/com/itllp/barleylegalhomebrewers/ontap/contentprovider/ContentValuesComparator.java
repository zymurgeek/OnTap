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
}
