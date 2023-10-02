package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditarSocioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_socio);
    }

    public void btnVolverEditarSocio(View view){
        Intent intent=new Intent(EditarSocioActivity.this,ListaSocioActivity.class);
        startActivity(intent);
    }

    public void btnGuardarSocio(View view){
        Intent intent=new Intent(EditarSocioActivity.this,ListaSocioActivity.class);
        startActivity(intent);
    }

    public void btnEliminarSocio(View view){
        Intent intent=new Intent(EditarSocioActivity.this,ListaSocioActivity.class);
        startActivity(intent);
    }
}