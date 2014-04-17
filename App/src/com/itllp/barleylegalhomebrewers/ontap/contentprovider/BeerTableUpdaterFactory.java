package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToSQLiteDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToStringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JSONDateToJavaDate;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.BeerJSONObjectToContentValuesConverterImpl;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayToContentValuesListConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayToContentValuesListConverterImpl;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONObjectToContentValuesConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONServerJSONArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.jsonserver.TableFromJSONArrayRetrieverUpdater;


public class BeerTableUpdaterFactory {

	public static final String betaSiteUrl = "http://misdb.com/barleylegalapp/getbeersforevent.aspx?id=#";
	public static final String productionSiteUrl = "http://barleylegalevents.com/barleylegal/getbeersforevent.aspx?id=#";
	private static String serverUrlTemplate;
	private static JSONArrayRetriever arrayRetriever;
	
	private static void createSQLiteBeerTableUpdater(String serverURL) {
		serverUrlTemplate = serverURL;
		arrayRetriever = new JSONServerJSONArrayRetriever(serverURL);
		StringToJavaDateConverter jsonDateConverter = new JSONDateToJavaDate();
		JavaDateToStringConverter javaDateConverter = 
				new JavaDateToSQLiteDateConverter();
		JSONObjectToContentValuesConverter objectConverter =
				new BeerJSONObjectToContentValuesConverterImpl
				(jsonDateConverter,	javaDateConverter);
		JSONArrayToContentValuesListConverter listConverter =
				new JSONArrayToContentValuesListConverterImpl(objectConverter);
		CursorConverter cursorConverter = new CursorConverterImpl();
		BeerTable beerTable = new SQLiteBeerTable(cursorConverter);
		TableFromJSONArrayUpdater beerTableFromJSONArrayUpdater = 
				new SQLiteBeerTableFromJSONArrayUpdaterImpl(listConverter,
						beerTable);
		beerTableUpdater = new TableFromJSONArrayRetrieverUpdater
				(arrayRetriever, beerTableFromJSONArrayUpdater);
	}

	
	public static void createSQLiteBeerTableFromProductionServerUpdater() {
		createSQLiteBeerTableUpdater(productionSiteUrl);
	}
	
	
	public static void createSQLiteBeerTableFromBetaServerUpdater() {
		createSQLiteBeerTableUpdater(betaSiteUrl);
	}

	
	public static TableUpdater getInstance(String eventId) {
		String serverUrl = serverUrlTemplate.replace("#", eventId);
		arrayRetriever.setServerUrl(serverUrl);
		return beerTableUpdater;
	}
	
	
	private static TableUpdater beerTableUpdater = null;
}
