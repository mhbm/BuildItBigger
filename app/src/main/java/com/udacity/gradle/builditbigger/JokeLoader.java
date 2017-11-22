package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

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

public class JokeLoader extends AsyncTask<Context, Void, String> {

    private static JokeApi jokeApiService = null;

    private Context context;

    public JokeLoader(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Context... params) {
        if (jokeApiService == null) {
            final JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            jokeApiService = builder.build();
        }

        context = params[0];

        try {
            String teste = jokeApiService.tellOneJoke().execute().getData();
            System.out.println("asuhuhsahuesauhase = " + teste);
            return jokeApiService.tellOneJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        JokeActivity jokeActivity = new JokeActivity();
        jokeActivity.putJokeinIntent(context, joke);
        context.startActivity(jokeActivity.getIntent());
    }
}
