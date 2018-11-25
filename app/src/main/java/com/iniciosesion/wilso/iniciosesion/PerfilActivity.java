package com.iniciosesion.wilso.iniciosesion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PerfilActivity extends AppCompatActivity {
    TextView nombre, caracteristicas, precioU, precioD, precioM, existencia, marca;
    Bundle CatB, ProdB;
    String CategoriaS, ProductoS;
    ImageView imagenI;
    Button salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        nombre = findViewById(R.id.txNombre);
        caracteristicas=findViewById(R.id.txcaracteristicas);
        precioU = findViewById(R.id.txU);
        precioD = findViewById(R.id.txD);
        precioM = findViewById(R.id.txM);
        existencia = findViewById(R.id.txExistencias);
        imagenI= findViewById(R.id.ImagenV);
        marca= findViewById(R.id.txMarca);
        CatB=getIntent().getExtras();
        ProdB=getIntent().getExtras();
        salir=findViewById(R.id.btSalir);
        CategoriaS=CatB.getString("Categoria");
        ProductoS=ProdB.getString("Producto");

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilActivity.this, CategoriasActivity.class);
                startActivity(i);
                finish();
            }
        });
        DatabaseReference Perfil = FirebaseDatabase.getInstance().getReference().child("Categorias").child(CategoriaS).child("Productos").child(ProductoS);
        Perfil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nombre.setText(dataSnapshot.child("nombre").getValue().toString());
                caracteristicas.setText(dataSnapshot.child("caracteristicas").getValue().toString());
                precioU.setText(dataSnapshot.child("precio_Unitario").getValue().toString());
                precioD.setText(dataSnapshot.child("precio_Distribuidor").getValue().toString());
                precioM.setText(dataSnapshot.child("precio_Mayorista").getValue().toString());
                existencia.setText(dataSnapshot.child("existencia").getValue().toString());
                marca.setText(dataSnapshot.child("marca").getValue().toString());
                final String imagen = dataSnapshot.child("imagenP").getValue().toString();
                Picasso.get().load(imagen).into(imagenI);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
