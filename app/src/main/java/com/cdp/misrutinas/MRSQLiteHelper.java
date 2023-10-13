package com.cdp.misrutinas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MRSQLiteHelper extends SQLiteOpenHelper {

    String[][] tables = new String[8][2]; //Esto después cambia a la cantidad de tablas total: [12][2]

    public MRSQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);

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