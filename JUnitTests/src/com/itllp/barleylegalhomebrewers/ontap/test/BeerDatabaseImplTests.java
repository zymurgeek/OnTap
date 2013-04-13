package com.itllp.barleylegalhomebrewers.ontap.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.Beer;
import com.itllp.barleylegalhomebrewers.ontap.BeerDatabase;
import com.itllp.barleylegalhomebrewers.ontap.BeerDatabaseImpl;

public class BeerDatabaseImplTests {

	private BeerDatabase beerDatabase;
	private final Beer expectedBeer1 = new Beer(1);
	private final Beer expectedBeer2 = new Beer(2);
	private List<Beer> expectedBeerList;
	
	@Before
	public void setUp() throws Exception {
		FakeBeerDatabase.clearInstance();
		BeerDatabaseImpl.create();
		beerDatabase = BeerDatabase.getInstance();
		
		expectedBeerList = new ArrayList<Beer>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		// Postconditions
		assertNotNull(beerDatabase);
		assertTrue(beerDatabase instanceof BeerDatabaseImpl);
		assertTrue(beerDatabase.isEmpty());
		assertEquals(0, beerDatabase.size());
		assertEquals(0, beerDatabase.getBeerList().size());
	}

	@Test
	public void testAddNullBeer() {
		// Method under test
		beerDatabase.addOrUpdateBeer(null);
		
		// Postconditions
		assertTrue(beerDatabase.isEmpty());
		assertEquals(0, beerDatabase.size());
		assertEquals(expectedBeerList, beerDatabase.getBeerList());
	}
	
	@Test
	public void testAddOneBeer() {
		// Preconditions
		expectedBeerList.add(expectedBeer1);
		
		// Method under test
		beerDatabase.addOrUpdateBeer(expectedBeer1);
		
		// Postconditions
		assertFalse(beerDatabase.isEmpty());
		assertEquals(1, beerDatabase.size());
		assertEquals(expectedBeerList, beerDatabase.getBeerList());
	}
	
	@Test
	public void testAddTwoBeers() {
		// Preconditions
		expectedBeerList.add(expectedBeer1);
		expectedBeerList.add(expectedBeer2);
		
		// Method under test
		beerDatabase.addOrUpdateBeer(expectedBeer1);
		beerDatabase.addOrUpdateBeer(expectedBeer2);
		
		// Postconditions
		assertFalse(beerDatabase.isEmpty());
		assertEquals(2, beerDatabase.size());
		assertEquals(expectedBeerList, beerDatabase.getBeerList());
	}

	@Test
	public void testContainsIdWhenDatabaseEmpty() {
		assertFalse(beerDatabase.containsId(0));
	}

	@Test
	public void testContainsIdWhenIdNotInDatabase() {
		// Preconditions
		beerDatabase.addOrUpdateBeer(expectedBeer1);
		int idNotInDatabase = expectedBeer1.getId()+1;
		
		// Method under test
		assertFalse(beerDatabase.containsId(idNotInDatabase));
	}
	
	@Test
	public void testContainsIdWhenIdInDatabase() {
		// Preconditions
		beerDatabase.addOrUpdateBeer(expectedBeer1);
		int idInDatabase = expectedBeer1.getId();
		
		// Method under test
		assertTrue(beerDatabase.containsId(idInDatabase));
	}
	
	@Test 
	public void testUpdateBeer() {
		// Preconditions
		Beer updatedExpectedBeer2 = new Beer(expectedBeer2.getId());
		String updatedExpectedName = "Updated Beer 2";
		updatedExpectedBeer2.setBeerName(updatedExpectedName);
		
		// Method under test
		beerDatabase.addOrUpdateBeer(expectedBeer2);
		beerDatabase.addOrUpdateBeer(updatedExpectedBeer2);
		
		// Postconditions
		assertFalse(beerDatabase.isEmpty());
		assertEquals(1, beerDatabase.size());
		List<Beer> actualBeerList = beerDatabase.getBeerList();
		Beer actualUpdatedBeer = actualBeerList.get(0);
		assertEquals(updatedExpectedBeer2, actualUpdatedBeer);
	}
	
	@Test
	public void testDeleteIdNotInDatabase() {
		// Preconditions
		beerDatabase.addOrUpdateBeer(expectedBeer1);
		int idNotInDatabase = expectedBeer1.getId()+1;
		
		// Method under test
		beerDatabase.deleteId(idNotInDatabase);
		
		// Postconditions
		assertFalse(beerDatabase.isEmpty()); 
	}
	
	@Test
	public void testDeleteIdInDatabase() {
		// Preconditions
		beerDatabase.addOrUpdateBeer(expectedBeer1);
		int idInDatabase = expectedBeer1.getId();
		
		// Method under test
		beerDatabase.deleteId(idInDatabase);
		
		// Postconditions
		assertTrue(beerDatabase.isEmpty()); 
	}

	@Test
	public void testGetBeerWhenNotInDatabaseReturnsNull() {
		assertNull(beerDatabase.getBeer(0));
	}
	
	@Test
	public void testGetBeerWhenInDatabase() {
		// Preconditions
		beerDatabase.addOrUpdateBeer(expectedBeer1);
		int idInDatabase = expectedBeer1.getId();
		
		// Method under test
		Beer beerInDatabase = beerDatabase.getBeer(idInDatabase);
		
		// Postconditions
		assertEquals(expectedBeer1, beerInDatabase);
	}
	
	@Test
	public void testClearBeers() {
		// Preconditions
		beerDatabase.addOrUpdateBeer(expectedBeer1);
		
		// Method under test
		beerDatabase.clearBeerList();
		
		// Postconditions
		assertTrue(beerDatabase.isEmpty());
		assertEquals(0, beerDatabase.size());
		assertEquals(expectedBeerList, beerDatabase.getBeerList());
	}
}
