package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.Event;

public class EventTests {

	private Event event;
	private int id = 99;
	private String name = "name";
	private long dateMillis = 987654321L;
	private Date date = new Date(dateMillis);
	private Event event1;
	private Event sameAsEvent1;

	@Before
	public void setUp() throws Exception {
		event = new Event(0);
		
		event1 = new Event(id);
		event1.setName(name);
		event1.setDate(date);
		
		sameAsEvent1 = new Event(id);
		sameAsEvent1.setName(name);
		sameAsEvent1.setDate(date);
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
	
	@Test
	public void testEqualsWithEqualObjects() {
		// Method under test and postconditions
		assertEquals(event1, sameAsEvent1);
	}
	
	@Test
	public void testEqualsWithUnequalIds() {
		// Preconditions
		Event event2 = new Event(id+1);
		event2.setName(name);
		event2.setDate(date);
		
		// Method under test and postconditions
		assertFalse(event1.equals(event2));
	}
	
	@Test
	public void testEqualsWithUnequalNames() {
		// Preconditions
		Event event2 = new Event(id);
		String differentName = "not " + name;
		event2.setName(differentName);
		event2.setDate(date);
		
		// Method under test and postconditions
		assertFalse(event1.equals(event2));
	}
	
	@Test
	public void testEqualsWithUnequalDates() {
		// Preconditions
		Event event2 = new Event(id);
		event2.setName(name);
		Date differentDate = new Date(dateMillis * 2);
		event2.setDate(differentDate);
		
		// Method under test and postconditions
		assertFalse(event1.equals(event2));
	}
	
	@Test
	public void testHashCode() {
		// Preconditions
		assertEquals(id, event1.hashCode());
	}
}
