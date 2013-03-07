package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.mockito.Mockito.*;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactory;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseFactoryProvider;
import com.itllp.barleylegalhomebrewers.ontap.FactoryAlreadySetException;
import com.itllp.barleylegalhomebrewers.ontap.NullFactoryException;
import com.itllp.barleylegalhomebrewers.ontap.UninitializedFactoryException;

import junit.framework.TestCase;

public class EventDatabaseFactoryProviderTests extends TestCase {

	private final EventDatabaseFactory factory = mock(EventDatabaseFactory.class);

	public EventDatabaseFactoryProviderTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		EventDatabaseFactoryProviderTestHelper.clearEventDatabaseFactory();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetFactoryWhenUninitialized() {
		// Method under test and postconditions
		try {
			EventDatabaseFactoryProvider.getEventDatabaseFactory();
			fail("Uninitialized provider should throw exception");
		} catch (UninitializedFactoryException correctException) {
			assertNotNull(correctException);
		} catch (Exception wrongException) {
			fail("Wrong exception thrown: " + wrongException);
		}
	}

	public void testGetFactoryWhenInitialized() {
		// Preconditions
		EventDatabaseFactoryProvider.setEventDatabaseFactory(factory);
		EventDatabaseFactory actualFactory = null;
		
		// Method under test and postconditions
		try {
			actualFactory = EventDatabaseFactoryProvider.getEventDatabaseFactory();
		} catch (Exception e) {
			fail("Should not have thrown exception, but threw: " + e);
		}
		
		// Postconditions
		assertEquals(factory, actualFactory);
	}

	public void testSetFactoryWhenUninitializedDoesNotThrowException() {
		// Method under test and postconditions
		try {
			EventDatabaseFactoryProvider.setEventDatabaseFactory(factory);
		} catch (Exception e) {
			fail("Should not have thrown an exception, but threw: " + e);
		}
	}

	public void testSetFactoryWhenAlreadySetThrowsException() {
		// Preconditions
		EventDatabaseFactoryProvider.setEventDatabaseFactory(factory);
		
		// Method under test and postconditions
		try {
			EventDatabaseFactoryProvider.setEventDatabaseFactory(factory);
			fail("Should have thrown an exception");
		} catch (FactoryAlreadySetException correctException) {
			assertNotNull(correctException);
		} catch (Exception wrongException) {
			fail("Threw wrong exception: " + wrongException);
		}
	}

	public void testSetNullFactoryNotAllowed() {
		// Preconditions
		try {
			EventDatabaseFactoryProvider.setEventDatabaseFactory(null);
			fail("Should have thrown an exception");
		} catch (NullFactoryException correctException) {
			assertNotNull(correctException);
		} catch (Exception wrongException) {
			fail("Threw wrong exception: " + wrongException);
		}
	}
}
