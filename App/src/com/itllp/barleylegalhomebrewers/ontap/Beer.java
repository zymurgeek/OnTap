package com.itllp.barleylegalhomebrewers.ontap;

public class Beer {

	private final int id;
	private String name;
	
	public Beer(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String newName) {
		name = newName;
	}

}
