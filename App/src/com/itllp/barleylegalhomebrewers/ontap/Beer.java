package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.util.EqualsUtil;

public class Beer {

	private final int id;
	private String beerName;
	private String brewerFirstName;
	private String brewerLastName;
	private String styleCode;
	private String styleName;
	private String styleOverride;
	
	public Beer(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setBeerName(String newName) {
		beerName = newName;
	}

	public String getBeerName() {
		return beerName;
	}

	@Override
	public boolean equals(Object aThat) {
		if ( this == aThat ) return true;
		if ( !(aThat instanceof Beer) ) return false;
		Beer that = (Beer)aThat;
		return EqualsUtil.areEqual(this.id, that.id) 
			&& EqualsUtil.areEqual(this.beerName, that.beerName)
			&& EqualsUtil.areEqual(this.brewerFirstName, that.brewerFirstName)
			&& EqualsUtil.areEqual(this.brewerLastName, that.brewerLastName)
			&& EqualsUtil.areEqual(this.styleCode, that.styleCode)
			&& EqualsUtil.areEqual(this.styleName, that.styleName)
			&& EqualsUtil.areEqual(this.styleOverride, that.styleOverride)
			;
	}

	@Override 
	public int hashCode() {
		return id;
	  }

	public String toString() {
		return "id: " + id
				+ " beerName: \"" + beerName + "\""
				+ " brewerFirstName: " + brewerFirstName
				+ " brewerLastName: " + brewerLastName
				+ " styleCode: " + styleCode
				+ " styleName: " + styleName
				+ " styleOverride: " + styleOverride
				;
	}

	public void setBrewerFirstName(String name) {
		brewerFirstName = name;
	}
	
	public void setBrewerLastName(String name) {
		brewerLastName = name;
	}

	public String getBrewerName() {
		return brewerFirstName + " " + brewerLastName;
	}

	public String getBrewerFirstName() {
		return brewerFirstName;
	}

	public String getBrewerLastName() {
		return brewerLastName;
	}

	public void setStyleCode(String code) {
		styleCode = code;
	}

	public String getStyleCode() {
		return styleCode;
	}

	public void setStyleName(String name) {
		styleName = name;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleOverride(String override) {
		styleOverride = override;
	}

	public String getStyleOverride() {
		return styleOverride;
	}
	
}
