package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoaderIF;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;

public class EventDatabaseLoaderTests {

	@Before
	public void setUp() throws Exception {
		StubEventDatabaseLoader.clearInstance();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetInstanceWhenNotInitialized() {
		// Method under test and postconditions
		assertNull(EventDatabaseLoader.getInstance());
	}

	@Test
	public void testGetInstanceWhenInitialized() {
		// Preconditions
		StubEventDatabaseLoader.create();

		// Method under test
		EventDatabaseLoaderIF loader = EventDatabaseLoader.getInstance();
		
		// Postconditions
		assertNotNull(loader);
		assertTrue(loader instanceof StubEventDatabaseLoader);
	}
	
	@Test
	public void testClearInstance() {
		// Preconditions
		StubEventDatabaseLoader.create();
		
		// Method under test
		EventDatabaseLoader.clearInstance();
		
		// Postconditions
		assertNull(EventDatabaseLoader.getInstance());
	}
}
