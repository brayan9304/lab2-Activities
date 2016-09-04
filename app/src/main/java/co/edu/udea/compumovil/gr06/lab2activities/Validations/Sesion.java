package co.edu.udea.compumovil.gr06.lab2activities.Validations;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import co.edu.udea.compumovil.gr06.lab2activities.UI.Loggin;

/**
 * Created by jaime on 4/09/2016.
 */
public class Sesion {

    SharedPreferences preferencias;
    SharedPreferences.Editor edt;
    Context contextoApp;
    int MODO_PRIVADO = 0;
    private static final String NOMBRE_PREF = "AndroidHivePref";
    private static final String ESTA_LOGGEADO = "EstaLogueado";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_CONTRASEÑA = "contraseña";

    public Sesion(Context context) {
        this.contextoApp = context;
        preferencias = contextoApp.getSharedPreferences(NOMBRE_PREF, MODO_PRIVADO);
        edt = preferencias.edit();
    }

    public void crearSesion(String nombre, String clave) {
        edt.putBoolean(ESTA_LOGGEADO, true);
        edt.putString(KEY_NOMBRE, nombre);
        edt.putString(KEY_CONTRASEÑA, clave);
        edt.commit();
    }

    public void validarLog() {
        // Check login status
        if (!this.estaLoggueado()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(contextoApp, Loggin.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            contextoApp.startActivity(i);
        }

    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NOMBRE, preferencias.getString(KEY_NOMBRE, null));

        // user email id
        user.put(KEY_CONTRASEÑA, preferencias.getString(KEY_CONTRASEÑA, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void cerrarSesion() {
        // Clearing all data from Shared Preferences
        edt.clear();
        edt.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(contextoApp, Loggin.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        contextoApp.startActivity(i);
    }

    public boolean estaLoggueado() {
        return preferencias.getBoolean(ESTA_LOGGEADO, false);
    }

}
