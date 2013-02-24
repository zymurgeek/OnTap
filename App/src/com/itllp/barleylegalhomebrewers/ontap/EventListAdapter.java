package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventListAdapter extends ArrayAdapter<Event> {
    private final LayoutInflater mInflater;

	public EventListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

    public void setData(List<Event> data) {
        clear();
        if (data != null) {
            addAll(data);
        }
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.event_list_item, parent, false);
        } else {
            view = convertView;
        }

        Event item = getItem(position);
        String idString = String.valueOf(item.getId());
        String nameString = item.getName();
        
        TextView idView = (TextView)view.findViewById(R.id.id);
        idView.setText(idString);

        TextView nameView = (TextView)view.findViewById(R.id.name); 
        nameView.setText(nameString);

        return view;
    }
}

