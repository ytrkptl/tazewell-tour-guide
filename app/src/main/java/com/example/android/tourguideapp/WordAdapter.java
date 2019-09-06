package com.example.android.tourguideapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Word} objects.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    //Resource ID for the background color for this list of words
    //leave this here in case I want to change color in future.
    private int mColorResourceId;

    /**
     * Create a new {@link WordAdapter} object.
     *
     * @param context         is the current context (i.e. Activity) that the adapter is being created in.
     * @param words           is the list of businesses to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of words
     */
    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID name_text_view.
        TextView nameOfPlace = (TextView) convertView.findViewById(R.id.name_text_view);
        nameOfPlace.setText(currentWord.getNameOfPlace());

        // Find the TextView in the list_item.xml layout with the ID street_text_view.
        TextView streetTextView = (TextView) convertView.findViewById(R.id.street_text_view);
        streetTextView.setText(currentWord.getStreetAddress());

        // Find the TextView in the list_item.xml layout with the ID town_text_view.
        TextView townTextView = (TextView) convertView.findViewById(R.id.town_text_view);
        townTextView.setText(currentWord.getTownAddress());

        // Find the TextView in the list_item.xml layout with the ID phone_text_view.
        TextView phoneTextView = (TextView) convertView.findViewById(R.id.phone_text_view);
        phoneTextView.setText(currentWord.getPhoneNumber());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);

        // Display the provided image based on the resource ID
        imageView.setImageResource(currentWord.getImageResourceId());

        // Set the theme color for the list item
        View container = convertView.findViewById(R.id.container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        container.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return convertView;
    }
}