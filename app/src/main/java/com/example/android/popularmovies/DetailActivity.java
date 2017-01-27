package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.utilities.NetworkUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView mMovieDescriptionDisplay;
    private ImageView mBackgroundPoster;
    private TextView mMovieTitle;
    private String mDescription;
    private TextView mReleaseDate;
    private TextView mUserRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        mMovieDescriptionDisplay = (TextView) findViewById(R.id.tv_display_description);
        mBackgroundPoster = (ImageView) findViewById(R.id.iv_bg_poster);
        mMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        mReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mUserRating = (TextView) findViewById(R.id.tv_user_rating);

        Intent intentThatStartedThisActivity = getIntent();

        // COMPLETED (2) Display the weather forecast that was passed from MainActivity
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                Gson gson = new Gson();
                String jsonString = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                Movie movie = gson.fromJson(jsonString, Movie.class);
                mDescription = movie.getDescription();
                movie.setPosterUrl(NetworkUtils.getBasicPosterUrl(), 185, movie.getPoster_path());
                Picasso.with(this).load(movie.getPosterUrl()).into(mBackgroundPoster);
                setTitle(movie.getTitle());
                mMovieTitle.setText(movie.getTitle());
                mReleaseDate.setText(movie.getReleaseDate());
                mUserRating.setText("user vote: " + movie.getVoteAverage());
                mMovieDescriptionDisplay.setText(mDescription);

            }
        }
    }
}
