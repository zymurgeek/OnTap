package com.itllp.beerfestival.test;

import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;

public class AlwaysUpNetworkConnectivity implements NetworkConnectivity {

	@Override
	public boolean isConnected() {
		return true;
	}

}
