package com.itllp.barleylegalhomebrewers.ontap.json.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.Beer;
import com.itllp.barleylegalhomebrewers.ontap.JsonArrayBeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.JsonArrayBeerDatabaseLoaderImpl;
import com.itllp.barleylegalhomebrewers.ontap.test.FakeBeerDatabase;

/* Each element of the JSON beer array has these fields: 
 * { 
 * "BeerStyle": "10A", 
 * "BeerStyleName": "American Pale Ale", 
 * "BeerName": "Bees Suck", 
 * "FirstName": "Chris", 
 * "LastName": "Smith", 
 * "EventName": "April Meeting", 
 * "BeerStyleOverride": "", 
 * "OG": "1.061", 
 * "FG": "1.015", 
 * "ABV": "6.0%", 
 * "IBU": "65", 
 * "SRM": "10.8", 
 * "Description": "Bees Suck is a Pale Ale brewed while being attacked by a swarm of bees. It's dry hopped with the Zythos hop blend.", 
 * "BrewerID": 1, 
 * "Active": true, 
 * "Deleted": false, 
 * "EventID": 9, 
 * "BeerStyleID": 33, 
 * "ID": 26, 
 * "KegOrBottle": "Keg", 
 * "OnTap": 1, 
 * "Kicked": false, 
 * "EmailAddress": "chris@misdb.com", 
 * "CanEmail": true, 
 * "Starred": true 
 * }
 */
public class JsonArrayBeerDatabaseLoaderImplTests extends TestCase {
	
	private FakeBeerDatabase fakeBeerDatabase;
	private JsonArrayBeerDatabaseLoader cut;
	
	public JsonArrayBeerDatabaseLoaderImplTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		fakeBeerDatabase = new FakeBeerDatabase();
		cut = new JsonArrayBeerDatabaseLoaderImpl(fakeBeerDatabase);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCreateWithNullDatabase() {
		// Method under test and postconditions
		try {
			new JsonArrayBeerDatabaseLoaderImpl(null);
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

		Beer expectedBeer = new Beer(expectedId);
		expectedBeer.setBeerName(expectedBeerName);

		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer);

		String jsonString = getJsonString(idString, expectedBeerName);
		JSONArray jsonArrayWithOneBeer = null;
		try {
			jsonArrayWithOneBeer = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}

		// Method under test
		cut.load(jsonArrayWithOneBeer);
		
		// Postconditions
		List<Beer> actualBeerList = fakeBeerDatabase.getBeerList();
		assertEquals(expectedBeerList, actualBeerList);
	}

	private String getJsonString(String idString, String expectedBeerName) {
		String jsonString = "[ { ";
		jsonString += "\"ID\": " + idString + ", ";
		jsonString += "\"BeerName\": \"" + expectedBeerName + "\", ";
		jsonString += "} ]";
		
		return jsonString;
	}

	public void testDatabaseFromJsonArrayWithTwoBeers() {
		// Preconditions
		int expectedId1 = 1;
		String idString1 = String.valueOf(expectedId1);
		String expectedBeerName1 = "A";
		String expectedInputBeerDate1 = "input_date1";
		Beer expectedBeer1 = new Beer(expectedId1);
		expectedBeer1.setBeerName(expectedBeerName1);
		
		int expectedId2 = 2;
		String idString2 = String.valueOf(expectedId2);
		String expectedBeerName2 = "B";
		String expectedInputBeerDate2 = "input_date2";
		Beer expectedBeer2 = new Beer(expectedId2);
		expectedBeer2.setBeerName(expectedBeerName2);
		
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

		Beer expectedBeer = new Beer(expectedId);
		expectedBeer.setBeerName(expectedBeerName);

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

		// Method under test
		cut.load(jsonArrayWithBeerTwo);
		
		// Postconditions
		List<Beer> actualBeerList = fakeBeerDatabase.getBeerList();
		assertEquals(expectedBeerList, actualBeerList);
	}
}
