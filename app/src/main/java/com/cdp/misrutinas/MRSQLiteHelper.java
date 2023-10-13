package com.cdp.misrutinas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MRSQLiteHelper extends SQLiteOpenHelper {

    String[][] tables = new String[4][2]; //ESTO VA CAMBIANDO A MEDIDA LE VAN AÑADIENDO TABLAS. DEBE TERMINAR EN [12][2].

    public MRSQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);

        tables[0][0] = "CREATE TABLE Rol (id_rol INTEGER PRIMARY KEY AUTOINCREMENT, nombre_rol CHAR(10) NOT NULL)";
        tables[0][1] = "Rol";

        tables[1][0] = "CREATE TABLE Usuario (id_usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, username VARCHAR(45) NOT NULL, apellido VARCHAR(45), nombre VARCHAR(45), dni INTEGER, fec_nac DATE, email VARCHAR(45), pass VARCHAR(45), active BOOLEAN, id_rol INTEGER, FOREIGN KEY (id_rol) REFERENCES Rol(id_rol))";
        tables[1][1] = "Usuario";

        tables[2][0] = "CREATE TABLE Socio (id_socio INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario))";
        tables[2][1] = "Socio";

        tables[3][0] = "CREATE TABLE Entrenador (id_entrenador INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, id_usuario INTEGER, FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario))";
        tables[3][1] = "Entrenador";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(int i = 0; i < tables.length; i++) {
            String tableSQL = tables[i][0];
            db.execSQL(tableSQL);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        for(int i = 0; i < tables.length; i++) {
            String tableName = tables[i][1];
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
        onCreate(db);
    }
}