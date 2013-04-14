package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.Beer;

public class BeerEqualityTests {

	private Beer beer1;
	private Beer beer2;
	private static final int ID1 = 10;
	private static final int ID2 = 20;
	private static final String BEER_NAME = "Beer One";
	private static final String BREWER_FIRST_NAME = "Dave";
	private static final String BREWER_LAST_NAME = "Greenbaum";
	private static final String STYLE_CODE = "13B";
	private static final String STYLE_NAME = "Sweet Stout";
	private static final String STYLE_OVERRIDE = "Milk Stout";
	private static final String DESCRIPTION = "This is a really nice beer "
			+ "that doesn't taste like it's had a dead rat in it.";
	private static final String PACKAGING = "bottle";
	private static final String ORIGINAL_GRAVITY = "1.054";
	private static final String FINAL_GRAVITY = "1.009";
	private static final String ABV = "5.3%";
	private static final String IBU = "28";
	private static final String SRM = "9.7";
	private static final String EMAIL = "a.gal@beer.com";
	private static final int TAP_NUMBER = -1;

	@Before
	public void setUp() throws Exception {
		beer1 = new Beer(ID1);
		beer2 = new Beer(ID1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualIds() {
		// method under test and postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualIds() {
		// preconditions
		beer2 = new Beer(ID2);
		
		// method under test and postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualBeerNames() {
		// preconditions
		beer1.setBeerName(BEER_NAME);
		beer2.setBeerName(BEER_NAME);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualBeerNames() {
		// preconditions
		beer1.setBeerName(BEER_NAME);
		beer2.setBeerName("not" + BEER_NAME);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualBrewerFirstNames() {
		// preconditions
		beer1.setBrewerFirstName(BREWER_FIRST_NAME);
		beer2.setBrewerFirstName(BREWER_FIRST_NAME);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualBrewerFirstNames() {
		// preconditions
		beer1.setBrewerFirstName(BREWER_FIRST_NAME);
		beer2.setBrewerFirstName("not" + BREWER_FIRST_NAME);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualBrewerLastNames() {
		// preconditions
		beer1.setBrewerLastName(BREWER_LAST_NAME);
		beer2.setBrewerLastName(BREWER_LAST_NAME);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualBrewerLastNames() {
		// preconditions
		beer1.setBrewerLastName(BREWER_LAST_NAME);
		beer2.setBrewerLastName("not" + BREWER_LAST_NAME);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualStyleCodes() {
		// preconditions
		beer1.setStyleCode(STYLE_CODE);
		beer2.setStyleCode(STYLE_CODE);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualStyleCodes() {
		// preconditions
		beer1.setStyleCode(STYLE_CODE);
		beer2.setStyleCode("not " + STYLE_CODE);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualStyleNames() {
		// preconditions
		beer1.setStyleName(STYLE_NAME);
		beer2.setStyleName(STYLE_NAME);
		
		// postconditions
		assertEquals(beer1, beer2);
	}

	@Test
	public void testUnEqualStyleNames() {
		// preconditions
		beer1.setStyleName(STYLE_NAME);
		beer2.setStyleName("not " + STYLE_NAME);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualStyleOverrides() {
		// preconditions
		beer1.setStyleOverride(STYLE_OVERRIDE);
		beer2.setStyleOverride(STYLE_OVERRIDE);
		
		// postconditions
		assertEquals(beer1, beer2);
	}

	@Test
	public void testUnEqualStyleOverrides() {
		// preconditions
		beer1.setStyleOverride(STYLE_OVERRIDE);
		beer2.setStyleOverride("not " + STYLE_OVERRIDE);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}
	
	@Test
	public void testEqualStyleDescriptions() {
		// preconditions
		beer1.setDescription(DESCRIPTION);
		beer2.setDescription(DESCRIPTION);
		
		// postconditions
		assertEquals(beer1, beer2);
	}

	@Test
	public void testUnEqualDescriptions() {
		// preconditions
		beer1.setDescription(DESCRIPTION);
		beer2.setDescription("not " + DESCRIPTION);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}
	
	@Test
	public void testEqualPackaging() {
		// preconditions
		beer1.setPackaging(PACKAGING);
		beer2.setPackaging(PACKAGING);
		
		// postconditions
		assertEquals(beer1, beer2);
	}

	@Test
	public void testUnEqualPackaging() {
		// preconditions
		beer1.setPackaging(PACKAGING);
		beer2.setPackaging("not " + PACKAGING);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualOriginalGravity() {
		// preconditions
		beer1.setOriginalGravity(ORIGINAL_GRAVITY);
		beer2.setOriginalGravity(ORIGINAL_GRAVITY);
		
		// postconditions
		assertEquals(beer1, beer2);
	}

	@Test
	public void testUnEqualOriginalGravity() {
		// preconditions
		beer1.setOriginalGravity(ORIGINAL_GRAVITY);
		beer2.setOriginalGravity("not " + ORIGINAL_GRAVITY);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}
	
	@Test
	public void testEqualFinalGravity() {
		// preconditions
		beer1.setFinalGravity(FINAL_GRAVITY);
		beer2.setFinalGravity(FINAL_GRAVITY);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualFinalGravity() {
		// preconditions
		beer1.setFinalGravity(FINAL_GRAVITY);
		beer2.setFinalGravity("not " + FINAL_GRAVITY);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualAlcoholByVolume() {
		// preconditions
		beer1.setAlcoholByVolume(ABV);
		beer2.setAlcoholByVolume(ABV);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualAlcoholByVolume() {
		// preconditions
		beer1.setAlcoholByVolume(ABV);
		beer2.setAlcoholByVolume("not " + ABV);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualInternationalBitternessUnits() {
		// preconditions
		beer1.setInternationalBitternessUnits(IBU);
		beer2.setInternationalBitternessUnits(IBU);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualInternationalBitternessUnits() {
		// preconditions
		beer1.setInternationalBitternessUnits(IBU);
		beer2.setInternationalBitternessUnits("not " + IBU);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualStandardReferenceMethod() {
		// preconditions
		beer1.setStandardReferenceMethod(SRM);
		beer2.setStandardReferenceMethod(SRM);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualStandardReferenceMethod() {
		// preconditions
		beer1.setStandardReferenceMethod(SRM);
		beer2.setStandardReferenceMethod("not " + SRM);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualBrewerEmailAddress() {
		// preconditions
		beer1.setBrewerEmailAddress(EMAIL);
		beer2.setBrewerEmailAddress(EMAIL);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualBrewerEmailAddress() {
		// preconditions
		beer1.setBrewerEmailAddress(EMAIL);
		beer2.setBrewerEmailAddress("not " + EMAIL);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualShowBrewerEmailAddress() {
		// preconditions
		beer1.setShowBrewerEmailAddress(true);
		beer2.setShowBrewerEmailAddress(true);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualShowBrewerEmailAddress() {
		// preconditions
		beer1.setShowBrewerEmailAddress(true);
		beer2.setShowBrewerEmailAddress(false);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualKicked() {
		// preconditions
		beer1.setKicked(true);
		beer2.setKicked(true);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualKicked() {
		// preconditions
		beer1.setKicked(true);
		beer2.setKicked(false);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testEqualTapNumber() {
		// preconditions
		beer1.setOnTapNumber(TAP_NUMBER);
		beer2.setOnTapNumber(TAP_NUMBER);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualTapNumber() {
		// preconditions
		beer1.setOnTapNumber(TAP_NUMBER);
		beer2.setOnTapNumber(TAP_NUMBER - 1);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

}
