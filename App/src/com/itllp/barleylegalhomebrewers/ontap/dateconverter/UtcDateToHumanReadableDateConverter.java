package com.itllp.barleylegalhomebrewers.ontap.dateconverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.content.Context;


public class UtcDateToHumanReadableDateConverter implements JavaDateToStringConverter {

	@Override
	public String getString(Date javaDate) {
		String stringDate = "";
		if (javaDate != null) {
			stringDate = DateFormat.getDateInstance(DateFormat.FULL).format(javaDate);
		}
		return stringDate;
	}

    @SuppressLint("SimpleDateFormat")
	public static String formatDateTime(Context context, String timeToFormat) {

        String finalDateTime = "";          

        SimpleDateFormat iso8601Format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        Date date = null;
        if (timeToFormat != null) {
            try {
                date = iso8601Format.parse(timeToFormat);
            } catch (java.text.ParseException e) {
                date = null;
            }

            if (date != null) {
                long when = date.getTime();
                int flags = 0;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_TIME;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_DATE;
                flags |= android.text.format.DateUtils.FORMAT_ABBREV_MONTH;
                flags |= android.text.format.DateUtils.FORMAT_SHOW_YEAR;

                finalDateTime = android.text.format.DateUtils.formatDateTime(context,
                when + TimeZone.getDefault().getOffset(when), flags);               
            }
        }
        return finalDateTime;
    }

}
