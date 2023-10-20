package com.cdp.misrutinas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearClaseActivity extends AppCompatActivity {

    private EditText editId, editNombre, editPrecio,editDescripcion;
    private Button btnAgregar, btnActualizar, btnEliminar, btnConsultar;
    private CrudClase CrudClase;
    private SQLiteDatabase db;


    //public void irListaClase(View view){
    //    Intent intent=new Intent(MainActivity.this, ListaClaseActivity.class);
    //    startActivity(intent);
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_clase);

        //editId = findViewById(R.id.editId);
        editNombre = findViewById(R.id.editNombre);
        editPrecio = findViewById(R.id.editPrecio);
        editDescripcion = findViewById(R.id.editDescripcion);
        btnAgregar = findViewById(R.id.btnAgregar);
        //btnActualizar = findViewById(R.id.btnActualizar);
        //btnEliminar = findViewById(R.id.btnEliminar);
        //btnConsultar = findViewById(R.id.btnLista);
        //txtResultado = findViewById(R.id.txtResultado);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrudClase = new CrudClase(CrearClaseActivity.this);
                long id = CrudClase.insertarClase(editNombre.getText().toString(),editPrecio.getText().toString(),editDescripcion.getText().toString());
                if (id > 0){
                    Toast.makeText(getApplicationContext(),"REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                }else {
                    Toast.makeText(getApplicationContext(),"ERROR AL GUARDAR REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void limpiar(){
        editNombre.setText("");
        editPrecio.setText("");
        editDescripcion.setText("");
    }
}