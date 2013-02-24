package com.itllp.barleylegalhomebrewers.ontap.test;

import android.content.Context;

public class MockContext extends android.test.mock.MockContext {

	@Override
	public Context getApplicationContext() {
		return this;
	}

}
