package co.edu.udea.compumovil.gr06.lab2activities.UI;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr06.lab2activities.R;
import co.edu.udea.compumovil.gr06.lab2activities.Validations.ValidationLog;

public class Loggin extends AppCompatActivity implements View.OnFocusChangeListener {

    private TextView usuario;
    private TextView clave;
    private int colorBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        usuario = (TextView) findViewById(R.id.TxtUsuario);
        colorBase = usuario.getCurrentHintTextColor();
        clave = (TextView) findViewById(R.id.PassUsuario);
        usuario.setOnFocusChangeListener(this);
        clave.setOnFocusChangeListener(this);

    }

    public  void Onclick(View e){

        switch (e.getId()){

            case R.id.BtnLog:
                Log.i("En inciar Sesión", "Onclick: Iniciar");

                String userText = usuario.getText().toString();

                String userPass = clave.getText().toString();
                if(ValidationLog.validarCampo(userText)&&ValidationLog.validarCampo(userPass)){

                }else{
                    if(!ValidationLog.validarCampo(userText)){
                        usuario.setHintTextColor(getResources().getColor(R.color.colorErrorCamp));
                    }else{
                        usuario.setHintTextColor(ColorStateList.valueOf(colorBase));
                    }
                    if(!ValidationLog.validarCampo(userPass)){
                        clave.setHintTextColor(getResources().getColor(R.color.colorErrorCamp));
                    }
                    Toast.makeText(this, getResources().getString(R.string.REQUIRED_CAMPS),Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.txtNuevaCuenta:
                Log.i("EN nueva Cuenta", "Onclick: Nueva Cuenta");
                break;
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()){

            case R.id.TxtUsuario:
                if(!hasFocus){
                    String userText = usuario.getText().toString();
                    if(!ValidationLog.validarCampo(userText)){
                        usuario.setHintTextColor(getResources().getColor(R.color.colorErrorCamp));
                        Toast.makeText(this, getResources().getString(R.string.REQUIRED_CAMP) ,Toast.LENGTH_SHORT).show();
                    }else{
                        usuario.setHintTextColor(ColorStateList.valueOf(colorBase));
                    }
                    Log.i("En txtUsuario", "FocusLost: TxtUsuario");
                }

                break;

            case R.id.PassUsuario:
                if(!hasFocus){
                    String userPass = clave.getText().toString();
                    if(!ValidationLog.validarCampo(userPass)){
                        clave.setHintTextColor(getResources().getColor(R.color.colorErrorCamp));
                        Toast.makeText(this, getResources().getString(R.string.REQUIRED_CAMP) ,Toast.LENGTH_SHORT).show();
                    }else{
                        usuario.setHintTextColor(ColorStateList.valueOf(colorBase));
                    }
                }

                Log.i("En txtPass", "FocusLost: TxtPass");
                break;
        }
    }
}
