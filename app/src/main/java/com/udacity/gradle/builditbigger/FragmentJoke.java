package com.udacity.gradle.builditbigger;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lsitec101.macedo on 21/11/17.
 */

public class FragmentJoke extends Fragment {

    Context mContext;

    private static final int JOKE_LOADER_ID = 1;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    protected void getOneJoke() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getLoaderManager().initLoader(JOKE_LOADER_ID,null,null);
        }
    }

}
