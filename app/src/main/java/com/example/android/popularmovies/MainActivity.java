package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.utilities.MovieDBJsonUtilities;
import com.example.android.popularmovies.utilities.NetworkUtils;
import com.google.gson.Gson;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Jean-Marc Hendrikse on 25.01.17.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private TextView mError;
    private ProgressBar mLoadingIndicator;
    private String sortedBy = "popular";

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movie);
        mError = (TextView) findViewById(R.id.tv_error_message);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sortedBy = mSharedPreferences.getString(getString(R.string.sp_key_order), "");

        if (sortedBy.equals("popular")) {
            setTitle("Most popular movies");
        } else {
            setTitle("Highest-rated movies");
        }
        loadMovieData();
    }

    public void initView(ArrayList<Movie> movieArrayList) {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mMovieAdapter = new MovieAdapter(getApplicationContext(), movieArrayList, this);
        mRecyclerView.setAdapter(mMovieAdapter);
    }

    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);

        Gson gson = new Gson();
        String jsonString = gson.toJson(movie);

        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, jsonString);
        startActivity(intentToStartDetailActivity);
    }

    private void showMovieDataView() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mError.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        mError.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    private void loadMovieData() {
        showMovieDataView();
        String region = "USA";
        new FetchMovieDBTasks().execute(region);
    }

    /**
     * This class is necessary in order to perform network requests.
     */
    public class FetchMovieDBTasks extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }

            String region = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(region, sortedBy);
            try {
                String jasonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
                String[] jsonMovieList = MovieDBJsonUtilities
                        .getMovieDataStringsFromJson(jasonMovieResponse);
                return jsonMovieList;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] jsonMovieList) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (jsonMovieList != null) {
                showMovieDataView();
                ArrayList<Movie> movies = new ArrayList<Movie>();

                for (String jsonMovie : jsonMovieList) {
                    Gson gson = new Gson();
                    Movie movie = gson.fromJson(jsonMovie, Movie.class);
                    movie.setPosterUrl(NetworkUtils.getBasicPosterUrl(), 185, movie.getPoster_path());
                    movies.add(movie);
                }
                initView(movies);
            } else {
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_refresh) {
            loadMovieData();
            return true;
        }
        if (itemId == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
