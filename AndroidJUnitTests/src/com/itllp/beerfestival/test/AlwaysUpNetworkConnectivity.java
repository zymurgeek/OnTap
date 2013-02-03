package com.itllp.beerfestival.test;

import com.itllp.beerfestival.NetworkConnectivity;

public class AlwaysUpNetworkConnectivity implements NetworkConnectivity {

	@Override
	public boolean isConnected() {
		return true;
	}

}
