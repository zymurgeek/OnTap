package com.itllp.barleylegalhomebrewers.ontap.json.test;

import org.json.JSONArray;

import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONUrlBeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.test.AlwaysDownNetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.test.AlwaysUpNetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.test.StubBeerDatabaseLoader;

public class JSONUrlBeerDatabaseLoaderTests extends TestCase {

	private final String url = "xyz";
	private StubJSONArrayBeerDatabaseLoader stubJSONArrayLoader = new StubJSONArrayBeerDatabaseLoader();
	NetworkConnectivity upNetworkConnectivity = new AlwaysUpNetworkConnectivity();
	NetworkConnectivity downNetworkConnectivity = new AlwaysDownNetworkConnectivity();

	public void setUp() throws Exception {
		StubBeerDatabaseLoader.clearInstance();
		stubJSONArrayLoader = new StubJSONArrayBeerDatabaseLoader();
	}

	public void tearDown() throws Exception {
	}

	public void testCreateWhenNotInitialized() {
		// Method under test
		JSONUrlBeerDatabaseLoader.create(null, url, null, null);
		
		// Postconditions
		BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		assertNotNull(loader);
		assertTrue(loader instanceof JSONUrlBeerDatabaseLoader);
		JSONUrlBeerDatabaseLoader urlLoader = (JSONUrlBeerDatabaseLoader)loader;
		assertEquals(url, urlLoader.getUrl());
	}

	public void testCreateWhenAlreadyInitialized() {
		// Preconditions
		JSONUrlBeerDatabaseLoader.create(null, null, null, null);
		
		// Method under test and postconditions
		try {
			JSONUrlBeerDatabaseLoader.create(null, null, null, null);
			fail("Should throw exception");
		} catch (DatabaseLoaderAlreadyInstantiatedException e) {
			assertNotNull(e);
		}
	}

	public void testLoadWhenNetworkIsUp() {
		// Preconditions
		int id = 15;
		JSONArray expectedJSONArray = new JSONArray();
		expectedJSONArray.put(true);
		StubJSONArrayRetriever stubRetriever = new StubJSONArrayRetriever();
		stubRetriever.stub_setReturnArray(15, expectedJSONArray);
		JSONUrlBeerDatabaseLoader.create(upNetworkConnectivity, url, 
				stubRetriever, stubJSONArrayLoader);
		BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		
		// Method under test
		loader.load(id);
		
		// Postconditions
		assertEquals(1, stubJSONArrayLoader.getLoadCount());
		JSONArray actualJSONArray = stubJSONArrayLoader.getLastLoadArgument();
		assertEquals(expectedJSONArray, actualJSONArray);
	}
	
	public void testLoadWhenNetworkIsDown() {
		// Preconditions
		JSONUrlBeerDatabaseLoader.create(downNetworkConnectivity, url, 
				null, stubJSONArrayLoader);
		BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		
		// Method under test
		loader.load(14);
		
		// Postconditions
		assertEquals(0, stubJSONArrayLoader.getLoadCount());
	}
}
