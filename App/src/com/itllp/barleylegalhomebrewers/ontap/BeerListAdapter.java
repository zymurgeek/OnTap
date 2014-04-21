package com.itllp.barleylegalhomebrewers.ontap;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BeerListAdapter extends SimpleCursorAdapter {

	public BeerListAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
	}

	
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        view = super.getView(position, convertView, parent);

        Cursor cursor = (Cursor)getItem(position);
        int thisSectionTextId = getSectionTextId(cursor);

        TextView locationView = (TextView)view.findViewById(R.id.beer_location);
        String location = "";
        int tapNumber = cursorUtil.getTapNumber(cursor);
        if (!cursorUtil.isKicked(cursor)) {
            if (tapNumber > 0) {
            	location = "Tap #" + tapNumber; 
            } else {
            	if (tapNumber < 0) {
            		location = "Bottle";
            	}
            }
        }
        if (cursorUtil.isKicked(cursor) || tapNumber == 0) {
        	location = cursorUtil.getPackaging(cursor);
        }
    	locationView.setText(location);
        
        TextView styleOverrideView = (TextView)view.findViewById(R.id.beer_style_override); 
		if (0 == styleOverrideView.length()) {
			styleOverrideView.setVisibility(View.GONE);
		}
        
        int lastSectionTextId = -1;
        if (cursor.moveToPrevious()) {
        	lastSectionTextId = getSectionTextId(cursor);
        }
        TextView sectionHeaderView = (TextView)view.findViewById(R.id.section_title);
        if (thisSectionTextId != lastSectionTextId) {
        	sectionHeaderView.setText(thisSectionTextId);
        	sectionHeaderView.setVisibility(View.VISIBLE);
        } else {
        	sectionHeaderView.setVisibility(View.GONE);
        }
        
        return view;
    }

	private int getSectionTextId(Cursor beer) {
		int sectionText = R.string.on_deck;
		if (cursorUtil.isKicked(beer)) {
			sectionText = R.string.kicked_text;
		} else if (cursorUtil.getTapNumber(beer) != 0) {
			sectionText = R.string.pouring;
		}
		return sectionText;
	}

	private BeerCursorUtility cursorUtil = new BeerCursorUtility();
}

