package com.itllp.barleylegalhomebrewers.ontap.dateconverter;

import java.text.DateFormat;
import java.util.Date;

public class JavaDateToHumanReadableDate implements JavaDateToString {

	@Override
	public String getString(Date javaDate) {
		String stringDate = DateFormat.getDateInstance(DateFormat.FULL).format(javaDate);
		return stringDate;
	}

}
