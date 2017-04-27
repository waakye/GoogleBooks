package com.waakye.gbl;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lesterlie on 4/26/17.
 */

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();

    /** Create a private constructor because no {@link QueryUtils} object should be created */
    private QueryUtils() {}

    /**
     * Query the Google Books dataset and return a list of {@link BookListing} objects
     */
    public static List<BookListing> fetchBookListingData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link BookListing}s
        List<BookListing> bookListings = extractItemFromJson(jsonResponse);

        // Return the list of {@link BookListing}s
        return bookListings;
    }

    /**
     * Returns new URL object from the given string URL
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Google Books JSON results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the whole JSON response from
     * the server
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link BookListing} objects that has been built up from parsing the given
     * JSON response
     */
    private static List<BookListing> extractItemFromJson(String bookListingJson) {
        // If the JSON string is empty or null, then return early
        if (TextUtils.isEmpty(bookListingJson)) {
            return null;
        }

        // Global string variables to determine the authors of a book
        String authors = null;
        String totalAuthors1 = "";
        String totalAuthors2 = "";
        String totalAuthors3 = "";
        String totalAuthors4 = "";

        // Create an empty ArrayList that we can start adding booklistings to
        ArrayList<BookListing> bookListings = new ArrayList<>();

        // Try to parse SAMPLE_JSON_RESPONSE.  If there's a problem with the way the JSON is
        // formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash and print the error message to the logs
        try {

            JSONObject baseJsonResponse = new JSONObject(bookListingJson);

            JSONArray booksArray = baseJsonResponse.getJSONArray("items");

            for (int i = 0; i < booksArray.length(); i++) {

                // Get book JSONObject at position i
                JSONObject currentBook = booksArray.getJSONObject(i);

                // Get "volumeInfo" of JSONObject
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                // Get book title
                String bookTitle = volumeInfo.getString("title");

                // Get book author
                if (volumeInfo.has("authors")) {
                    JSONArray bookAuthors = volumeInfo.getJSONArray("authors");
                    if (volumeInfo.optJSONArray("authors") != null) {

                        authors = bookAuthors.toString();

                        totalAuthors1 = authors.replace("[", "");
                        totalAuthors2 = totalAuthors1.replace("]", "");
                        totalAuthors3 = totalAuthors2.replace("\"", "");
                        totalAuthors4 = totalAuthors3.replace(",", ", ");

                        Log.e(LOG_TAG, "the authors are " + totalAuthors4);

                    }
                }
                // Get book description
                String bookDescription = volumeInfo.optString("description", "No description found.");

                // Get book page count
                String bookPageCount = volumeInfo.optString("pageCount", "Page count not available");

                BookListing bookListing = new BookListing(bookTitle, totalAuthors4, bookDescription, bookPageCount);

                bookListings.add(bookListing);
            }
        } catch (JSONException e) {

            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash.  Print a log message with the
            // message from the execution
            Log.e("Query Utils", "Problem parsing the booklisting JSON results");
        }

        // Return the list of booklistings
        return bookListings;

    }
}
