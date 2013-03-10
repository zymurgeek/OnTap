package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderNotInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;

public class EventDatabaseLoaderTests {

	@Before
	public void setUp() throws Exception {
		FakeEventDatabaseLoader.clearInstance();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetInstanceWhenNotInitialized() {
		// Method under test and postconditions
		try {
			EventDatabaseLoader.getInstance();
			fail("Should throw exception");
		} catch (DatabaseLoaderNotInstantiatedException e) {
			assertNotNull(e);
		}
	}

	@Test
	public void testGetInstanceWhenInitialized() {
		// Preconditions
		FakeEventDatabaseLoader.create();

		// Method under test
		EventDatabaseLoader loader = EventDatabaseLoader.getInstance();
		
		// Postconditions
		assertNotNull(loader);
		assertTrue(loader instanceof FakeEventDatabaseLoader);
	}
	

}
