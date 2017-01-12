package com.hormiga6.androidapipractice;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class RawResourceTest {

    @Test
    public void testReadRaw() {
        InputStream inputStream = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.fuga);
        try {
            assertThat(IOUtils.readLines(inputStream, "UTF-8").get(0), is("fugafuga"));
        } catch (IOException e) {
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
