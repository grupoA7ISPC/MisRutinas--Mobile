package com.cdp.misrutinas.adaptadores;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.misrutinas.R;
import com.cdp.misrutinas.entidades.Clientes;

import java.util.ArrayList;

public class ListaSociosAdapter extends RecyclerView.Adapter<ListaSociosAdapter.SocioViewHolder> {
    ArrayList<Clientes> listaSocios;
    public ListaSociosAdapter(ArrayList<Clientes> listaSocios) {
        this.listaSocios = listaSocios;
    }

    @NonNull
    @Override
    public SocioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_socio, null, false);
        return new SocioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SocioViewHolder holder, int position) {
        holder.cardSocioName.setText(listaSocios.get(position).getNombreApellido());
    }

    @Override
    public int getItemCount() {
        return listaSocios.size();
    }

    public class SocioViewHolder extends RecyclerView.ViewHolder {
        TextView cardSocioName;
        public SocioViewHolder(@NonNull View itemView) {
            super(itemView);

            cardSocioName = itemView.findViewById(R.id.cardSocioName);
        }
    }
}
