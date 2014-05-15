package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.content.ContentValues;

@RunWith(RobolectricTestRunner.class)
public class ContentValuesComparatorTests {

	private ContentValuesComparator cut;
	private ContentValues emptyA;
	private ContentValues emptyB;
	private ContentValues oneItemKeyAValue1;
	private ContentValues oneItemKeyAValue2;
	private ContentValues oneItemKeyBValue1;
	private String noSuchKey;
	ContentValues beerA;
	ContentValues beerB;
	private String intKey;
	private ContentValues hasIntKey7;
	private ContentValues hasIntKey8;
	private ContentValues alsoHasIntKey7;
	private ContentValues hasStringX;
	private ContentValues alsoHasStringX;
	private ContentValues hasStringY;
	private String stringKey;
	private String floatKey;
	private ContentValues hasFloat2_0;
	private ContentValues hasFloat2;
	private ContentValues hasFloat4_5;
	
	@Before
	public void setUp() throws Exception {
		cut = new ContentValuesComparator();
		emptyA = new ContentValues();
		emptyB = new ContentValues();
		oneItemKeyAValue1 = new ContentValues();
		oneItemKeyAValue1.put("Key A", "Value 1");
		oneItemKeyAValue2 = new ContentValues();
		oneItemKeyAValue2.put("Key A", "Value 2");
		oneItemKeyBValue1 = new ContentValues();
		oneItemKeyBValue1.put("Key B", "Value 1");
		beerA = new ContentValues();
		beerB = new ContentValues();
		noSuchKey = "NoSuchKey";
		intKey = "int_key";
		hasIntKey7 = new ContentValues();
		hasIntKey7.put(intKey, Integer.valueOf(7));
		alsoHasIntKey7 = new ContentValues();
		alsoHasIntKey7.put(intKey, Integer.valueOf(7));
		hasIntKey8 = new ContentValues();
		hasIntKey8.put(intKey, Integer.valueOf(8));
		stringKey = "string_key";
		hasStringX = new ContentValues();
		hasStringX.put(stringKey, "X");
		alsoHasStringX = new ContentValues();
		alsoHasStringX.put(stringKey, "X");
		hasStringY = new ContentValues();
		hasStringY.put(stringKey, "Y");
		floatKey = "float_key";
		hasFloat2_0 = new ContentValues();
		hasFloat2_0.put(floatKey, Float.valueOf((float) 2.0));
		hasFloat2 = new ContentValues();
		hasFloat2.put(floatKey, Float.valueOf((float) 2));
		hasFloat4_5 = new ContentValues();
		hasFloat4_5.put(floatKey, Float.valueOf((float) 4.5));
	}


	@Test
	public void testAreNullsEqual() {
		// Call method under test
		boolean actual = cut.areEqual(null, null);
		
		// Verify postconditions
		assertEquals("Nulls should be equal", true, actual);
	}

	@Test
	public void testSameObjectIsEqual() {
		// Call method under test
		boolean actual = cut.areEqual(emptyA, emptyA);
		
		// Verify postconditions
		assertEquals("Same object should be equal", true, actual);
	}

	@Test
	public void testDifferentEmptyObjectsAreEqual() {
		// Call method under test
		boolean actual = cut.areEqual(emptyA, emptyB);
		
		// Verify postconditions
		assertEquals("Empty objects should be equal", true, actual);
	}

	@Test
	public void testDifferentSizedObjectsAreNotEqual() {
		// Call method under test
		boolean actual = cut.areEqual(emptyA, oneItemKeyAValue1);
		
		// Verify postconditions
		assertEquals("Different sized objects should not be equal", false, 
				actual);
	}

	@Test
	public void testDifferentKeyedObjectsAreNotEqual() {
		// Call method under test
		boolean actual = cut.areEqual(oneItemKeyAValue1, oneItemKeyBValue1);
		
		// Verify postconditions
		assertEquals("Objects with different keys should not be equal", false, 
				actual);
	}

