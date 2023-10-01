package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListaSocioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_socio);
    }

    public void btnVolverDashboard(View view){
        Intent intent=new Intent(ListaSocioActivity.this,DashboardActivity.class);
        startActivity(intent);
    }

    public void btnEditarSocio(View view) {
        Intent intent=new Intent(ListaSocioActivity.this,EditarSocioActivity.class);
        startActivity(intent);
    }
}