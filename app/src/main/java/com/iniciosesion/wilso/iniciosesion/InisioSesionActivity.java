package com.iniciosesion.wilso.iniciosesion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class InisioSesionActivity extends AppCompatActivity {
    EditText TxUsuario, TxPass;
    Button btnInicio;
    String Usuario, Pass;
    ProgressDialog pdLoading;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inisio_sesion);
        TxPass=findViewById(R.id.TxPass);
        TxUsuario=findViewById(R.id.TxUsuario);
        btnInicio=findViewById(R.id.btninicio);


        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = TxUsuario.getText().toString();
                String pass = TxPass.getText().toString();
                inisioSecion(user,pass);

            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    //Intent i = new Intent(inicioSesionActivity.this, DocActivity.class);
                    Toast.makeText(getApplicationContext(),"sesion iniciada con mail: "+user.getEmail(),Toast.LENGTH_LONG)
                            .show();
                    Log.i("SESION","sesion iniciada con mail: "+user.getEmail());
                    //startActivity(i);
                    //finish();
                }else {
                    Log.i("SESION", "sesion cerrada");
                }
            }
        };
    }
    private void inisioSecion(String id_usuario, String contraseña_usuario){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(id_usuario, contraseña_usuario).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Intent i = new Intent(inicioSesionActivity.this, DocActivity.class);
                    //Toast.makeText(getApplicationContext(),"sesion iniciada correctamente",Toast.LENGTH_LONG).show();
                    Log.i("SESION", "sesion iniciada correctamente");
                    Toast.makeText(getApplicationContext(),"Sesion iniciada",Toast.LENGTH_LONG).show();
                    //startActivity(i);
                    //finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Verifique los datos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}
