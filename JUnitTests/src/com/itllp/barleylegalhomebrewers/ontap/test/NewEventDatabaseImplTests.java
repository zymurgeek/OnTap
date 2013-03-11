package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;
import com.itllp.barleylegalhomebrewers.ontap.NewEventDatabaseImpl;

public class NewEventDatabaseImplTests {

	private EventDatabase eventDatabase;
	private final Event expectedEvent1 = new Event(1);
	private final Event expectedEvent2 = new Event(2);
	private List<Event> expectedEventList;
	
	@Before
	public void setUp() throws Exception {
		FakeEventDatabase.clearInstance();
		NewEventDatabaseImpl.create();
		eventDatabase = EventDatabase.getInstance();
		
		expectedEventList = new ArrayList<Event>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		// Postconditions
		assertNotNull(eventDatabase);
		assertTrue(eventDatabase instanceof NewEventDatabaseImpl);
		assertTrue(eventDatabase.isEmpty());
		assertEquals(0, eventDatabase.size());
		assertEquals(0, eventDatabase.getEventList().size());
	}

	@Test
	public void testAddNullEventDoesNothing() {
		// Method under test
		eventDatabase.addOrUpdateEvent(null);
		
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
		eventDatabase.addOrUpdateEvent(expectedEvent1);
		
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
		eventDatabase.addOrUpdateEvent(expectedEvent1);
		eventDatabase.addOrUpdateEvent(expectedEvent2);
		
		// Postconditions
		assertFalse(eventDatabase.isEmpty());
		assertEquals(2, eventDatabase.size());
		assertEquals(expectedEventList, eventDatabase.getEventList());
	}

	@Test
	public void testContainsIdWhenDatabaseEmpty() {
		assertFalse(eventDatabase.containsId(0));
	}

	@Test
	public void testContainsIdWhenIdNotInDatabase() {
		// Preconditions
		eventDatabase.addOrUpdateEvent(expectedEvent1);
		int idNotInDatabase = expectedEvent1.getId()+1;
		
		// Method under test
		assertFalse(eventDatabase.containsId(idNotInDatabase));
	}
	
	@Test
	public void testContainsIdWhenIdInDatabase() {
		// Preconditions
		eventDatabase.addOrUpdateEvent(expectedEvent1);
		int idInDatabase = expectedEvent1.getId();
		
		// Method under test
		assertTrue(eventDatabase.containsId(idInDatabase));
	}
	
	@Test 
	public void testUpdateEvent() {
		// Preconditions
		Event updatedExpectedEvent2 = new Event(expectedEvent2.getId());
		String updatedExpectedName = "Updated Event 2";
		updatedExpectedEvent2.setName(updatedExpectedName);
		
		// Method under test
		eventDatabase.addOrUpdateEvent(expectedEvent2);
		eventDatabase.addOrUpdateEvent(updatedExpectedEvent2);
		
		// Postconditions
		assertFalse(eventDatabase.isEmpty());
		assertEquals(1, eventDatabase.size());
		List<Event> actualEventList = eventDatabase.getEventList();
		Event actualUpdatedEvent = actualEventList.get(0);
		assertEquals(updatedExpectedEvent2, actualUpdatedEvent);
	}
	
	@Test
	public void testDeleteIdNotInDatabase() {
		// Preconditions
		eventDatabase.addOrUpdateEvent(expectedEvent1);
		int idNotInDatabase = expectedEvent1.getId()+1;
		
		// Method under test
		eventDatabase.deleteId(idNotInDatabase);
		
		// Postconditions
		assertFalse(eventDatabase.isEmpty()); 
	}
	
	@Test
	public void testDeleteIdInDatabase() {
		// Preconditions
		eventDatabase.addOrUpdateEvent(expectedEvent1);
		int idInDatabase = expectedEvent1.getId();
		
		// Method under test
		eventDatabase.deleteId(idInDatabase);
		
		// Postconditions
		assertTrue(eventDatabase.isEmpty()); 
	}

	@Test
	public void testGetEventWhenNotInDatabaseReturnsNull() {
		assertNull(eventDatabase.getEvent(0));
	}
	
	@Test
	public void testGetEventWhenInDatabase() {
		// Preconditions
		eventDatabase.addOrUpdateEvent(expectedEvent1);
		int idInDatabase = expectedEvent1.getId();
		
		// Method under test
		Event eventInDatabase = eventDatabase.getEvent(idInDatabase);
		
		// Postconditions
		assertEquals(expectedEvent1, eventInDatabase);
	}
	
	@Test
	public void testClearEvents() {
		// Preconditions
		eventDatabase.addOrUpdateEvent(expectedEvent1);
		
		// Method under test
		eventDatabase.clearEventList();
		
		// Postconditions
		assertTrue(eventDatabase.isEmpty());
		assertEquals(0, eventDatabase.size());
		assertEquals(expectedEventList, eventDatabase.getEventList());
	}
}
