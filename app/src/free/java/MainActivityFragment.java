import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.AsyncEvent;

import com.udacity.gradle.builditbigger.R;

import org.greenrobot.eventbus.EventBus;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    ProgressBar mProgressBar;
    Button mButton;

    InterstitialAd mInterstitialAd;

    public void viewProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }
    public  void hideProgressBar(){
        mProgressBar.setVisibility(View.INVISIBLE);

    }
    Boolean firstLoad = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressBar = root.findViewById(R.id.pb_loading_indicator);
        mButton = root.findViewById(R.id.joke_btn);
        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();


            MobileAds.initialize(getContext(),
                    getString(R.string.interstial_add));
            mInterstitialAd = new InterstitialAd(getContext());
            mInterstitialAd.setAdUnitId(getString(R.string.interstial_add));

            AdView mAdView = (AdView) root.findViewById(R.id.adView);

            mInterstitialAd.loadAd(adRequest);
            mInterstitialAd.setAdListener(new AdListener(){
                public void onAdLoaded(){
                    Log.e("XXXXX", "onAdLoaded: Loaded! " );
                    if (firstLoad) {
                         mInterstitialAd.show();
                    }
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    firstLoad = false;
                    mInterstitialAd.loadAd(adRequest);

                }

            });

            mAdView.loadAd(adRequest);


        //Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    Log.e("XXXXXXX", "The interstitial is beeing showed");

                    mInterstitialAd.show();
                } else {
                    Log.e("XXXXXXX", "The interstitial wasn't loaded yet.");
                }
                viewProgressBar();
                EventBus.getDefault().post(new AsyncEvent(view));
            }
        });
        return root;
    }


    @Override
    public void onPause() {
        hideProgressBar();
        super.onPause();
    }


}
