package com.waakye.gbl;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        // Get a reference to the LoaderManager, in order to interact with loaders
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader.  Pass in the int ID constant defined above and pass in null for
        // the bundle
        loaderManager.initLoader(BOOKLISTING_LOADER_ID, null, this);

//        // Start the AsyncTask to fetch the booklistings data
//        BookListingAsyncTask task = new BookListingAsyncTask();
//        task.execute(GOOGLE_BOOKS_QUERY);
    }

    @Override
    public Loader<List<BookListing>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL
        return new BookListingLoader(this, GOOGLE_BOOKS_QUERY);
    }

    @Override
    public void onLoadFinished(Loader<List<BookListing>> loader, List<BookListing> data) {
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
        // Loader reset, so we can clear out our existing data
        mAdapter.clear();
    }
}
