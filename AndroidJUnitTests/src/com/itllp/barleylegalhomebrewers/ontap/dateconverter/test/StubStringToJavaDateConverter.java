package com.itllp.barleylegalhomebrewers.ontap.dateconverter.test;

import java.util.HashMap;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

public class StubStringToJavaDateConverter implements StringToJavaDateConverter {

	private HashMap<String, java.util.Date> conversionMap 
	= new HashMap<String, java.util.Date>();
	
	@Override
	public java.util.Date getJavaDate(String input) {
		return conversionMap.get(input);
	}

	public void stub_addConversion(String input, java.util.Date output) {
		conversionMap.put(input, output);
	}

}
