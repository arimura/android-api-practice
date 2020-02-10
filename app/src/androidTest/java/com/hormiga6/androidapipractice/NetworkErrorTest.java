package com.hormiga6.androidapipractice;

import androidx.test.filters.SmallTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class NetworkErrorTest {
    private static final String TAG = "NetworkErrorTest";

    @Test
    @Ignore
    public void testNoNetoworkAvailableOnDevice() throws MalformedURLException {
        //This test should be executed on device without network
        try {
            HttpURLConnection con = (HttpURLConnection) (new URL("http://google.com")).openConnection();
            con.getResponseCode();
        } catch (IOException e) {
            assertThat(e, is(instanceOf(UnknownHostException.class)));
        }
    }
}
