package co.edu.udea.compumovil.gr06.lab2activities.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import co.edu.udea.compumovil.gr06.lab2activities.R;
import co.edu.udea.compumovil.gr06.lab2activities.sqlitedb.DataBase;

public class AddPlace extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText placeName;
    private EditText location;
    private RatingBar score;
    private EditText temperature;
    private EditText description;
    private ImageView placePicture;
    private Bitmap imageBitmap;

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
    }//End onCreate

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void addPhotoPlace(View view) {
        dispatchTakePictureIntent();
    }//End addPhotoPlace

    private void dispatchTakePictureIntent() {
        Intent intentGaleria = new Intent();
        intentGaleria.setType("image/*");
        intentGaleria.setAction(Intent.ACTION_GET_CONTENT);
        if (intentGaleria.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(intentGaleria, getString(R.string.select_Image)), PICK_IMAGE_REQUEST);
        }//End if
    }//End dispatchTakePictureIntent

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri extras = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), extras);
                placePicture.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                Log.e("Error", "onActivityResult: no caarga imagen");
            }

        }//End if
    }//End onActivityResult

    public void addPlace(View v){
        DataBase admin = new DataBase(this);
        ByteArrayOutputStream bitesOut = new ByteArrayOutputStream();
        String pName = placeName.getText().toString();
        String pLocation = location.getText().toString();
        double pScore = score.getRating();
        String pTemp =  temperature.getText().toString();
        String pDescription = description.getText().toString();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bitesOut);
        byte[] pPicture = bitesOut.toByteArray();
        admin.addPlace(pName, pLocation, pScore, pTemp, pDescription, pPicture);
        Toast.makeText(this, "Los datos han sido guardados",
                Toast.LENGTH_SHORT).show();
    }//End addPlace
}//End class
