package com.itllp.barleylegalhomebrewers.ontap.json.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.Beer;
import com.itllp.barleylegalhomebrewers.ontap.JSONArrayBeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.JSONArrayBeerDatabaseLoaderImpl;
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
public class JSONArrayBeerDatabaseLoaderImplTests extends TestCase {
	
	private FakeBeerDatabase fakeBeerDatabase;
	private JSONArrayBeerDatabaseLoader cut;
	
	private static final int EXPECTED_BEER_1_ID = 101;
	private static final String EXPECTED_BEER_1_NAME = "Beer One";
	private static final String EXPECTED_BEER_1_ID_STRING = String.valueOf(EXPECTED_BEER_1_ID);
	private Beer expectedBeer1;
	
	private static final int EXPECTED_BEER_2_ID = 102;
	private static final String EXPECTED_BEER_2_NAME = "Beer Two";
	private static final String EXPECTED_BEER_2_ID_STRING = String.valueOf(EXPECTED_BEER_2_ID);
	private Beer expectedBeer2;
	
	public JSONArrayBeerDatabaseLoaderImplTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		expectedBeer1 = new Beer(EXPECTED_BEER_1_ID);
		expectedBeer1.setBeerName(EXPECTED_BEER_1_NAME);

		expectedBeer2 = new Beer(EXPECTED_BEER_2_ID);
		expectedBeer2.setBeerName(EXPECTED_BEER_2_NAME);

		fakeBeerDatabase = new FakeBeerDatabase();
		cut = new JSONArrayBeerDatabaseLoaderImpl(fakeBeerDatabase);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCreateWithNullDatabase() {
		// Method under test and postconditions
		try {
			new JSONArrayBeerDatabaseLoaderImpl(null);
			fail("Should throw NullPointerException");
		} catch (NullPointerException e) {
			assertNotNull(e);
		}
	}

	public void testLoadOfNullJSONArray() {
		// Method under test
		cut.load(null);
		
		// Postconditions
		assertTrue(fakeBeerDatabase.isEmpty());
	}

	public void testLoadOfEmptyJSONArray() {
		// Preconditions
		JSONArray emptyJSONArray = new JSONArray();
		
		// Method under test
		cut.load(emptyJSONArray);

		// Postconditions
		assertTrue(fakeBeerDatabase.isEmpty());
	}

	public void testDatabaseFromJSONArrayWithOneBeer() {

		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, 
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

	private String getJSONArrayString(String id, String beerName) {
		return getJSONArrayString(id, beerName, null, null);
	}
	
	private String getJSONArrayString(String id, String beerName,
			String fieldId, String fieldValue) {
		String jsonString = "[ "; 
		jsonString += getJSONArrayElementString(id, beerName,
				fieldId, fieldValue); 
		jsonString += " ]";
		
		return jsonString;
	}

	private String getJSONArrayString(String id, String beerName,
			String fieldId, boolean fieldValue) {
		String jsonString = "[ "; 
		String unquotedValue = "" + fieldValue;
		jsonString += getJSONArrayElementString(id, beerName,
				fieldId, unquotedValue); 
		jsonString += " ]";
		
		return jsonString;
	}

	private String getJSONArrayString(String id, String beerName,
			String fieldId, int fieldValue) {
		String jsonString = "[ ";
		String unquotedValue = "" + fieldValue;
		jsonString += getJSONArrayElementStringWithUnquotedValue(id, beerName,
				fieldId, unquotedValue); 
		jsonString += " ]";
		
		return jsonString;
	}

	private String getJSONArrayElementString(String id,
			String beerName) {
		return getJSONArrayElementString(id, beerName, null, null);
	}

	private String getJSONArrayElementString(String id,
			String beerName, String fieldId, String fieldValue) {
		String jsonString = "{ ";
		jsonString += "\"ID\": " + id + ", ";
		jsonString += "\"BeerName\": \"" + beerName + "\"";
		if (null != fieldId) {
			jsonString += ", \"" + fieldId + "\": \"" + fieldValue + "\"";
		}
		jsonString += "}";
		return jsonString;
	}

	private String getJSONArrayElementStringWithUnquotedValue(String id,
			String beerName, String fieldId, String unquotedFieldValue) {
		String jsonString = "{ ";
		jsonString += "\"ID\": " + id + ", ";
		jsonString += "\"BeerName\": \"" + beerName + "\"";
		if (null != fieldId) {
			jsonString += ", \"" + fieldId + "\": " + unquotedFieldValue;
		}
		jsonString += "}";
		return jsonString;
	}

	public void testDatabaseFromJSONArrayWithTwoBeers() {
		// Set up preconditions
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);
		expectedBeerList.add(expectedBeer2);

		String jsonString = "[ "; 
		jsonString += getJSONArrayElementString(EXPECTED_BEER_1_ID_STRING, 
				EXPECTED_BEER_1_NAME);
		jsonString += ", "; 
		jsonString += getJSONArrayElementString(EXPECTED_BEER_2_ID_STRING, 
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
	
	public void testDatabaseFromJSONArrayWithMultipleLoads() {
		// Preconditions
		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, 
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

		jsonString = getJSONArrayString(EXPECTED_BEER_2_ID_STRING, EXPECTED_BEER_2_NAME);
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

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.BREWER_FIRST_NAME, BREWER_FIRST_NAME);
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

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.BREWER_LAST_NAME, BREWER_LAST_NAME);
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

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.BEER_STYLE_CODE, STYLE_CODE);
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

