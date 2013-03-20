package com.itllp.barleylegalhomebrewers.ontap;

import java.util.List;

import com.itllp.barleylegalhomebrewers.ontap.dateconverter.JavaDateToHumanReadableDateConverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventListAdapter extends ArrayAdapter<Event> {
    private final LayoutInflater mInflater;
    private JavaDateToHumanReadableDateConverter dateConverter;
    
	public EventListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dateConverter = new JavaDateToHumanReadableDateConverter();
	}

    public void setData(List<Event> data) {
        clear();
        if (data != null) {
        	for (Event event : data) {
        		add(event);
        	}
        }
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.event_list_item, parent, false);
        } else {
            view = convertView;
        }

        Event event = getItem(position);
        String idString = String.valueOf(event.getId());
        String nameString = event.getName();
        
        TextView idView = (TextView)view.findViewById(R.id.id);
        idView.setText(idString);

        TextView nameView = (TextView)view.findViewById(R.id.name); 
        nameView.setText(nameString);
        
        TextView dateView = (TextView)view.findViewById(R.id.date);
        String dateString = dateConverter.getString(event.getDate());
        dateView.setText(dateString);

        return view;
    }
}

