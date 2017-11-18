package com.example.android.booklisting;

/**
 * Created by casab on 10/06/2017.
 */

public class Book {
    private String mID;
    private String mTitle;
    private String mSubTitle;
    private StringBuilder mAuthor;
    private String mPublisher;
    private String mUrl;

    /* Constructor to build the Book Object will be use in the listView after being processed by the Adapter*/
    public Book(String ID,String Title,String SubTitle,StringBuilder Author, String Publisher,String Url){
        mID =ID;
        mTitle=Title;
        mSubTitle=SubTitle;
        mAuthor=Author;
        mPublisher=Publisher;
        mUrl=Url;
    }

    /*List of public methods to get the 5 parameters of the Object Book*/
    public String getID(){
        return mID;
    }

    public String getTitle(){
        return mTitle;
    }
    public String getSubTitle(){
        return mSubTitle;
    }
    public StringBuilder getAuthor(){
        return mAuthor;
    }
    public String getPublisher() {
        return mPublisher;
    }
    /*method to get the URL of the Book Result*/
    public String getUrl() {
        return mUrl;
    }
}

