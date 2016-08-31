package co.edu.udea.compumovil.gr06.lab2activities.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by brayan on 30/08/16.
 */
public class User implements Serializable {
    //Table name
    public static String TABLE = "user";

    //Columns name
    public static String COLUMN_ID = "id";
    public static String COLUMN_NAME_USER = "nameUser";
    public static String COLUMN_EMAIL = "email";
    public static String COLUMN_PASSWORD = "password";
    public static String COLUMN_PICTURE = "picture";
    public static String COLUMN_BIRTHDATE = "birthDate";

    //Privates Attributes
    private int id;
    private String nameUser;
    private String email;
    private String password;
    private byte[] picture;
    private Calendar birthDate;
    private int edad;

    //Constructor with paramaters
    public User(Calendar birthDate, String email, int id, String nameUser, String password, byte[] picture) {
        this.birthDate = birthDate;
        this.email = email;
        this.id = id;
        this.nameUser = nameUser;
        this.password = password;
        this.picture = picture;
    }

    //Empty constructor
    public User() {
    }

    //Getter and setter
    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
