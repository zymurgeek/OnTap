package com.itllp.barleylegalhomebrewers.ontap.dateconverter;

import java.text.DateFormat;
import java.util.Date;

public class JavaDateToHumanReadableDateConverter implements JavaDateToStringConverter {

	@Override
	public String getString(Date javaDate) {
		String stringDate = "";
		if (javaDate != null) {
			stringDate = DateFormat.getDateInstance(DateFormat.FULL).format(javaDate);
		}
		return stringDate;
	}

}
