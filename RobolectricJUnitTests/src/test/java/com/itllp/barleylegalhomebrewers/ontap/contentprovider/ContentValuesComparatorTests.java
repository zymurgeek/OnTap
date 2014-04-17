package com.itllp.barleylegalhomebrewers.ontap.contentprovider;

import static org.junit.Assert.*;

import org.junit.After;
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
	}

	@After
	public void tearDown() throws Exception {
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
}
