package com.waakye.gbl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Create a fake list of books
        ArrayList<BookListing> bookListings = QueryUtils.extractBookListings();

        // Create an {@link BookListingAdapter} whose data source is a list of {@link BookListing}s.
        // The adapter knows how to create list item views for each item in the list
        BookListingAdapter bookListingAdapter = new BookListingAdapter(this, bookListings);

        // Get a reference to the ListView and attach the adapter to the listView
        ListView listView = (ListView)findViewById(R.id.listview_booklisting);
        listView.setAdapter(bookListingAdapter);
    }
}
