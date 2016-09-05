package co.edu.udea.compumovil.gr06.lab2activities.UI;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import co.edu.udea.compumovil.gr06.lab2activities.R;
import co.edu.udea.compumovil.gr06.lab2activities.sqlitedb.DataBase;

public class AddPlace extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText placeName;
    private EditText location;
    private RatingBar score;
    private EditText temperature;
    private EditText description;
    private ImageView placePicture;
    DataBase admin;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        placeName = (EditText) findViewById(R.id.placeName);
        location = (EditText) findViewById(R.id.placeLocation);
        score = (RatingBar) findViewById(R.id.placeScore);
        temperature = (EditText) findViewById(R.id.placeTemperature);
        description = (EditText) findViewById((R.id.placeDescription));
        placePicture = (ImageView) findViewById((R.id.placePicture));
        admin = new DataBase(this, DataBase.NAME_DATABASE, null, DataBase.DB_VERSION);
        bd = admin.getWritableDatabase();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void addPhotoPlace(View view) {
        dispatchTakePictureIntent();
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            placePicture.setImageBitmap(imageBitmap);

        }
    }

    public void addPlace(View v) {


        ContentValues registro = new ContentValues();
        registro.put(DataBase.COLUMN_NAME_PLACE, placeName.getText().toString());
        registro.put(DataBase.COLUMN_PLACE_LOCATION, location.getText().toString());
        registro.put(DataBase.COLUMN_PLACE_SCORE, score.getRating() + "");
        registro.put(DataBase.COLUMN_PLACE_TEMPERATURE, temperature.getText().toString());
        registro.put(DataBase.COLUMN_PLACE_DESCRIPTION, description.getText().toString());
        registro.put(DataBase.COLUMN_PLACE_PICTURE, "");
        Log.e("error", "addPlace: " + bd.insert(DataBase.PLACE_TABLE, null, registro));
        bd.close();
        Toast.makeText(this, "Se cargaron los datos del artículo",
                Toast.LENGTH_SHORT).show();
    }
}
