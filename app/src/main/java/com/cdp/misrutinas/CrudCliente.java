package com.cdp.misrutinas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;

import com.cdp.misrutinas.entidades.Clientes;

import java.util.ArrayList;

public class CrudCliente extends MRSQLiteHelper{
    Context context;

    public CrudCliente(Context contexto) {
        super(contexto);
        this.context = contexto;
    }

    //---------VALIDACIONES--------------

    private boolean areFieldsValid(FieldLengthValidation... fields) {
        for (FieldLengthValidation fieldValidation : fields) {
            String field = fieldValidation.field;
            int minLength = fieldValidation.minLength;
            int maxLength = fieldValidation.maxLength;

            if (field != null && (field.length() < minLength || field.length() > maxLength)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidEmail(SQLiteDatabase db, String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (email.matches(emailRegex) && !existeRegistro(db, email, "email")) {
            return true;
        }
        return false;
    }

    private boolean existeRegistro(SQLiteDatabase db, String value, String columna) {
        String[] columns = {columna};
        String selection = columna + "= ?";
        String[] selectionArgs = {value};

        Cursor cursor = db.query("Usuario", columns, selection, selectionArgs, null, null, null);

        boolean existe = cursor.moveToFirst();
        cursor.close();

        return existe;
    }


    //------------------------------------
    //"CREATE TABLE Usuario (id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username VARCHAR(20) UNIQUE , apellido VARCHAR(45), nombre VARCHAR(45), dni INTEGER,  email VARCHAR(75) NOT NULL,tel INTEGER, pass VARCHAR(16), active BOOLEAN, id_rol INTEGER, FOREIGN KEY (id_rol) REFERENCES Rol(id_rol))";
    public long insertarUsuario(String username, String email, String password, String nombre, String apellido, String dni) {
        SQLiteDatabase db = super.getWritableDatabase();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(context, "Los campos Username,Email y Contraseña son  obligatorios." , Toast.LENGTH_SHORT).show();
            db.close();
            return -1;
        }

        if (!areFieldsValid(
                new FieldLengthValidation(username, 4, 20),
                new FieldLengthValidation(email, 8, 75),
                new FieldLengthValidation(password, 8, 16)
        )) {
            if (username.length() < 4 || username.length() > 20) {
                Toast.makeText(context, "Username debe tener entre 4 y 20 caracteres.", Toast.LENGTH_SHORT).show();
            }

            if (email.length() < 8 || email.length() > 75) {
                Toast.makeText(context, "Email debe tener entre 8 y 75 caracteres.", Toast.LENGTH_SHORT).show();
            }

            if (password.length() < 8 || password.length() > 16) {
                Toast.makeText(context, "Password debe tener entre 8 y 16 caracteres.", Toast.LENGTH_SHORT).show();
            }

            db.close();
            return -1;
        }
        if (!isValidEmail(db, email)) {
            Toast.makeText(context, "El campo Email tiene un formato inválido o ya esta en uso", Toast.LENGTH_SHORT).show();
            db.close();
            return -1;
        }

        if (existeRegistro(db, username, "username")) {
            Toast.makeText(context, "El nombre de usuario ya existe.", Toast.LENGTH_SHORT).show();
            db.close();
            return -1;
        }

        try {
            ContentValues values = new ContentValues();
            values.put("username", username); // NOT NULL
            values.put("email", email); // NOT NULL
            values.put("pass", password); // NOT NULL
            values.put("nombre", nombre); // NULL
            values.put("apellido", apellido); // NULL
            values.put("dni", dni); // NULL
            values.put("id_rol", 1); // NOT NULL

            long idUsuario = db.insert("Usuario", null, values);

            if (idUsuario != -1) {
                db.close();
                return idUsuario;
            } else {
                db.close();
                return -1;
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al insertar el registro.", Toast.LENGTH_SHORT).show();
            db.close();
            return -1;
        }
    }



//------------------------------------
//"CREATE TABLE Socio (id_socio INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario))";
// "CREATE TABLE Entrenador (id_entrenador INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario))"

    public long insertarCliente(String nombre, String apellido, String dni, String email, String telefono, int idRol) {
        SQLiteDatabase db = super.getWritableDatabase();

        if (!areFieldsValid(
                new FieldLengthValidation(nombre, 1, 45),
                new FieldLengthValidation(apellido, 1, 45),
                new FieldLengthValidation(dni, 8, 8),
                new FieldLengthValidation(email, 8, 75),
                new FieldLengthValidation(telefono, 10, 10)
        ) || !isValidEmail(db, email)) {
            db.close();
            return -1;
        }

        ContentValues valuesUser = new ContentValues();
        valuesUser.put("nombre", nombre); //NULL
        valuesUser.put("apellido", apellido); //NULL
        valuesUser.put("dni", dni); //NULL
        valuesUser.put("email", email); //NOT NULL
        valuesUser.put("tel", telefono); //NOT NULL
        valuesUser.put("id_rol", idRol); //NOT NULL

        long idUsuario = db.insert("Usuario", null, valuesUser);

        String table = (idRol == 3) ? "Entrenador" : "Socio";

        if (idUsuario != -1) {
            ContentValues valuesCliente = new ContentValues();
            valuesCliente.put("id_usuario", idUsuario);
            long idCliente = db.insert(table, null, valuesCliente);

            db.close();

            return idCliente;
        } else {
            db.close();
            return -1;
        }
    }

    public ArrayList<Clientes> listarClientes(int idRol){
        SQLiteDatabase db = super.getWritableDatabase();

        ArrayList<Clientes> listaClientes = new ArrayList<>();
        Clientes cliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM Usuario WHERE id_rol = " + idRol, null);

        if (cursor.moveToFirst()){
            do {
                cliente = new Clientes();
                cliente.setId(cursor.getInt(0));
                cliente.setNombre(cursor.getString(3));
                cliente.setApellido(cursor.getString(2));

                listaClientes.add(cliente);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listaClientes;
    }

    public Clientes verCliente(int id){
        SQLiteDatabase db = super.getWritableDatabase();

        Clientes cliente = null;
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM Usuario WHERE id_usuario = " + id + " Limit 1", null );

        //"CREATE TABLE Usuario (id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username VARCHAR(20) UNIQUE , apellido VARCHAR(45), nombre VARCHAR(45), dni INTEGER,  email VARCHAR(75) NOT NULL,tel INTEGER, pass VARCHAR(16), active BOOLEAN, id_rol INTEGER, FOREIGN KEY (id_rol) REFERENCES Rol(id_rol))";
        if (cursor.moveToFirst()){
            cliente = new Clientes();
            cliente.setId(cursor.getInt(0));
            cliente.setNombre(cursor.getString(3));
            cliente.setApellido(cursor.getString(2));
            cliente.setDni(cursor.getString(4));
            cliente.setEmail(cursor.getString(5));
            cliente.setTel(cursor.getString(6));
        }
        cursor.close();
        return cliente;
    }
}
