package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CrearEntrenadorActivity extends AppCompatActivity {

    private CrudCliente crud;
    private EditText textNombre;
    private EditText textApellido;
    private EditText textDNI;
    private EditText textEmail;
    private EditText textPhone;

    private Button btnGuardar;

    private TextView textoResultado;

    private int idRol = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_entrenador);
    }

    public void btnVolverListaEntrenador(View view){
        Intent intent=new Intent(CrearEntrenadorActivity.this, ListaEntrenadorActivity.class);
        startActivity(intent);
    }

    public void btnCancelar(View view){
        Intent intent=new Intent(CrearEntrenadorActivity.this, ListaEntrenadorActivity.class);
        startActivity(intent);
    }


}