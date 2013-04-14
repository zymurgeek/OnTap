package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.Beer;

public class BeerTests {

	private Beer beer1;
	private Beer beer2;
	private static final int BEER1_ID = 10;
	private static final int BEER2_ID = 20;
	private static final String BEER1_NAME = "Beer One";
	private static final String BEER1_BREWER_FIRST_NAME = "Dave";
	private static final String BEER1_BREWER_LAST_NAME = "Greenbaum";
	private static final String BEER1_STYLE_CODE = "13B";
	private static final String BEER1_STYLE_NAME = "Sweet Stout";
	private static final String BEER1_STYLE_OVERRIDE = "Milk Stout";
	private static final String BEER1_DESCRIPTION = "This is a really nice beer "
			+ "that doesn't taste like it's had a dead rat in it.";

	@Before
	public void setUp() throws Exception {
		beer1 = new Beer(BEER1_ID);
		beer2 = new Beer(BEER1_ID);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialization() {
		// postconditions
		assertNotNull(beer1);
	}
	
	@Test
	public void testId() {
		// method under test and postconditions
		assertEquals(BEER1_ID, beer1.getId());
	}

	@Test
	public void testEqualIds() {
		// method under test and postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualIds() {
		// preconditions
		beer2 = new Beer(BEER2_ID);
		
		// method under test and postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testToStringId() {
		// postconditions
		assertTrue(beer1.toString().contains("id: " + BEER1_ID));
	}
	
	@Test
	public void testBeerName() {
		// method under test
		beer1.setBeerName(BEER1_NAME);
		
		// postconditions
		assertEquals(BEER1_NAME, beer1.getBeerName());
	}
	
	@Test
	public void testEqualBeerNames() {
		// preconditions
		beer1.setBeerName(BEER1_NAME);
		beer2.setBeerName(BEER1_NAME);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualBeerNames() {
		// preconditions
		beer1.setBeerName(BEER1_NAME);
		beer2.setBeerName("Beer Two");
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testToStringBeerName() {
		// preconditions
		beer1.setBeerName(BEER1_NAME);
		
		// postconditions
		assertTrue(beer1.toString().contains("beerName: " + BEER1_NAME));
	}
	
	@Test
	public void testBrewerFirstName() {
		// method under test
		beer1.setBrewerFirstName(BEER1_BREWER_FIRST_NAME);
		
		// postconditions
		assertEquals(BEER1_BREWER_FIRST_NAME, beer1.getBrewerFirstName());
	}
	
	@Test
	public void testEqualBrewerFirstNames() {
		// preconditions
		beer1.setBrewerFirstName(BEER1_BREWER_FIRST_NAME);
		beer2.setBrewerFirstName(BEER1_BREWER_FIRST_NAME);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualBrewerFirstNames() {
		// preconditions
		beer1.setBrewerFirstName(BEER1_BREWER_FIRST_NAME);
		beer2.setBrewerFirstName("Joe");
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testToStringBrewerFirstName() {
		// preconditions
		beer1.setBrewerFirstName(BEER1_BREWER_FIRST_NAME);
		
		// postconditions
		assertTrue(beer1.toString().contains("brewerFirstName: " + BEER1_BREWER_FIRST_NAME));
	}
	
	@Test
	public void testBrewerLastName() {
		// method under test
		beer1.setBrewerLastName(BEER1_BREWER_LAST_NAME);
		
		// postconditions
		assertEquals(BEER1_BREWER_LAST_NAME, beer1.getBrewerLastName());
	}
	
	@Test
	public void testEqualBrewerLastNames() {
		// preconditions
		beer1.setBrewerLastName(BEER1_BREWER_LAST_NAME);
		beer2.setBrewerLastName(BEER1_BREWER_LAST_NAME);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualBrewerLastNames() {
		// preconditions
		beer1.setBrewerLastName(BEER1_BREWER_LAST_NAME);
		beer2.setBrewerLastName("Schmoe");
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testToStringBrewerLastName() {
		// preconditions
		beer1.setBrewerLastName(BEER1_BREWER_LAST_NAME);
		
		// postconditions
		assertTrue(beer1.toString().contains("brewerLastName: " + BEER1_BREWER_LAST_NAME));
	}
	
	@Test
	public void testBrewerName() {
		// preconditions
		beer1.setBrewerFirstName(BEER1_BREWER_FIRST_NAME);
		beer1.setBrewerLastName(BEER1_BREWER_LAST_NAME);
		String expectedBrewerName = BEER1_BREWER_FIRST_NAME + " "
				+ BEER1_BREWER_LAST_NAME;
		
		// method under test and postconditions
		assertEquals(expectedBrewerName, beer1.getBrewerName());
	}
	
	@Test
	public void testBeerStyleCode() {
		// method under test
		beer1.setStyleCode(BEER1_STYLE_CODE);
		
		// postconditions
		assertEquals(BEER1_STYLE_CODE, beer1.getStyleCode());
	}

	@Test
	public void testEqualStyleCodes() {
		// preconditions
		beer1.setStyleCode(BEER1_STYLE_CODE);
		beer2.setStyleCode(BEER1_STYLE_CODE);
		
		// postconditions
		assertEquals(beer1, beer2);
	}
	
	@Test
	public void testUnEqualStyleCodes() {
		// preconditions
		beer1.setStyleCode(BEER1_STYLE_CODE);
		beer2.setStyleCode("not " + BEER1_STYLE_CODE);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testToStringStyleCode() {
		// preconditions
		beer1.setStyleCode(BEER1_STYLE_CODE);
		
		// postconditions
		assertTrue(beer1.toString().contains("styleCode: " + BEER1_STYLE_CODE));
	}
	
	@Test
	public void testBeerStyleName() {
		// method under test
		beer1.setStyleName(BEER1_STYLE_NAME);
		
		// postconditions
		assertEquals(BEER1_STYLE_NAME, beer1.getStyleName());
	}

	@Test
	public void testEqualStyleNames() {
		// preconditions
		beer1.setStyleName(BEER1_STYLE_NAME);
		beer2.setStyleName(BEER1_STYLE_NAME);
		
		// postconditions
		assertEquals(beer1, beer2);
	}

	@Test
	public void testUnEqualStyleNames() {
		// preconditions
		beer1.setStyleName(BEER1_STYLE_NAME);
		beer2.setStyleName("not " + BEER1_STYLE_NAME);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}

	@Test
	public void testToStringStyleName() {
		// preconditions
		beer1.setStyleName(BEER1_STYLE_NAME);
		
		// postconditions
		assertTrue(beer1.toString().contains("styleName: " + BEER1_STYLE_NAME));
	}
	
	@Test
	public void testBeerStyleOverride() {
		// method under test
		beer1.setStyleOverride(BEER1_STYLE_OVERRIDE);
		
		// postconditions
		assertEquals(BEER1_STYLE_OVERRIDE, beer1.getStyleOverride());
	}

	@Test
	public void testEqualStyleOverrides() {
		// preconditions
		beer1.setStyleOverride(BEER1_STYLE_OVERRIDE);
		beer2.setStyleOverride(BEER1_STYLE_OVERRIDE);
		
		// postconditions
		assertEquals(beer1, beer2);
	}

	@Test
	public void testUnEqualStyleOverrides() {
		// preconditions
		beer1.setStyleOverride(BEER1_STYLE_OVERRIDE);
		beer2.setStyleOverride("not " + BEER1_STYLE_OVERRIDE);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}
	
	@Test
	public void testToStringStyleOverride() {
		// preconditions
		beer1.setStyleOverride(BEER1_STYLE_OVERRIDE);
		
		// postconditions
		assertTrue(beer1.toString().contains("styleOverride: " + BEER1_STYLE_OVERRIDE));
	}
	
	@Test
	public void testDescription() {
		// method under test
		beer1.setDescription(BEER1_DESCRIPTION);
		
		// postconditions
		assertEquals(BEER1_DESCRIPTION, beer1.getDescription());
	}

	@Test
	public void testEqualStyleDescriptions() {
		// preconditions
		beer1.setDescription(BEER1_DESCRIPTION);
		beer2.setDescription(BEER1_DESCRIPTION);
		
		// postconditions
		assertEquals(beer1, beer2);
	}

	@Test
	public void testUnEqualDescriptions() {
		// preconditions
		beer1.setDescription(BEER1_DESCRIPTION);
		beer2.setDescription("not " + BEER1_DESCRIPTION);
		
		// postconditions
		assertFalse(beer1.equals(beer2));
	}
	
	@Test
	public void testToStringDescription() {
		// preconditions
		beer1.setDescription(BEER1_DESCRIPTION);
		
		// postconditions
		assertTrue(beer1.toString().contains("description: " + BEER1_DESCRIPTION));
		
	}
}
