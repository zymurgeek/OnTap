package com.itllp.barleylegalhomebrewers.ontap.dateconverter.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToSQLiteDateConverter;

public class JavaDateToSQLiteDateConverterTests extends TestCase {

	private JavaDateToSQLiteDateConverter cut = null;
	
	public JavaDateToSQLiteDateConverterTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		cut = new JavaDateToSQLiteDateConverter();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testNullInput() {
		// Method under test
		String actual = cut.getString(null);
		
		// Postconditions
		assertEquals("", actual);
	}
	
	public void testConversion() {
		// Set up Preconditions
		String expectedDate = "2013-08-07 13:10:03";
		SimpleDateFormat iso8601Format = cut.iso8601Format;
        Date javaDate = null;
        try {
        	javaDate = iso8601Format.parse(expectedDate);
        } catch (java.text.ParseException e) {
        	fail("Could not parse date");
        }
        
		// Call method under test
		String actualDate = cut.getString(javaDate);
		
		// Postconditions
		assertEquals(expectedDate, actualDate);
	}
}
