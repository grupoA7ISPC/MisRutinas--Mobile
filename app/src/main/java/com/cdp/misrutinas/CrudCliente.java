package com.cdp.misrutinas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;

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











}
