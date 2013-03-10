package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;

public class JsonUrlEventDatabaseLoaderTests {

	private final String url = "xyz";

	@Before
	public void setUp() throws Exception {
		FakeEventDatabaseLoader.clearInstance();
	}

	@After
	public void tearDown() throws Exception {
	}
	// TODO: Refactor tests

	@Test
	public void testCreateWhenNotInitialized() {
		// Method under test
		JsonUrlEventDatabaseLoader.create(url);
		
		// Postconditions
		EventDatabaseLoader loader = EventDatabaseLoader.getInstance();
		assertNotNull(loader);
		assertTrue(loader instanceof JsonUrlEventDatabaseLoader);
		JsonUrlEventDatabaseLoader urlLoader = (JsonUrlEventDatabaseLoader)loader;
		assertEquals(url, urlLoader.getUrl());
	}

	@Test
	public void testCreateWhenAlreadyInitialized() {
		// Preconditions
		JsonUrlEventDatabaseLoader.create(url);
		
		// Method under test and postconditions
		try {
			JsonUrlEventDatabaseLoader.create(url);
			fail("Should throw exception");
		} catch (DatabaseLoaderAlreadyInstantiatedException e) {
			assertNotNull(e);
		}
	}
/*	
	@Test
	public void testLoad() {
		// Preconditions
		JsonArrayEventDatabaseLoader 
		JsonUrlEventDatabaseLoader.create(url);
		EventDatabaseLoader loader = EventDatabaseLoader.getInstance();
		
		// Method under test
		loader.load();
		
		// Postconditions
		verify(mockUrlLoader).load();
	}
	*/
	// TODO:  Finish JsonUrlEventDatabase tests
}
