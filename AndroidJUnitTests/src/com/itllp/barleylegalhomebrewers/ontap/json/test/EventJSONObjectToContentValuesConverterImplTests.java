package com.itllp.barleylegalhomebrewers.ontap.json.test;

import org.json.JSONObject;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.test.StubJavaDateToStringConve;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.test.StubStringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.EventJSONObjectToContentValuesConverterImpl;

import junit.framework.TestCase;

import android.content.ContentValues;

public class EventJSONObjectToContentValuesConverterImplTests extends TestCase {

	private JSONObject validJSONObject;
	private final int expectedID = 7;
	private final String expectedEventName = "A Festival";
	private final String jsonEventDate = "JSON date";
	private final java.util.Date javaEventDate = new java.util.Date(42);
	private final String expectedEventDate = "SQLite date";
	private final int validJSONObjectKeys = 3;
	private StubStringToJavaDateConverter stubJSONDateConverter;
	private StubJavaDateToStringConve stubJavaDateConverter;
	private EventJSONObjectToContentValuesConverterImpl cut;
	
	
	public EventJSONObjectToContentValuesConverterImplTests(String name) {
		super(name);
	}

	
	protected void setUp() throws Exception {
		super.setUp();
		validJSONObject = new JSONObject();
		try {
			validJSONObject.put(
					com.itllp.barleylegalhomebrewers.ontap.jsonserver.Event.ID, 
					expectedID);
		} catch (Exception e) {
			fail("Could not populate JSON object ID");
		}
		try {
			validJSONObject.put(com.itllp.barleylegalhomebrewers.ontap.jsonserver
					.Event.EVENT_NAME, expectedEventName);
		} catch (Exception e) {
			fail("Could not populate JSON object event name");
		}
		try {
			validJSONObject.put(com.itllp.barleylegalhomebrewers.ontap.jsonserver
					.Event.EVENT_DATE, jsonEventDate);
		} catch (Exception e) {
			fail("Could not populate JSON object event name");
		}
		stubJSONDateConverter = new StubStringToJavaDateConverter(); 
		stubJSONDateConverter.stub_addConversion(jsonEventDate, javaEventDate);
		stubJavaDateConverter = new StubJavaDateToStringConve();
		stubJavaDateConverter.stub_addConversion(javaEventDate, expectedEventDate);
		cut = new EventJSONObjectToContentValuesConverterImpl(
				stubJSONDateConverter, stubJavaDateConverter);
	}

	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testConversionOfNullJSONObject() {
		// Call method under test
		ContentValues actual = cut.getContentValues(null);
		
		// Verify postconditions
		assertEquals(0, actual.size());
	}


	public void testConversionOfEmptyJSONObject() {
		// Set up preconditions
		JSONObject emptyJSONObject = new JSONObject();

		// Call method under test
		ContentValues actual = cut.getContentValues(emptyJSONObject);
		
		// Verify postconditions
		assertEquals(0, actual.size());
	}

	public void testConversionOfValidJSONObject() {
		// Call method under test
		ContentValues actual = cut.getContentValues(validJSONObject);
		
		// Verify postconditions
		assertEquals(validJSONObjectKeys, actual.size());
		Integer id = actual.getAsInteger(com.itllp.barleylegalhomebrewers.ontap.database.EventTable.ID_COLUMN);
		int actualID = -1;
		if (null != id) {
			actualID = id.shortValue();
		}
		assertEquals(expectedID, actualID);
		String actualEventName = actual.getAsString(com.itllp.barleylegalhomebrewers.ontap.database.EventTable.NAME_COLUMN);
		assertEquals(expectedEventName, actualEventName);
		String actualEventDate = actual.getAsString(com.itllp
				.barleylegalhomebrewers.ontap.database.SQLiteEventTable
				.START_LOCAL_TIME_COLUMN);
		assertEquals(expectedEventDate, actualEventDate);
	}

}
