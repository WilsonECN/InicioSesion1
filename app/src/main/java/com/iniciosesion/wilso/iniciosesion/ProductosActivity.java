package com.iniciosesion.wilso.iniciosesion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iniciosesion.wilso.iniciosesion.Objetos.AdaptadorCat;
import com.iniciosesion.wilso.iniciosesion.Objetos.Nombres;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductosActivity extends AppCompatActivity {
    List<Nombres> nombresList;
    AdaptadorCat adaptadorCat;
    RecyclerView rv;
    TextView producto;
    String categoriaS;
    Bundle CategoriaB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CategoriaB=getIntent().getExtras();
        categoriaS= CategoriaB.getString("Categoria");
        setContentView(R.layout.activity_productos);
        rv= findViewById(R.id.RV1);
        rv.setLayoutManager(new LinearLayoutManager(this));
        nombresList = new ArrayList<>();
        adaptadorCat = new AdaptadorCat(nombresList);
        rv.setAdapter(adaptadorCat);
        producto=findViewById(R.id.txProducto);
        producto.setText(categoriaS+"");

        final DatabaseReference categorias = FirebaseDatabase.getInstance().getReference().child("Categorias")
                .child(categoriaS).child("Productos");
        adaptadorCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ProSelect;
                Intent i = new Intent(ProductosActivity.this, PerfilActivity.class);
                ProSelect = nombresList.get(rv.getChildAdapterPosition(v)).getNombre();
                i.putExtra("Categoria", categoriaS);
                i.putExtra("Producto", ProSelect);
                //Toast.makeText(getApplicationContext(), "Categoria: "+categoriaS+" Producto: "+ProSelect, Toast.LENGTH_SHORT).show();
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