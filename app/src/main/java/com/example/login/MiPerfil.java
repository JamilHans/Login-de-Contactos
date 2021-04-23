package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MiPerfil extends AppCompatActivity {
    private TextInputEditText nombreEditText, emailEditText, passwordEditText;
    private Button inicioSesion;
    //BD Authe
    private FirebaseAuth fAuth;
    //BD Realtime
    private DatabaseReference mDatabase;
    //ID Ubicar
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);
        nombreEditText = (TextInputEditText)findViewById(R.id.nombreEditText);
        emailEditText = (TextInputEditText)findViewById(R.id.emailEditText);
        passwordEditText = (TextInputEditText)findViewById(R.id.passwordEditText);
        //Base de datos configuracion
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference("Contacto");
        /*Obtenemos los datos de la base de datos*/
        mDatabase.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si el usuario existe
                if(snapshot.exists()){
                    //Obtenemos los datos de la bd
                    String nombre = snapshot.child("nombre").getValue().toString();
                    String email = snapshot.child("correo").getValue().toString();
                    String contraseña = snapshot.child("contraseña").getValue().toString();
                    //Ahora seteamos los datos en los Text
                    nombreEditText.setText(nombre);
                    emailEditText.setText(email);
                    passwordEditText.setText(contraseña);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}