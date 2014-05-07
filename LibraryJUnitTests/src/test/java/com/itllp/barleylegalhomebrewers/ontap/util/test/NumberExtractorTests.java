package com.itllp.barleylegalhomebrewers.ontap.util.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.util.NumberExtractor;

public class NumberExtractorTests {
	private NumberExtractor cut;
	
	@Before
	public void setUp() throws Exception {
		cut = new NumberExtractor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmptyString() {
		// Set up preconditions
		String input = "";
		
		// Call method under test
		Float actual = cut.extractNumber(input);
		
		// Verify postconditions
		Float expected = null;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNoNumericCharacters() {
		// Set up preconditions
		String input = "abc";
		
		// Call method under test
		Float actual = cut.extractNumber(input);
		
		// Verify postconditions
		Float expected = null;
		assertEquals(expected, actual);
	}

	@Test
	public void testOnlyDigits() {
		// Set up preconditions
		String input = "123";
		
		// Call method under test
		Float actual = cut.extractNumber(input);
		
		// Verify postconditions
		Float expected = (float) 123;
		assertEquals(expected, actual);
	}

	
	@Test
	public void testDigitsAndOneDecimalPoint() {
		// Set up preconditions
		String input = "123.17";
		
		// Call method under test
		Float actual = cut.extractNumber(input);
		
		// Verify postconditions
		Float expected = (float) 123.17;
		assertEquals(expected, actual);
	}

	@Test
	public void testDigitsAndTwoDecimalPoints() {
		// Set up preconditions
		String input = "987.65.43";
		
		// Call method under test
		Float actual = cut.extractNumber(input);
		
		// Verify postconditions
		Float expected = (float) 987.65;
		assertEquals(expected, actual);
	}

	@Test
	public void testOnlyDecimalPoint() {
		// Set up preconditions
		String input = ".";
		
		// Call method under test
		Float actual = cut.extractNumber(input);
		
		// Verify postconditions
		Float expected = null;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEndsWithDecimalPoint() {
		// Set up preconditions
		String input = "5.";
		
		// Call method under test
		Float actual = cut.extractNumber(input);
		
		// Verify postconditions
		Float expected = (float) 5;
		assertEquals(expected, actual);
	}

	
	@Test
	public void testNegative() {
		// Set up preconditions
		String input = "-2";
		
		// Call method under test
		Float actual = cut.extractNumber(input);
		
		// Verify postconditions
		Float expected = (float) -2;
		assertEquals(expected, actual);
		
	}
	
	
	@Test
	public void testDoubleNegative() {
		// Set up preconditions
		String input = "-2-1";
		
		// Call method under test
		Float actual = cut.extractNumber(input);
		
		// Verify postconditions
		Float expected = (float) -2;
		assertEquals(expected, actual);
		
	}
	
	
	@Test
	public void numberInsideJunk() {
		// Set up preconditions
		String input = "a1b2c3df.4g5h.i6j";
		
		// Call method under test
		Float actual = cut.extractNumber(input);
		
		// Verify postconditions
		Float expected = (float) 123.45;
		assertEquals(expected, actual);
	}
}
