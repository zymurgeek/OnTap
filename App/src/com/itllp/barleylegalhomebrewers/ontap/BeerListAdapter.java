package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BeerListAdapter extends ArrayAdapter<Beer> {
    private final LayoutInflater mInflater;
    
	public BeerListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

    public void setData(List<Beer> data) {
        clear();
        if (data != null) {
        	for (Beer beer : data) {
        		add(beer);
        	}
        }
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.beer_list_item, parent, false);
        } else {
            view = convertView;
        }

        Beer beer = getItem(position);
        
        TextView idView = (TextView)view.findViewById(R.id.id);
        idView.setText(String.valueOf(beer.getId()));
        
        TextView beerNameView = (TextView)view.findViewById(R.id.beer_name); 
        beerNameView.setText(beer.getBeerName());
        
        TextView brewerNameView = (TextView)view.findViewById(R.id.brewer_name); 
        brewerNameView.setText(beer.getBrewerName());
        
        TextView styleCodeView = (TextView)view.findViewById(R.id.beer_style_code); 
        styleCodeView.setText(beer.getStyleCode());
        
        return view;
    }
}

