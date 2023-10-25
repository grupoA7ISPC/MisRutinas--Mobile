package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {
    private CrudCliente crud;

    private EditText textUserName;
    private EditText textNombre;
    private EditText textApellido;
    private EditText textDNI;
    private EditText textEmail;

    private EditText textPassword;

    private Button btnInsertar;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        textUserName = findViewById(R.id.textUserName);
        textNombre = findViewById(R.id.textNombre);
        textApellido = findViewById(R.id.textApellido);
        textDNI = findViewById(R.id.textDNI);
        textEmail = findViewById(R.id.textEmail);
        textPassword = findViewById(R.id.textPassword);
        btnInsertar = findViewById(R.id.btnInsertar);
        textoResultado = findViewById(R.id.textoResultado);

        crud = new CrudCliente(this);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = textUserName.getText().toString();
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();
                String nombre = textNombre.getText().toString();
                String apellido = textApellido.getText().toString();
                String dni = textDNI.getText().toString();

                long id = crud.insertarUsuario(username, email, password, nombre, apellido, dni);

                if (id != -1) {
                    textoResultado.setText("Registro insertado con ID: " + id);
                } else {
                    textoResultado.setText("Error al insertar el registro.");
                }
            }
        });

    }



    public void btnVolverRegistro(View view){
        Intent intent=new Intent(RegistroActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void btnRegistro(View view){
//        Intent intent=new Intent(RegistroActivity.this,IniciarSesionActivity.class);
//        startActivity(intent);
    }
}