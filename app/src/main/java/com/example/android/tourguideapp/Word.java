package com.example.android.tourguideapp;

import android.widget.ImageView;

/**
 * {@link Word} represents a place or business in the Town of Tazewell or North Tazewell
 * It contains a nameOfPlace, streetAddress, townAddress, phoneNumber, and imageResourceID
 */
public class Word {

    //name of the business or place
    private String nameOfPlace;

    //the street address of business
    private String streetAddress;

    //the town, state, and zip of business location
    private String townAddress;

    //the audio resource ID if any
    private int mAudioResourceId;

    //the businesss phone number
    private String phoneNumber;

    //the image resouce ID
    private int imageResourceId;

    //if there is no image, the placeholder image will be used.
    // the following constructor will be used if no image is assigned to the business
    public Word(String defaultTranslation, String defaultStreetAddress, String defaultTownAddress, String defaultNumber) {
        nameOfPlace = defaultTranslation;
        streetAddress = defaultStreetAddress;
        townAddress = defaultTownAddress;
        phoneNumber = defaultNumber;
        imageResourceId = R.drawable.no_image_icon_23500;
    }

    //if there is an image, the image will be used.
    public Word(String defaultTranslation, String defaultStreetAddress, String defaultTownAddress, String defaultNumber, int imageID) {
        nameOfPlace = defaultTranslation;
        streetAddress = defaultStreetAddress;
        townAddress = defaultTownAddress;
        phoneNumber = defaultNumber;
        imageResourceId = imageID;
    }

    //get name of business
    public String getNameOfPlace() {
        return nameOfPlace;
    }
    //get name of street
    public String getStreetAddress() {
        return streetAddress;
    }
    //get name of town, state, and zip-code
    public String getTownAddress() {
        return townAddress;
    }
    //get the phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }
    //the imageResourceId was fine.
    public int getImageResourceId() {
        return imageResourceId;
    }
}
