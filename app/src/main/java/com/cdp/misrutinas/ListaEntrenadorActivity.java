package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListaEntrenadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_entrenador);
    }

    public void irCrearEntrenador(View view){
        Intent intent=new Intent(ListaEntrenadorActivity.this, CrearEntrenadorActivity.class);
        startActivity(intent);
    }


    public void btnVolverDashboard(View view){
        Intent intent=new Intent(ListaEntrenadorActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}