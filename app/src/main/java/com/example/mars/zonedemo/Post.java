package com.example.mars.zonedemo;

/**
 * Created by mars on 2017.12.13..
 */

public class Post {
    private String uid;
    private String user_name;
    private String song_title;
    private String song_artist;
    private String imageUrl;
    private double user_location_lat;
    private double user_location_lng;
    private String song_spotifyID;

    public Post() {
    }

    public Post(String uid, String user_name, String title, String song_artist) {
        this.uid = uid;
        this.user_name = user_name;
        this.song_title = title;
        this.song_artist = song_artist;
        user_location_lat = 0.0;
        user_location_lng = 0.0;
        song_spotifyID = "";
    }



    public Post(String uid, String user_name, String title, String song_artist, String spotifyID) {
        this.uid = uid;
        this.user_name = user_name;
        this.song_title = title;
        this.song_artist = song_artist;
        user_location_lat = 0.0;
        user_location_lng = 0.0;
        song_spotifyID = spotifyID;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getSongTitle() {
        return song_title;
    }

    public void setSongTitle(String song_title) {
        this.song_title = song_title;
    }

    public String getSongArtist() {
        return song_artist;
    }

    public double getUserLocationLat() {
        return user_location_lat;
    }

    public void setUserLocationLat(double user_location_lat) {
        this.user_location_lat = user_location_lat;
    }

    public double getUserLocationLng() {
        return user_location_lng;
    }

    public void setUserLocationLng(double user_location_lng) {
        this.user_location_lng = user_location_lng;
    }

    public String getSongSpotifyID() {
        return song_spotifyID;
    }

    public void setSongSpotifyID(String song_spotifyID) {
        this.song_spotifyID = song_spotifyID;
    }

    public void setSongArtist(String song_artist) {
        this.song_artist = song_artist;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}