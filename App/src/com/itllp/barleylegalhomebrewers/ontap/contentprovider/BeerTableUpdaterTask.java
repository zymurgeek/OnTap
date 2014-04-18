package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


class BeerTableUpdaterTask implements Runnable {

	
	public BeerTableUpdaterTask(BeerTableUpdater beerTableUpdater, String eventId) {
		updater = beerTableUpdater;
		this.eventId = eventId;
	}


	@Override
	public void run() {
		updater.update(eventId);
	}
	
	
	private BeerTableUpdater updater;
	private String eventId;
}
