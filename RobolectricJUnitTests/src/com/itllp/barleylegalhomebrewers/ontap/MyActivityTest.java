package com.itllp.barleylegalhomebrewers.ontap;

import com.itllp.barleylegalhomebrewers.ontap.BeerDetailActivity;
import com.itllp.barleylegalhomebrewers.ontap.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {
    @Test
    public void shouldHaveHappySmiles() throws Exception {
        String hello = new MyActivity().getResources().getString(R.string.hello);
        assertThat(hello, equalTo("Hello World, MyActivity!"));
    }

}
