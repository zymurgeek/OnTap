package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONServerJSONArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONUrlBeerDatabaseLoader;


public class BeerDatabaseLoaderFactory {

	public static final String betaSiteUrl = "http://misdb.com/barleylegalapp/getbeersforevent.aspx?id=";
	public static final String productionSiteUrl = "http://www.barleylegalevents.com/barleylegal/getbeersforevent.aspx?id=";

	private static void createBeerDatabaseLoader(
			NetworkConnectivity networkConnectivity, String url) {
		BeerDatabase beerDatabase = BeerDatabase.getInstance();
		JSONArrayBeerDatabaseLoader arrayLoader = 
				new JSONArrayBeerDatabaseLoaderImpl(beerDatabase);
		JSONArrayRetriever arrayRetriever = 
				new JSONServerJSONArrayRetriever(url);
		JSONUrlBeerDatabaseLoader.create(networkConnectivity, url, 
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
