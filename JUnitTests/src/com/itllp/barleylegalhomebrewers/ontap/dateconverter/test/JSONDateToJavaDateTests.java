package com.itllp.barleylegalhomebrewers.ontap.dateconverter.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JSONDateToJavaDate;

public class JSONDateToJavaDateTests {

	private JSONDateToJavaDate converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new JSONDateToJavaDate();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNull() {
		// Method under test
		Date actual = converter.getJavaDate(null);
		
		// Postconditions
		Date expected = new Date(0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNonDate() {
		// Method under test
		Date actual = converter.getJavaDate("not a date");
		
		// Postconditions
		Date expected = new Date(0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test1Jan1970() {
		// Method under test
		Date actual = converter.getJavaDate("/Date(0)/");
		
		// Postconditions
		Date expected = new Date(0);
		assertEquals(expected, actual);
	}

	
	@Test
	public void test14Dec2012_194230_123() {
		// Preconditions
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MMM-dd HH:mm:ss.SSSS");
		Date expectedDate = null;
		try { 
	          expectedDate = ft.parse("2012-Dec-14 19:42:30.123"); 
	      } catch (ParseException e) { 
	          fail(e.toString()); 
	      }
		long expectedMillis = expectedDate.getTime();
		
		String jsonDate = "\\/Date(" + String.valueOf(expectedMillis) + ")\\/";
		
		// Method under test
		Date actualDate = converter.getJavaDate(jsonDate);
		
		// Postconditions
		assertEquals(expectedDate, actualDate);
	}
	
	@Test
	public void testGetMillisFromMinutesZero() {
		// Method under test
		long actualMillis = converter.getMillisFromMinutes(0);
		
		// Postconditions
		assertEquals(0, actualMillis);
	}

	@Test
	public void testGetMillisFromMinutesSixty() {
		// Method under test
		long actualMillis = converter.getMillisFromMinutes(60);
		
		// Postconditions
		assertEquals(60 * 60 * 1000, actualMillis);
	}

	@Test
	public void testGetMillisFromHoursZero() {
		// Method under test
		long actualMillis = converter.getMillisFromHours(0);
		
		// Postconditions
		assertEquals(0, actualMillis);
	}

	@Test
	public void testGetMillisFromHours12() {
		// Method under test
		long actualMillis = converter.getMillisFromHours(12);
		
		// Postconditions
		assertEquals(12 * 60 * 60 * 1000, actualMillis);
	}

	
	@Test
	public void testGetTzMinutesZero() {
		// Method under test
		long actualMinutes = converter.getTzMinutes(0);
		
		// Postconditions
		assertEquals(0, actualMinutes);
	}

	
	@Test
	public void testGetTzMinutes30() {
		// Method under test
		long actualMinutes = converter.getTzMinutes(130);
		
		// Postconditions
		assertEquals(30, actualMinutes);
	}

	
	@Test
	public void testGetTzHours0() {
		// Method under test
		long actualHours = converter.getTzHours(0);
		
		// Postconditions
		assertEquals(0, actualHours);
	}

	
	@Test
	public void testGetTzHours5() {
		// Method under test
		long actualHours = converter.getTzHours(530);
		
		// Postconditions
		assertEquals(5, actualHours);
	}

	
	@Test
	public void testRemoveJSONDateWrapperNull() {
		// Method under test
		String actual = converter.removeJSONDateWrapper(null);
		
		// Postconditions
		assertEquals("", actual);
	}

	
	@Test
	public void testRemoveJSONDateWrapperBadDate() {
		// Preconditions
		String badJSONDate = "Date(1352001600000-0400)";
		
		// Method under test
		String actual = converter.removeJSONDateWrapper(badJSONDate);
		
		// Postconditions
		assertEquals(badJSONDate, actual);
	}
	
	
	@Test
	public void testRemoveJSONDateWrapperZeroDate() {
		// Method under test
		String actual = converter.removeJSONDateWrapper("\\/Date(0)\\/");
		
		// Postconditions
		assertEquals("0", actual);
	}

	
	@Test
	public void testRemoveJSONDateWrapperTypicalDate() {
		// Method under test
		String actual = converter.removeJSONDateWrapper("\\/Date(1352001600000-0400)\\/");
		
		// Postconditions
		assertEquals("1352001600000-0400", actual);
	}
	
 
	@Test
	public void testGetTimezoneStartOffset() {
		// Method under test
		int actual = converter.getTimezoneStartOffset(null);
		
		// Postconditions
		assertEquals(-1, actual);
	}
	
 
	@Test
	public void testGetTimezoneStartOffsetNoTimezone() {
		// Method under test
		int actual = converter.getTimezoneStartOffset("1234");
		
		// Postconditions
		assertEquals(-1, actual);
	}
	
 
	@Test
	public void testGetTimezoneStartOffsetPositive() {
		// Method under test
		int actual = converter.getTimezoneStartOffset("1234+2");
		
		// Postconditions
		assertEquals(4, actual);
	}
	
	 
	@Test
	public void testGetTimezoneStartOffsetNegative() {
		// Method under test
		int actual = converter.getTimezoneStartOffset("1234567890-0500");
		
		// Postconditions
		assertEquals(10, actual);
	}

	
	@Test
	public void testParseLongNull() {
		// Method under test
		long actual = converter.parseLong(null);
		
		// Postconditions
		assertEquals(0, actual);
	}

	
	@Test
	public void testParseLongPositive() {
		// Method under test
		long actual = converter.parseLong("+1234");
		
		// Postconditions
		assertEquals(1234, actual);
	}

	
	@Test
	public void testParseLongNegative() {
		// Method under test
		long actual = converter.parseLong("-5678");
		
		// Postconditions
		assertEquals(-5678, actual);
	}
}
