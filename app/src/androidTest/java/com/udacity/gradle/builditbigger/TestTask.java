package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.ExecutionException;

/**
 * Created by lsitec101.macedo on 22/11/17.
 */

public class TestTask extends AndroidTestCase {

    private final String TAG = TestTask.class.getSimpleName();

    //Test About the empty joke
    public void testJokeIsNotEmpty() {
        JokeLoader jokeLoader = new JokeLoader(getContext());
        jokeLoader.testRun();
        jokeLoader.execute();
        try {
            String joke = jokeLoader.get();
            assertNotNull(joke);
            assertTrue(joke.length() > 0);
        } catch (ExecutionException e) {
            Log.e(TAG, "testManyJokes: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "testManyJokes: " + e.getMessage());
        }

    }
}
