package com.itllp.beerfestival;

public class AlwaysUpNetworkConnectivity implements NetworkConnectivity {

	@Override
	public boolean isConnected() {
		return true;
	}

}
