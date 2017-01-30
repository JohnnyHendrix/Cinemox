package com.example.android.popularmovies.utilities;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility functions to handle Movie JSON data.
 */
public final class MovieDBJsonUtilities {
    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing movie attributes.
     *
     * @param movieJsonStr JSON response from server
     *
     * @return Array of Strings describing movie data represented in JSON format.
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static String[] getMovieDataStringsFromJson(String movieJsonStr)
            throws JSONException {
        /* Movie information. Each movie info is an element of the "results" array */
        final String MDB_RESULTS = "results";

        /*converting the String object into a JSON object */
        JSONObject movieJson = new JSONObject(movieJsonStr);
        /*pick the result list*/
        JSONArray movieArray = movieJson.getJSONArray(MDB_RESULTS);

        String[] jsonMovieString = new String[movieArray.length()];

        /*taking all elements from JSONArray object converting them and add them to the String array */
        for (int i = 0; i < movieArray.length(); i++) {
            JSONObject movieObject = movieArray.getJSONObject(i);
            String movieString = movieObject.toString();
            jsonMovieString[i] = movieString;
        }
        return jsonMovieString;
    }
}