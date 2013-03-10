package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.EventDatabaseLoader;

public class JsonUrlEventDatabaseLoader extends EventDatabaseLoader {

	private String url;
	
	private JsonUrlEventDatabaseLoader(String url) {
		this.url = url;
	}
	
	public void load() {
		// TODO Auto-generated method stub
	}

	public String getUrl() {
		return url;
	}

	public static void create(String url) {
		setInstance(new JsonUrlEventDatabaseLoader(url));
	}
}
