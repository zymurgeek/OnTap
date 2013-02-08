package com.itllp.beerfestival.dateconverter.test;

import static org.mockito.Mockito.*;
import java.util.Date;
import junit.framework.TestCase;

import com.itllp.beerfestival.dateconverter.JavaDateToString;
import com.itllp.beerfestival.dateconverter.StringToJavaDate;
import com.itllp.beerfestival.json.JsonDateToHumanReadableDate;

public class JsonDateToHumanReadableDateTests extends TestCase {

	private JsonDateToHumanReadableDate cut = null;
	StringToJavaDate mockJsonDateConverter = null;
	JavaDateToString mockJavaDateConverter = null; 
	
	public JsonDateToHumanReadableDateTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mockJsonDateConverter = mock(StringToJavaDate.class);
		when(mockJsonDateConverter.getJavaDate(anyString())).thenReturn(new Date());
		mockJavaDateConverter = mock(JavaDateToString.class);
		when(mockJavaDateConverter.getString((Date)anyObject())).thenReturn("");
		
		cut = new JsonDateToHumanReadableDate(mockJsonDateConverter, 
				mockJavaDateConverter);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testNullInput() {
		// Method under test
		String actual = cut.convertString(null);
		
		// Postconditions
		assertEquals("", actual);
	}
	
	public void testEmptyInput() {
		// Method under test
		String actual = cut.convertString("");
		
		// Postconditions
		assertEquals("", actual);
	}
	
	public void testConversion() {
		// Preconditions
		String jsonDate = "json_date";
		Date javaDate = new Date(12345);
		String humanReadableDate = "human_readable_date";
		when(mockJsonDateConverter.getJavaDate(jsonDate)).thenReturn(javaDate);
		when(mockJavaDateConverter.getString(javaDate)).thenReturn(humanReadableDate);

		// Method under test
		String actual = cut.convertString(jsonDate);
		
		// Postconditions
		assertEquals(humanReadableDate, actual);
	}
}
