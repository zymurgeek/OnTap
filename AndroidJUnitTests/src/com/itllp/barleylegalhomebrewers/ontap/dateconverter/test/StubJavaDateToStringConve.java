package com.itllp.barleylegalhomebrewers.ontap.dateconverter.test;

import java.util.HashMap;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToStringConverter;

public class StubJavaDateToStringConve implements JavaDateToStringConverter {

	private HashMap<java.util.Date, String> conversionMap 
	= new HashMap<java.util.Date, String>();
	
	@Override
	public String getString(java.util.Date input) {
		return conversionMap.get(input);
	}

	public void stub_addConversion(java.util.Date input, String output) {
		conversionMap.put(input, output);
	}

}
