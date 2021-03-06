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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookListing>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String GOOGLE_BOOKS_QUERY
        = "https://www.googleapis.com/books/v1/volumes?q=hawthorne&key=AIzaSyA49wUdKh_qlFv5FiexsdYdLyv8rNEDiko";

    private static final String GOOGLE_BOOKS_QUERY_PREFIX = "https://www.googleapis.com/books/v1/volumes?q=";

    private static final String API_KEY = "AIzaSyA49wUdKh_qlFv5FiexsdYdLyv8rNEDiko";

    /**
     * Constant value for the booklisting loader ID
     */
    private static final int BOOKLISTING_LOADER_ID = 1;

    /** Adapter for the list of booklistings */
    private BookListingAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    public String search_terms = "";

    /** URL to query the Google Books API for book information */
    public static String google_books_query_url = "";

    private String concatenated_search_terms = "";

    private String urlQueryString(String search_terms) {

        concatenated_search_terms = search_terms.trim().replace(" ", "+");
        StringBuilder sb = new StringBuilder(GOOGLE_BOOKS_QUERY_PREFIX);
        sb.append(concatenated_search_terms);
        sb.append("&key=");
        sb.append(API_KEY);
        String builtString = sb.toString();
        return builtString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "TEST: MainActivity onCreate() called... ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Find a reference to the {@link ListView} in the layout
        final ListView listView = (ListView)findViewById(R.id.listview_booklisting);

        mEmptyStateTextView = (TextView)findViewById(R.id.empty_view);
//        listView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of booklistings as inputs
        mAdapter = new BookListingAdapter(this, new ArrayList<BookListing>());

        // So the adapter on the {@link ListView} so the list can be populated in the user interface
        listView.setAdapter(mAdapter);

        Button searchButton = (Button)findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                // Get input from the EditText
                EditText searchTerms = (EditText)findViewById(R.id.edit_search_terms);
                search_terms = searchTerms.getText().toString();

                // Tests whether anything was entered
                if (search_terms.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nothing entered" , Toast.LENGTH_SHORT).show();
                    listView.setEmptyView(mEmptyStateTextView); // No results found
                }

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
                    loaderManager.initLoader(BOOKLISTING_LOADER_ID, null, MainActivity.this);
                    // Check to see if there is already a loader
                    if (getLoaderManager().getLoader(BOOKLISTING_LOADER_ID).isStarted()) {
                        // restart the loader if it already exists
                        getLoaderManager().restartLoader(BOOKLISTING_LOADER_ID, null, MainActivity.this);
                    }

                } else {
                    // Otherwise, display error
                    // First, hide the loading indicator so error message will be visible
                    View loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);

                    // Update empty state with no connection error message
                    mEmptyStateTextView.setText(R.string.no_internet_connection);
                }
            }
        });
    }

    @Override
    public Loader<List<BookListing>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "TEST: MainActivity onCreateLoader() called... ");
        google_books_query_url = urlQueryString(search_terms);

        // Create a new loader for the given URL
        return new BookListingLoader(this, google_books_query_url);
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
