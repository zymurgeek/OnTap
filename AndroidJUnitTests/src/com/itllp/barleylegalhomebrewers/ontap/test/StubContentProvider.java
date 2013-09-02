package com.itllp.barleylegalhomebrewers.ontap.test;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class StubContentProvider extends ContentProvider {

	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		lastInsertedUri = uri;
		insertedContentValuesList.add(values);
		return null;
	}

	
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public int stub_getInsertCount() {
		return insertedContentValuesList.size();
	}
	
	
	public void stub_resetInsertedList() {
		insertedContentValuesList.clear();
	}
	
	
	public Uri stub_getLastUri() {
		return lastInsertedUri;
	}

	
	public List<ContentValues> stub_getContentValuesList() {
		return insertedContentValuesList;
	}
	
	
	private Uri lastInsertedUri = null;
	private List<ContentValues> insertedContentValuesList = new LinkedList<ContentValues>();
}
