package co.edu.udea.compumovil.gr06.lab2activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr06.lab2activities.sqlitedb.DataBase;

public class AddPlace extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText placeName;
    private EditText location;
    private EditText score;
    private EditText temperature;
    private EditText description;
    private ImageView placePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        placeName = (EditText) findViewById(R.id.placeName);
        location = (EditText) findViewById(R.id.placeLocation);
        score = (EditText)findViewById(R.id.placeScore);
        temperature = (EditText)findViewById(R.id.placeTemperature);
        description = (EditText)findViewById((R.id.placeDescription));
        placePicture = (ImageView) findViewById((R.id.placePicture));
    }//End onCreate

    public void addPhotoPlace(View view){
        dispatchTakePictureIntent();
    }//End addPhotoPlace

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }//End if
    }//End dispatchTakePictureIntent

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            placePicture.setImageBitmap(imageBitmap);
        }//End if
    }//End onActivityResult

    public void addPlace(View v){
        DataBase admin = new DataBase(this);
        String pName = placeName.getText().toString();
        String pLocation = location.getText().toString();
        int pScore = Integer.parseInt(score.getText().toString());
        String pTemp =  temperature.getText().toString();
        String pDescription = description.getText().toString();
        String pPicture = "";
        admin.addPlace(pName, pLocation, pScore, pTemp, pDescription, pPicture);
        Toast.makeText(this, "Se cargaron los datos del artículo",
                Toast.LENGTH_SHORT).show();
    }//End addPlace
}//End class
