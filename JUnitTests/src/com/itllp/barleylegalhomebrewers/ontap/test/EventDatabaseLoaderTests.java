package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;

public class EventDatabaseLoaderTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstanceWhenNotInitialized() {
		// Preconditions
		FakeEventDatabaseLoader.clearInstance();
		
		// Method under test
		EventDatabaseLoader loader = EventDatabaseLoader.getInstance();
		
		// Postconditions
		assertNull(loader);
	}

	@Test
	public void testCreateWhenNotInitialized() {
		// Preconditions
		FakeEventDatabaseLoader.clearInstance();
		
		// Method under test
		FakeEventDatabaseLoader.create();
		
		// Postconditions
		assertNotNull(EventDatabaseLoader.getInstance());
		
	}
	
	@Test
	public void testCreateWhenInitialized() {
		// Preconditions
		FakeEventDatabaseLoader.clearInstance();
		FakeEventDatabaseLoader.create();

		// Method under test and postconditions
		try {
			FakeEventDatabaseLoader.create();
			fail("Should throw exception");
		} catch (DatabaseLoaderAlreadyInstantiatedException e) {
			assertNotNull(e);
		}
	}
}
