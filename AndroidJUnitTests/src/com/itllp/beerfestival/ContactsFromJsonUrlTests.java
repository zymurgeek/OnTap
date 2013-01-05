package com.itllp.beerfestival;

import junit.framework.TestCase;

public class ContactsFromJsonUrlTests extends TestCase {

	public ContactsFromJsonUrlTests(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testContactsFromJsonUrl() {
		ContactsFromJsonUrl cut = new ContactsFromJsonUrl(null, null);
		assertNotNull(cut);
	}

}
