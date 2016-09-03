package co.edu.udea.compumovil.gr06.lab2activities.UI;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;

import co.edu.udea.compumovil.gr06.lab2activities.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcercaDe extends Fragment {

    private static final String ERROR_CARGA_IMAGEN = "no se pudo cargar la imagen";
    private static final String TAG_ERROR = "Error en AcercaDe";


    public AcercaDe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_acerca_de, container, false);
        ImageView logoUdea = (ImageView) fragment.findViewById(R.id.ImageAcercaDe);
        AssetManager assets = fragment.getContext().getAssets();
        Bitmap logo;
        try {
            logo = BitmapFactory.decodeStream(assets.open("Imagenes/logo-udea.png"));
            logoUdea.setImageBitmap(logo);
        } catch (IOException e) {
            Log.e(TAG_ERROR, "onCreateView:" + ERROR_CARGA_IMAGEN);
        }
        return fragment;
    }

}
