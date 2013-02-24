package com.itllp.barleylegalhomebrewers.ontap;

import java.util.Date;

public class Event {
	private int id;
	private String name;
	private Date date;
	
	public Event(int id) {
		this.id = id;
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
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}
}
