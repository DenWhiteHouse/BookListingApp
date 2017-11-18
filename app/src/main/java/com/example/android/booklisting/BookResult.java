package com.example.android.booklisting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.duration;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.os.Build.VERSION_CODES.N;

/**
 * Created by casab on 10/06/2017.
 */

public class BookResult extends AppCompatActivity {

    // Declaring the temporary elements to pass to the list_view and declaring the textView and the Progress bar of the List_view
    private ListView listView;
    private BookAdapter mAdapter;
    // private ProgressBar progress_bar;
    private static final String BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    //ProgressBar progress_bar= (ProgressBar)findViewById(R.id.progress_bar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*set the textView to change the NoDataFound Message in the list_view*/
        /*Setting the views to be used to update the list_view TextViews and ProgressBar*/
        super.onCreate(savedInstanceState);
        setTitle("Result");
        setContentView(R.layout.list_view);
        //Getting the input
        String bookTitle = getIntent().getStringExtra("TITLE");
        //Maximun Results
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String maxResult = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));
        //String end = "&maxResults=20";
        String end =("&maxResults="+maxResult);
        Log.e("TITLE OF THE BOOK IS: ", bookTitle);
        //This method check if there is connection
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        //Creation of the URL
        String newUrl = BOOK_REQUEST_URL + bookTitle.toLowerCase() + end;
        Log.e("The new URL", newUrl);
        listView = (ListView) findViewById(R.id.listmain);

        BookAsyncTask task = new BookAsyncTask();
        //If there is internet connection we execute the task and set the message to the user
        if (isConnected) {
            task.execute(newUrl);
        } else {
            //progress_bar.setVisibility(View.GONE);
            TextView message = (TextView) findViewById(R.id.message);
            message.setText(R.string.no_results);
        }
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        listView.setAdapter(mAdapter);
        // OnItemClickListener will open the website for the specific place
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Book book = mAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(book.getUrl()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Book> result = Utils.fetchBookList(urls[0]);
            return result;
        }

        /**
         * This method is invoked on the main UI thread after the background work has been
         * completed.
         */
        protected void onPostExecute(List<Book> data) {
            // Clear the adapter of previous book data ( to make the user re-use the research
            //and set visible the progressbar
            mAdapter.clear();
            //progress_bar.setVisibility(View.GONE);
            // If there are data will add the data
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
                // If there is no information on a given book we set a text message to inform the user about it
            } else {
                TextView message = (TextView) findViewById(R.id.message);
                message.setText(R.string.no_results);
            }
        }
    }
}
