package com.cdp.misrutinas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        return 1;
    }












}
