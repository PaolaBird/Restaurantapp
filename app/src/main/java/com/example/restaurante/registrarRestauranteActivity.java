package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registrarRestauranteActivity extends AppCompatActivity {
    ImageView imagen;
    TextInputEditText nombre;
    TextInputEditText descripcion;
    TextInputEditText ubicacion;
    Button guardar;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_restaurante);
        imagen = imagen.findViewById(R.id.imagen);
        nombre = nombre.findViewById(R.id.txtnombre)
        descripcion = descripcion.findViewById(R.id.descripcionrestaurante);
        ubicacion = ubicacion.findViewById(R.id.txtubicacion);
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarRestaurante();
            }
        });
    }

    public void guardarRestaurante(){

    }
}
