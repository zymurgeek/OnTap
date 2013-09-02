package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderAlreadyInstantiatedException;

public class FakeBeerDatabaseLoaderTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateWhenNotInitialized() {
		// Preconditions
		StubBeerDatabaseLoader.clearInstance();
		
		// Method under test
		StubBeerDatabaseLoader.create();
		
		// Postconditions
		assertNotNull(BeerDatabaseLoader.getInstance());
	}

	@Test
	public void testCreateWhenInitialized() {
		// Preconditions
		StubEventDatabaseLoader.clearInstance();
		StubEventDatabaseLoader.create();

		// Method under test and postconditions
		try {
			StubEventDatabaseLoader.create();
			fail("Should throw exception");
		} catch (DatabaseLoaderAlreadyInstantiatedException e) {
			assertNotNull(e);
		}
	}
}
