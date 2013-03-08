package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoaderFactory;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoaderFactoryProvider;
import com.itllp.barleylegalhomebrewers.ontap.FactoryAlreadySetException;
import com.itllp.barleylegalhomebrewers.ontap.NullFactoryException;
import com.itllp.barleylegalhomebrewers.ontap.UninitializedFactoryException;

public class EventDatabaseLoaderFactoryProviderTests extends TestCase {

	private final EventDatabaseLoaderFactory factory = mock(EventDatabaseLoaderFactory.class);

	public EventDatabaseLoaderFactoryProviderTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		EventDatabaseLoaderFactoryProviderTestHelper.clearEventDatabaseLoaderFactory();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetFactoryWhenUninitialized() {
		// Method under test and postconditions
		try {
			EventDatabaseLoaderFactoryProvider.getEventDatabaseLoaderFactory();
			fail("Uninitialized provider should throw exception");
		} catch (UninitializedFactoryException correctException) {
			assertNotNull(correctException);
		} catch (Exception wrongException) {
			fail("Wrong exception thrown: " + wrongException);
		}
	}

	public void testGetFactoryWhenInitialized() {
		// Preconditions
		EventDatabaseLoaderFactoryProvider.setEventDatabaseLoaderFactory(factory);
		EventDatabaseLoaderFactory actualFactory = null;
		
		// Method under test and postconditions
		try {
			actualFactory = EventDatabaseLoaderFactoryProvider.getEventDatabaseLoaderFactory();
		} catch (Exception e) {
			fail("Should not have thrown exception, but threw: " + e);
		}
		
		// Postconditions
		assertEquals(factory, actualFactory);
	}

	public void testSetFactoryWhenUninitialized() {
		// Method under test and postconditions
		try {
			EventDatabaseLoaderFactoryProvider.setEventDatabaseLoaderFactory(factory);
		} catch (Exception e) {
			fail("Should not have thrown an exception, but threw: " + e);
		}
	}

	public void testSetFactoryWhenAlreadySet() {
		// Preconditions
		EventDatabaseLoaderFactoryProvider.setEventDatabaseLoaderFactory(factory);
		
		// Method under test and postconditions
		try {
			EventDatabaseLoaderFactoryProvider.setEventDatabaseLoaderFactory(factory);
			fail("Should have thrown an exception");
		} catch (FactoryAlreadySetException correctException) {
			assertNotNull(correctException);
		} catch (Exception wrongException) {
			fail("Threw wrong exception: " + wrongException);
		}
	}

	public void testSetNullFactory() {
		// Preconditions
		try {
			EventDatabaseLoaderFactoryProvider.setEventDatabaseLoaderFactory(null);
			fail("Should have thrown an exception");
		} catch (NullFactoryException correctException) {
			assertNotNull(correctException);
		} catch (Exception wrongException) {
			fail("Threw wrong exception: " + wrongException);
		}
	}
}
