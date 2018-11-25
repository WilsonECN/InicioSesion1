package com.iniciosesion.wilso.iniciosesion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iniciosesion.wilso.iniciosesion.Objetos.AdaptadorCat;
import com.iniciosesion.wilso.iniciosesion.Objetos.Nombres;

import java.util.ArrayList;
import java.util.List;

public class CategoriasActivity extends AppCompatActivity {
    List<Nombres> nombresList;
    AdaptadorCat adaptadorCat;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        rv= findViewById(R.id.RV);
        rv.setLayoutManager(new LinearLayoutManager(this));
        nombresList = new ArrayList<>();
        adaptadorCat = new AdaptadorCat(nombresList);
        rv.setAdapter(adaptadorCat);
        final DatabaseReference categorias = FirebaseDatabase.getInstance().getReference().child("Categorias");
        adaptadorCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CatSelect;
                Intent i = new Intent(CategoriasActivity.this, ProductosActivity.class);
                CatSelect = nombresList.get(rv.getChildAdapterPosition(v)).getNombre();
                i.putExtra("Categoria", CatSelect);
                //Toast.makeText(getApplicationContext(), "Categoria: "+CatSelect, Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        categorias.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nombresList.removeAll(nombresList);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    Nombres nombre1 = snapshot.getValue(Nombres.class);
                    nombresList.add(nombre1);
                }
                adaptadorCat.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
