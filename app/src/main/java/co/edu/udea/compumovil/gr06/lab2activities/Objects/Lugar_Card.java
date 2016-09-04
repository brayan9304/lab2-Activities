package co.edu.udea.compumovil.gr06.lab2activities.Objects;

import android.graphics.Bitmap;

/**
 * Created by jaime on 4/09/2016.
 */
public class Lugar_Card {

    private Bitmap imagenLugar;
    private String nombreLugar;
    private String descripcionLugar;
    private float puntuacion;

    public Lugar_Card(Bitmap imagen, String nombre, String descripcion, float puntuacion) {
        this.imagenLugar = imagen;
        this.nombreLugar = nombre;
        this.descripcionLugar = descripcion;
        this.puntuacion = puntuacion;
    }

    public Bitmap getImagenLugar() {
        return imagenLugar;
    }

    public void setImagenLugar(Bitmap imagenLugar) {
        this.imagenLugar = imagenLugar;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getDescripcionLugar() {
        return descripcionLugar;
    }

    public void setDescripcionLugar(String descripcionLugar) {
        this.descripcionLugar = descripcionLugar;
    }

    public float getPuntuación() {
        return puntuacion;
    }

    public void setPuntuación(float puntuación) {
        this.puntuacion = puntuación;
    }
}
