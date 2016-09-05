package co.edu.udea.compumovil.gr06.lab2activities.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr06.lab2activities.R;
import co.edu.udea.compumovil.gr06.lab2activities.sqlitedb.DataBase;

public class AddUser extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText userName;
    private EditText userPassword;
    private EditText userAge;
    private EditText userEmail;
    private ImageView userPicture;
    public static Context context;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        userAge = (EditText) findViewById(R.id.userAge);
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPicture = (ImageView) findViewById(R.id.userPicture);
        context = this;
    }//End onCreate

    public void addPhoto(View view) {
        dispatchTakePictureIntent();
    }//End addPhoto


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
            userPicture.setImageBitmap(imageBitmap);
        }//End if
    }//End onActivityResult

    public void addUser(View v) {
        DataBase admin = new DataBase(this);
        String uName = userName.getText().toString();
        String uPass = userPassword.getText().toString();
        int uAge = Integer.parseInt(userAge.getText().toString());
        String uEmail = userEmail.getText().toString();
        byte[] uPicture = null;
        if(!admin.userExist(uName)){
            if(!admin.emailExist(uEmail)){
                admin.addUser(uName, uPass, uAge, uEmail, uPicture);
                Toast.makeText(this, "los datos han sido guardados",
                        Toast.LENGTH_SHORT).show();
            }//End if (!admin.emailExist())
            Toast.makeText(this, "Ya existe un usuario con esa cuenta de correo",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ya existe un usuario con ese nombre",
                    Toast.LENGTH_SHORT).show();
        }//End if (!admin.userExist())
    }//End addUser
}//End class


