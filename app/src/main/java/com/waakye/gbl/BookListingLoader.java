package com.waakye.gbl;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by lesterlie on 4/28/17.
 */

public class BookListingLoader extends AsyncTaskLoader<List<BookListing>> {

    /** Tag for log messages */
    private static final String LOG_TAG = BookListingLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link BookListingLoader}
     * @param context of the activity
     * @param url to load the data
     */
    public BookListingLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on the background thread
     */
    @Override
    public List<BookListing> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of booklistings
        List<BookListing> bookListings = QueryUtils.fetchBookListingData(mUrl);
        return bookListings;
    }
}
