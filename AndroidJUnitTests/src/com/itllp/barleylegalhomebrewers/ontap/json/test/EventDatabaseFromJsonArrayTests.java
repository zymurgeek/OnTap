package com.itllp.barleylegalhomebrewers.ontap.json.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.test.MockStringConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.EventDatabaseFromJsonArray;
import com.itllp.barleylegalhomebrewers.ontap.json.OldEventDatabaseFromJsonArray;

import junit.framework.TestCase;

public class EventDatabaseFromJsonArrayTests extends TestCase {

	private MockStringConverter mockDateConverter = null;
	
	public EventDatabaseFromJsonArrayTests(String name) {
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

	
	public void testDatabaseFromJsonArrayWithOneEvent() {
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
		EventDatabaseFromJsonArray databaseWithOneRow 
		= new EventDatabaseFromJsonArray(jsonArray, mockDateConverter);
		
		// Postconditions
		List<Event> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
	}

	
	public void testDatabaseFromJsonArrayWithOneId() {
		// Preconditions
		int expectedId = 11;
		String expectedIdString = String.valueOf(expectedId);
		
		String jsonString = "[ { ";
		jsonString += "\"ID\": " + expectedIdString + " ";
		jsonString += "} ]";
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}

		// Method under test
		EventDatabaseFromJsonArray databaseWithOneRow 
		= new EventDatabaseFromJsonArray(jsonArray, mockDateConverter);
		
		// Postconditions
		List<Event> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
		Event event = eventList.get(0);
		int actualId = event.getId();
		assertEquals(expectedId, actualId);
	}
	
	
	public void testDatabaseFromJsonArrayWithOneEventName() {
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
		EventDatabaseFromJsonArray databaseWithOneRow 
		= new EventDatabaseFromJsonArray(jsonArray, mockDateConverter);
		
		// Postconditions
		List<Event> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
		Event event = eventList.get(0);
		String actualEventName = event.getName();
		assertEquals(expectedEventName, actualEventName);
	}

	
	public void testDatabaseFromJsonArrayWithOneEventDate() {
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
		EventDatabaseFromJsonArray databaseWithOneRow 
		= new EventDatabaseFromJsonArray(jsonArray, mockDateConverter);
		
		// Postconditions
		List<Event> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
		Event event = eventList.get(0);
		Date actualEventDate = event.getDate();
		assertEquals(expectedOutputEventDate, actualEventDate);
	}
}
