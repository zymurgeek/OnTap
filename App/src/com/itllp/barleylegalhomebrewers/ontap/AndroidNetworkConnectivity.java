package com.itllp.barleylegalhomebrewers.ontap;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

// http://developer.android.com/training/basics/network-ops/connecting.html
public class AndroidNetworkConnectivity implements NetworkConnectivity {

	private NetworkInfo networkInfo;
	
	public AndroidNetworkConnectivity(ConnectivityManager connMgr) {
		
		disableConnectionReuseIfNecessary();
	    networkInfo = connMgr.getActiveNetworkInfo();
	}

	public boolean isConnected() {
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}
		return false;
	}
	
	void disableConnectionReuseIfNecessary() {
	    // HTTP connection reuse which was buggy pre-froyo
	    if (VERSION.SDK_INT < VERSION_CODES.FROYO) {
	        System.setProperty("http.keepAlive", "false");
	    }
	}

}

