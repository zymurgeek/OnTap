package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import com.itllp.barleylegalhomebrewers.ontap.database.SQLiteEventTable;

import android.provider.BaseColumns;

public class Event {
	public static final class Events implements BaseColumns {
		/**
		* The event itself
		* <P>Type: TEXT</P>
		*/
		public static final String EVENT = "event";
		
		public static final String DEFAULT_SORT_ORDER = SQLiteEventTable.START_LOCAL_TIME_COLUMN;
		
		private Events() {
		}
	}

}
// TODO Can this class be deleted?