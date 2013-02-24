package com.itllp.barleylegalhomebrewers.ontap.json.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.test.MockStringConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.EventDatabaseFromJsonArray;
import com.itllp.barleylegalhomebrewers.ontap.json.OldEventDatabaseFromJsonArray;

import junit.framework.TestCase;

public class OldEventDatabaseFromJsonArrayTests extends TestCase {

	private MockStringConverter mockDateConverter = null;
	
	public OldEventDatabaseFromJsonArrayTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mockDateConverter = new MockStringConverter();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDatabaseFromNullJsonArray() {
		// Method under test
		EventDatabaseFromJsonArray emptyDatabase 
		= new EventDatabaseFromJsonArray(null, mockDateConverter);
		
		// Postconditions
		assertTrue(emptyDatabase.isEmpty());
	}

	public void testDatabaseWithNullDateConverter() {
		try {
			// Method under test
			new EventDatabaseFromJsonArray(null, null);

			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	public void testDatabaseFromEmptyJsonArray() {
		// Preconditions
		JSONArray jsonArray = new JSONArray();
		
		// Method under test
		EventDatabaseFromJsonArray emptyDatabase 
		= new EventDatabaseFromJsonArray(jsonArray, mockDateConverter);
		
		// Postconditions
		assertTrue(emptyDatabase.isEmpty());
	}

	
	public void testOldDatabaseFromJsonArrayWithOneEvent() {
		// Preconditions
		String jsonString = "[ { ";
		jsonString += "\"ID\": 0 ";
		jsonString += "} ]";
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string: " + x);
		}

		// Method under test
		OldEventDatabaseFromJsonArray databaseWithOneRow 
		= new OldEventDatabaseFromJsonArray(jsonArray, mockDateConverter);
		
		// Postconditions
		ArrayList<HashMap<String, String>> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
	}
	
	public void testOldDatabaseFromJsonArrayWithOneId() {
		// Preconditions
		String expectedId = "1";
		
		String jsonString = "[ { ";
		jsonString += "\"ID\": " + expectedId + " ";
		jsonString += "} ]";
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}

		// Method under test
		OldEventDatabaseFromJsonArray databaseWithOneRow 
		= new OldEventDatabaseFromJsonArray(jsonArray, mockDateConverter);
		
		// Postconditions
		ArrayList<HashMap<String, String>> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
		HashMap<String, String> event = eventList.get(0);
		String actualId = event.get("ID");
		assertEquals(expectedId, actualId);
	}
	
	public void testOldDatabaseFromJsonArrayWithOneEventName() {
		// Preconditions
		String expectedEventName = "A";
		
		String jsonString = "[ { ";
		jsonString += "\"EventName\": \"" + expectedEventName + "\" ";
		jsonString += "} ]";
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		// Method under test
		OldEventDatabaseFromJsonArray databaseWithOneRow 
		= new OldEventDatabaseFromJsonArray(jsonArray, mockDateConverter);
		
		// Postconditions
		ArrayList<HashMap<String, String>> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
		HashMap<String, String> event = eventList.get(0);
		String actualEventName = event.get("EventName");
		assertEquals(expectedEventName, actualEventName);
	}

	public void testOldDatabaseFromJsonArrayWithOneEventDate() {
		// Preconditions
		String expectedInputEventDate = "input_date";
		String expectedOutputEventDate = "output_date";
		
		String jsonString = "[ { ";
		jsonString += "\"EventDate\": \"" + expectedInputEventDate + "\" ";
		jsonString += "} ]";
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string:  " + x);
		}
		mockDateConverter.addConversion(expectedInputEventDate, expectedOutputEventDate);
		
		// Method under test
		OldEventDatabaseFromJsonArray databaseWithOneRow 
		= new OldEventDatabaseFromJsonArray(jsonArray, mockDateConverter);
		
		// Postconditions
		ArrayList<HashMap<String, String>> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
		HashMap<String, String> event = eventList.get(0);
		String actualEventDate = event.get("EventDate");
		assertEquals(expectedOutputEventDate, actualEventDate);
	}
}
