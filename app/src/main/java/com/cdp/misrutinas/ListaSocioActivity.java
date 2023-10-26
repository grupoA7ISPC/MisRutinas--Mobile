package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cdp.misrutinas.entidades.Cliente;
import java.util.ArrayList;

public class ListaSocioActivity extends AppCompatActivity {
    private RecyclerView listaSocios;
    private CrudCliente crud;
    private ArrayList<Cliente> listaArraySocios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_socio);

        listaSocios = findViewById(R.id.rvListaSocios);
        listaSocios.setLayoutManager(new LinearLayoutManager(this));

        crud = new CrudCliente(this);

        listaArraySocios = new ArrayList<>();
    }

    public void btnVolverDashboard(View view){
        Intent intent=new Intent(ListaSocioActivity.this,DashboardActivity.class);
        startActivity(intent);
    }

    public void irCrearSocio(View view) {
        Intent intent=new Intent(ListaSocioActivity.this,CrearSocioActivity.class);
        startActivity(intent);
    }
}