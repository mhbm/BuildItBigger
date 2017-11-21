package com.udacity.gradle.builditbigger;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

/**
 * Created by lsitec101.macedo on 21/11/17.
 */

public class JokeLoader implements LoaderManager.LoaderCallbacks<Cursor>  {

    private static final int JOKE_LOADER_ID = 1;
    private static final String TAG = JokeLoader.class.getSimpleName();


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle loaderArgs) {

        return new AsyncTaskLoader<Cursor>(this) {

            // Initialize a Cursor, this will hold all the task data
            Cursor mTaskData = null;

            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    // Delivers any previously loaded data immediately
                    deliverResult(mTaskData);
                } else {
                    // Force a new load
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                return null;
            }
        }
    }
}
