package com.itllp.barleylegalhomebrewers.ontap;

import java.util.ArrayList;
import java.util.List;

public abstract class BeerDatabase {
	protected List<Beer> beerList = new ArrayList<Beer>();
	private static BeerDatabase instance = null;

//	public static final String ID = "ID";
//	public static final String BEER_STYLE_CODE = "BeerStyle";
//	public static final String BEER_STYLE_NAME = "BeerStyleName";
//	public static final String BEER_NAME = "BeerName";

	protected static void setInstance(BeerDatabase newInstance) {
		if (null != newInstance && null != instance) {
			throw new DatabaseAlreadyInstantiatedException();
		}
		instance = newInstance;
	}

	public static BeerDatabase getInstance() {
		return instance;
	}

	public void addOrUpdateBeer(Beer beer) {}

	public void clearBeerList() {
		beerList.clear();
	}
	
	public boolean containsId(int id) {
		return (getBeer(id) != null);
	}

	public void deleteId(int id) {
		Beer b = getBeer(id);
		if (null != b) {
			beerList.remove(b);
		}
	}

	public Beer getBeer(int id) {
		for (Beer b : beerList) {
			if (b.getId() == id) {
				return b;
			}
		}
		return null;
	}

	public List<Beer> getBeerList() {
		return beerList;
	}

	public boolean isEmpty() {
		return beerList.isEmpty();
	}

	public int size() {
		return beerList.size();
	}

}
