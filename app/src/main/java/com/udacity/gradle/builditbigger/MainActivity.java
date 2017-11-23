package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view)
    {
        new JokeLoader(this).execute();
    }

//    public static class FetchDisplayJokeLoader extends JokeLoader {
//        private Context mContext;
//
//        public FetchDisplayJokeLoader(Context context) {
//            super(context);
//            mContext = context;
//        }
//
//        @Override
//        protected void onPostExecute(@Nullable String joke) {
//            if (joke != null) {
//                Intent viewJokeIntent = new Intent(mContext, JokeActivity.class);
//                viewJokeIntent.putExtra(JokeActivity.getFinalStringJoke(), joke);
//                mContext.startActivity(viewJokeIntent);
//            } else {
//                Toast.makeText(mContext, "Error!!!!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }
//
//    }
}
