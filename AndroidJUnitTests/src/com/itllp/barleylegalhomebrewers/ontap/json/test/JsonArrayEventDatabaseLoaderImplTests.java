package com.itllp.barleylegalhomebrewers.ontap.json.test;

import org.json.JSONArray;

import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.JsonArrayEventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.JsonArrayEventDatabaseLoaderImpl;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.test.FakeStringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.test.FakeNewEventDatabase;

public class JsonArrayEventDatabaseLoaderImplTests extends TestCase {

	private FakeStringToJavaDateConverter fakeJsonDateConverter;
	private FakeNewEventDatabase fakeEventDatabase;
	
	public JsonArrayEventDatabaseLoaderImplTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		fakeJsonDateConverter = new FakeStringToJavaDateConverter();
		fakeEventDatabase = new FakeNewEventDatabase();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCreateWithNullDateConverter() {
		// Method under test and postconditions
		try {
			new JsonArrayEventDatabaseLoaderImpl(null, fakeEventDatabase);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			assertNotNull(e);
		}
	}

	public void testCreateWithNullDatabase() {
		// Method under test and postconditions
		try {
			new JsonArrayEventDatabaseLoaderImpl(fakeJsonDateConverter, null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			assertNotNull(e);
		}
	}

	public void testLoadOfNullJsonArray() {
		JsonArrayEventDatabaseLoader loader = new JsonArrayEventDatabaseLoaderImpl
				(fakeJsonDateConverter, fakeEventDatabase);
		
		// Method under test
		loader.load(null);
		
		// Postconditions
		assertTrue(fakeEventDatabase.isEmpty());
	}

	public void testLoadOfEmptyJsonArray() {
		// Preconditions
		JSONArray jsonArray = new JSONArray();
		FakeNewEventDatabase fakeEventDatabase = new FakeNewEventDatabase(); 
		JsonArrayEventDatabaseLoader loader = new JsonArrayEventDatabaseLoaderImpl
				(fakeJsonDateConverter, fakeEventDatabase);
		
		// Method under test
		loader.load(jsonArray);
		
		// Postconditions
		assertTrue(fakeEventDatabase.isEmpty());
	}

	/*
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
		= new EventDatabaseFromJsonArray(jsonArray, fakeJsonDateConverter);
		
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
		= new EventDatabaseFromJsonArray(jsonArray, fakeJsonDateConverter);
		
		// Postconditions
		List<Event> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
		Event event = eventList.get(0);
		int actualId = event.getId();
		assertEquals(expectedId, actualId);
	}
	
	
	public void testDatabaseFromJsonArrayWithOneEventName() {
		// Preconditions
		int id = 99;
		String idString = String.valueOf(id);
		String expectedEventName = "A";
		
		String jsonString = "[ { ";
		jsonString += "\"ID\": " + idString + ", ";
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
		= new EventDatabaseFromJsonArray(jsonArray, fakeJsonDateConverter);
		
		// Postconditions
		List<Event> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
		Event event = eventList.get(0);
		String actualEventName = event.getName();
		assertEquals(expectedEventName, actualEventName);
	}

	
	public void testDatabaseFromJsonArrayWithOneEventDate() {
		// Preconditions
		int id = 17;
		String idString = String.valueOf(id);
		String expectedInputEventDate = "input_date";
		Date expectedOutputEventDate = new Date(42);
		
		String jsonString = "[ { ";
		jsonString += "\"ID\": " + idString + ", ";
		jsonString += "\"EventDate\": \"" + expectedInputEventDate + "\" ";
		jsonString += "} ]";
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string:  " + x);
		}
		fakeJsonDateConverter.addConversion(expectedInputEventDate, expectedOutputEventDate);
		
		// Method under test
		EventDatabaseFromJsonArray databaseWithOneRow 
		= new EventDatabaseFromJsonArray(jsonArray, fakeJsonDateConverter);
		
		// Postconditions
		List<Event> eventList = databaseWithOneRow.getEventList();
		assertEquals(1, eventList.size());
		Event event = eventList.get(0);
		Date actualEventDate = event.getDate();
		assertEquals(expectedOutputEventDate, actualEventDate);
	}
	*/
	// TODO:  Working here
}
