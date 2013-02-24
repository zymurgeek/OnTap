package com.itllp.barleylegalhomebrewers.ontap.dateconverter.test;

import java.util.Date;
import java.util.HashMap;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

public class MockStringToJavaDateConverter implements StringToJavaDateConverter {

	private HashMap<String, Date> conversionMap = new HashMap<String, Date>();
	
	@Override
	public Date getJavaDate(String input) {
		return conversionMap.get(input);
	}

	public void addConversion(String input, Date output) {
		conversionMap.put(input, output);
	}

}