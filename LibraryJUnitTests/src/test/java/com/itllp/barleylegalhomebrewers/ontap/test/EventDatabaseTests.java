package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.DatabaseAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;

public class EventDatabaseTests {

	@Before
	public void setUp() throws Exception {
		EventDatabase.clearInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstanceWhenNotInstantiated() {
		assertNull(EventDatabase.getInstance());
	}
	
	@Test
	public void testGetInstanceWhenInstantiated() {
		// Preconditions
		FakeEventDatabase.create();
		
		// Postconditions
		assertNotNull(EventDatabase.getInstance());
		
		// Clean up
		FakeEventDatabase.clearInstance();
	}

	@Test
	public void testSetInstanceWhenAlreadySet() {
		// Preconditions
		FakeEventDatabase.clearInstance();
		FakeEventDatabase.create();
		
		// Method under test
		try {
			FakeEventDatabase.create();
			fail("Should throw exception");
		} catch (DatabaseAlreadyInstantiatedException e) {
			assertNotNull(e);
		}
		
		// Clean up
		FakeEventDatabase.clearInstance();

	}
	
	@Test
	public void testClearInstance() {
		// Preconditions
		FakeEventDatabase.clearInstance();
		FakeEventDatabase.create();
		
		// Method under test
		EventDatabase.clearInstance();
		
		// Postconditions
		assertNull(EventDatabase.getInstance());
	}
}
