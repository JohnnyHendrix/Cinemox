package com.example.android.popularmovies.utilities;


import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * These utilities will be used to communicate with the movie database api servers.
 * <p>
 * * Created by jean-marchendrikse on 24.01.17.
 */
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String DYNAMIC_MOVIEDB_URL = "";

    private static final String STATIC_MOVIEDB_URL =
            "https://api.themoviedb.org/3/movie/";

    private static final String API_KEY = "e8b5e0e10f25bd1751dfca9d25415ef0";

    private static final String MOVIEDB_BASE_URL = STATIC_MOVIEDB_URL;

    final static String QUERY_PARAM = "api_key";

    final static String LANGUAGE_PARAM = "language";


    /**
     * Builds the URL used to talk to the weather server using a location. This location is based
     * on the query capabilities of the weather provider that we are using.
     *
     * @param languageQuery The location that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String languageQuery) {

        Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, API_KEY)
                .appendQueryParameter(LANGUAGE_PARAM, languageQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * Builds the URL used to talk to the weather server using latitude and longitude of a
     * location.
     *
     * @param lat The latitude of the location
     * @param lon The longitude of the location
     * @return The Url to use to query the weather server.
     */
    public static URL buildUrl(Double lat, Double lon) {
        /** This will be implemented in a future lesson **/
        return null;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}