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
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String STATIC_MOVIEDB_URL =
            "https://api.themoviedb.org/3/movie/popular";

    private final static String BASIC_POSTER_URL = "https://image.tmdb.org/t/p/";

    private final static String SIZE = "w92";

    private static final String API_KEY = "e8b5e0e10f25bd1751dfca9d25415ef0";

    private static final String MOVIEDB_BASE_URL = STATIC_MOVIEDB_URL;

    final static String API_KEY_PARAM = "api_key";

    final static String LANG_PARAM = "language";

    final static String PAGE_PARAM = "page";

    final static String REGION_PARAM = "region";

    private static final String numPage = "1";

    private static final String language = "en-US";

    /**
     * Builds the URL used to talk to the weather server using a location. This location is based
     * on the query capabilities of the weather provider that we are using.
     *
     * @param locationQuery The location that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String locationQuery) {

        Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .appendQueryParameter(PAGE_PARAM, numPage)
                .appendQueryParameter(LANG_PARAM, language)
                .appendQueryParameter(REGION_PARAM, locationQuery)
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

    public static String getBasicPosterUrl() {
        return BASIC_POSTER_URL;
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