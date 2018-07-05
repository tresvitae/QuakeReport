package com.example.android.quakereport;

public class Earthquake {

    private double fieldMagnitudeOfEarthquake;

    private String fieldPlaceOfEarthquake;

    private Long fieldTimeOfEarthquake;

    private String fieldURLwebSite;

    public Earthquake(double magnitudeOfEarthquake, String placeOfEarthquake, Long timeOfEarthquake, String urlWebSite) {
        fieldMagnitudeOfEarthquake = magnitudeOfEarthquake;
        fieldPlaceOfEarthquake = placeOfEarthquake;
        fieldTimeOfEarthquake = timeOfEarthquake;
        fieldURLwebSite = urlWebSite;
    }

    public double getMagnitudeOfEarthquake() {
        return fieldMagnitudeOfEarthquake;
    }

    public String getPlaceOfEarthquake() {
        return fieldPlaceOfEarthquake;
    }

    public Long getTimeOfEarthquake() {
        return fieldTimeOfEarthquake;
    }

    public String getURLwebSite() {
        return fieldURLwebSite;
    }
}