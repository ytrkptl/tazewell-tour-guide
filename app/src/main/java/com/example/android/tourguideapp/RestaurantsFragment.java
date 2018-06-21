package com.example.android.tourguideapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantsFragment extends Fragment {

    public RestaurantsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Italian Village", "1525 Fincastle Turnpike", "Tazewell, VA 24651", "(276) 988-6572", R.drawable.pizza_23477_1280));
        words.add(new Word("El Mariachi", "624 E Riverside Dr", "North Tazewell, VA 24630", "(276) 988-0889", R.drawable.taco_155812_1280));
        words.add(new Word("Your Grate Escape", "2871 Fincastle Turnpike", "North Tazewell, VA 24630", "(276) 988-7867", R.drawable.eat_2411129_1280));
        words.add(new Word("Tray Ting House", "631 E Riverside Dr", "North Tazewell, VA 24630", "(276) 988-4874", R.drawable.dragon_1597597_1280));
        words.add(new Word("Big Burrito", "618 Carline Ave", "Tazewell, VA 24651", "(276) 385-1745", R.drawable.taco_155812_1280));
        words.add(new Word("Seven", "203 Main Street", "Tazewell, VA 24651", "(276) 385-1100", R.drawable.eat_2411129_1280));
        words.add(new Word("Coal Bucket Deli", "100 Tazewell Mall Cir #16", "Tazewell, VA 24651", "(276) 988-9111", R.drawable.eat_2411129_1280));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.colorGreen);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                String addressToSend = word.getNameOfPlace() + " " + word.getStreetAddress() + " " + word.getTownAddress();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + addressToSend);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        return rootView;
    }
}
