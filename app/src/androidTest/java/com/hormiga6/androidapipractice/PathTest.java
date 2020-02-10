package com.hormiga6.androidapipractice;

import android.app.Activity;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class PathTest {
    private static final String TAG = "FilePathTest";

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testRead(){
        assertThat(new File("").getAbsoluteFile().toString(), is("/"));
        assertThat(getCurrentActivity().getFilesDir().toString(), is("/data/user/0/com.hormiga6.androidapipractice/files"));
    }

    public static Activity getCurrentActivity() {
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        final Activity[] activities = new Activity[1];
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Collection<Activity> activitiesInStage = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                activities[0] = activitiesInStage.iterator().next();
            }
        });
        return activities[0];
    }
}
