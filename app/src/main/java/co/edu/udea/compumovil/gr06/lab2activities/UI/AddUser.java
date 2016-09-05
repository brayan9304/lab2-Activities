package co.edu.udea.compumovil.gr06.lab2activities.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

import co.edu.udea.compumovil.gr06.lab2activities.Objects.Place;
import co.edu.udea.compumovil.gr06.lab2activities.R;
import co.edu.udea.compumovil.gr06.lab2activities.Validations.ValidationLog;
import co.edu.udea.compumovil.gr06.lab2activities.sqlitedb.DataBase;

public class AddUser extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText userName;
    private EditText userPassword;
    private EditText userAge;
    private EditText userEmail;
    private EditText userPassword2;
    private ImageView userPicture;
    public static Context context;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        userPassword2 = (EditText) findViewById(R.id.userPassword2);
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

        List<Place> all = admin.getAllPlaces();
        Log.d("AllPlaces", ""+all.size());
        Iterator iterator = all.iterator();
        while(iterator.hasNext()){
            Place p = (Place)iterator.next();
            Log.d("_id", ""+p.getPlaceId());
            Log.d("name", ""+p.getNamePlace());
            Log.d("location", ""+p.getLocation());
            Log.d("temperature", ""+p.getTemperature());
            Log.d("picture", ""+p.getPicture());

        }
        String uName = userName.getText().toString();
        String uPass = userPassword.getText().toString();
        String uPass2 = userPassword2.getText().toString();
        int uAge;
        String uEmail = userEmail.getText().toString();
        byte[] uPicture = null;
        if (ValidationLog.validarCampo(userAge.getText().toString())) {
            uAge = Integer.parseInt(userAge.getText().toString());
            if (ValidationLog.validarCampo(uName) && (ValidationLog.validarCampo(uPass)) &&
                    (ValidationLog.validarCampo(uPass2)) && (ValidationLog.validarCampo(uEmail))) {
                if (uPass.equals(uPass2)) {
                    if (!validarEmail(uEmail)) {
                        if (!admin.userExist(uName)) {
                            if (!admin.emailExist(uEmail)) {
                                admin.addUser(uName, uPass, uAge, uEmail, uPicture);
                                Toast.makeText(this, "los datos han sido guardados",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            }//End if (!admin.emailExist())
                            Toast.makeText(this, "Ya existe un usuario con esa cuenta de correo",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Nombre de usuario no disponible",
                                    Toast.LENGTH_SHORT).show();
                        }//End if (!admin.userExist())
                    } else {
                        Toast.makeText(this, "Email incorrecto",
                                Toast.LENGTH_SHORT).show();
                    }//End if(validarEmail(uEmail))
                } else {
                    Toast.makeText(this, "Las contrasenas no coinciden",
                            Toast.LENGTH_SHORT).show();
                }//End  if(uPass.equals(uPass2))
            } else {
                Toast.makeText(this, "Campos en blancos",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Espacios en blancos",
                    Toast.LENGTH_SHORT).show();
        }
    }//End addUser

    public boolean validarEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }//End validarEmail
}//End class


