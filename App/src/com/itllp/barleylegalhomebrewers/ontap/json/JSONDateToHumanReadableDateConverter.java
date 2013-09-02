package com.itllp.barleylegalhomebrewers.ontap.json;

import java.util.Date;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToStringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

public class JSONDateToHumanReadableDateConverter implements StringConverter {

	private StringToJavaDateConverter jsonDateConverter = null;
	private JavaDateToStringConverter javaDateConverter = null;
	
	public JSONDateToHumanReadableDateConverter(StringToJavaDateConverter jsonDateConverter,
			JavaDateToStringConverter javaDateConverter) {
		this.jsonDateConverter = jsonDateConverter;
		this.javaDateConverter = javaDateConverter;
	}
	
	@Override
	public String convertString(String jsonDate) {
		Date javaDate = jsonDateConverter.getJavaDate(jsonDate);
		return javaDateConverter.getString(javaDate);
	}

}
