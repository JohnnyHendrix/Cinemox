package com.example.android.popularmovies;

import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * Created by Jean-Marc Hendrikse on 24.01.17.
 *
 * * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Movie {

    private String title;
    private String overview;
    private String poster_path;
    private String posterUrl;
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
    private String releaseYear;
    private String releaseMonth;
    private String releaseDay;



    public void setTitle(String title) {
        this.title = title;
    }


    public void setDescription(String overview) {
        this.overview = overview;
    }

    public void setPosterPath(String poster_path) {
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
        String [] date = release_date.split("-");
        if (date.length == 3) {
            releaseYear = date[0];
            releaseMonth = date[1];
            releaseDay = date [2];
        }

        switch (releaseMonth) {
            case "1":
                releaseMonth = "Jan";
                break;
            case "2":
                releaseMonth = "Feb";
                break;
            case "3":
                releaseMonth = "Mar";
                break;
            case "4":
                releaseMonth = "Apr";
                break;
            case "5":
                releaseMonth = "May";
                break;
            case "6":
                releaseMonth = "Jun";
                break;
            case "7":
                releaseMonth = "Jul";
                break;
            case "8":
                releaseMonth = "Aug";
                break;
            case "9":
                releaseMonth = "Sep";
                break;
            case "10":
                releaseMonth = "Oct";
                break;
            case "11":
                releaseMonth = "Nov";
                break;
            case "12":
                releaseMonth = "Dec";
                break;
            default:
        }


        this.release_date = releaseMonth + " " + releaseYear;
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

    public String getPoster_path() {
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

    public void setPosterUrl(String basicUrl, int size, String path) {
        switch (size) {
            case 92:
                break;
            case 154:
                break;
            case 185:
                break;
            case 342:
                break;
            case 500:
                break;
            case 780:
                break;
            default:
                size = 92;
        }
        posterUrl = basicUrl + "w" + size + path;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}
