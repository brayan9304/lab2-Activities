package co.edu.udea.compumovil.gr06.lab2activities.Objects;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.compumovil.gr06.lab2activities.R;

/**
 * Created by jaime on 4/09/2016.
 */
public class AdapterCard extends RecyclerView.Adapter<AdapterCard.LugaresViewHolder> {

    List<Lugar_Card> lugares;
    private static OnItemClickListenerPropio onItemClickListenerPropio;

    public interface OnItemClickListenerPropio {
        void onItemClicked(View view, int position);
    }

    public static class LugaresViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imagenLugar;
        TextView nombre, descripcion;
        RatingBar puntuacion;

        public LugaresViewHolder(final View itemView) {
            super(itemView);
            imagenLugar = (ImageView) itemView.findViewById(R.id.imagenLugarCard);
            nombre = (TextView) itemView.findViewById(R.id.txtnombreCard);
            descripcion = (TextView) itemView.findViewById(R.id.txtDescCard);
            puntuacion = (RatingBar) itemView.findViewById(R.id.puntuacionCard);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = LugaresViewHolder.super.getAdapterPosition();
            onItemClickListenerPropio.onItemClicked(v, position);
        }
    }

    public AdapterCard(List lugares) {
        this.lugares = lugares;
    }


    public void setOnItemClickListenerPropio(OnItemClickListenerPropio listener) {
        onItemClickListenerPropio = listener;
    }

    @Override
    public LugaresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_lugar, parent, false);
        return new LugaresViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(LugaresViewHolder holder, int position) {
        holder.imagenLugar.setImageBitmap(lugares.get(position).getImagenLugar());
        holder.nombre.setText(lugares.get(position).getNombreLugar());
        holder.descripcion.setText(lugares.get(position).getDescripcionLugar());
        holder.puntuacion.setRating(lugares.get(position).getPuntuación());

    }

    @Override
    public int getItemCount() {
        return lugares.size();
    }
}
