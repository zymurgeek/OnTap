package com.itllp.barleylegalhomebrewers.ontap;

public class BeerDatabaseImpl extends BeerDatabase {

	public void addOrUpdateBeer(Beer beer) {
		if (null == beer) {
			return;
		}
		if (containsId(beer.getId()))
		{
			deleteId(beer.getId());
		}
		beerList.add(beer);
	}

	public static void create() {
		setInstance(new BeerDatabaseImpl());
	}

}
