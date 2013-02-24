package com.itllp.barleylegalhomebrewers.ontap.dateconverter.test;

import static org.mockito.Mockito.*;
import java.util.Date;
import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToString;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.json.JsonDateToHumanReadableDate;

public class JsonDateToHumanReadableDateTests extends TestCase {

	private JsonDateToHumanReadableDate cut = null;
	StringToJavaDateConverter mockJsonDateConverter = null;
	JavaDateToString mockJavaDateConverter = null; 
	
	public JsonDateToHumanReadableDateTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mockJsonDateConverter = mock(StringToJavaDateConverter.class);
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
