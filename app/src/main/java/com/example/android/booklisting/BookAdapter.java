package com.example.android.booklisting;

/**
 * Created by casab on 10/06/2017.
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {


    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.bookslist_item, parent, false);
        }
        Book currentBook = getItem(position);

        // Find the TextView with view ID
        TextView IDView = (TextView) listItemView.findViewById(R.id.ID);
        // Display the Price of the current book in that TextView
        IDView.setText(currentBook.getID());

        // Find the TextView with view Title
        TextView titleView = (TextView) listItemView.findViewById(R.id.Title);
        // Display the Title of the current book in that TextView
        titleView.setText(currentBook.getTitle());

        // Find the TextView with view SubTitle
        TextView subtitleView = (TextView) listItemView.findViewById(R.id.SubTitle);
        // Display the SubTitle of the current book in that TextView
        subtitleView.setText(currentBook.getSubTitle());

        // Find the TextView with view Author
        TextView authorView = (TextView) listItemView.findViewById(R.id.Author);
        // Display the Author of the current book in that TextView
        authorView.setText(currentBook.getAuthor());

        // Find the TextView with view Publisher
        TextView publisherView = (TextView) listItemView.findViewById(R.id.Publisher);
        // Display the Publisher of the current book in that TextView
        publisherView.setText(currentBook.getPublisher());

        return listItemView;

    }
}
