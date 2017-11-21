package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

import udacity.gradle.builditbigger.JokeActivity;

/**
 * Created by lsitec101.macedo on 21/11/17.
 */

public class JokeLoader implements LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = JokeLoader.class.getSimpleName();
    Context mContext;
    String mTaskData;
    JokeApi mJokeApi = null;

    public JokeLoader(Context context) {
        mContext = context;
        mTaskData = null;
        mJokeApi = null;
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<String>(mContext) {


            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    deliverResult(mTaskData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public String loadInBackground() {
                try {
                    JokeApi.Builder builderJokeAPI = new JokeApi.Builder(
                            AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(),
                            null
                    ).setRootUrl("http://10.0.2.2:8080/_ah/api/")
                            .setGoogleClientRequestInitializer(
                                    new GoogleClientRequestInitializer() {
                                        @Override
                                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                            request.setDisableGZipContent(true);
                                        }
                                    }
                            );
                    mJokeApi = builderJokeAPI.build();
                    mTaskData = mJokeApi.tellOneJoke().execute().getData();
                    return mTaskData;

                } catch (Exception e) {
                    Log.e(TAG, " Failed to asynchrously load data");
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(String data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        JokeActivity jokeActivity = new JokeActivity();
        jokeActivity.putJokeinIntent(mContext, data);
        Intent intent = jokeActivity.getIntent();
        mContext.startActivity(intent);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        mTaskData = null;
    }
}
