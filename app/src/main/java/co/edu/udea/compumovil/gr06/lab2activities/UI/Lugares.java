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
import java.util.Iterator;
import java.util.List;

import co.edu.udea.compumovil.gr06.lab2activities.Objects.AdapterCard;
import co.edu.udea.compumovil.gr06.lab2activities.Objects.Lugar_Card;
import co.edu.udea.compumovil.gr06.lab2activities.Objects.Place;
import co.edu.udea.compumovil.gr06.lab2activities.R;
import co.edu.udea.compumovil.gr06.lab2activities.sqlitedb.DataBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lugares extends Fragment {
    ImageView lugar;
    List<Lugar_Card> lugares;
    RecyclerView recycler;
    RecyclerView.Adapter adaptador;
    RecyclerView.LayoutManager layout;
    DataBase admin;

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
        admin = new DataBase(fragment.getContext());
        List<Place> lugaresPla = admin.getAllPlaces();
        Log.e("lugares", "onCreateView: " + lugaresPla.size());
        lugares = new ArrayList<>();
        Iterator iterator = lugaresPla.iterator();

        while (iterator.hasNext()) {
            Place place = (Place) iterator.next();
            if (place.getPicture() == null) {

                try {
                    AssetManager am = fragment.getContext().getAssets();
                    lugarImagen = BitmapFactory.decodeStream(am.open("Imagenes/Rio-de-Janeiro.jpg"));
                    lugares.add(new Lugar_Card(lugarImagen, place.getNamePlace(), place.getDescription(), place.getScore()));
                } catch (Exception e) {
                }
            } else {
                lugares.add(new Lugar_Card(BitmapFactory.decodeByteArray(place.getPicture(), 0, place.getPicture().length), place.getNamePlace(), place.getDescription(), place.getScore()));
            }
        }//End while(iterator.hasNext())

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
