package co.edu.udea.compumovil.gr06.lab2activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import co.edu.udea.compumovil.gr06.lab2activities.sqlitedb.DataBase;

public class AddUser extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText userId;
    private EditText userName;
    private EditText userPassword;
    private EditText userAge;
    private EditText userEmail;
    private ImageView userPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userId = (EditText) findViewById(R.id.userID);
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        userAge = (EditText) findViewById(R.id.userAge);
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPicture =  (ImageView) findViewById(R.id.userPicture);
    }

    public void addPhoto(View view){
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
            userPicture.setImageBitmap(imageBitmap);

        }
    }
    public void addUser(View v){

        DataBase admin = new DataBase(this, "root", null, 2);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(DataBase.COLUMN_USER_ID, Integer.parseInt(userId.getText().toString()));
        registro.put(DataBase.COLUMN_USER_NAME, userName.getText().toString());
        registro.put(DataBase.COLUMN_USER_PASSWORD, userPassword.getText().toString());
        registro.put(DataBase.COLUMN_USER_AGE, userAge.getText().toString());
        registro.put(DataBase.COLUMN_USER_EMAIL, userEmail.getText().toString());
        registro.put(DataBase.COLUMN_USER_PICTURE,"oou");
        bd.insert(DataBase.USER_TABLE, null, registro);
        bd.close();
        Toast.makeText(this, "Se cargaron los datos del artículo",
                Toast.LENGTH_SHORT).show();
    }

}
