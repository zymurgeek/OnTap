package com.itllp.barleylegalhomebrewers.ontap.persistence.impl;

import java.math.BigDecimal;

import com.itllp.barleylegalhomebrewers.ontap.persistence.Persister;

import android.content.Context;
import android.content.SharedPreferences;


/* The preferences are stored in a SharedPreferences file. 
 * All instances of an app share the same instance of this file 
 */
public class PreferencesFilePersister implements Persister {

	private String preferencesFileName = null;
	private SharedPreferences.Editor editor = null;
	
	public PreferencesFilePersister(String preferencesFileName) {
		this.preferencesFileName = preferencesFileName;
	}
	
	@Override
	public void beginSave(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(
				this.preferencesFileName, 
				android.content.Context.MODE_PRIVATE);
		editor = prefs.edit();
		editor.clear();
	}

	@Override
	public void save(String key, BigDecimal value) throws Exception {
		if (null == editor) {
			throw new Exception("beginSave must be called first");
		}
		if (null == key) {
			throw new Exception("key may not be null");
		}
		if (null == value) {
			throw new Exception("value may not be null");
		}
        String stringValue = value.toPlainString();
        editor.putString(key, stringValue);
	}

	@Override
	public void save(String key, boolean value) throws Exception {
		if (null == editor) {
			throw new Exception("beginSave must be called first");
		}
		if (null == key) {
			throw new Exception("key may not be null");
		}
        editor.putBoolean(key, value);
	}

	@Override
	public void save(String key, int value) throws Exception {
		if (null == editor) {
			throw new Exception("beginSave not called");
		}
		if (null == key) {
			throw new Exception("key may not be null");
		}
        editor.putInt(key, value);
	}

	@Override
	public void endSave() throws Exception {
		if (null == editor) {
			throw new Exception("beginSave must be called first");
		}
		editor.commit();
		editor = null;
	}

	@Override
	public BigDecimal retrieveBigDecimal(Context context, String key) {
		SharedPreferences prefs =
				context.getSharedPreferences(
						this.preferencesFileName, 
						android.content.Context.MODE_PRIVATE);
		if (!prefs.contains(key)) {
			return null;
		}
		String noValue = "";
        String stringValue = prefs.getString(key, noValue);
        BigDecimal bigDecimalValue = null;
        if (null != stringValue) {
        	try {
        		bigDecimalValue = new BigDecimal(stringValue);
        	} catch (NumberFormatException x) {
        		// leave bigDecimalValue == null
        	}
        }
		return bigDecimalValue;
	}

	
	@Override
	public Boolean retrieveBoolean(Context context, String key) {
		SharedPreferences prefs =
				context.getSharedPreferences(
						this.preferencesFileName, 
						android.content.Context.MODE_PRIVATE);
		if (!prefs.contains(key)) {
			return null;
		}
		Boolean booleanValue = null;
        try {
        	boolean boolValue = prefs.getBoolean(key, false);
        	booleanValue = Boolean.valueOf(boolValue);
        } catch (Exception x) {
        	// leave booleanValue == null
        }
		return booleanValue;
	}

	
	@Override
	public Integer retrieveInteger(Context context, String key) {
		SharedPreferences prefs =
				context.getSharedPreferences(
						this.preferencesFileName, 
						android.content.Context.MODE_PRIVATE);
		if (!prefs.contains(key)) {
			return null;
		}
		Integer integerValue = null;
        try {
        	int intValue = prefs.getInt(key, Integer.MIN_VALUE);
        	integerValue = Integer.valueOf(intValue);
        } catch (Exception x) {
        	// leave integerValue == null
        }
		return integerValue;
	}

}
