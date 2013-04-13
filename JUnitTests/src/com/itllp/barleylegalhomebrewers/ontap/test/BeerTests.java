package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.Beer;

public class BeerTests {

	private static final int ID1 = 10;
	private static final int ID2 = 20;
	private Beer beer1;
	private Beer beer2;
	private static final String BEER1_NAME = "Beer One";
	private static final String BEER1_BREWER_FIRST_NAME = "Dave";
	private static final String BEER1_BREWER_LAST_NAME = "Greenbaum";
	private static final String BEER1_STYLE_CODE = "14B";
	private static final String BEER1_STYLE_NAME = "American IPA";

	@Before
	public void setUp() throws Exception {
		beer1 = new Beer(ID1);
		beer2 = new Beer(ID1);
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
		assertEquals(ID1, beer1.getId());
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
}