	@Test
	public void testSameKeysDifferentValuesAreNotEqual() {
		// Call method under test
		boolean actual = cut.areEqual(oneItemKeyAValue1, oneItemKeyAValue2);
		
		// Verify postconditions
		assertEquals("Objects with different values should not be equal", 
				false, actual);
	}

	
	@Test
	public void testAreNullBeersEqual() {
		// Call method under test
		boolean actual = cut.areBeersEqual(null, null);
		
		// Verify postconditions
		assertEquals("Null beers should be equal", true, actual);
	}


	@Test
	public void testSameBeerIsEqual() {
		// Call method under test
		boolean actual = cut.areBeersEqual(emptyA, emptyA);
		
		// Verify postconditions
		assertEquals("Same beer should be equal", true, actual);
	}

	@Test
	public void testDifferentEmptyBeersAreEqual() {
		// Call method under test
		boolean actual = cut.areBeersEqual(emptyA, emptyB);
		
		// Verify postconditions
		assertEquals("Empty beers should be equal", true, actual);
	}

	@Test
	public void testDifferentSizedBeersAreNotEqual() {
		// Call method under test
		boolean actual = cut.areBeersEqual(emptyA, oneItemKeyAValue1);
		
		// Verify postconditions
		assertEquals("Different sized beers should not be equal", false, 
				actual);
	}


