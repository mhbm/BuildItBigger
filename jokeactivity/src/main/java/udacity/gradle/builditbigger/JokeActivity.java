package udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by lsitec101.macedo on 20/11/17.
 */

public class JokeActivity extends AppCompatActivity {

    private static final String JOKE = "JOKE";
    TextView mTxtJoke;

    public static String getFinalStringJoke() {
        return JOKE;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getStringExtra(JOKE);
        mTxtJoke = (TextView) findViewById(R.id.tv_joke);

        if (mTxtJoke != null) {
            mTxtJoke.setText(joke);
        }
    }
}
