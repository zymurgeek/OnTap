package com.itllp.barleylegalhomebrewers.ontap.json.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itllp.barleylegalhomebrewers.ontap.json.JSONArrayToContentValuesListConverterImpl;

import junit.framework.TestCase;

import android.content.ContentValues;

public class JSONArrayToContentValuesListConverterImplTests extends TestCase {

	final String jsonString1 = "{ \"ID\": 1 }";
	final String jsonString2 = "{ \"ID\": 2 }";
	JSONObject jsonObject1 = null;
	JSONObject jsonObject2 = null;
	JSONArray emptyArray;
	JSONArray jsonArrayWithOneItem;
	JSONArray jsonArrayWithTwoItems;
	
	ContentValues expectedContentValues1;
	ContentValues expectedContentValues2;
	List<ContentValues> expectedContentValuesList; 
	MockJSONObjectToContentValuesConverter mockConverter;
	private JSONArrayToContentValuesListConverterImpl cut;
	
	
	public JSONArrayToContentValuesListConverterImplTests(String name) {
		super(name);
	}

	
	protected void setUp() throws Exception {
		super.setUp();
		try {
			jsonObject1 = new org.json.JSONObject(jsonString1);
		} catch (org.json.JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		try {
			jsonObject2 = new org.json.JSONObject(jsonString2);
		} catch (org.json.JSONException x) {
			fail("Failed to parse JSON string" + x);
		}
		emptyArray = new JSONArray();
		jsonArrayWithOneItem = new JSONArray();
		jsonArrayWithOneItem.put(jsonObject1);
		jsonArrayWithTwoItems = new JSONArray();
		jsonArrayWithTwoItems.put(jsonObject1);
		jsonArrayWithTwoItems.put(jsonObject2);

		expectedContentValuesList = new ArrayList<ContentValues>();
		expectedContentValues1 = new ContentValues();
		expectedContentValues1.put("one", 1);
		expectedContentValuesList.add(expectedContentValues1);
		
		expectedContentValues2 = new ContentValues();
		expectedContentValues2.put("two", 2);
		expectedContentValuesList.add(expectedContentValues2);

		mockConverter = new MockJSONObjectToContentValuesConverter();
		mockConverter.mock_addConversion(jsonObject1, expectedContentValues1);
		mockConverter.mock_addConversion(jsonObject2, expectedContentValues2);
		
		cut = new JSONArrayToContentValuesListConverterImpl(mockConverter);
	}

	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testConversionOfNullJSONArray() {
		// Call method under test
		List<ContentValues> actual = cut.getContentValuesList(null);
		
		// Verify postconditions
		assertEquals(0, actual.size());
	}

	
	public void testConversionOfEmptyJSONArray() {
		// Call method under test
		List<ContentValues> actualList = cut.getContentValuesList(emptyArray);
		
		// Verify postconditions
		assertEquals(0, actualList.size());
	}

	
	public void testConversionOfJSONArrayWithOneItem() {
		// Call method under test
		List<ContentValues> actualList 
		= cut.getContentValuesList(jsonArrayWithOneItem);
		
		// Verify postconditions
		assertEquals(1, actualList.size());
		assertEquals(expectedContentValues1, actualList.get(0));
	}
	
	
	public void testConversionOfJSONArrayWithTwoItems() {
		// Call method under test
		List<ContentValues> actualList 
		= cut.getContentValuesList(jsonArrayWithTwoItems);
		
		// Verify postconditions
		assertEquals(2, actualList.size());
		assertEquals(expectedContentValues1, actualList.get(0));
		assertEquals(expectedContentValues2, actualList.get(1));
	}
	
	
}
