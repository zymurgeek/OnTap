package com.itllp.barleylegalhomebrewers.ontap.persistence.impl.test;

import java.math.BigDecimal;

import com.itllp.barleylegalhomebrewers.ontap.persistence.Persister;

import android.content.Context;


public class StubPreferencesPersister implements Persister {

	@Override
	public void beginSave(Context context) {
	}

	@Override
	public void save(String key, BigDecimal value) throws Exception {
	}

	@Override
	public void save(String key, boolean value) throws Exception {
	}

	@Override
	public void save(String key, int value) throws Exception {
	}

	@Override
	public void endSave() throws Exception {
	}

	@Override
	public BigDecimal retrieveBigDecimal(Context context, String key) {
		return null;
	}

	
	@Override
	public Boolean retrieveBoolean(Context context, String key) {
		return null;
	}

	
	@Override
	public Integer retrieveInteger(Context context, String key) {
		return null;
	}

}
