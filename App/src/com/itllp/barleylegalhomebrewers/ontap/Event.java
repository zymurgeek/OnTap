package com.itllp.barleylegalhomebrewers.ontap;

import java.util.Date;

public class Event {
	private int id;
	private String name;
	private Date date;
	
	public Event(int id) {
		this.id = id;
		name = "";
		date = new Date(0);
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
