package com.itllp.barleylegalhomebrewers.ontap.dateconverter.test;

import java.util.HashMap;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringConverter;

// TODO:  Rename this to Fake*
public class MockStringConverter implements StringConverter {

	private HashMap<String, String> conversionMap = new HashMap<String, String>();
	
	@Override
	public String convertString(String input) {
		return conversionMap.get(input);
	}

	public void addConversion(String input, String output) {
		conversionMap.put(input, output);
	}

}
