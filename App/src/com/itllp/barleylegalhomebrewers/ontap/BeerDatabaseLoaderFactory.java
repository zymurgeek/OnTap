package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JsonDateToJavaDate;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonArrayRetrieverImpl;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonUrlBeerDatabaseLoader;


public class BeerDatabaseLoaderFactory {

	public static final String betaSiteUrl = "http://misdb.com/barleylegalapp/getbeersforevent.aspx?id=";
	public static final String productionSiteUrl = "http://www.barleylegalevents.com/barleylegal/getbeersforevent.aspx?id=";

	private static void createBeerDatabaseLoader(
			NetworkConnectivity networkConnectivity, String url) {
		BeerDatabase beerDatabase = BeerDatabase.getInstance();
		StringToJavaDateConverter dateConverter = new JsonDateToJavaDate(); 
		JsonArrayBeerDatabaseLoader arrayLoader = 
				new JsonArrayBeerDatabaseLoaderImpl(dateConverter, beerDatabase);
		JsonArrayRetriever arrayRetriever = new JsonArrayRetrieverImpl();
		JsonUrlBeerDatabaseLoader.create(networkConnectivity, url, 
				arrayRetriever, arrayLoader);
	}
	
	public static void createBetaSiteBeerDatabaseLoader(
			NetworkConnectivity networkConnectivity) {
		createBeerDatabaseLoader(networkConnectivity, betaSiteUrl);
	}

	public static void createProductionSiteBeerDatabaseLoader(
			NetworkConnectivity networkConnectivity) {
		createBeerDatabaseLoader(networkConnectivity, productionSiteUrl);
	}
}
