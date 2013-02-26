package com.itllp.barleylegalhomebrewers.ontap;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventDatabaseImplTests {

	private EventDatabaseImpl eventDatabase;
	private final Event expectedEvent1 = new Event(1);
	private final Event expectedEvent2 = new Event(2);
	private List<Event> expectedEventList;

	@Before
	public void setUp() throws Exception {
		eventDatabase = new EventDatabaseImpl();
		expectedEventList = new ArrayList<Event>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitializationCreatesZeroSizeDatabase() {
		// Postconditions
		assertTrue(eventDatabase.isEmpty());
		assertEquals(0, eventDatabase.size());
		assertEquals(expectedEventList, eventDatabase.getEventList());
	}
	
	@Test
	public void testInitializationCreatesEmptyList() {
		// Postconditions
		assertTrue(eventDatabase.isEmpty());
		assertEquals(0, eventDatabase.size());
		assertEquals(expectedEventList, eventDatabase.getEventList());
	}
	
	@Test
	public void testAddNullEvent() {
		// Method under test
		eventDatabase.addEvent(null);
		
		// Postconditions
		assertTrue(eventDatabase.isEmpty());
		assertEquals(0, eventDatabase.size());
		assertEquals(expectedEventList, eventDatabase.getEventList());
	}
	
	@Test
	public void testAddOneEvent() {
		// Preconditions
		expectedEventList.add(expectedEvent1);
		
		// Method under test
		eventDatabase.addEvent(expectedEvent1);
		
		// Postconditions
		assertFalse(eventDatabase.isEmpty());
		assertEquals(1, eventDatabase.size());
		assertEquals(expectedEventList, eventDatabase.getEventList());
	}
	
	@Test
	public void testAddTwoEvents() {
		// Preconditions
		expectedEventList.add(expectedEvent1);
		expectedEventList.add(expectedEvent2);
		
		// Method under test
		eventDatabase.addEvent(expectedEvent1);
		eventDatabase.addEvent(expectedEvent2);
		
		// Postconditions
		assertFalse(eventDatabase.isEmpty());
		assertEquals(2, eventDatabase.size());
		assertEquals(expectedEventList, eventDatabase.getEventList());
	}
	
	@Test 
	public void testAddDuplicateEvents() {
		// Preconditions
		expectedEventList.add(expectedEvent2);
		
		// Method under test
		eventDatabase.addEvent(expectedEvent2);
		eventDatabase.addEvent(expectedEvent2);
		
		// Postconditions
		assertFalse(eventDatabase.isEmpty());
		assertEquals(1, eventDatabase.size());
		assertEquals(expectedEventList, eventDatabase.getEventList());
	}
	
	@Test
	public void testClearEvents() {
		// Preconditions
		eventDatabase.addEvent(expectedEvent1);
		
		// Method under test
		eventDatabase.clearEventList();
		
		// Postconditions
		assertTrue(eventDatabase.isEmpty());
		assertEquals(0, eventDatabase.size());
		assertEquals(expectedEventList, eventDatabase.getEventList());
	}
}
