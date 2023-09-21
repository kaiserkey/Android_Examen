package com.example.examen.ui.farmacia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.R;

import java.util.List;

public class FarmaciaAdapter extends RecyclerView.Adapter<FarmaciaAdapter.ViewHolder>{
    private Context context;
    private List<Farmacia> listaFarmacias;

    public FarmaciaAdapter(Context context, List<Farmacia> listaFarmacias) {
        this.context = context;
        this.listaFarmacias = listaFarmacias;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.farmacia_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Farmacia farmacia = listaFarmacias.get(position);
        holder.nombre.setText(farmacia.getNombre());
        holder.direccion.setText(farmacia.getDireccion());
        holder.coordenadas.setText(farmacia.getLat() + ", " + farmacia.getLon());
    }

    @Override
    public int getItemCount() {
        return listaFarmacias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView direccion;
        public TextView coordenadas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreFarmacia);
            direccion = itemView.findViewById(R.id.tvDireccionFarmacia);
            coordenadas = itemView.findViewById(R.id.tvCoordenadas);
        }
    }
}
