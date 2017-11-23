package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by lsitec101.macedo on 22/11/17.
 */

public class TestTask extends AndroidTestCase {
    private final static String TAG_NAME = TestTask.class.getSimpleName();

    public void testJokeIsNotEmpty(){
        JokeLoader jokeLoader = new JokeLoader(getContext());
            jokeLoader.testRun();
        jokeLoader.execute();
        try
        {
            String joke = jokeLoader.get();
            assertNotNull(joke);
            assertTrue(joke.length() > 0);
        }
        catch (ExecutionException e) {
            Log.e(TAG, "testManyJokes: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "testManyJokes: " + e.getMessage());
        }

    }
}
