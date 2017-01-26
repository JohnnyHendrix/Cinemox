package com.example.android.popularmovies;

import java.util.ArrayList;
import com.google.gson.Gson;

/**
 * Created by jean-marchendrikse on 25.01.17.
 */

public class Movie {

    private String title;
    private String overview;
    private String poster_path;
    private boolean adult;
    private String release_date;
    private int[] genre_ids;
    private int id;
    private String original_title;
    private String original_language;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;


    public void setTitle(String title) {
        this.title = title;
    }


    public void setDescription(String overview) {
        this.overview = overview;
    }

    public void setPosterUrl(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setBackdropPath(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setGenreIdList(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOriginalTitle(String original_title) {
        this.original_title = original_title;
    }

    public void setOrigLanguage(String original_language) {
        this.original_language = original_language;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setVoteAverage(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setVoteCount(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getDescription() {
        return overview;
    }

    public String getPosterUrl() {
        return poster_path;
    }

    public String getTitle() {
        return title;
    }

    public int[] getGenreIdList() {
        return genre_ids;
    }

    public double getPopularity() {
        return popularity;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public int getId() {
        return id;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getOrigLanguage() {
        return original_language;
    }

    public String getOverview() {
        return overview;
    }

    public boolean isAdult() {
        return adult;
    }

    public boolean isVideo() {
        return video;
    }
}
