package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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

public class JokeLoader extends AsyncTask<Void, Void, String> {

    private static final String JOKE = "JOKE";
    private static JokeApi jokeApiService = null;
    private Context mContext;

    private boolean isTest = false;

    public JokeLoader(Context context) {
        mContext = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (jokeApiService == null) {

            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            jokeApiService = builder.build();
        }

        try {
            return jokeApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public void testRun() {
        isTest = true;
    }

    @Override
    protected void onPostExecute(String joke) {
        if (isTest == false) {
            if (joke != null) {
                Intent viewJokeIntent = new Intent(mContext, JokeActivity.class);
                viewJokeIntent.putExtra(JokeActivity.getFinalStringJoke(), joke);
                System.out.println("ERRO " + joke);
                mContext.startActivity(viewJokeIntent);
            } else {
                Toast.makeText(mContext, "Error!!!!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

}
