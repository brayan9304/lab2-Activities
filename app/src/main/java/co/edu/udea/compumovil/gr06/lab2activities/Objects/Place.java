package co.edu.udea.compumovil.gr06.lab2activities.Objects;

/**
 * Created by brayan on 4/09/16.
 */
public class Place {
    private int placeId;
    private  String namePlace;
    private String location;
    private int score;
    private String temperature;
    private String description ;
    private String picture;

    public Place(String temperature, String description, String location, String namePlace, String picture, int placeId, int score) {
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

    public String getPicture() {
        return picture;
    }//End getPicture

    public void setPicture(String picture) {
        this.picture = picture;
    }//End setPicture

    public int getPlaceId() {
        return placeId;
    }//End getPlaceId

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }//End setPlaceId

    public int getScore() {
        return score;
    }//End getScore

    public void setScore(int score) {
        this.score = score;
    }//End setScore

    public String getTemperature() {
        return temperature;
    }//End getTemperature

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }//End seTemperature
}//End class
