package com.example.android.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mDisplayMovieTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDisplayMovieTextView = (TextView) findViewById(R.id.tv_movie_data);
    }


    /**
     * This class is necessary in order to perform network requests.
     */
    public class FetchMovieDBTasks extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }

            String language = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(language);
            try {
                String jasonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);

                //TODO transform json format data into string array format with moviedbjsonutils class
                String[] simpleJsonMovieData = MovieDBJsonUtils();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
