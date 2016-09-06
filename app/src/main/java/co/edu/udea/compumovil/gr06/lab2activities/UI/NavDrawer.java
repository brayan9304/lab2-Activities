package co.edu.udea.compumovil.gr06.lab2activities.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import co.edu.udea.compumovil.gr06.lab2activities.R;
import co.edu.udea.compumovil.gr06.lab2activities.Validations.Sesion;
import co.edu.udea.compumovil.gr06.lab2activities.sqlitedb.DataBase;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Fragment acercade;
    Fragment perfil;
    Fragment listaLugares;
    FloatingActionButton fab;
    AlertDialog.Builder mensaje;
    Sesion sesion;
    int temp;
    public static byte[] photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sesion = new Sesion(getApplicationContext());
        //si no esta logueado finaliza esta actividad y carga login
        if (!sesion.validarLog()) {
            finish();
        }
        Intent i = getIntent();
        photo = i.getByteArrayExtra(DataBase.COLUMN_USER_PICTURE);
        mensaje = new AlertDialog.Builder(this);
        mensaje.setMessage(R.string.mensaje_cerrar_sesion);
        mensaje.setPositiveButton(R.string.opcion_positiva, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                sesion.cerrarSesion();
                finish();
                Toast.makeText(getApplicationContext(), R.string.txtsesionCerrada, Toast.LENGTH_SHORT).show();
            }
        });
        mensaje.setNegativeButton(R.string.opcion_negativa, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent place = new Intent(getApplicationContext(), AddPlace.class);
                startActivity(place);

            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        acercade = new AcercaDe();
        perfil = new Perfil();
        perfil.setArguments(savedInstanceState);
        listaLugares = new Lugares();

        if (getSupportFragmentManager().findFragmentByTag("lugares") != null) {
            fab.setVisibility(View.VISIBLE);
            Log.e("que tal eh?", "onCreate: estado 1");
        } else {
            fab.setClickable(false);
        }

    }

    @Override
    protected void onResume() {
        replaceFragment(listaLugares, "lugares");
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            mensaje.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lugares) {
            replaceFragment(listaLugares, "lugares");
        } else if (id == R.id.nav_perfil) {
            replaceFragment(perfil, "perfil");
        } else if (id == R.id.nav_acercaDe) {
            replaceFragment(acercade, "acercaDe");
        } else if (id == R.id.nav_cerrarsesion) {
            mensaje.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(android.support.v4.app.Fragment fragment, String TAG) {

        FragmentTransaction adminFrag = getSupportFragmentManager().beginTransaction();
        adminFrag.replace(R.id.NavContainerFragments, fragment, TAG);
        adminFrag.disallowAddToBackStack();
        adminFrag.commit();
        if (fragment instanceof Lugares) {
            Animation entradaFab = AnimationUtils.loadAnimation(getApplication(), R.anim.floating_button_in);
            if (!fab.isClickable()) {
                fab.startAnimation(entradaFab);
            }
            fab.setClickable(true);
            fab.setFocusable(true);
        } else {
            Animation salidaFab = AnimationUtils.loadAnimation(getApplication(), R.anim.floating_button_out);
            if (fab.isClickable()) {
                fab.startAnimation(salidaFab);
            }
            fab.setClickable(false);
            fab.setFocusable(false);
        }

    }


}
