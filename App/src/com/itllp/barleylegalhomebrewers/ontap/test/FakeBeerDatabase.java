package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.Beer;
import com.itllp.barleylegalhomebrewers.ontap.BeerDatabase;
import com.itllp.barleylegalhomebrewers.ontap.Event;
import com.itllp.barleylegalhomebrewers.ontap.EventDatabase;

public class FakeBeerDatabase extends BeerDatabase {

	public void addOrUpdateBeer(Beer beer) {
		beerList.add(beer);
	}

	public static void clearInstance() {
		setInstance(null);
	}
	
	public static void create() {
		setInstance(new FakeBeerDatabase());
	}

}
