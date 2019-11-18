package com.example.restaurante;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurante.modelo.Restaurante;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestauranteAdapter  extends RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder> {

    ArrayList<Restaurante> restaurante;
    Context context;
    int recurso;

    public RestauranteAdapter(ArrayList<Restaurante> restaurante, Context context, int recurso) {
        this.restaurante = restaurante;
        this.context = context;
        this.recurso = recurso;
    }

    @NonNull
    @Override
    public RestauranteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new RestauranteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteViewHolder holder, int position) {
        Restaurante restaurante = this.restaurante.get(position);
        holder.nombrerestaurante.setText(restaurante.getNombre());
        holder.descripcionrestaurante.setText(restaurante.getDescripcion());
       Picasso.get().load(restaurante.getImagen()).into(holder.imagen); //Para cargar imagenes desde a red, se pueden cargar de los 3 tipos (disco, memoria, red)

    }

    @Override
    public int getItemCount() {
        return restaurante.size();
    }

    static class RestauranteViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen,localizar, compartir;
        TextView nombrerestaurante, descripcionrestaurante;
        CheckBox megusta;

        public RestauranteViewHolder(@NonNull View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.imagen);
            nombrerestaurante = itemView.findViewById(R.id.nombrerestaurante);
            descripcionrestaurante = itemView.findViewById(R.id.descripcionrestaurante);
            megusta = itemView.findViewById(R.id.megusta);
            localizar = itemView.findViewById(R.id.localizar);
            compartir = itemView.findViewById(R.id.compartir);
        }
    }

}
