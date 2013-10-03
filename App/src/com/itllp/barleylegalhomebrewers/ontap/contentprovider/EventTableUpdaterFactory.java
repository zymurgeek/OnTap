package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToSQLiteDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToStringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JSONDateToJavaDate;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.EventJSONObjectToContentValuesConverterImpl;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayToContentValuesListConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayToContentValuesListConverterImpl;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONObjectToContentValuesConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONServerJSONArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.jsonserver.TableFromJSONArrayRetrieverUpdater;


public class EventTableUpdaterFactory {

	public static final String betaSiteUrl = "http://misdb.com/barleylegalapp/getevent.aspx";
	public static final String productionSiteUrl = "http://www.barleylegalevents.com/barleylegal/getevent.aspx";

	
	private static void createSQLiteEventTableUpdater(String serverURL) {
		JSONArrayRetriever arrayRetriever = 
				new JSONServerJSONArrayRetriever(serverURL);
		StringToJavaDateConverter jsonDateConverter = new JSONDateToJavaDate();
		JavaDateToStringConverter javaDateConverter = 
				new JavaDateToSQLiteDateConverter();
		JSONObjectToContentValuesConverter objectConverter =
				new EventJSONObjectToContentValuesConverterImpl
				(jsonDateConverter,	javaDateConverter);
		JSONArrayToContentValuesListConverter listConverter =
				new JSONArrayToContentValuesListConverterImpl(objectConverter);
		CursorConverter cursorConverter = new CursorConverterImpl();
		EventTable eventTable = new SQLiteEventTable(cursorConverter);
		TableFromJSONArrayUpdater eventTableFromJSONArrayUpdater = 
				new SQLiteEventTableFromJSONArrayUpdaterImpl(listConverter,
						eventTable);
		eventTableUpdater = new TableFromJSONArrayRetrieverUpdater
				(arrayRetriever, eventTableFromJSONArrayUpdater);
	}

	
	public static void createSQLiteEventTableFromProductionServerUpdater() {
		createSQLiteEventTableUpdater(productionSiteUrl);
	}
	
	
	public static void createSQLiteEventTableFromBetaServerUpdater() {
		createSQLiteEventTableUpdater(betaSiteUrl);
	}

	
	public static TableUpdater getInstance() {
		return eventTableUpdater;
	}
	
	
	private static TableUpdater eventTableUpdater = null;
}
