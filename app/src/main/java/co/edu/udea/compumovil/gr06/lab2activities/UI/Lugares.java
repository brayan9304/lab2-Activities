package co.edu.udea.compumovil.gr06.lab2activities.UI;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr06.lab2activities.Objects.AdapterCard;
import co.edu.udea.compumovil.gr06.lab2activities.Objects.Lugar_Card;
import co.edu.udea.compumovil.gr06.lab2activities.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lugares extends Fragment {
    ImageView lugar;
    List<Lugar_Card> lugares;
    RecyclerView recycler;
    RecyclerView.Adapter adaptador;
    RecyclerView.LayoutManager layout;

    public Lugares() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragment = inflater.inflate(R.layout.fragment_lugares, container, false);
        lugar = (ImageView) fragment.findViewById(R.id.imagenLugarCard);
        Bitmap lugarImagen = null;
        try {
            AssetManager temp = fragment.getContext().getAssets();
            lugarImagen = BitmapFactory.decodeStream(temp.open("Imagenes/Torre-Eiffel-vista-panoramica.jpg"));
            //lugar.setImageBitmap(lugarImagen);
            lugares = new ArrayList<>();
            lugares.add(new Lugar_Card(lugarImagen, "Torre Eiffel", "es bonita y muy grande est sdsff sdfds f rewr  eld asdo sdn sd", 3.0F));
            lugarImagen = BitmapFactory.decodeStream(temp.open("Imagenes/lugares-turisticos-kipcool.jpg"));
            lugares.add(new Lugar_Card(lugarImagen, "Kipcool", "información rara sobre este lugar pero algo mas raro es como copio XD", 5.0F));
            lugarImagen = BitmapFactory.decodeStream(temp.open("Imagenes/Rio-de-Janeiro.jpg"));
            lugares.add(new Lugar_Card(lugarImagen, "Rio e Janeiro", "Queda en brasil", 3.5F));
        } catch (IOException e) {
            Log.e("CARGA DE DATOS", "onCreateView: ERROR NO CARGO IMAGEN");
        }


        recycler = (RecyclerView) fragment.findViewById(R.id.recicladorLugares);
        recycler.hasFixedSize();

        layout = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(layout);

        adaptador = new AdapterCard(lugares);
        ((AdapterCard) adaptador).setOnItemClickListenerPropio(new AdapterCard.OnItemClickListenerPropio() {
            @Override
            public void onItemClicked(View view, int position) {
                Lugar_Card lugarseleccinado = lugares.get(position);
                Toast.makeText(fragment.getContext(), lugarseleccinado.getNombreLugar(), Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(adaptador);


        return fragment;
    }

}
