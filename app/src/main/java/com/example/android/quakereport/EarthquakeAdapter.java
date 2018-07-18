package com.example.android.quakereport;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.AnimatorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public EarthquakeAdapter(@NonNull Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Earthquake currentEarthquake = getItem(position);

        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag_view);
//        magTextView.setText(currentEarthquake.getMagnitudeOfEarthquake());


//        DecimalFormat formatter = new DecimalFormat("0.00");
//        String output = formatter.format(currentEarthquake.getMagnitudeOfEarthquake());

        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitudeOfEarthquake());

        magTextView.setText(formattedMagnitude);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitudeOfEarthquake());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String originalLocation = currentEarthquake.getPlaceOfEarthquake();

        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.description_place_view);
        locationOffsetView.setText(locationOffset);
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.place_view);
        primaryLocationView.setText(primaryLocation);


        // My first idea:
//        String placeText = currentEarthquake.getPlaceOfEarthquake();
//        int commaPoint = 0;
//        if (placeText.contains(",")) {
//            commaPoint = placeText.indexOf(",");
//        } else  System.out.print("Near the");
//
//        String descriptionPlaceTextToShow = placeText.substring(0, commaPoint);
//
//        String placeTextViewToShow = placeText.substring(commaPoint, placeText.length());
//
//        descriptionPlaceView.setText(descriptionPlaceTextToShow);
//        placeTextView.setText(placeTextViewToShow);


        // My second idea:
//        TextView descriptionPlaceView = (TextView) listItemView.findViewById(R.id.description_place_view);
//        TextView placeTextView = (TextView) listItemView.findViewById(R.id.place_view);
//
//        String placeText = currentEarthquake.getPlaceOfEarthquake();
//
//        String[] splitSentence;
//
//        if (placeText.contains(",")) {
//            splitSentence = placeText.split(",");
//
//            String firstPartOfSentence = splitSentence[0];
//            String secondPartOfSentence = splitSentence[1];
//
//            descriptionPlaceView.setText(firstPartOfSentence);
//            placeTextView.setText(secondPartOfSentence);
//        } else {
//            descriptionPlaceView.setText("Near the");
//            placeTextView.setText(placeText);
//        }


        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_view);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_view);

        Date dateObject = new Date(currentEarthquake.getTimeOfEarthquake());

        dateTextView.setText(formatDate(dateObject));
        timeTextView.setText(formatTime(dateObject));

        return listItemView;
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormatter = new DecimalFormat("0.0");
        return magnitudeFormatter.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormatter.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        return timeFormatter.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}