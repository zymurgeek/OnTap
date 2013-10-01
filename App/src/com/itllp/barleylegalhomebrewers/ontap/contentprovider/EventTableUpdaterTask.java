package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


public class EventTableUpdaterTask implements Runnable {

	
	public EventTableUpdaterTask(TableUpdater eventTableUpdater) {
		updater = eventTableUpdater;
	}


	@Override
	public void run() {
		updater.update();
	}
	
	
	private TableUpdater updater;
}
