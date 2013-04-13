package com.itllp.barleylegalhomebrewers.ontap.json.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.Beer;
import com.itllp.barleylegalhomebrewers.ontap.JsonArrayBeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.JsonArrayBeerDatabaseLoaderImpl;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.test.FakeStringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.test.FakeBeerDatabase;

public class JsonArrayBeerDatabaseLoaderImplTests extends TestCase {

	private FakeStringToJavaDateConverter fakeJsonDateConverter;
	private FakeBeerDatabase fakeBeerDatabase;
	private JsonArrayBeerDatabaseLoader cut;
	
	public JsonArrayBeerDatabaseLoaderImplTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		fakeJsonDateConverter = new FakeStringToJavaDateConverter();
		fakeBeerDatabase = new FakeBeerDatabase();
		cut = new JsonArrayBeerDatabaseLoaderImpl
			(fakeJsonDateConverter, fakeBeerDatabase);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCreateWithNullDateConverter() {
		// Method under test and postconditions
		try {
			new JsonArrayBeerDatabaseLoaderImpl(null, fakeBeerDatabase);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			assertNotNull(e);
		}
	}

	public void testCreateWithNullDatabase() {
		// Method under test and postconditions
		try {
			new JsonArrayBeerDatabaseLoaderImpl(fakeJsonDateConverter, null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			assertNotNull(e);
		}
	}

	public void testLoadOfNullJsonArray() {
		// Method under test
		cut.load(null);
		
		// Postconditions
		assertTrue(fakeBeerDatabase.isEmpty());
	}

	public void testLoadOfEmptyJsonArray() {
		// Preconditions
		JSONArray emptyJsonArray = new JSONArray();
		
		// Method under test
		cut.load(emptyJsonArray);

		// Postconditions
		assertTrue(fakeBeerDatabase.isEmpty());
	}

	public void testDatabaseFromJsonArrayWithOneBeer() {
		// Preconditions
		int expectedId = 99;
		String idString = String.valueOf(expectedId);
		String expectedBeerName = "A";
		String expectedInputBeerDate = "input_date";
		Date expectedOutputBeerDate = new Date(98765432109L);

		Beer expectedBeer = new Beer(expectedId);
		expectedBeer.setName(expectedBeerName);
		//expectedBeer.setDate(expectedOutputBeerDate);

		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer);

		String jsonString = "[ { ";
		jsonString += "\"ID\": " + idString + ", ";
		jsonString += "\"BeerName\": \"" + expectedBeerName + "\", ";
		jsonString += "\"BeerDate\": \"" + expectedInputBeerDate + "\" ";
		jsonString += "} ]";
		JSONArray jsonArrayWithOneBeer = null;
		try {
			jsonArrayWithOneBeer = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		fakeJsonDateConverter.FAKE_addConversion(expectedInputBeerDate, expectedOutputBeerDate);

		// Method under test
		cut.load(jsonArrayWithOneBeer);
		
		// Postconditions
		List<Beer> actualBeerList = fakeBeerDatabase.getBeerList();
		assertEquals(expectedBeerList, actualBeerList);
	}

	public void testDatabaseFromJsonArrayWithTwoBeers() {
		// Preconditions
		int expectedId1 = 1;
		String idString1 = String.valueOf(expectedId1);
		String expectedBeerName1 = "A";
		String expectedInputBeerDate1 = "input_date1";
		Date expectedOutputBeerDate1 = new Date(98765432109L);
		Beer expectedBeer1 = new Beer(expectedId1);
		expectedBeer1.setName(expectedBeerName1);
		//expectedBeer1.setDate(expectedOutputBeerDate1);
		
		int expectedId2 = 2;
		String idString2 = String.valueOf(expectedId2);
		String expectedBeerName2 = "B";
		String expectedInputBeerDate2 = "input_date2";
		Date expectedOutputBeerDate2 = new Date(87654321098L);
		Beer expectedBeer2 = new Beer(expectedId2);
		expectedBeer2.setName(expectedBeerName2);
//		expectedBeer2.setDate(expectedOutputBeerDate2);
		
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);
		expectedBeerList.add(expectedBeer2);

		String jsonString = "[ { ";
		jsonString += "\"ID\": " + idString1 + ", ";
		jsonString += "\"BeerName\": \"" + expectedBeerName1 + "\", ";
		jsonString += "\"BeerDate\": \"" + expectedInputBeerDate1 + "\" ";
		jsonString += "}, { ";
		jsonString += "\"ID\": " + idString2 + ", ";
		jsonString += "\"BeerName\": \"" + expectedBeerName2 + "\", ";
		jsonString += "\"BeerDate\": \"" + expectedInputBeerDate2 + "\" ";
		jsonString += "} ]";
		JSONArray jsonArrayWithTwoBeers = null;
		try {
			jsonArrayWithTwoBeers = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		
		fakeJsonDateConverter.FAKE_addConversion(expectedInputBeerDate1, expectedOutputBeerDate1);
		fakeJsonDateConverter.FAKE_addConversion(expectedInputBeerDate2, expectedOutputBeerDate2);

		// Method under test
		cut.load(jsonArrayWithTwoBeers);
		
		// Postconditions
		List<Beer> actualBeerList = fakeBeerDatabase.getBeerList();
		assertEquals(expectedBeerList, actualBeerList);
	}
	
	public void testDatabaseFromJsonArrayWithMultipleLoads() {
		// Preconditions
		String jsonString = "[ { ";
		jsonString += "\"ID\": 1, ";
		jsonString += "\"BeerName\": \"Beer One\", ";
		jsonString += "\"BeerDate\": \"Date One\" ";
		jsonString += "} ]";
		JSONArray jsonArrayWithBeerOne = null;
		try {
			jsonArrayWithBeerOne = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		cut.load(jsonArrayWithBeerOne);
		
		int expectedId = 99;
		String idString = String.valueOf(expectedId);
		String expectedBeerName = "A";
		String expectedInputBeerDate = "input_date";
		Date expectedOutputBeerDate = new Date(98765432109L);

		Beer expectedBeer = new Beer(expectedId);
		expectedBeer.setName(expectedBeerName);

		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer);

		jsonString = "[ { ";
		jsonString += "\"ID\": " + idString + ", ";
		jsonString += "\"BeerName\": \"" + expectedBeerName + "\", ";
		jsonString += "\"BeerDate\": \"" + expectedInputBeerDate + "\" ";
		jsonString += "} ]";
		JSONArray jsonArrayWithBeerTwo = null;
		try {
			jsonArrayWithBeerTwo = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		fakeJsonDateConverter.FAKE_addConversion(expectedInputBeerDate, expectedOutputBeerDate);

		// Method under test
		cut.load(jsonArrayWithBeerTwo);
		
		// Postconditions
		List<Beer> actualBeerList = fakeBeerDatabase.getBeerList();
		assertEquals(expectedBeerList, actualBeerList);
	}
}
