package com.waakye.gbl;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lesterlie on 4/25/17.
 */

public class BookListingAdapter extends ArrayAdapter<BookListing> {

    private static final String LOG_TAG = BookListingAdapter.class.getName();

    public BookListingAdapter(Activity context, ArrayList<BookListing> bookListings) {
        super(context, 0, bookListings);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView)
     *
     * @param position  The position in the list of data that should be displayed in the list item view
     * @param convertView  The recycled view to populate
     * @return The View for the position in the AdapterView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.booklisting_list_item, parent, false);
        }

        // Get the {@link BookListing} object located at this position in the list
        BookListing currentBookListing = getItem(position);

        // Find the TextView in the booklisting_list_item.xml layout with the ID book_title
        TextView titleTextView = (TextView)listItemView.findViewById(R.id.book_title);
        // Get the book title from the current BookListing object and set this text on the
        // title TextView
        titleTextView.setText(currentBookListing.getBookTitle());

        // Find the TextView in the booklisting_list_item.xml layout with the ID book_author
        TextView authorTextView = (TextView)listItemView.findViewById(R.id.book_author);
        // Get the book author from the current BookListing object and set this text on the
        // author TextView
        authorTextView.setText(currentBookListing.getBookAuthor());

        // Find the TextView in the booklisting_list_item.xml layout with the ID book_description
        TextView descriptionTextView = (TextView)listItemView.findViewById(R.id.book_description);
        // Get the book description from the current BookListing object and set this text on the
        // description TextView
        descriptionTextView.setText(currentBookListing.getBookDescription());

        // Find the TextView in the booklisting_list_item.xml layout with the ID book_page_count
        TextView pageCountTextView = (TextView)listItemView.findViewById(R.id.book_page_count);
        // Get the book page count from the current BookListing object and set this text on the
        // page count TextView
        pageCountTextView.setText(currentBookListing.getBookPageCount());

        // Return the whole list item layout (containing 4 TextViews) so that it can be shown
        // in the ListView
        return listItemView;
    }

}
