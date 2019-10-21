package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.restaurante.modelo.Restaurante;
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
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_restaurante);
        //imagen = imagen.findViewById(R.id.imagen);
        nombre = findViewById(R.id.txtnombre);
        descripcion = findViewById(R.id.txtdescripcion);
        ubicacion = findViewById(R.id.txtubicacion);
        guardar = findViewById(R.id.guardar);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarRestaurante();
            }
        });
    }

    public void guardarRestaurante(){

        Restaurante restaurtante = new Restaurante(myRef.push().getKey(), nombre.getText().toString(),descripcion.getText().toString(),0,0,0);
        myRef.child("restaurante").child(restaurtante.getId()).setValue(restaurtante);


    }
}
