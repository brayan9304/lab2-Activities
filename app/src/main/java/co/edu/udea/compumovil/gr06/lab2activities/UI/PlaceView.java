package co.edu.udea.compumovil.gr06.lab2activities.UI;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.udea.compumovil.gr06.lab2activities.Objects.Place;
import co.edu.udea.compumovil.gr06.lab2activities.R;
import co.edu.udea.compumovil.gr06.lab2activities.sqlitedb.DataBase;

public class PlaceView extends AppCompatActivity {
    private ImageView foto;
    private TextView nombre;
    private TextView ubicacion;
    private TextView puntaje;
    private TextView temperatura;
    private TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_view);
        Intent intent = getIntent();
        String placeName = intent.getStringExtra(Place.PLACE_NAME);
        foto = (ImageView) findViewById(R.id.viewPlacePhoto);
        nombre = (TextView) findViewById(R.id.viewPlaceName);
        ubicacion = (TextView) findViewById(R.id.viewPlaceLocation);
        puntaje = (TextView) findViewById(R.id.viewPlaceScore);
        temperatura = (TextView) findViewById(R.id.viewPlaceTemperature);
        descripcion = (TextView) findViewById(R.id.viewPlaceDescription);
        DataBase admin = new DataBase(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Place place = admin.getPlace(bd,placeName);
        loadView(place);
        bd.close();
    }

    private void loadView(Place place) {
        foto.setImageBitmap(BitmapFactory.decodeByteArray(place.getPicture(), 0, place.getPicture().length));
        nombre.setText(place.getNamePlace());
        ubicacion.setText(place.getLocation());
        temperatura.setText(place.getTemperature());
        puntaje.setText(Float.toString(place.getScore()) + " grados centigrados");
        descripcion.setText(place.getDescription());

    }


}
