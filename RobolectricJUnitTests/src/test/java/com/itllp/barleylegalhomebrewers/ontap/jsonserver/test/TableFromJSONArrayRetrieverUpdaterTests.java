package com.itllp.barleylegalhomebrewers.ontap.jsonserver.test;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.itllp.barleylegalhomebrewers.ontap.json.test.StubJSONArrayRetriever;
import com.itllp.barleylegalhomebrewers.ontap.jsonserver.TableFromJSONArrayRetrieverUpdater;


@RunWith(RobolectricTestRunner.class)
public class TableFromJSONArrayRetrieverUpdaterTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testUpdate() {
		// Set up preconditions
		JSONArray expectedJSONArray = new JSONArray();
		expectedJSONArray.put(true);
		StubJSONArrayRetriever stubRetriever = new StubJSONArrayRetriever();
		stubRetriever.stub_setReturnArray(expectedJSONArray);
		StubTableFromJSONArrayUpdater stubUpdater 
		= new StubTableFromJSONArrayUpdater();
		TableFromJSONArrayRetrieverUpdater cut 
		= new TableFromJSONArrayRetrieverUpdater(stubRetriever, 
				stubUpdater);
		// Call method under test
		cut.update();
		
		// Verify postconditions
		assertEquals(1, stubUpdater.stub_getLoadCount());
		JSONArray actualJSONArray = stubUpdater.stub_getLastLoadedJSONArray();
		assertEquals(expectedJSONArray, actualJSONArray);
	}
}
