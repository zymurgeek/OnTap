package com.itllp.barleylegalhomebrewers.ontap;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
		
		TextView brewerEmailView = (TextView)convertView.findViewById(R.id.brewer_email);
		Button emailBrewerButton = (Button)convertView.findViewById(R.id.email_brewer);
		if (beerQuery.isEmailShown(cursor)) {
			brewerEmailView.setText(beerQuery.getBrewerEmail(cursor));
			emailBrewerButton.setEnabled(true);
		} else {
			brewerEmailView.setText("");
			emailBrewerButton.setEnabled(false);
		}
		
        return view;
    }

	private BeerCursorUtility beerQuery = new BeerCursorUtility();
}

