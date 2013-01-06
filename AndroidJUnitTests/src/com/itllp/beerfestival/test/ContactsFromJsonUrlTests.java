package com.itllp.beerfestival.test;

import com.itllp.beerfestival.AlwaysUpNetworkConnectivity;
import com.itllp.beerfestival.ContactsFromJsonUrl;
import com.itllp.beerfestival.NetworkConnectivity;

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
		NetworkConnectivity net = new AlwaysUpNetworkConnectivity(); 
		ContactsFromJsonUrl cut = new ContactsFromJsonUrl(net, null);
		assertNotNull(cut);
	}

}
