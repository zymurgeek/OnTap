package com.itllp.beerfestival;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itllp.beerfestival.json.JSONParser;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

public class ContactsFromJsonUrl extends Contacts {
    // Hashmap for ListView
	private static final String TAG_CONTACTS = "contacts";
	public static final String TAG_PHONE = "phone";
	public ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
	public JSONParser jParser = new JSONParser();
	public JSONObject json = null;
	public JSONArray contacts = null;
	public String id = null;
	public String name = null;
	public String email = null;
	public JSONObject phone = null;
	public String mobile = null;
	public HashMap<String, String> map = null;
	
	public ContactsFromJsonUrl(NetworkConnectivity networkConnectivity, String url) {
	    if (networkConnectivity.isConnected()) {

	    	json = this.jParser.getJsonObjectFromUrl(url);

	    	try {
	    		this.contacts = this.json.getJSONArray(TAG_CONTACTS);
	    		for(int i = 0; i < this.contacts.length(); i++){
	    			JSONObject c = this.contacts.getJSONObject(i);

	    			this.id = c.getString(this.TAG_ID);
	    			this.name = c.getString(this.TAG_NAME);
	    			this.email = c.getString(this.TAG_EMAIL);
	    			this.phone = c.getJSONObject(this.TAG_PHONE);
	    			this.mobile = this.phone.getString(this.TAG_PHONE_MOBILE);

	    			this.map = new HashMap<String, String>();

	    			this.map.put(this.TAG_ID, this.id);
	    			this.map.put(this.TAG_NAME, this.name);
	    			this.map.put(this.TAG_EMAIL, this.email);
	    			this.map.put(this.TAG_PHONE_MOBILE, this.mobile);

	    			// adding HashList to ArrayList
	    			this.contactList.add(this.map);
	    		}
	    	} catch (JSONException e) {
	    		e.printStackTrace();
	    	}
	    }
	}


}
