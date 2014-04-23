package com.itllp.barleylegalhomebrewers.ontap;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//TODO Is this class needed or can the adapter be replaced by updateFields() in the fragment?
public class BeerDetailAdapter extends SimpleCursorAdapter {

	public BeerDetailAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
	}

	
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        view = super.getView(position, convertView, parent);
        Cursor cursor = (Cursor)getItem(position);
        Context context = convertView.getContext();
        
		// TODO add tests for these fields
		TextView statusView = (TextView)convertView.findViewById(R.id.status);
		if (beerQuery.isKicked(cursor)) {
			statusView.setText(R.string.kicked_text);
		} else {
			if (beerQuery.isPouring(cursor)) {
				if (0 < beerQuery.getTapNumber(cursor)) {
					String status = context.getString(R.string.on_tap_number) + " " 
							+ beerQuery.getTapNumber(cursor);
					statusView.setText(status);
				} else {
					statusView.setText(R.string.pouring);
				}
			} else {
				statusView.setText(R.string.on_deck);
			}
		}
		
		TextView beerStyleView = (TextView)convertView.findViewById(R.id.beer_style);
		String styleCode = beerQuery.getStyleCode(cursor);
		String styleName = beerQuery.getStyleName(cursor);
		String style = styleCode;
		if (null != styleCode && null != styleName) {
			style += " - " + styleName;
		}
		beerStyleView.setText(style);
		
		TextView beerStyleOverrideView = (TextView)convertView.findViewById(R.id.beer_style_override);
		String styleOverride = beerQuery.getStyleOverride(cursor);
		if (null != styleOverride && 0 != styleOverride.length()) {
			beerStyleOverrideView.setText(styleOverride);
		} else {
			beerStyleOverrideView.setVisibility(View.GONE);
		}
		
//		TextView fgView = (TextView)findViewById(R.id.fg);
//		fgView.setText(beer.getFinalGravity());
//		
//		TextView abvView = (TextView)findViewById(R.id.abv);
//		abvView.setText(beer.getAlcoholByVolume());
//		
//		TextView ibuView = (TextView)findViewById(R.id.ibu);
//		ibuView.setText(beer.getInternationalBitternessUnits());
//
//		TextView srmView = (TextView)findViewById(R.id.srm);
//		srmView.setText(beer.getStandardReferenceMethod());
//		
//		if (beer.getShowBrewerEmailAddress()) {
//			TextView brewerEmailView = (TextView)findViewById(R.id.brewer_email);
//			brewerEmailView.setText(beer.getBrewerEmailAddress());
//		} else {
//			TextView brewerEmailLabelView = (TextView)findViewById(R.id.brewer_email_label);
//			brewerEmailLabelView.setEnabled(false);
//		}
//	*/

        
        
/*
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
  */      
        return view;
    }

	private BeerCursorUtility beerQuery = new BeerCursorUtility();
}

