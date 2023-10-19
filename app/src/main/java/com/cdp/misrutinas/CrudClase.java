package com.cdp.misrutinas;

import android.content.Context;

public class CrudClase extends MRSQLiteHelper{

    Context context;

    public CrudClase(Context contexto) {
        super(contexto);
        this.context = contexto;
    }
    public long insertarClase (String nombreClase,String precioClase, String descripcionClase){

        MRSQLiteHelper usdbh = new MRSQLiteHelper(context);
        SQLiteDatabase db = usdbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombreClase);
        values.put("precio", precioClase);
        values.put("descripcion", descripcionClase);

        long id = db.insert("Clase", null, values);

        return id;

    }

}
