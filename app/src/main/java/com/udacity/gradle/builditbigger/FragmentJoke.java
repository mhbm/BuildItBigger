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

import java.io.IOException;

/**
 * Created by lsitec101.macedo on 21/11/17.
 */

public abstract class FragmentJoke extends Fragment {

    Context mContext;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public abstract void showJoke() throws IOException;

    protected void getOneJoke() throws IOException {
        new JokeLoader(mContext).execute(getActivity());
    }

}
