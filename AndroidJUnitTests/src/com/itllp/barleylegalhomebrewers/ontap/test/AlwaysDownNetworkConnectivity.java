package com.itllp.barleylegalhomebrewers.ontap.test;

import com.itllp.barleylegalhomebrewers.ontap.NetworkConnectivity;

public class AlwaysDownNetworkConnectivity implements NetworkConnectivity {

	@Override
	public boolean isConnected() {
		return false;
	}

}
