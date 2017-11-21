package udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.jokelibrary.R;

/**
 * Created by lsitec101.macedo on 20/11/17.
 */

public class JokeActivity extends AppCompatActivity {

    private static final String JOKE = "JOKE";
    static Intent mIntent;
    TextView mTxtJoke;

    public static void putJokeinIntent(Context context, String word) {
        mIntent = new Intent(context, JokeActivity.class);
        mIntent.putExtra(JOKE, word);
    }

    public static Intent getIntent() {
        return mIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        mIntent = getIntent();

        if (mIntent != null && mIntent.hasExtra(JOKE)) {
            mTxtJoke = (TextView) findViewById(R.id.tv_joke);
            if (mIntent.getStringExtra(JOKE) != null) {
                mTxtJoke.setText(mIntent.getStringExtra(JOKE));
            } else {
                String none = "";
                mTxtJoke.setText(none);
            }
        }
    }
}
