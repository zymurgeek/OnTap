package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class Event {
	public static final String AUTHORITY = 
			"com.itllp.barleylegalhomebrewers.ontap.Event";
	
	public static final class Events implements BaseColumns {
		public static final Uri CONTENT_URI =
				Uri.parse("content://" + AUTHORITY + "/event");
		public static final String CONTENT_TYPE =
				"vnd.android.cursor.dir/vnd.ontap.event";
		/**
		* The event itself
		* <P>Type: TEXT</P>
		*/
		public static final String EVENT = "event";
		
		/**
		* Column name for the name of the event
		* <P>Type: TEXT</P>
		*/
		public static final String NAME = "name";
		
		/**
		* Column name for the date of the event
		* <P>Type: TEXT</P>
		*/
		public static final String DATE = "date";
		
		private Events() {
		}
	}

}
