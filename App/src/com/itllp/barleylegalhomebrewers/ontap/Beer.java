package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.util.EqualsUtil;

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

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object aThat) {
		if ( this == aThat ) return true;
		if ( !(aThat instanceof Beer) ) return false;
		Beer that = (Beer)aThat;
		return
			EqualsUtil.areEqual(this.id, that.id) &&
			EqualsUtil.areEqual(this.name, that.name);
	}

	@Override 
	public int hashCode() {
		return id;
	  }

	public String toString() {
		return "id: " + id +
				" name: \"" + name + "\"" /*+
				" date: " + date*/;
	}
}
