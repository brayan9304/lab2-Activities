package co.edu.udea.compumovil.gr06.lab2activities.UI;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.udea.compumovil.gr06.lab2activities.R;
import co.edu.udea.compumovil.gr06.lab2activities.Validations.Sesion;
import co.edu.udea.compumovil.gr06.lab2activities.sqlitedb.DataBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class Perfil extends Fragment {

    Sesion sesion;
    Bitmap foto;

    public Perfil() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_perfil, container, false);
        if(NavDrawer.photo != null) {
            foto = BitmapFactory.decodeByteArray(NavDrawer.photo, 0, NavDrawer.photo.length);
            ImageView userFoto = (ImageView) fragment.findViewById(R.id.UserPhoto);
            userFoto.setImageBitmap(foto);
        }
        sesion = new Sesion(getContext());
        String nombre =sesion.getUserDetails().get("nombre");
        String clave = sesion.getUserDetails().get("clave");
        DataBase admin = new DataBase(this.getContext());
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor cursor = admin.getUser(db, nombre, clave);
        cursor.moveToFirst();
        byte[] img = cursor.getBlob(2);
        if (img != null) {
            Bitmap imagen = Lugares.decodeSampledBitmapFromByte(img, 300, 300);
            ImageView userFoto = (ImageView) fragment.findViewById(R.id.UserPhoto);
            userFoto.setImageBitmap(imagen);

        }
        String edad = sesion.getUserDetails().get("edad");
        TextView nombreTV = (TextView)fragment.findViewById(R.id.UserName);
        nombreTV.setText(nombre);
        TextView edadTV = (TextView)fragment.findViewById(R.id.UserAge);
        edadTV.setText("Edad  "+edad);
        return fragment;
    }

}
