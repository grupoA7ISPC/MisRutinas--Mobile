package com.cdp.misrutinas;

import android.content.Context;

public class CrudClase extends MRSQLiteHelper{

    Context context;

    public CrudClase(Context contexto) {
        super(contexto);
        this.context = contexto;
    }
}
