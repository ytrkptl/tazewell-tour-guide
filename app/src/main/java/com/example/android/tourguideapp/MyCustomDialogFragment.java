package com.example.android.tourguideapp;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

    //  Learned how to use dialog fragments and pass data at the following sites:
    //  https://medium.com/@xabaras/creating-a-custom-dialog-with-dialogfragment-f0198dab656d
    //  https://stackoverflow.com/questions/25887373/calling-dialogfragment-from-fragment-not-fragmentactivity
public class MyCustomDialogFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_layout, container, false);
        //find textview from dialog_layout.xml and fill it with business name
        TextView businessName = (TextView) v.findViewById(R.id.name_of_place_dialog);
        String business = getArguments().getString(getString(R.string.bus_in_cus_dialog_fragment));//Get pass data with its key value
        businessName.setText(business);
        //find textview from dialog_layout.xml and fill it with street address
        TextView streetText = (TextView) v.findViewById(R.id.street_in_dialog);
        String street = getArguments().getString(getString(R.string.str_in_cus_dialog_fragment));//Get pass data with its key value
        streetText.setText(street);
        //find textview from dialog_layout.xml and fill it with town address
        TextView townText = (TextView) v.findViewById(R.id.town_in_dialog);
        String town = getArguments().getString(getString(R.string.twn_in_cus_dialog_fragment));//Get pass data with its key value
        townText.setText(town);
        //find textview from dialog_layout.xml and fill it with phone number
        TextView phoneText = (TextView) v.findViewById(R.id.phone_in_dialog);
        String phone = getArguments().getString(getString(R.string.ph_in_cus_dialog_fragment));//Get pass data with its key value
        phoneText.setText(phone);

        String addressToSend = business + street + town;
        //set up intent for sending to maps
        Uri gmmIntentUri = Uri.parse(getString(R.string.map_uri_1) + addressToSend);
        final Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage(getString(R.string.map_package_1));


        //find yes button from dialog and set click listener on it
        Button yesButton = (Button) v.findViewById(R.id.yes_dialog_button);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to maps if user selects yes button
                startActivity(mapIntent);
            }
        });
        //find ImageButton from dialog and set click listener on it
        ImageButton closeButton = (ImageButton) v.findViewById(R.id.close_dialog_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //close dialog if no button is selected
                getDialog().dismiss();
            }
        });

        return v;
    }

    //    https://stackoverflow.com/questions/15277460/how-to-create-a-dialogfragment-without-title
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}