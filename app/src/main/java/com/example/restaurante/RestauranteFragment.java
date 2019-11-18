package com.example.restaurante;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurante.modelo.Restaurante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestauranteFragment extends Fragment {

    FloatingActionButton agregarrestaurante;
    RecyclerView recycler_restaurante;

    public RestauranteFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurante, container, false);
        recycler_restaurante = (RecyclerView)view.findViewById(R.id.recycler_restaurante);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_restaurante.setLayoutManager(linearLayoutManager);
        RestauranteAdapter restauranteAdapter= new RestauranteAdapter(obtenerRedCloud(),getActivity(),R.layout.cardview);
        recycler_restaurante.setAdapter(restauranteAdapter);
        agregarrestaurante = view.findViewById(R.id.agregarRestaurante);
        recycler_restaurante.setAdapter(restauranteAdapter);
        agregarrestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irNuevoRestaurante();
            }
        });

        return view;
    }

    private ArrayList<Restaurante> obtenerRedCloud() {
        ArrayList<Restaurante> restaurantes = new ArrayList();
        restaurantes.add(new Restaurante("01", "La mazorca", "Esta es la descripción", 10000000, 122222, 2, "https://www.restaurantelamazorca.com/wp-content/uploads/2015/07/logo-nuevo-2019.png"));
        restaurantes.add(new Restaurante("01", "La mazorca", "Esta es la descripción", 10000000, 122222, 2, "https://www.restaurantelamazorca.com/wp-content/uploads/2015/07/logo-nuevo-2019.png"));
        restaurantes.add(new Restaurante("01", "La mazorca", "Esta es la descripción", 10000000, 122222, 2, "https://www.restaurantelamazorca.com/wp-content/uploads/2015/07/logo-nuevo-2019.png"));
        restaurantes.add(new Restaurante("01", "La mazorca", "Esta es la descripción", 10000000, 122222, 2, "https://www.restaurantelamazorca.com/wp-content/uploads/2015/07/logo-nuevo-2019.png"));
        return restaurantes;
    }

    private void irNuevoRestaurante (){
        Intent intent = new Intent(getActivity(), registrarRestauranteActivity.class);
        startActivity(intent);
    }
}
