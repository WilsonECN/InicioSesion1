package com.iniciosesion.wilso.iniciosesion.Objetos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iniciosesion.wilso.iniciosesion.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by wilso on 24/11/2018.
 */

public class AdaptadorCat extends RecyclerView.Adapter<AdaptadorCat.CategoriasViewHolder> implements View.OnClickListener{
    List<Nombres> nombres;
    private View.OnClickListener listener;

    public AdaptadorCat(List<Nombres> nombres){
        this.nombres = nombres;
    }
    @Override
    public CategoriasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_categoria, parent, false);
        v.setOnClickListener(this);
        CategoriasViewHolder holder = new CategoriasViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CategoriasViewHolder holder, int position) {
        Nombres nombres1 = nombres.get(position);
        holder.txNombre.setText(nombres1.getNombre());
        if (nombres1.getImagen()!=null){
            Picasso.get().load(nombres1.getImagen()).error(R.drawable.boton).into(holder.imagen);
        }
    }

    @Override
    public int getItemCount() {
        return nombres.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class CategoriasViewHolder extends RecyclerView.ViewHolder {
        TextView txNombre;
        ImageView imagen;
        public CategoriasViewHolder(View itemView) {
            super(itemView);
            txNombre= itemView.findViewById(R.id.txNombre);
            imagen= itemView.findViewById(R.id.imagen);
        }
    }
}
