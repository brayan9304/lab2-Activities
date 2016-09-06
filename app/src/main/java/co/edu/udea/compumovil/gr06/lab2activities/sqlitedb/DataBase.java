package co.edu.udea.compumovil.gr06.lab2activities.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr06.lab2activities.Objects.Place;

/**
 * Created by brayan on 30/08/16.
 */
public class DataBase extends SQLiteOpenHelper {
    //Currently version of the data Base
    public static int DB_VERSION = 4;

    //Data base name
    public  static String NAME_DATABASE = "laboratorio2.db";

    //User table
    public static String USER_TABLE ="user";
    public static String COLUMN_USER_ID = "userid";
    public static String COLUMN_USER_NAME = "username";
    public static String COLUMN_USER_PASSWORD = "password";
    public static String COLUMN_USER_AGE = "edad";
    public static String COLUMN_USER_EMAIL = "email";
    public static String COLUMN_USER_PICTURE = "userpicture";

    //Place table
    public static String PLACE_TABLE ="place";
    public static String COLUMN_PLACE_ID = "placeid";
    public static String COLUMN_NAME_PLACE = "nameplace";
    public static String COLUMN_PLACE_LOCATION = "location";
    public static String COLUMN_PLACE_SCORE= "score";
    public static String COLUMN_PLACE_TEMPERATURE = "temperature";
    public static String COLUMN_PLACE_DESCRIPTION = "description";
    public static String COLUMN_PLACE_PICTURE = "placepicture";


    public DataBase(Context context) {
        super(context, NAME_DATABASE, null, DB_VERSION);
    }//End construct method

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER = "create table " + USER_TABLE + "("
                + COLUMN_USER_ID  + " integer primary key AUTOINCREMENT not null, "
                + COLUMN_USER_NAME + " text,"
                + COLUMN_USER_PASSWORD + " text,"
                + COLUMN_USER_AGE + " text,"
                + COLUMN_USER_EMAIL + " text,"
                + COLUMN_USER_PICTURE + " blob"+")";
        db.execSQL(CREATE_TABLE_USER);

        String CREATE_TABLE_PLACE = "create table " + PLACE_TABLE  + "("
                + COLUMN_PLACE_ID + " integer primary key AUTOINCREMENT not null, "
                + COLUMN_NAME_PLACE + " text,"
                + COLUMN_PLACE_LOCATION + " text,"
                + COLUMN_PLACE_SCORE + " real,"
                + COLUMN_PLACE_TEMPERATURE + " text,"
                + COLUMN_PLACE_DESCRIPTION + " text,"
                + COLUMN_PLACE_PICTURE + " blob" + ")";
        Log.d("place",CREATE_TABLE_PLACE);
        db.execSQL(CREATE_TABLE_PLACE);
    }//End onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " +USER_TABLE);
        db.execSQL("drop table if exists " + PLACE_TABLE);
        onCreate(db);
    }//End onUpgrade

    //************USER METHODS**************

    public void addUser(String userName, String password, int userAge, String email, byte[] userPicture){
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(COLUMN_USER_NAME, userName);
        registro.put(COLUMN_USER_PASSWORD, password);
        registro.put(COLUMN_USER_AGE, userAge);
        registro.put(COLUMN_USER_EMAIL, email);
        registro.put(COLUMN_USER_PICTURE, userPicture);
        bd.insert(DataBase.USER_TABLE, null, registro);
        bd.close();
    }//End addUser

    public Cursor getUser(SQLiteDatabase db, String userName, String password){
        String[] campos = new String[] {COLUMN_USER_NAME,COLUMN_USER_AGE,COLUMN_USER_PICTURE};
        String[] args = new String[] {userName, password};

        return db.query(USER_TABLE, campos, "username=? and password=?", args, null, null, null);
    }//End getUser

    public boolean userExist(String user) {
        SQLiteDatabase db = getWritableDatabase();
        String[] campos = new String[] {COLUMN_USER_NAME};
        String[] args = new String[] {user};
        Cursor cursor = db.query(USER_TABLE, campos, "username=?", args, null, null, null);
        return cursor.getCount() > 0;
    }//End userExist()

    public boolean emailExist(String email) {
        SQLiteDatabase db = getWritableDatabase();
        String[] campos = new String[] {COLUMN_USER_EMAIL};
        String[] args = new String[] {email};
        Cursor cursor = db.query(USER_TABLE, campos, "email=?", args, null, null, null);
        return cursor.getCount() > 0;
    }//End emailExist()

    //************PLACE METHODS**************

    public void addPlace(String pName, String pLocation, double pScore, String pTemperature, String pDescription, byte[] pPicture){
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(DataBase.COLUMN_NAME_PLACE, pName);
        registro.put(DataBase.COLUMN_PLACE_LOCATION, pLocation);
        registro.put(DataBase.COLUMN_PLACE_SCORE, pScore);
        registro.put(DataBase.COLUMN_PLACE_TEMPERATURE,pTemperature);
        registro.put(DataBase.COLUMN_PLACE_DESCRIPTION, pDescription);
        registro.put(DataBase.COLUMN_PLACE_PICTURE, pPicture);
        bd.insert(DataBase.PLACE_TABLE, null, registro);
        bd.close();
    }//End addPlace

    public List<Place> getAllPlaces() {
        List<Place> placesList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + PLACE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Place place = new Place();
                place.setPlaceId(Integer.parseInt(cursor.getString(0)));
                place.setNamePlace(cursor.getString(1));
                place.setLocation(cursor.getString(2));
                place.setScore(Float.parseFloat(cursor.getString(3)));
                place.setTemperature(cursor.getString(4));
                place.setDescription(cursor.getString(5));
                place.setPicture(cursor.getBlob(6));
                placesList.add(place);
            } while (cursor.moveToNext());
        }//End if (cursor.moveToFirst())

        return placesList;
    }//End getAllPlaces

    public Cursor getPlace(SQLiteDatabase db, String placeName){
        String[] campos = new String[] { COLUMN_NAME_PLACE, COLUMN_PLACE_LOCATION, COLUMN_PLACE_SCORE,
        COLUMN_PLACE_TEMPERATURE, COLUMN_PLACE_DESCRIPTION, COLUMN_PLACE_PICTURE};
        String[] args = new String[] {placeName};
        return db.query(USER_TABLE, campos, "nameplace=?", args, null, null, null);
    }//End getPlace

    public boolean placeExist(String placeName){
        SQLiteDatabase db = getWritableDatabase();
        String[] campos = new String[] {COLUMN_NAME_PLACE};
        String[] args = new String[] {placeName};
        Cursor cursor = db.query(PLACE_TABLE, campos, "nameplace=?", args, null, null, null);
        return cursor.getCount() > 0;
    }//End placeExist

    public boolean locationExist(String location){
        SQLiteDatabase db = getWritableDatabase();
        String[] campos = new String[] {COLUMN_PLACE_LOCATION};
        String[] args = new String[] {location};
        Cursor cursor = db.query(PLACE_TABLE, campos, "LOCATION=?", args, null, null, null);
        return cursor.getCount() > 0;
    }//End location
}//End class
