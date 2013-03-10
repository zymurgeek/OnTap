package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.DatabaseAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.NewEventDatabase;

public class NewEventDatabaseTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInstanceWhenNotInstantiated() {
		assertNull(NewEventDatabase.getInstance());
	}
	
	@Test
	public void testGetInstanceWhenInstantiated() {
		// Preconditions
		FakeNewEventDatabase.create();
		
		// Postconditions
		assertNotNull(NewEventDatabase.getInstance());
	}

	@Test
	public void testSetInstanceWhenAlreadySet() {
		// Preconditions
		FakeNewEventDatabase.clearInstance();
		FakeNewEventDatabase.create();
		
		// Method under test
		try {
			FakeNewEventDatabase.create();
			fail("Should throw exception");
		} catch (DatabaseAlreadyInstantiatedException e) {
			assertNotNull(e);
		}
	}
}
