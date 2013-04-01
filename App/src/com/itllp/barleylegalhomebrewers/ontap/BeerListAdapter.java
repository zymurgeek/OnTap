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
        String idString = String.valueOf(beer.getId());
        String nameString = beer.getName();
        
        TextView idView = (TextView)view.findViewById(R.id.id);
        idView.setText(idString);

        TextView nameView = (TextView)view.findViewById(R.id.name); 
        nameView.setText(nameString);
        
        return view;
    }
}

