package com.itllp.barleylegalhomebrewers.ontap.json.test;

import org.json.JSONArray;

import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoaderIF;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.json.JSONUrlEventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.test.AlwaysDownNetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.test.AlwaysUpNetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.test.StubEventDatabaseLoader;

public class JSONUrlEventDatabaseLoaderTests extends TestCase {

	private final String url = "xyz";
	private StubJSONArrayEventDatabaseLoader stubJSONArrayLoader = new StubJSONArrayEventDatabaseLoader();
	NetworkConnectivity upNetworkConnectivity = new AlwaysUpNetworkConnectivity();
	NetworkConnectivity downNetworkConnectivity = new AlwaysDownNetworkConnectivity();
	
	public void setUp() throws Exception {
		StubEventDatabaseLoader.clearInstance();
		stubJSONArrayLoader = new StubJSONArrayEventDatabaseLoader();
	}

	public void tearDown() throws Exception {
	}

	public void testCreateWhenNotInitialized() {
		// Method under test
		JSONUrlEventDatabaseLoader.create(null, url, null, null);
		
		// Postconditions
		EventDatabaseLoaderIF loader = EventDatabaseLoader.getInstance();
		assertNotNull(loader);
		assertTrue(loader instanceof JSONUrlEventDatabaseLoader);
		JSONUrlEventDatabaseLoader urlLoader = (JSONUrlEventDatabaseLoader)loader;
		assertEquals(url, urlLoader.getUrl());
	}

	public void testCreateWhenAlreadyInitialized() {
		// Preconditions
		JSONUrlEventDatabaseLoader.create(null, null, null, null);
		
		// Method under test and postconditions
		try {
			JSONUrlEventDatabaseLoader.create(null, null, null, null);
			fail("Should throw exception");
		} catch (DatabaseLoaderAlreadyInstantiatedException e) {
			assertNotNull(e);
		}
	}

	public void testLoadWhenNetworkIsUp() {
		// Preconditions
		JSONArray expectedJSONArray = new JSONArray();
		expectedJSONArray.put(true);
		StubJSONArrayRetriever stubRetriever = new StubJSONArrayRetriever();
		stubRetriever.stub_setReturnArray(expectedJSONArray);
		JSONUrlEventDatabaseLoader.create(upNetworkConnectivity, url, 
				stubRetriever, stubJSONArrayLoader);
		EventDatabaseLoaderIF loader = EventDatabaseLoader.getInstance();
		
		// Method under test
		loader.load();
		
		// Postconditions
		assertEquals(1, stubJSONArrayLoader.getLoadCount());
		JSONArray actualJSONArray = stubJSONArrayLoader.getLastLoadArgument();
		assertEquals(expectedJSONArray, actualJSONArray);
	}
	
	public void testLoadWhenNetworkIsDown() {
		// Preconditions
		JSONUrlEventDatabaseLoader.create(downNetworkConnectivity, url, 
				null, stubJSONArrayLoader);
		EventDatabaseLoaderIF loader = EventDatabaseLoader.getInstance();
		
		// Method under test
		loader.load();
		
		// Postconditions
		assertEquals(0, stubJSONArrayLoader.getLoadCount());
	}

}
