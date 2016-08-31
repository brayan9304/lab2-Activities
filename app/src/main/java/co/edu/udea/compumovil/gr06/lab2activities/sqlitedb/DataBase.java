package co.edu.udea.compumovil.gr06.lab2activities.sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import co.edu.udea.compumovil.gr06.lab2activities.model.Place;
import co.edu.udea.compumovil.gr06.lab2activities.model.User;

/**
 * Created by brayan on 30/08/16.
 */
public class DataBase extends SQLiteOpenHelper {


    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER = "CREATE TABLE " + User.TABLE  + "("
                + User.COLUMN_ID  + " INTEGER PRIMARY KEY NOT NULL, "
                + User.COLUMN_NAME_USER + " TEXT NOT NULL"
                + User.COLUMN_PASSWORD + "TEXT NOT NULL"
                + User.COLUMN_BIRTHDATE + "DATE NOT NULL"
                + User.COLUMN_EMAIL + "TEX NOT NULLT"
                + User.COLUMN_PICTURE + "BLOB";
        db.execSQL(CREATE_TABLE_USER);

        String CREATE_TABLE_PLACE = "CREATE TABLE " + Place.TABLE  + "("
                + Place.COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + Place.COLUMN_NAME_PLACE + "TEXT NOT NULL"
                + Place.COLUMN_LOCATION + "TEXT NOT NULL"
                + Place.CLOUMN_SCORE + "INTEGER NOT NULL"
                + Place.COLUM_TEMPERATURE + "TEXT"
                + Place.COLUMN_DESCRIPTION + "TEXT NOT NULL"
                + Place.COLUM_PICTURE + "BLOB";
        db.execSQL(CREATE_TABLE_PLACE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
