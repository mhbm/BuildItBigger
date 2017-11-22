package com.udacity.builditbigger;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.FragmentJoke;
import com.udacity.gradle.builditbigger.R;

import java.io.IOException;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends FragmentJoke {

    private InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = super.onCreateView(inflater, container, savedInstanceState);
        AdView adView = (AdView) view.findViewById(R.id.ad_view);

        mInterstitialAd = new InterstitialAd(getActivity());

        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                try {
                    getOneJoke();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        //according to the https://developers.google.com/admob/android/interstitial


        requestNewInterstitial();

        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showJoke() throws IOException {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            getOneJoke();
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

}
