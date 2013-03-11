package com.itllp.barleylegalhomebrewers.ontap;

import java.util.Date;

import com.itllp.barleylegalhomebrewers.ontap.util.EqualsUtil;

public class Event {
	private int id;
	private String name;
	private Date date;
	
	public Event(int id) {
		this.id = id;
		name = "";
		date = new Date(0);
	}

	@Override
	public boolean equals(Object aThat) {
		if ( this == aThat ) return true;
		if ( !(aThat instanceof Event) ) return false;
		Event that = (Event)aThat;
		return
			EqualsUtil.areEqual(this.id, that.id) &&
			EqualsUtil.areEqual(this.name, that.name) &&
		    EqualsUtil.areEqual(this.date, that.date);
	}

	@Override 
	public int hashCode() {
		return id;
	  }

	public Date getDate() {
		return date;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setDate(Date date) {
		if (null != date) {
			this.date = date;
		}
	}

	public void setName(String name) {
		if (null != name) {
			this.name = name;
		}
	}
	
	public String toString() {
		return "id: " + id +
				" name: \"" + name + "\"" +
				" date: " + date;
	}
}
