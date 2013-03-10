package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderAlreadyInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.DatabaseLoaderNotInstantiatedException;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;
import com.itllp.barleylegalhomebrewers.ontap.ProductionSiteEventDatabaseLoader;

public class ProductionSiteEventDatabaseLoaderTest {

	@Before
	public void setUp() throws Exception {
		FakeEventDatabaseLoader.clearInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateWhenNotInitialized() {
		// Method under test
		ProductionSiteEventDatabaseLoader.create();
		
		// Postconditions
		assertTrue(EventDatabaseLoader.getInstance() instanceof ProductionSiteEventDatabaseLoader);
	}

	@Test
	public void testCreateWhenAlreadyInitialized() {
		// Preconditions
		ProductionSiteEventDatabaseLoader.create();
		
		// Method under test and postconditions
		try {
			ProductionSiteEventDatabaseLoader.create();
			fail("Should throw exception");
		} catch (DatabaseLoaderAlreadyInstantiatedException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testGetInstanceWhenInitialized() {
		// Preconditions
		ProductionSiteEventDatabaseLoader.create();

		// Method under test
		EventDatabaseLoader loader = EventDatabaseLoader.getInstance();
		
		// Postconditions
		assertTrue(loader instanceof ProductionSiteEventDatabaseLoader);
	}
	
	@Test
	public void testGetInstanceWhenNotInitialized() {
		// Method under test
		try {
			EventDatabaseLoader.getInstance();
			fail("Should throw exception");
		} catch (DatabaseLoaderNotInstantiatedException e) {
			assertNotNull(e);
		}
	}

}
