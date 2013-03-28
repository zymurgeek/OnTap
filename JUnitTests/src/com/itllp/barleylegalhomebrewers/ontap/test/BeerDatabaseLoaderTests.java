package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderNotInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;

public class BeerDatabaseLoaderTests {

	@Before
	public void setUp() throws Exception {
		FakeBeerDatabaseLoader.clearInstance();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetInstanceWhenNotInitialized() {
		// Method under test and postconditions
		try {
			BeerDatabaseLoader.getInstance();
			fail("Should throw exception");
		} catch (DatabaseLoaderNotInstantiatedException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void testGetInstanceWhenInitialized() {
		// Preconditions
		FakeBeerDatabaseLoader.create();

		// Method under test
		BeerDatabaseLoader loader = BeerDatabaseLoader.getInstance();
		
		// Postconditions
		assertNotNull(loader);
		assertTrue(loader instanceof FakeBeerDatabaseLoader);
	}
	

}
