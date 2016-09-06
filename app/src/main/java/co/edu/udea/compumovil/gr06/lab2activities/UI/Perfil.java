package co.edu.udea.compumovil.gr06.lab2activities.UI;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import co.edu.udea.compumovil.gr06.lab2activities.R;
import co.edu.udea.compumovil.gr06.lab2activities.Validations.Sesion;

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
        String edad = sesion.getUserDetails().get("edad");
        TextView nombreTV = (TextView)fragment.findViewById(R.id.UserName);
        nombreTV.setText(nombre);
        TextView edadTV = (TextView)fragment.findViewById(R.id.UserAge);
        edadTV.setText("Edad  "+edad);
        return fragment;
    }

}
