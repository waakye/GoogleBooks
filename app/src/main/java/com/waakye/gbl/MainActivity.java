package com.waakye.gbl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String GOOGLE_BOOKS_QUERY
    = "https://www.googleapis.com/books/v1/volumes?q=hawthorne&key=AIzaSyA49wUdKh_qlFv5FiexsdYdLyv8rNEDiko";

    /** Adapter for the list of booklistings */
    private BookListingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Find a reference to the {@link ListView} in the layout
        ListView listView = (ListView)findViewById(R.id.listview_booklisting);

        // Create a new adapter that takes an empty list of booklistings as inputs
        mAdapter = new BookListingAdapter(this, new ArrayList<BookListing>());

        // So the adapter on the {@link ListView} so the list can be populated in the user interface
        listView.setAdapter(mAdapter);

        // Start the AsyncTask to fetch the booklistings data
        BookListingAsyncTask task = new BookListingAsyncTask();
        task.execute(GOOGLE_BOOKS_QUERY);
    }

    private class BookListingAsyncTask extends AsyncTask<String, Void, List<BookListing>> {

        @Override
        protected List<BookListing> doInBackground(String... urls) {
            // Don't perform the request if there is no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<BookListing> result = QueryUtils.fetchBookListingData(GOOGLE_BOOKS_QUERY);
            return result;
        }

        @Override
        protected void onPostExecute(List<BookListing> data) {
            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
