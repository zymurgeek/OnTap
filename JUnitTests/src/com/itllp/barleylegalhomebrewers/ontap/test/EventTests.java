package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.Event;

public class EventTests {

	private Event event;

	@Before
	public void setUp() throws Exception {
		event = new Event(0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreation() {
		// Method under test
		event = new Event(0);
		
		// Postconditions
		assertNotNull(event);
		assertEquals(0, event.getId());
		assertEquals("", event.getName());
		assertEquals(new Date(0), event.getDate());
	}
	
	@Test
	public void testSetNameWithNull() {
		// Method under test
		event.setName(null);
		
		// Postconditions
		assertEquals("", event.getName());
	}
	
	@Test
	public void testSetNameWithNonNull() {
		// Preconditions
		String expectedName = "My Name";
		
		// Method under test
		event.setName(expectedName);
		
		// Postconditions
		assertEquals(expectedName, event.getName());
		
	}

	@Test
	public void testSetDateWithNull() {
		// Method under test
		event.setDate(null);
		
		// Postconditions
		assertEquals(new Date(0), event.getDate());
	}

	@Test
	public void testSetDateWithNonNull() {
		// Preconditions
		Date expectedDate = new Date(12345);
		
		// Method under test
		event.setDate(expectedDate);
		
		// Postconditions
		assertEquals(expectedDate, event.getDate());
	}
	
}
