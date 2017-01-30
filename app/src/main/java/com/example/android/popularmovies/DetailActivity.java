package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.utilities.NetworkUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * Created by Jean-Marc Hendrikse on 24.01.17.
 * <p>
 * * Licensed under the Apache License, Version 2.0 (the "License");
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
public class DetailActivity extends AppCompatActivity {

    private TextView mMovieDescriptionDisplay;
    private ImageView mMoviePoster;
    private TextView mMovieTitle;
    private String mDescription;
    private TextView mReleaseDate;
    private TextView mUserRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        mMovieDescriptionDisplay = (TextView) findViewById(R.id.tv_display_description);
        mMoviePoster = (ImageView) findViewById(R.id.iv_bg_poster);
        mMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        mReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mUserRating = (TextView) findViewById(R.id.tv_user_rating);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                Gson gson = new Gson();
                String jsonString = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                Movie movie = gson.fromJson(jsonString, Movie.class);
                mDescription = movie.getDescription();
                movie.setPosterUrl(NetworkUtils.getBasicPosterUrl(), 185, movie.getPoster_path());
                Picasso.with(this).load(movie.getPosterUrl()).into(mMoviePoster);
                setTitle(movie.getTitle());
                mMovieTitle.setText(movie.getTitle());
                mReleaseDate.setText(movie.getReleaseDate());
                mUserRating.setText("User Vote: " + movie.getVoteAverage());
                mMovieDescriptionDisplay.setText(mDescription);
            }
        }
    }
}
