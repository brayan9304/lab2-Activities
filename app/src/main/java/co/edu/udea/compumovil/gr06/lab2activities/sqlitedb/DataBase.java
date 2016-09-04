package co.edu.udea.compumovil.gr06.lab2activities.sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by brayan on 30/08/16.
 */
public class DataBase extends SQLiteOpenHelper {

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

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER = "create table " + USER_TABLE + "("
                + COLUMN_USER_ID  + " int primary key, "
                + COLUMN_USER_NAME + " text ,"
                + COLUMN_USER_PASSWORD + " text,"
                + COLUMN_USER_AGE+ "  text,"
                + COLUMN_USER_EMAIL + " text,"
                + COLUMN_USER_PICTURE + " text )";
        db.execSQL(CREATE_TABLE_USER);

        String CREATE_TABLE_PLACE = "create table " + PLACE_TABLE  + "("
                + COLUMN_PLACE_ID + " integer primary key AUTOINCREMENT not null, "
                + COLUMN_NAME_PLACE + " text,"
                + COLUMN_PLACE_LOCATION + " text,"
                + COLUMN_PLACE_SCORE + " int,"
                + COLUMN_PLACE_TEMPERATURE + " text,"
                + COLUMN_PLACE_DESCRIPTION + " text,"
                + COLUMN_PLACE_PICTURE + " text"+")";
        Log.d("place",CREATE_TABLE_PLACE);
        db.execSQL(CREATE_TABLE_PLACE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
