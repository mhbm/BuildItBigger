package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import com.udacity.gradle.builditbigger.back

/**
 * Created by lsitec101.macedo on 21/11/17.
 */

public class JokeLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int JOKE_LOADER_ID = 1;
    private static final String TAG = JokeLoader.class.getSimpleName();
    Context mContext;
    Cursor mTaskData;
    JokeApi jokeApi = null;

    public JokeLoader(Context context) {
        mContext = context;
        mTaskData = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(mContext) {


            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    deliverResult(mTaskData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                try {
                    return getContext().getContentResolver().query(
                            null,
                            null,
                            null,
                            null,
                            null
                    );
                } catch (Exception e) {
                    Log.e(TAG, " Failed to asynchrously load data" );
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Intent intent = JokeView.makeIntent(mContext, data);
        mContext.startActivity(intent);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mTaskData = null;
    }
}
