package com.example.android.tourguideapp;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * {@link Fragment} that displays a list of number vocabulary words.
 */
public class ShoppingFragment extends Fragment {

    Dialog showImageDialog;
    Intent mapIntent;
    Button yesButton;
    Button noButton;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Food Lion", "248 Market St", "North Tazewell, VA 24630", "(276) 988-2900", R.drawable.shopping_cart_371979_1280));
        words.add(new Word("Grants Supermarket", "629 E Riverside Dr", "North Tazewell, VA 24630", "(276) 988-0945", R.drawable.shopping_cart_371979_1280));
        words.add(new Word("Magic Mart", "13 Tazewell Mall Cir", "Tazewell, VA 24651", "(276) 988-4567", R.drawable.shopping_cart_371979_1280));
        words.add(new Word("Loose Change", "850 Fincastle Turnpike", "Tazewell, VA 24651", "(276) 988-7444", R.drawable.shopping_cart_371979_1280));
        words.add(new Word("Country Variety Consignment Shop", "216 Tazewell Mall Circle", "Tazewell, VA 24651", "(276) 979-4284", R.drawable.shopping_cart_371979_1280));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.colorGreen);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play open a dialog view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                String addressToSend = word.getNameOfPlace() + " " + word.getStreetAddress() + " " + word.getTownAddress();
                //Toast.makeText(getContext(), addressToSend, Toast.LENGTH_LONG).show();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + addressToSend);
                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                showImageDialog = new Dialog(getContext());
                showImageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                showImageDialog.setContentView(R.layout.dialog_layout);
                showImageDialog.show();
            }
        });
        yesButton = new Button(getContext());
        yesButton.findViewById(R.id.yes_dialog_button);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mapIntent);
            }
        });
        return rootView;


    }
}
