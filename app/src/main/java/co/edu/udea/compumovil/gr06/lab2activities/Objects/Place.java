package co.edu.udea.compumovil.gr06.lab2activities.Objects;

/**
 * Created by brayan on 4/09/16.
 */
public class Place {
    private int placeId;
    private  String namePlace;
    private String location;
    private float score;
    private String temperature;
    private String description ;
    private byte[] picture;

    public Place(String temperature, String description, String location, String namePlace, byte[] picture, int placeId, int score) {
        this.temperature = temperature;
        this.description = description;
        this.location = location;
        this.namePlace = namePlace;
        this.picture = picture;
        this.placeId = placeId;
        this.score = score;
    }//End construct with paramaters

    public Place() {
    }//End empty construct

    public String getDescription() {
        return description;
    }//End getDescription

    public void setDescription(String description) {
        this.description = description;
    }//End setDescription

    public String getLocation() {
        return location;
    }//End getLocation

    public void setLocation(String location) {
        this.location = location;
    }//End setLocation

    public String getNamePlace() {
        return namePlace;
    }//End getName

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }//End setNamePlace

    public byte[] getPicture() {
        return picture;
    }//End getPicture

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }//End setPicture

    public int getPlaceId() {
        return placeId;
    }//End getPlaceId

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }//End setPlaceId

    public float getScore() {
        return score;
    }//End getScore

    public void setScore(float score) {
        this.score = score;
    }//End setScore

    public String getTemperature() {
        return temperature;
    }//End getTemperature

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }//End seTemperature
}//End class
