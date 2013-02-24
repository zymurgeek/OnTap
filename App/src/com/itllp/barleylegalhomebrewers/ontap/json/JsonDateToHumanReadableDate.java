package com.itllp.barleylegalhomebrewers.ontap.json;

import java.util.Date;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToString;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

public class JsonDateToHumanReadableDate implements StringConverter {

	private StringToJavaDateConverter jsonDateConverter = null;
	private JavaDateToString javaDateConverter = null;
	
	public JsonDateToHumanReadableDate(StringToJavaDateConverter jsonDateConverter,
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
