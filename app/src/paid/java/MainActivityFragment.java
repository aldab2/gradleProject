import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.AsyncEvent;
import com.udacity.gradle.builditbigger.BuildConfig;
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

    public void viewProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }
    public  void hideProgressBar(){
        mProgressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressBar = root.findViewById(R.id.pb_loading_indicator);
        mButton = root.findViewById(R.id.joke_btn);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
