package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link Fragment} that displays a list of places to eat
 */
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
        words.add(new Word(getString(R.string.restaurant_name1), getString(R.string.restaurant_address1), getString(R.string.restaurant_town1), getString(R.string.restaurant_phone1), R.drawable.pizza_23477_1280));
        words.add(new Word(getString(R.string.restaurant_name2), getString(R.string.restaurant_address2), getString(R.string.restaurant_town2), getString(R.string.restaurant_phone2), R.drawable.taco_155812_1280));
        words.add(new Word(getString(R.string.restaurant_name3), getString(R.string.restaurant_address3), getString(R.string.restaurant_town2), getString(R.string.restaurant_phone3), R.drawable.eat_2411129_1280));
        words.add(new Word(getString(R.string.restaurant_name4), getString(R.string.restaurant_address4), getString(R.string.restaurant_town2), getString(R.string.restaurant_phone4), R.drawable.dragon_1597597_1280));
        words.add(new Word(getString(R.string.restaurant_name5), getString(R.string.restaurant_address5), getString(R.string.restaurant_town1), getString(R.string.restaurant_phone5), R.drawable.taco_155812_1280));
        words.add(new Word(getString(R.string.restaurant_name6), getString(R.string.restaurant_address6), getString(R.string.restaurant_town1), getString(R.string.restaurant_phone6), R.drawable.eat_2411129_1280));
        words.add(new Word(getString(R.string.restaurant_name7), getString(R.string.restaurant_address7), getString(R.string.restaurant_town1), getString(R.string.restaurant_phone7), R.drawable.eat_2411129_1280));

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

        // Set a click listener to show a dialog fragment when the list item is clicked.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);
                //create a strings to send to dialogFragment
                String businessName = word.getNameOfPlace();
                String streetText = word.getStreetAddress();
                String townText = word.getTownAddress();
                String phoneText = word.getPhoneNumber();
                //Learned how to display dialog fragment from the following:
                //    https://medium.com/@xabaras/creating-a-custom-dialog-with-dialogfragment-f0198dab656d
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag(getString(R.string.frag_tag));
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                android.support.v4.app.DialogFragment dialogFragment = new MyCustomDialogFragment();
                // Learned how to pass data to dialog fragments from the following site:
                //    http://www.androhub.com/android-pass-data-from-activity-to-fragment/
                Bundle data = new Bundle();//create bundle instance
                data.putString(getString(R.string.bus_in_cus_dialog_fragment), businessName);//put string to pass with a key value
                data.putString(getString(R.string.str_in_cus_dialog_fragment), streetText);
                data.putString(getString(R.string.twn_in_cus_dialog_fragment), townText);
                data.putString(getString(R.string.ph_in_cus_dialog_fragment), phoneText);
                dialogFragment.setArguments(data);
                dialogFragment.show(ft, getString(R.string.frag_tag));
            }
        });
        return rootView;
    }
}