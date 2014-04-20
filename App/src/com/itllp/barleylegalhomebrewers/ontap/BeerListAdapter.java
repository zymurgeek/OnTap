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
//        if (convertView == null) {
//            view = mInflater.inflate(R.layout.beer_list_item, parent, false);
//        } else {
//            view = convertView;
//        }

        Cursor itemCursor = (Cursor)getItem(position);
        //itemCursor.getString(cursor.getColumnIndex("my_column_name"))
        
//        Beer beer = getItem(position);
//        
        TextView sectionHeaderView = (TextView)view.findViewById(R.id.section_title);
//        int lastSectionTextId = -1;
//        if (position > 0) {
//        	Beer lastBeer = getItem(position - 1);
//        	if (lastBeer != null) {
//        		lastSectionTextId = getSectionTextId(lastBeer);
//        	}
//        }
        int thisSectionTextId = getSectionTextId(itemCursor);
//        if (thisSectionTextId != lastSectionTextId) {
        	sectionHeaderView.setText(thisSectionTextId);
        	sectionHeaderView.setVisibility(View.VISIBLE);
//        } else {
//        	sectionHeaderView.setVisibility(View.GONE);
//        }
//        
//        TextView idView = (TextView)view.findViewById(R.id.id);
//        idView.setText(String.valueOf(beer.getId()));
//        
//        TextView beerNameView = (TextView)view.findViewById(R.id.beer_name); 
//        beerNameView.setText(beer.getBeerName());
//        
//        TextView brewerNameView = (TextView)view.findViewById(R.id.brewer_name); 
//        brewerNameView.setText(beer.getBrewerName());
//        
//        TextView styleCodeView = (TextView)view.findViewById(R.id.beer_style_code);
//        String styleCode = beer.getStyleCode();
//        styleCodeView.setText(styleCode);
//        
//        TextView styleNameView = (TextView)view.findViewById(R.id.beer_style_name);
//        String styleName = beer.getStyleName();
//        if (null != styleCode && null != styleName) {
//        	styleName = " - " + styleName;
//        }
//        styleNameView.setText(styleName);
//
//        TextView styleOverrideView = (TextView)view.findViewById(R.id.beer_style_override); 
//        String styleOverride = beer.getStyleOverride();
//		if (null != styleOverride && 0 != styleOverride.length()) {
//			styleOverrideView.setText(styleOverride);
//		} else {
//			styleOverrideView.setVisibility(View.GONE);
//		}
        
        return view;
    }

	private int getSectionTextId(Cursor beer) {
		int sectionText = R.string.on_deck;
		int colIndex = beer.getColumnIndex(BeerTableMetadata.IS_KICKED_COLUMN);
		int isKicked = beer.getInt(colIndex);
		if (isKicked != 0) {
			sectionText = R.string.kicked_text;
//		} else if (beer.isPouring()) {
			sectionText = R.string.pouring;
		}
		return sectionText;
	}
}

