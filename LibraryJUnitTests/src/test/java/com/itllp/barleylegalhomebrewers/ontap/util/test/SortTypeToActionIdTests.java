package com.itllp.barleylegalhomebrewers.ontap.util.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.SortType;
import com.itllp.barleylegalhomebrewers.ontap.util.SortTypeToActionId;

public class SortTypeToActionIdTests {

	private SortTypeToActionId cut;
	private final int srmActionId = 77;
	private final int ibuActionId = 99;


	@Before
	public void setUp() throws Exception {
		cut = new SortTypeToActionId();
		cut.add(SortType.SRM, srmActionId);
		cut.add(SortType.IBU, ibuActionId);
	}

	
	@Test 
	public void testGetIdForSrmSortType() {
		// Call method under test
		int actualActionId = cut.getActionId(SortType.SRM);
		
		// Verify postconditions
		assertEquals("Incorrect action ID", srmActionId, actualActionId);
	}


	@Test 
	public void testGetSortTypeForSrmId() {
		// Call method under test
		SortType actualSortType = cut.getSortType(srmActionId);
		
		// Verify postconditions
		assertEquals("Incorrect sort type", SortType.SRM, actualSortType);
	}

	
	@Test 
	public void testGetIdForIbuSortType() {
		// Call method under test
		int actualActionId = cut.getActionId(SortType.IBU);
		
		// Verify postconditions
		assertEquals("Incorrect action ID", ibuActionId, actualActionId);
	}


	@Test 
	public void testGetSortTypeForIbuId() {
		// Call method under test
		SortType actualSortType = cut.getSortType(ibuActionId);
		
		// Verify postconditions
		assertEquals("Incorrect sort type", SortType.IBU, actualSortType);
	}


}