	public void testLoadOfStyleName() {
		// Preconditions
		final String STYLE_NAME = "American IPA";
		expectedBeer1.setStyleName(STYLE_NAME);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.BEER_STYLE_NAME, STYLE_NAME);
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

	public void testLoadOfStyleOverride() {
		// Preconditions
		final String STYLE_OVERRIDE = "Milk Stout";
		expectedBeer1.setStyleOverride(STYLE_OVERRIDE);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.BEER_STYLE_OVERRIDE, STYLE_OVERRIDE);
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

	public void testLoadOfDescription() {
		// Preconditions
		final String DESCRIPTION = "It's nice and not very watery";
		expectedBeer1.setDescription(DESCRIPTION);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.BEER_DESCRIPTION, DESCRIPTION);
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

	public void testLoadOfPackaging() {
		// Preconditions
		final String PACKAGING = "Bomber";
		expectedBeer1.setPackaging(PACKAGING);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.PACKAGING, PACKAGING);
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

	public void testLoadOfOriginalGravity() {
		// Preconditions
		final String ORIGINAL_GRAVITY = "1.110";
		expectedBeer1.setOriginalGravity(ORIGINAL_GRAVITY);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.ORIGINAL_GRAVITY, ORIGINAL_GRAVITY);
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

	public void testLoadOfFinalGravity() {
		// Preconditions
		final String FINAL_GRAVITY = "1.017";
		expectedBeer1.setFinalGravity(FINAL_GRAVITY);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.FINAL_GRAVITY, FINAL_GRAVITY);
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

	public void testLoadOfAlcoholByVolume() {
		// Preconditions
		final String ABV = "6.89%";
		expectedBeer1.setAlcoholByVolume(ABV);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.ALCOHOL_BY_VOLUME, ABV);
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
	
	public void testLoadOfInternationalBitternessUnits() {
		// Preconditions
		final String IBU = "22";
		expectedBeer1.setInternationalBitternessUnits(IBU);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.INTERNATIONAL_BITTERNESS_UNITS, IBU);
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
	
	public void testLoadOfStandardReferenceMethod() {
		// Preconditions
		final String SRM = "3.1";
		expectedBeer1.setStandardReferenceMethod(SRM);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.STANDARD_REFERENCE_METHOD, SRM);
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
	
	public void testLoadOfBrewerEmailAddress() {
		// Preconditions
		final String EMAIL = "Don.O.Treply@noanswer.com";
		expectedBeer1.setBrewerEmailAddress(EMAIL);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.BREWER_EMAIL_ADDRESS, EMAIL);
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
	
	public void testLoadOfShowBrewerEmailAddress() {
		// Preconditions
		final boolean SHOW = true;
		expectedBeer1.setShowBrewerEmailAddress(SHOW);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.SHOW_BREWER_EMAIL_ADDRESS, SHOW);
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
	
	public void testLoadOfIsKicked() {
		// Preconditions
		final boolean KICKED = true;
		expectedBeer1.setKicked(KICKED);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.KICKED, KICKED);
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
	
	public void testLoadOfOnTapNumber() {
		// Preconditions
		final int TAP_NUMBER = 7;
		expectedBeer1.setOnTapNumber(TAP_NUMBER);
		List<Beer> expectedBeerList = new ArrayList<Beer>();
		expectedBeerList.add(expectedBeer1);

		String jsonString = getJSONArrayString(EXPECTED_BEER_1_ID_STRING, EXPECTED_BEER_1_NAME,
				JSONArrayBeerDatabaseLoaderImpl.ON_TAP_NUMBER, TAP_NUMBER);
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
