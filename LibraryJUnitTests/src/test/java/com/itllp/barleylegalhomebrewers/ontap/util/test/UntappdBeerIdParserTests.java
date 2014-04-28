
package com.itllp.barleylegalhomebrewers.ontap.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.util.UntappdBeerIdParser;

public class UntappdBeerIdParserTests {


	@Test
	public void testWhenNoIdExists() {
		// Set up preconditions
		UntappdBeerIdParser parser = new UntappdBeerIdParser();
		final String DESCRIPTION_WITHOUT_ID = 
				"This is a really nice beer "
				+ "that doesn't taste like it's had a dead rat in it.";
		
		// Call method under test
		String actual = parser.getUntappdBeerId(DESCRIPTION_WITHOUT_ID);
		
		// Verify postconditions
		assertEquals("", actual);
	}

	@Test
	public void testWhenIdExists() {
		// Set up preconditions
		UntappdBeerIdParser parser = new UntappdBeerIdParser();
		final String ID = "987654";
		final String DESCRIPTION_WITH_ID = 
				"This is a really nice beer " + 
				"that doesn't taste like it's had a dead rat in it.\n" +
				"The unTAPpd id for this beer is " + ID + ".";
		
		// Call method under test
		String actual = parser.getUntappdBeerId(DESCRIPTION_WITH_ID);
		
		// Verify postconditions
		assertEquals(ID, actual);
	}
}
