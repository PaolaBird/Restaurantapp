package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ContenedorActivity extends AppCompatActivity {

    TabItem restaurante, favoritos, perfil;
    TabLayout menu;
    ViewPager contenedor;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);
        restaurante = findViewById(R.id.restaurantes);
        favoritos = findViewById(R.id.favoritos);
        perfil = findViewById(R.id.perfil);
        menu = findViewById(R.id.menu);
        contenedor = findViewById(R.id.contenedor);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), menu.getTabCount());
        contenedor.setAdapter(pagerAdapter);
        contenedor.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(menu));

    }
}
