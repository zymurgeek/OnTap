package com.itllp.barleylegalhomebrewers.ontap.dateconverter;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaDateToSQLiteDateConverter implements JavaDateToStringConverter {

	@SuppressLint("SimpleDateFormat")
	public final SimpleDateFormat iso8601Format = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

	@Override
	public String getString(Date javaDate) {
		if (null == javaDate) {
			return "";
		}
        String result = iso8601Format.format(javaDate);

		return result;
	}

}