	@Test
	public void testDifferentIdBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ID_COLUMN, 1);
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ID_COLUMN, 2);
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different IDs should not be equal", false, actual);
	}

	
	@Test
	public void testDifferentNameBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.NAME_COLUMN, "X");
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.NAME_COLUMN, "Y");
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different names should not be equal", false, actual);
	}


	@Test
	public void testDifferentEventIdBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.EVENT_ID_COLUMN, 1);
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.EVENT_ID_COLUMN, 2);
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different event IDs should not be equal", false, actual);
	}


	@Test
	public void testDifferentBrewerNameBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.BREWER_NAME_COLUMN, "A");
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.BREWER_NAME_COLUMN, "B");
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different brewer names should not be equal", false, actual);
	}


	@Test
	public void testDifferentStyleCodeBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_CODE_COLUMN, "X");
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_CODE_COLUMN, "Y");
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different style codes should not be equal", false, actual);
	}


	@Test
	public void testDifferentStyleNameBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_NAME_COLUMN, "X");
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_NAME_COLUMN, "Y");
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different style names should not be equal", false, actual);
	}


	@Test
	public void testDifferentStyleOverrideBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_OVERRIDE_COLUMN, "X");
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_OVERRIDE_COLUMN, "Y");
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different style overrides should not be equal", false, actual);
	}


	@Test
	public void testDifferentIsKickedOverrideBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.IS_KICKED_COLUMN, 0);
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.IS_KICKED_COLUMN, 1);
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different isKicked should not be equal", false, actual);
	}


	@Test
	public void testDifferentTapNumberBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.TAP_NUMBER_COLUMN, 2);
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.TAP_NUMBER_COLUMN, 3);
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different tap numbers should not be equal", false, actual);
	}


	@Test
	public void testDifferentPackagingBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.PACKAGING_COLUMN, "keg");
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.PACKAGING_COLUMN, "bottle");
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different packaging should not be equal", false, actual);
	}


	@Test
	public void testDifferentDescriptionBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.DESCRIPTION_COLUMN, "A");
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.DESCRIPTION_COLUMN, "B");
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different descriptions should not be equal", false, actual);
	}


	@Test
	public void testDifferentOriginalGravityBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ORIGINAL_GRAVITY_COLUMN, (float)1);
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ORIGINAL_GRAVITY_COLUMN, (float)2);
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different original gravities should not be equal", false, actual);
	}


	@Test
	public void testDifferentFinalGravityBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.FINAL_GRAVITY_COLUMN, (float)1);
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.FINAL_GRAVITY_COLUMN, (float)2);
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different final gravities should not be equal", false, actual);
	}


	@Test
	public void testDifferentAlcoholByVolumeBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ALCOHOL_BY_VOLUME_COLUMN, (float)1);
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ALCOHOL_BY_VOLUME_COLUMN, (float)2);
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different alcohols by volume should not be equal", false, actual);
	}


	@Test
	public void testDifferentInternationalBitternessUnitBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.INTERNATIONAL_BITTERNESS_UNITS_COLUMN, (float)1);
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.INTERNATIONAL_BITTERNESS_UNITS_COLUMN, (float)2);
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different international bitterness units should not be equal", false, actual);
	}


	@Test
	public void testDifferentStandardReferenceMethodBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STANDARD_REFERENCE_METHOD_COLUMN, (float)1);
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STANDARD_REFERENCE_METHOD_COLUMN, (float)2);
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different standard reference methods should not be equal", false, actual);
	}


	@Test
	public void testDifferentIsEmailShownBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.IS_EMAIL_SHOWN, 0);
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.IS_EMAIL_SHOWN, 1);
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different isEmailShown should not be equal", false, actual);
	}


	@Test
	public void testDifferentEmailAddressBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.EMAIL_ADDRESS, "A");
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.EMAIL_ADDRESS, "B");
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different email addresses should not be equal", false, actual);
	}


	@Test
	public void testDifferentUntappdIdBeersEqual() {
		// Set up preconditions
		beerA.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.UNTAPPD_BEER_ID, "1");
		beerB.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.UNTAPPD_BEER_ID, "2");
		
		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers with different Untappd IDs should not be equal", false, actual);
	}


	//TODO finish implementing equality tests for rest of Beer fields
	
	
	@Test
	public void testEqualBeerValues() {
		// Set up preconditions
		ContentValues beerA = new ContentValues();
		ContentValues beerB = new ContentValues();
		int id = 1;
		String beerName = "Beer A";
		int eventId = 2;
		String brewerName = "Brewer X";
		String styleCode = "Style Code";
		String styleName= "Style Name";
		String styleOverride = "Style Override";
		int isKicked = 1;
		int tapNumber = 3;
		String packaging = "Keg";
		String description = "Description";
		float og = (float) 4.0;
		float fg = (float) 5.1;
		float abv = (float) 6.2;
		float ibu = (float) 7.3;
		String srm = "8";
		int isEmailShown = 1;
		String emailAddress = "joe@example.com";
		String untappdBeerId = "123456";
		populateBeerContentValues(beerA, id, beerName, eventId, brewerName,
				styleCode, styleName, styleOverride, isKicked, tapNumber,
				packaging, description, og, fg, abv, ibu, srm, isEmailShown,
				emailAddress, untappdBeerId);
		populateBeerContentValues(beerB, id, beerName, eventId, brewerName,
				styleCode, styleName, styleOverride, isKicked, tapNumber,
				packaging, description, og, fg, abv, ibu, srm, isEmailShown,
				emailAddress, untappdBeerId);

		// Call method under test
		boolean actual = cut.areBeersEqual(beerA, beerB);
		
		// Verify postconditions
		assertEquals("Beers should be equal", true,	actual);
	}

	
	private void populateBeerContentValues(ContentValues beer, int id,
			String beerName, int eventId, String brewerName, String styleCode,
			String styleName, String styleOverride, int isKicked,
			int tapNumber, String packaging, String description, float og,
			float fg, float abv, float ibu, String srm, int isEmailShown,
			String emailAddress, String untappdBeerId) {
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ID_COLUMN, id);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.NAME_COLUMN, beerName);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.EVENT_ID_COLUMN, eventId);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.BREWER_NAME_COLUMN, brewerName);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_CODE_COLUMN, styleCode);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_NAME_COLUMN, styleName);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STYLE_OVERRIDE_COLUMN, styleOverride);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.IS_KICKED_COLUMN, isKicked);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.TAP_NUMBER_COLUMN, tapNumber);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.PACKAGING_COLUMN, packaging);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.DESCRIPTION_COLUMN, description);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ORIGINAL_GRAVITY_COLUMN, og);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.FINAL_GRAVITY_COLUMN, fg);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.ALCOHOL_BY_VOLUME_COLUMN, abv);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.INTERNATIONAL_BITTERNESS_UNITS_COLUMN, ibu);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.STANDARD_REFERENCE_METHOD_COLUMN, srm);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.IS_EMAIL_SHOWN, isEmailShown);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.EMAIL_ADDRESS, emailAddress);
		beer.put(com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata.UNTAPPD_BEER_ID, untappdBeerId);
	}
	
	
	@Test
	public void testNullIntegersEqual() {
		// Call method under test
		boolean actual = cut.areIntegersEqual(emptyA, emptyB, noSuchKey);
		
		// Verify postconditions
		assertEquals("Null integers should be equal", true, actual);
	}

	
	@Test
	public void testOneNullIntegerEqual() {
		// Call method under test
		boolean actual = cut.areIntegersEqual(hasIntKey7, emptyB, intKey);
		
		// Verify postconditions
		assertEquals("Null and non-null integers should not be equal", false, actual);
	}

	
	@Test
	public void testDifferentIntegersEqual() {
		// Call method under test
		boolean actual = cut.areIntegersEqual(hasIntKey7, hasIntKey8, intKey);
		
		// Verify postconditions
		assertEquals("Different integers should not be equal", false, actual);
	}

	
	@Test
	public void testSameIntegersEqual() {
		// Call method under test
		boolean actual = cut.areIntegersEqual(hasIntKey7, alsoHasIntKey7, intKey);
		
		// Verify postconditions
		assertEquals("Same integers should be equal", true, actual);
	}

	
	@Test
	public void testNullStringsEqual() {
		// Call method under test
		boolean actual = cut.areStringsEqual(emptyA, emptyB, noSuchKey);
		
		// Verify postconditions
		assertEquals("Null strings should be equal", true, actual);
	}

	
	@Test
	public void testOneNullStringEqual() {
		// Call method under test
		boolean actual = cut.areStringsEqual(hasStringX, emptyB, stringKey);
		
		// Verify postconditions
		assertEquals("Null and non-null strings should not be equal", false, actual);
	}

	
	@Test
	public void testDifferentStringsEqual() {
		// Call method under test
		boolean actual = cut.areStringsEqual(hasStringX, hasStringY, stringKey);
		
		// Verify postconditions
		assertEquals("Different strings should not be equal", false, actual);
	}

	
	@Test
	public void testSameStringsEqual() {
		// Call method under test
		boolean actual = cut.areStringsEqual(hasStringX, alsoHasStringX, stringKey);
		
		// Verify postconditions
		assertEquals("Same strings should be equal", true, actual);
	}

	
	@Test
	public void testNullFloatsEqual() {
		// Call method under test
		boolean actual = cut.areFloatsEqual(emptyA, emptyB, noSuchKey);

		// Verify postconditions
		assertEquals("Null floats should be equal", true, actual);
	}	

	
	@Test
	public void testOneNullFloatsEqual() {
		// Call method under test
		boolean actual = cut.areFloatsEqual(hasFloat2_0, emptyB, floatKey);
		
		// Verify postconditions
		assertEquals("Null and non-null floats should not be equal", false, actual);
	}

	
	@Test
	public void testDifferentFloatsEqual() {
		// Call method under test
		boolean actual = cut.areFloatsEqual(hasFloat2_0, hasFloat4_5, floatKey);
		
		// Verify postconditions
		assertEquals("Different floats should not be equal", false, actual);
	}

	@Test
	public void testSameFloatsEqual() {
		// Call method under test
		boolean actual = cut.areFloatsEqual(hasFloat2_0, hasFloat2, floatKey);
		
		// Verify postconditions
		assertEquals("Same floats should be equal", true, actual);
	}

	
	
}
