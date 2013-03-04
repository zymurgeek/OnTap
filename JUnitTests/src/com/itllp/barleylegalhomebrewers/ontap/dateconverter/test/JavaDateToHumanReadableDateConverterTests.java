package com.itllp.barleylegalhomebrewers.ontap.dateconverter.test;

import static org.mockito.Mockito.*;
import java.util.Date;
import junit.framework.TestCase;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToHumanReadableDateConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToStringConverter;
import com.itllp.barleylegalhomebrewers.ontap.dateconverter.StringToJavaDateConverter;

public class JavaDateToHumanReadableDateConverterTests extends TestCase {

	private JavaDateToHumanReadableDateConverter cut = null;
	StringToJavaDateConverter mockJsonDateConverter = null;
	JavaDateToStringConverter mockJavaDateConverter = null; 
	
	public JavaDateToHumanReadableDateConverterTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mockJsonDateConverter = mock(StringToJavaDateConverter.class);
		when(mockJsonDateConverter.getJavaDate(anyString())).thenReturn(new Date());
		mockJavaDateConverter = mock(JavaDateToStringConverter.class);
		when(mockJavaDateConverter.getString((Date)anyObject())).thenReturn("");
		
		cut = new JavaDateToHumanReadableDateConverter();
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
	
	public void testNonNullInput() {
		// Preconditions
		Date javaDate = new Date(12345);

		// Method under test
		String actual = cut.getString(javaDate);
		
		// Postconditions
		assertNotNull(actual);
	}
}
