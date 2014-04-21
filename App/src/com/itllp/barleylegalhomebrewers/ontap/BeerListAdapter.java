package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;

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
        int tapNumber = getTapNumber(cursor);
        if (!isKicked(cursor)) {
            if (tapNumber > 0) {
            	location = "Tap #" + tapNumber; 
            } else {
            	if (tapNumber < 0) {
            		location = "Bottle";
            	}
            }
        }
        if (isKicked(cursor) || tapNumber == 0) {
        	location = getPackaging(cursor);
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
		if (isKicked(beer)) {
			sectionText = R.string.kicked_text;
		} else if (getTapNumber(beer) != 0) {
			sectionText = R.string.pouring;
		}
		return sectionText;
	}

	private int getTapNumber(Cursor beer) {
		int tapColIndex = beer.getColumnIndex(BeerTableMetadata.TAP_NUMBER_COLUMN);
		int tapNumber = beer.getInt(tapColIndex);
		return tapNumber;
	}

	private boolean isKicked(Cursor beer) {
		int kickedColIndex = beer.getColumnIndex(BeerTableMetadata.IS_KICKED_COLUMN);
		int isKicked = beer.getInt(kickedColIndex);
		return (isKicked != 0);
	}
	
	private String getPackaging(Cursor beer) {
		int packagingColIndex = beer.getColumnIndex(BeerTableMetadata.PACKAGING_COLUMN);
		String packaging = beer.getString(packagingColIndex);
		return packaging;
	}

}

