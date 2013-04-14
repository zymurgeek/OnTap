package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.Beer;

public class BeerAttributeTests {

	private Beer beer;
	private static final int BEER_ID = 10;
	private static final String BEER_NAME = "Some Beer";
	private static final String BREWER_FIRST_NAME = "Dave";
	private static final String BREWER_LAST_NAME = "Greenbaum";
	private static final String STYLE_CODE = "13B";
	private static final String STYLE_NAME = "Sweet Stout";
	private static final String STYLE_OVERRIDE = "Milk Stout";
	private static final String DESCRIPTION = "This is a really nice beer "
			+ "that doesn't taste like it's had a dead rat in it.";
	private static final String PACKAGING = "Keg";
	private static final String ORIGINAL_GRAVITY = "1.062";
	private static final String FINAL_GRAVITY = "1.012";
	private static final String ABV = "6.25%";
	private static final String IBU = "25";
	private static final String SRM = "12.3";

	@Before
	public void setUp() throws Exception {
		beer = new Beer(BEER_ID);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialization() {
		// postconditions
		assertNotNull(beer);
	}
	
	@Test
	public void testId() {
		// method under test and postconditions
		assertEquals(BEER_ID, beer.getId());
	}

	@Test
	public void testToStringId() {
		// postconditions
		assertTrue(beer.toString().contains("id: " + BEER_ID));
	}
	
	@Test
	public void testBeerName() {
		// method under test
		beer.setBeerName(BEER_NAME);
		
		// postconditions
		assertEquals(BEER_NAME, beer.getBeerName());
	}
	
	@Test
	public void testToStringBeerName() {
		// preconditions
		beer.setBeerName(BEER_NAME);
		
		// postconditions
		assertTrue(beer.toString().contains("beerName: " + BEER_NAME));
	}
	
	@Test
	public void testBrewerFirstName() {
		// method under test
		beer.setBrewerFirstName(BREWER_FIRST_NAME);
		
		// postconditions
		assertEquals(BREWER_FIRST_NAME, beer.getBrewerFirstName());
	}
	
	@Test
	public void testToStringBrewerFirstName() {
		// preconditions
		beer.setBrewerFirstName(BREWER_FIRST_NAME);
		
		// postconditions
		assertTrue(beer.toString().contains("brewerFirstName: " + BREWER_FIRST_NAME));
	}
	
	@Test
	public void testBrewerLastName() {
		// method under test
		beer.setBrewerLastName(BREWER_LAST_NAME);
		
		// postconditions
		assertEquals(BREWER_LAST_NAME, beer.getBrewerLastName());
	}
	
	@Test
	public void testToStringBrewerLastName() {
		// preconditions
		beer.setBrewerLastName(BREWER_LAST_NAME);
		
		// postconditions
		assertTrue(beer.toString().contains("brewerLastName: " + BREWER_LAST_NAME));
	}
	
	@Test
	public void testBrewerName() {
		// preconditions
		beer.setBrewerFirstName(BREWER_FIRST_NAME);
		beer.setBrewerLastName(BREWER_LAST_NAME);
		String expectedBrewerName = BREWER_FIRST_NAME + " "
				+ BREWER_LAST_NAME;
		
		// method under test and postconditions
		assertEquals(expectedBrewerName, beer.getBrewerName());
	}
	
	@Test
	public void testBeerStyleCode() {
		// method under test
		beer.setStyleCode(STYLE_CODE);
		
		// postconditions
		assertEquals(STYLE_CODE, beer.getStyleCode());
	}

	@Test
	public void testToStringStyleCode() {
		// preconditions
		beer.setStyleCode(STYLE_CODE);
		
		// postconditions
		assertTrue(beer.toString().contains("styleCode: " + STYLE_CODE));
	}
	
	@Test
	public void testBeerStyleName() {
		// method under test
		beer.setStyleName(STYLE_NAME);
		
		// postconditions
		assertEquals(STYLE_NAME, beer.getStyleName());
	}

	@Test
	public void testToStringStyleName() {
		// preconditions
		beer.setStyleName(STYLE_NAME);
		
		// postconditions
		assertTrue(beer.toString().contains("styleName: " + STYLE_NAME));
	}
	
	@Test
	public void testBeerStyleOverride() {
		// method under test
		beer.setStyleOverride(STYLE_OVERRIDE);
		
		// postconditions
		assertEquals(STYLE_OVERRIDE, beer.getStyleOverride());
	}

	@Test
	public void testToStringStyleOverride() {
		// preconditions
		beer.setStyleOverride(STYLE_OVERRIDE);
		
		// postconditions
		assertTrue(beer.toString().contains("styleOverride: " + STYLE_OVERRIDE));
	}
	
	@Test
	public void testDescription() {
		// method under test
		beer.setDescription(DESCRIPTION);
		
		// postconditions
		assertEquals(DESCRIPTION, beer.getDescription());
	}

	@Test
	public void testToStringDescription() {
		// preconditions
		beer.setDescription(DESCRIPTION);
		
		// postconditions
		assertTrue(beer.toString().contains("description: " + DESCRIPTION));
	}
	
	@Test
	public void testPackaging() {
		// method under test
		beer.setPackaging(PACKAGING);
		
		// postconditions
		assertEquals(PACKAGING, beer.getPackaging());
	}

	@Test
	public void testToStringPackaging() {
		// preconditions
		beer.setPackaging(PACKAGING);
		
		// postconditions
		assertTrue(beer.toString().contains("packaging: " + PACKAGING));
	}
	
	@Test
	public void testOriginalGravity() {
		// method under test
		beer.setOriginalGravity(ORIGINAL_GRAVITY);
		
		// postconditions
		assertEquals(ORIGINAL_GRAVITY, beer.getOriginalGravity());
	}

	@Test
	public void testToStringOriginalGravity() {
		// preconditions
		beer.setOriginalGravity(ORIGINAL_GRAVITY);
		
		// postconditions
		assertTrue(beer.toString().contains("OG: " + ORIGINAL_GRAVITY));
	}
	
	@Test
	public void testFinalGravity() {
		// method under test
		beer.setFinalGravity(FINAL_GRAVITY);
		
		// postconditions
		assertEquals(FINAL_GRAVITY, beer.getFinalGravity());
	}

	@Test
	public void testToStringFinalGravity() {
		// preconditions
		beer.setFinalGravity(FINAL_GRAVITY);
		
		// postconditions
		assertTrue(beer.toString().contains("FG: " + FINAL_GRAVITY));
	}
	
	@Test
	public void testAlcoholByVolume() {
		// method under test
		beer.setAlcoholByVolume(ABV);
		
		// postconditions
		assertEquals(ABV, beer.getAlcoholByVolume());
	}

	@Test
	public void testToStringAlcoholByVolume() {
		// preconditions
		beer.setAlcoholByVolume(ABV);
		
		// postconditions
		assertTrue(beer.toString().contains("ABV: " + ABV));
	}

	@Test
	public void testInternationalBitternessUnits() {
		// method under test
		beer.setInternationalBitternessUnits(IBU);
		
		// postconditions
		assertEquals(IBU, beer.getInternationalBitternessUnits());
	}

	@Test
	public void testToStringInternationalBitternessUnits() {
		// preconditions
		beer.setInternationalBitternessUnits(IBU);
		
		// postconditions
		assertTrue(beer.toString().contains("IBU: " + IBU));
	}

	@Test
	public void testStandardReferenceMethod() {
		// method under test
		beer.setStandardReferenceMethod(SRM);
		
		// postconditions
		assertEquals(SRM, beer.getStandardReferenceMethod());
	}

	@Test
	public void testToStringStandardReferenceMethod() {
		// preconditions
		beer.setStandardReferenceMethod(SRM);
		
		// postconditions
		assertTrue(beer.toString().contains("SRM: " + SRM));
	}

}
