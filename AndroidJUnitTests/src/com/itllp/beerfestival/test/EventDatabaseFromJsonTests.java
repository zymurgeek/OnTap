package com.itllp.beerfestival.test;

import com.itllp.beerfestival.json.EventDatabaseFromJsonArray;

import junit.framework.TestCase;

public class EventDatabaseFromJsonTests extends TestCase {

	public EventDatabaseFromJsonTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreateDatabaseFromNullJson() {
		// Method under test
		EventDatabaseFromJsonArray emptyDatabase = new EventDatabaseFromJsonArray(null);
		
		// Postconditions
		assertTrue(emptyDatabase.isEmpty());
	}
}
