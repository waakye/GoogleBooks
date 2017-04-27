package com.waakye.gbl;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookListing>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String GOOGLE_BOOKS_QUERY
        = "https://www.googleapis.com/books/v1/volumes?q=hawthorne&key=AIzaSyA49wUdKh_qlFv5FiexsdYdLyv8rNEDiko";

    /**
     * Constant value for the booklisting loader ID
     */
    private static final int BOOKLISTING_LOADER_ID = 1;

    /** Adapter for the list of booklistings */
    private BookListingAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: MainActivity onCreate() called... ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Find a reference to the {@link ListView} in the layout
        ListView listView = (ListView)findViewById(R.id.listview_booklisting);

        mEmptyStateTextView = (TextView)findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of booklistings as inputs
        mAdapter = new BookListingAdapter(this, new ArrayList<BookListing>());

        // So the adapter on the {@link ListView} so the list can be populated in the user interface
        listView.setAdapter(mAdapter);


        // Get a reference to the ConnectivityManager to check the state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader.  Pass in the int ID constant defined above and pass in null for
            // the bundle
            loaderManager.initLoader(BOOKLISTING_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide the loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    @Override
    public Loader<List<BookListing>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "TEST: MainActivity onCreateLoader() called... ");
        // Create a new loader for the given URL
        return new BookListingLoader(this, GOOGLE_BOOKS_QUERY);
    }

    @Override
    public void onLoadFinished(Loader<List<BookListing>> loader, List<BookListing> data) {
        Log.i(LOG_TAG, "TEST: MainActivity onLoadFinished() called... ");

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state to display "No results found."
        mEmptyStateTextView.setText(R.string.no_results_found);

        // Clear the adapter of previous booklistings data
        mAdapter.clear();

        // If there is a valid list of {@link BookListing}s, then add them to the adapter's data
        // set.  This will trigger the ListView to update
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }



    @Override
    public void onLoaderReset(Loader<List<BookListing>> loader) {
        Log.i(LOG_TAG, "TEST: MainActivity onLoaderReset() called... ");
        // Loader reset, so we can clear out our existing data
        mAdapter.clear();
    }
}
