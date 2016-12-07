package com.hormiga6.androidapipractice;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.hormiga6.androidapipractice.Parcel.FooParcelable;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ParcelableTest {
    @Test
    public void testParcelable() {
        FooParcelable foo = new FooParcelable(1, 2, "name", false);

        Bundle bundle = new Bundle();
        bundle.putParcelable("foo",foo);
        Parcelable parceledFoo = bundle.getParcelable("foo");
        assertThat(parceledFoo.toString(), is(foo.toString()));
    }
}
