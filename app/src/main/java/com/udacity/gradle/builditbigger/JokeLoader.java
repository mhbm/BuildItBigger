package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

import udacity.gradle.builditbigger.JokeActivity;

/**
 * Created by lsitec101.macedo on 21/11/17.
 */

public class JokeLoader extends AsyncTask<Context, Void, String> {

    private static JokeApi jokeApiService = null;

    private static final String JOKE = "JOKE";

    private Context mContext;

    public JokeLoader(Context context) {
        mContext = context;
    }

    @Override
    protected String doInBackground(Context... params) {
        if (jokeApiService == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://udacity-jokebackend.appspot.com/_ah/api/");
            jokeApiService = builder.build();
        }

        try {
            return jokeApiService.tellOneJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        if (joke != null) {
            Intent viewJokeIntent = new Intent(mContext, JokeActivity.class);
            viewJokeIntent.putExtra(JokeActivity.getFinalStringJoke(), joke);
            mContext.startActivity(viewJokeIntent);
        } else {
            Toast.makeText(mContext, "Error!!!!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
