package com.example.restaurante;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestauranteFragment extends Fragment {

    FloatingActionButton agregarrestaurante;

    public RestauranteFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurante, container, false);

        agregarrestaurante = view.findViewById(R.id.agregarRestaurante);
        agregarrestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irNuevoRestaurante();
            }
        });

        return view;
    }

    private void irNuevoRestaurante (){
        Intent intent = new Intent(getActivity(), registrarRestauranteActivity.class);
        startActivity(intent);
    }
}
