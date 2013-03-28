package com.itllp.barleylegalhomebrewers.ontap.json.test;

import org.json.JSONArray;

import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonUrlBeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.test.AlwaysDownNetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.test.AlwaysUpNetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.test.FakeBeerDatabaseLoader;

public class JsonUrlBeerDatabaseLoaderTests extends TestCase {

	private final String url = "xyz";
	private FakeJsonArrayBeerDatabaseLoader fakeJsonArrayLoader = new FakeJsonArrayBeerDatabaseLoader();
	NetworkConnectivity upNetworkConnectivity = new AlwaysUpNetworkConnectivity();
	NetworkConnectivity downNetworkConnectivity = new AlwaysDownNetworkConnectivity();

	public void setUp() throws Exception {
		FakeBeerDatabaseLoader.clearInstance();
		fakeJsonArrayLoader = new FakeJsonArrayBeerDatabaseLoader();
	}

	public void tearDown() throws Exception {
	}

	public void testCreateWhenNotInitialized() {
		// Method under test
		JsonUrlBeerDatabaseLoader.create(null, url, null, null);
		
		// Postconditions
		BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		assertNotNull(loader);
		assertTrue(loader instanceof JsonUrlBeerDatabaseLoader);
		JsonUrlBeerDatabaseLoader urlLoader = (JsonUrlBeerDatabaseLoader)loader;
		assertEquals(url, urlLoader.getUrl());
	}

	public void testCreateWhenAlreadyInitialized() {
		// Preconditions
		JsonUrlBeerDatabaseLoader.create(null, null, null, null);
		
		// Method under test and postconditions
		try {
			JsonUrlBeerDatabaseLoader.create(null, null, null, null);
			fail("Should throw exception");
		} catch (DatabaseLoaderAlreadyInstantiatedException e) {
			assertNotNull(e);
		}
	}

	public void testLoadWhenNetworkIsUp() {
		// Preconditions
		JSONArray expectedJsonArray = new JSONArray();
		expectedJsonArray.put(true);
		FakeJsonArrayRetriever fakeRetriever = new FakeJsonArrayRetriever();
		fakeRetriever.FAKE_setWhenUrl(url);
		fakeRetriever.FAKE_setReturnArray(expectedJsonArray);
		JsonUrlBeerDatabaseLoader.create(upNetworkConnectivity, url, 
				fakeRetriever, fakeJsonArrayLoader);
		BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		
		// Method under test
		loader.load();
		
		// Postconditions
		assertEquals(1, fakeJsonArrayLoader.getLoadCount());
		JSONArray actualJsonArray = fakeJsonArrayLoader.getLastLoadArgument();
		assertEquals(expectedJsonArray, actualJsonArray);
	}
	
	public void testLoadWhenNetworkIsDown() {
		// Preconditions
		JsonUrlBeerDatabaseLoader.create(downNetworkConnectivity, url, 
				null, fakeJsonArrayLoader);
		BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		
		// Method under test
		loader.load();
		
		// Postconditions
		assertEquals(0, fakeJsonArrayLoader.getLoadCount());
	}
}
