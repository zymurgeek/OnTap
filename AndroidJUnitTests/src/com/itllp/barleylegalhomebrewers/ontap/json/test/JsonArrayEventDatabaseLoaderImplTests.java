package com.itllp.barleylegalhomebrewers.ontap.json.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.JsonArrayEventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.JsonArrayEventDatabaseLoaderImpl;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.test.FakeStringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.test.FakeEventDatabase;

public class JsonArrayEventDatabaseLoaderImplTests extends TestCase {

	private FakeStringToJavaDateConverter fakeJsonDateConverter;
	private FakeEventDatabase fakeEventDatabase;
	private JsonArrayEventDatabaseLoader cut;
	
	public JsonArrayEventDatabaseLoaderImplTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		fakeJsonDateConverter = new FakeStringToJavaDateConverter();
		fakeEventDatabase = new FakeEventDatabase();
		cut = new JsonArrayEventDatabaseLoaderImpl
			(fakeJsonDateConverter, fakeEventDatabase);
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
		// Method under test
		cut.load(null);
		
		// Postconditions
		assertTrue(fakeEventDatabase.isEmpty());
	}

	public void testLoadOfEmptyJsonArray() {
		// Preconditions
		JSONArray emptyJsonArray = new JSONArray();
		
		// Method under test
		cut.load(emptyJsonArray);

		// Postconditions
		assertTrue(fakeEventDatabase.isEmpty());
	}

	public void testDatabaseFromJsonArrayWithOneEvent() {
		// Preconditions
		int expectedId = 99;
		String idString = String.valueOf(expectedId);
		String expectedEventName = "A";
		String expectedInputEventDate = "input_date";
		Date expectedOutputEventDate = new Date(98765432109L);

		Event expectedEvent = new Event(expectedId);
		expectedEvent.setName(expectedEventName);
		expectedEvent.setDate(expectedOutputEventDate);

		List<Event> expectedEventList = new ArrayList<Event>();
		expectedEventList.add(expectedEvent);

		String jsonString = "[ { ";
		jsonString += "\"ID\": " + idString + ", ";
		jsonString += "\"EventName\": \"" + expectedEventName + "\", ";
		jsonString += "\"EventDate\": \"" + expectedInputEventDate + "\" ";
		jsonString += "} ]";
		JSONArray jsonArrayWithOneEvent = null;
		try {
			jsonArrayWithOneEvent = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		fakeJsonDateConverter.FAKE_addConversion(expectedInputEventDate, expectedOutputEventDate);

		// Method under test
		cut.load(jsonArrayWithOneEvent);
		
		// Postconditions
		List<Event> actualEventList = fakeEventDatabase.getEventList();
		assertEquals(expectedEventList, actualEventList);
	}

	public void testDatabaseFromJsonArrayWithTwoEvents() {
		// Preconditions
		int expectedId1 = 1;
		String idString1 = String.valueOf(expectedId1);
		String expectedEventName1 = "A";
		String expectedInputEventDate1 = "input_date1";
		Date expectedOutputEventDate1 = new Date(98765432109L);
		Event expectedEvent1 = new Event(expectedId1);
		expectedEvent1.setName(expectedEventName1);
		expectedEvent1.setDate(expectedOutputEventDate1);
		
		int expectedId2 = 2;
		String idString2 = String.valueOf(expectedId2);
		String expectedEventName2 = "B";
		String expectedInputEventDate2 = "input_date2";
		Date expectedOutputEventDate2 = new Date(87654321098L);
		Event expectedEvent2 = new Event(expectedId2);
		expectedEvent2.setName(expectedEventName2);
		expectedEvent2.setDate(expectedOutputEventDate2);
		
		List<Event> expectedEventList = new ArrayList<Event>();
		expectedEventList.add(expectedEvent1);
		expectedEventList.add(expectedEvent2);

		String jsonString = "[ { ";
		jsonString += "\"ID\": " + idString1 + ", ";
		jsonString += "\"EventName\": \"" + expectedEventName1 + "\", ";
		jsonString += "\"EventDate\": \"" + expectedInputEventDate1 + "\" ";
		jsonString += "}, { ";
		jsonString += "\"ID\": " + idString2 + ", ";
		jsonString += "\"EventName\": \"" + expectedEventName2 + "\", ";
		jsonString += "\"EventDate\": \"" + expectedInputEventDate2 + "\" ";
		jsonString += "} ]";
		JSONArray jsonArrayWithTwoEvents = null;
		try {
			jsonArrayWithTwoEvents = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		
		fakeJsonDateConverter.FAKE_addConversion(expectedInputEventDate1, expectedOutputEventDate1);
		fakeJsonDateConverter.FAKE_addConversion(expectedInputEventDate2, expectedOutputEventDate2);

		// Method under test
		cut.load(jsonArrayWithTwoEvents);
		
		// Postconditions
		List<Event> actualEventList = fakeEventDatabase.getEventList();
		assertEquals(expectedEventList, actualEventList);
	}
}
