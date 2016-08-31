package co.edu.udea.compumovil.gr06.lab2activities.model;

import java.io.Serializable;

/**
 * Created by brayan on 30/08/16.
 */
public class Place implements Serializable {
    //Table name
    public static String TABLE = "place";

    //Columns name
    public static String COLUMN_ID = "id";
    public static String COLUMN_NAME_PLACE = "nameplace";
    public static String COLUMN_DESCRIPTION = "description";
    public static String CLOUMN_SCORE = "score";
    public static String COLUMN_LOCATION = "location";
    public static String COLUM_TEMPERATURE = "temperature";
    public static String COLUM_PICTURE = "picture";

    //Privates Attributes
    private int id;
    private String namePlace;
    private String description;
    private int score;
    private String location;
    private String temperature;
    private byte[] picture;

    //Constructor with paramaters
    public Place(String temperature, String description, int id, String location, String namePlace, byte[] picture, int score) {
        this.temperature = temperature;
        this.description = description;
        this.id = id;
        this.location = location;
        this.namePlace = namePlace;
        this.picture = picture;
        this.score = score;
    }

    //Empty constructor
    public Place() {
    }

    //Getter and Setter

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
