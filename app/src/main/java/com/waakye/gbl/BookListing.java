package com.waakye.gbl;

/**
 * Created by lesterlie on 4/25/17.
 * BookListing class defines the attributes that describe a book
 */

public class BookListing {

    /** Book's title */
    private String mBookTitle;

    /** Book's author */
    private String mBookAuthor;

    /** Book's description or short summary */
    private String mBookDescription;

    /** Book's number of pages */
    private String mBookPageCount;

    public BookListing(String vBookTitle, String vBookAuthor, String vBookDescription, String vBookPageCount) {
        mBookTitle = vBookTitle;
        mBookAuthor = vBookAuthor;
        mBookDescription = vBookDescription;
        mBookPageCount = vBookPageCount;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public String getBookAuthor() {
        return mBookAuthor;
    }

    public String getBookDescription() {
        return mBookDescription;
    }

    public String getBookPageCount() {
        return mBookPageCount;
    }

}
