package com.itllp.barleylegalhomebrewers.ontap.json.test;

import org.json.JSONArray;

import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonUrlEventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.test.AlwaysDownNetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.test.AlwaysUpNetworkConnectivity;
import com.itllp.barleylegalhomebrewers.ontap.test.FakeEventDatabaseLoader;

public class JsonUrlEventDatabaseLoaderTests extends TestCase {

	private final String url = "xyz";
	private FakeJsonArrayEventDatabaseLoader fakeJsonArrayLoader = new FakeJsonArrayEventDatabaseLoader();
	NetworkConnectivity upNetworkConnectivity = new AlwaysUpNetworkConnectivity();
	NetworkConnectivity downNetworkConnectivity = new AlwaysDownNetworkConnectivity();
	
	public void setUp() throws Exception {
		FakeEventDatabaseLoader.clearInstance();
		fakeJsonArrayLoader = new FakeJsonArrayEventDatabaseLoader();
	}

	public void tearDown() throws Exception {
	}

	public void testCreateWhenNotInitialized() {
		// Method under test
		JsonUrlEventDatabaseLoader.create(null, url, null, null);
		
		// Postconditions
		EventDatabaseLoader loader = EventDatabaseLoader.getInstance();
		assertNotNull(loader);
		assertTrue(loader instanceof JsonUrlEventDatabaseLoader);
		JsonUrlEventDatabaseLoader urlLoader = (JsonUrlEventDatabaseLoader)loader;
		assertEquals(url, urlLoader.getUrl());
	}

	public void testCreateWhenAlreadyInitialized() {
		// Preconditions
		JsonUrlEventDatabaseLoader.create(null, null, null, null);
		
		// Method under test and postconditions
		try {
			JsonUrlEventDatabaseLoader.create(null, null, null, null);
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
		JsonUrlEventDatabaseLoader.create(upNetworkConnectivity, url, 
				fakeRetriever, fakeJsonArrayLoader);
		EventDatabaseLoader loader = EventDatabaseLoader.getInstance();
		
		// Method under test
		loader.load();
		
		// Postconditions
		assertEquals(1, fakeJsonArrayLoader.getLoadCount());
		JSONArray actualJsonArray = fakeJsonArrayLoader.getLastLoadArgument();
		assertEquals(expectedJsonArray, actualJsonArray);
	}
	
	public void testLoadWhenNetworkIsDown() {
		// Preconditions
		JsonUrlEventDatabaseLoader.create(downNetworkConnectivity, url, 
				null, fakeJsonArrayLoader);
		EventDatabaseLoader loader = EventDatabaseLoader.getInstance();
		
		// Method under test
		loader.load();
		
		// Postconditions
		assertEquals(0, fakeJsonArrayLoader.getLoadCount());
	}

	// TODO:  Finish JsonUrlEventDatabase tests
}
