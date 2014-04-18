package com.itllp.barleylegalhomebrewers.ontap.contentprovider;


class TableUpdaterTask implements Runnable {

	
	public TableUpdaterTask(TableUpdater eventTableUpdater) {
		updater = eventTableUpdater;
	}


	@Override
	public void run() {
		updater.update();
	}
	
	
	private TableUpdater updater;
}
