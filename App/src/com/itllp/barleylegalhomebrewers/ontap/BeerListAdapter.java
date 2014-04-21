package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

import com.itllp.barleylegalhomebrewers.ontap.contentproviderinterface.BeerTableMetadata;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BeerListAdapter extends SimpleCursorAdapter {

	public BeerListAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
      mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

    private final LayoutInflater mInflater;
    
//    public void setData(List<Beer> data) {
//        clear();
//        if (data != null) {
//        	for (Beer beer : data) {
//        		add(beer);
//        	}
//        }
//    }
//
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        view = super.getView(position, convertView, parent);

        Cursor cursor = (Cursor)getItem(position);
        int thisSectionTextId = getSectionTextId(cursor);

        TextView locationView = (TextView)view.findViewById(R.id.beer_location);
        String location = "";
        if (!isKicked(cursor)) {
            int tapNumber = getTapNumber(cursor);
            if (tapNumber > 0) {
            	location = "Tap #" + tapNumber; 
            } else {
            	if (tapNumber < 0) {
            		location = "Bottle";
            	}
            }
        }
        //TODO Mark on-deck and kicked beer locations as keg or bottle
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
}

