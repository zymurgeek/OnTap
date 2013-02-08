package com.itllp.beerfestival.json;

import java.util.Date;

import com.itllp.beerfestival.dateconverter.JavaDateToString;
import com.itllp.beerfestival.dateconverter.StringConverter;
import com.itllp.beerfestival.dateconverter.StringToJavaDate;

public class JsonDateToHumanReadableDate implements StringConverter {

	private StringToJavaDate jsonDateConverter = null;
	private JavaDateToString javaDateConverter = null;
	
	public JsonDateToHumanReadableDate(StringToJavaDate jsonDateConverter,
			JavaDateToString javaDateConverter) {
		this.jsonDateConverter = jsonDateConverter;
		this.javaDateConverter = javaDateConverter;
	}
	
	@Override
	public String convertString(String jsonDate) {
		Date javaDate = jsonDateConverter.getJavaDate(jsonDate);
		return javaDateConverter.getString(javaDate);
	}

}
