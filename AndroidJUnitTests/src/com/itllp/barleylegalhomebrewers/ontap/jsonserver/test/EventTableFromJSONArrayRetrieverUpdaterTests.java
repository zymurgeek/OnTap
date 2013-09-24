package com.itllp.barleylegalhomebrewers.ontap.jsonserver.test;

import org.json.JSONArray;

import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.json.test.StubJSONArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.jsonserver.EventTableFromJSONArrayRetrieverUpdater;

public class EventTableFromJSONArrayRetrieverUpdaterTests extends TestCase {

	
	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}
	
	public void testUpdate() {
		// Set up preconditions
		JSONArray expectedJSONArray = new JSONArray();
		expectedJSONArray.put(true);
		StubJSONArrayRetriever stubRetriever = new StubJSONArrayRetriever();
		stubRetriever.stub_setReturnArray(expectedJSONArray);
		StubEventTableFromJSONArrayUpdater stubUpdater 
		= new StubEventTableFromJSONArrayUpdater();
		EventTableFromJSONArrayRetrieverUpdater cut 
		= new EventTableFromJSONArrayRetrieverUpdater(stubRetriever, 
				stubUpdater);
		// Call method under test
		cut.update();
		
		// Verify postconditions
		assertEquals(1, stubUpdater.stub_getLoadCount());
		JSONArray actualJSONArray = stubUpdater.stub_getLastLoadedJSONArray();
		assertEquals(expectedJSONArray, actualJSONArray);
	}
}
