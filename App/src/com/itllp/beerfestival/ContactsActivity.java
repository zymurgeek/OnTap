package com.itllp.beerfestival;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ContactsActivity extends Activity {
	private ListView beerListView;
	private static String contactsUrl = "http://api.androidhive.info/contacts/";
	private ContactsFromJsonUrl contactList = null;
	
	public ContactsActivity() {
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        beerListView = (ListView)findViewById(R.id.list);
        this.loadContacts();
    }
    
    private void loadContacts() {
    	ConnectivityManager connMgr = (ConnectivityManager) 
    	        getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkConnectivity networkConnectivity
    	= new AndroidNetworkConnectivity(connMgr);
    	contactList = new ContactsFromJsonUrl(networkConnectivity, contactsUrl);

    	if (!contactList.contactList.isEmpty()) {
    		ListAdapter adapter = new 	SimpleAdapter(this, contactList.contactList,
    				R.layout.list_item,
    				new String[] { Contacts.TAG_NAME, Contacts.TAG_EMAIL, Contacts.TAG_PHONE_MOBILE }, new int[] {
    				R.id.name, R.id.email, R.id.mobile });

    		this.beerListView.setAdapter(adapter);
    	} else {
    		// display error
    		// http://developer.android.com/guide/topics/ui/layout/listview.html
    		String [] myStringArray = { "contacts not available" };
    		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
    				android.R.layout.simple_list_item_1, myStringArray);
    		this.beerListView.setAdapter(adapter);
    	}
    	    
    	// selecting single ListView item
    	// Launching new screen on Selecting Single ListItem
    	this.beerListView.setOnItemClickListener(new OnItemClickListener() {

    		@Override
    		public void onItemClick(AdapterView<?> parent, View view,
    				int position, long id) {
    			// getting values from selected ListItem
    			String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
    			String cost = ((TextView) view.findViewById(R.id.email)).getText().toString();
    			String description = ((TextView) view.findViewById(R.id.mobile)).getText().toString();
    				
    			// Starting new intent
    			Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
    			in.putExtra(SingleMenuItemActivity.TAG_NAME, name);
    			in.putExtra(SingleMenuItemActivity.TAG_EMAIL, cost);
    			in.putExtra(SingleMenuItemActivity.TAG_PHONE_MOBILE, description);
    			startActivity(in);

    		}
    	});
    }
}