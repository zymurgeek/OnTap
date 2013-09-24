package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseLoader;

public class BeerDatabaseLoaderTests {

	@Before
	public void setUp() throws Exception {
		StubBeerDatabaseLoader.clearInstance();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetInstanceWhenNotInitialized() {
		// Method under test and postconditions
		assertNull(BeerDatabaseLoader.getInstance());
	}

	@Test
	public void testGetInstanceWhenInitialized() {
		// Preconditions
		StubBeerDatabaseLoader.create();

		// Method under test
		BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		
		// Postconditions
		assertNotNull(loader);
		assertTrue(loader instanceof StubBeerDatabaseLoader);
	}
	

}
