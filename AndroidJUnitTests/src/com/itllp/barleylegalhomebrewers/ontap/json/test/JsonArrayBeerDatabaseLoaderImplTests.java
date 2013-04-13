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
	
	private static final int EXPECTED_BEER_1_ID = 101;
	private static final String EXPECTED_BEER_1_NAME = "Beer One";
	private static final String EXPECTED_BEER_1_ID_STRING = String.valueOf(EXPECTED_BEER_1_ID);
	private Beer expectedBeer1;
	
	private static final int EXPECTED_BEER_2_ID = 102;
	private static final String EXPECTED_BEER_2_NAME = "Beer Two";
	private static final String EXPECTED_BEER_2_ID_STRING = String.valueOf(EXPECTED_BEER_2_ID);
	private Beer expectedBeer2;
	
	public JsonArrayBeerDatabaseLoaderImplTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		expectedBeer1 = new Beer(EXPECTED_BEER_1_ID);
		expectedBeer1.setBeerName(EXPECTED_BEER_1_NAME);

		expectedBeer2 = new Beer(EXPECTED_BEER_2_ID);
		expectedBeer2.setBeerName(EXPECTED_BEER_2_NAME);

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

		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJsonArrayString(EXPECTED_BEER_1_ID_STRING, 
				EXPECTED_BEER_1_NAME);
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

	private String getJsonArrayString(String id, String beerName) {
		return getJsonArrayString(id, beerName, null, null);
	}
	
	private String getJsonArrayString(String id, String beerName,
			String fieldId, String fieldValue) {
		String jsonString = "[ "; 
		jsonString += getJsonArrayElementString(id, beerName,
				fieldId, fieldValue); 
		jsonString += " ]";
		
		return jsonString;
	}

	private String getJsonArrayElementString(String id,
			String beerName) {
		return getJsonArrayElementString(id, beerName, null, null);
	}

	private String getJsonArrayElementString(String id,
			String beerName, String fieldId, String fieldValue) {
		String jsonString = "{ ";
		jsonString += "\"ID\": " + id + ", ";
		jsonString += "\"BeerName\": \"" + beerName + "\", ";
		if (null != fieldId) {
			jsonString += "\"" + fieldId + "\": \"" + fieldValue + "\", ";
		}
		jsonString += "}";
		return jsonString;
	}

	public void testDatabaseFromJsonArrayWithTwoBeers() {
		// Set up preconditions
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);
		expectedBeerList.add(expectedBeer2);

		String jsonString = "[ "; 
		jsonString += getJsonArrayElementString(EXPECTED_BEER_1_ID_STRING, 
				EXPECTED_BEER_1_NAME);
		jsonString += ", "; 
		jsonString += getJsonArrayElementString(EXPECTED_BEER_2_ID_STRING, 
				EXPECTED_BEER_2_NAME);
		jsonString += " ]";
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
		String jsonString = getJsonArrayString(EXPECTED_BEER_1_ID_STRING, 
				EXPECTED_BEER_1_NAME);
		JSONArray jsonArrayWithBeerOne = null;
		try {
			jsonArrayWithBeerOne = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		cut.load(jsonArrayWithBeerOne);
		
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer2);

		jsonString = getJsonArrayString(EXPECTED_BEER_2_ID_STRING, EXPECTED_BEER_2_NAME);
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
	
	public void testLoadOfBrewerFirstName() {
		// Preconditions
		final String BREWER_FIRST_NAME = "Dave";
		expectedBeer1.setBrewerFirstName(BREWER_FIRST_NAME);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJsonArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JsonArrayBeerDatabaseLoaderImpl.BREWER_FIRST_NAME, BREWER_FIRST_NAME);
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}

		// Method under test
		cut.load(jsonArray);
		
		// Postconditions
		List<Beer> actualBeerList = fakeBeerDatabase.getBeerList();
		assertEquals(expectedBeerList, actualBeerList);
	}

	public void testLoadOfBrewerLastName() {
		// Preconditions
		final String BREWER_LAST_NAME = "Greenbaum";
		expectedBeer1.setBrewerLastName(BREWER_LAST_NAME);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJsonArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JsonArrayBeerDatabaseLoaderImpl.BREWER_LAST_NAME, BREWER_LAST_NAME);
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}

		// Method under test
		cut.load(jsonArray);
		
		// Postconditions
		List<Beer> actualBeerList = fakeBeerDatabase.getBeerList();
		assertEquals(expectedBeerList, actualBeerList);
	}

	public void testLoadOfStyleCode() {
		// Preconditions
		final String STYLE_CODE = "14B";
		expectedBeer1.setStyleCode(STYLE_CODE);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJsonArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JsonArrayBeerDatabaseLoaderImpl.BEER_STYLE_CODE, STYLE_CODE);
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(jsonString);
		} catch (JSONException x) {
			fail("Failed to parse JSON string" + x);
		}

		// Method under test
		cut.load(jsonArray);
		
		// Postconditions
		List<Beer> actualBeerList = fakeBeerDatabase.getBeerList();
		assertEquals(expectedBeerList, actualBeerList);
	}
}
