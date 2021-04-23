package com.example.login;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VerContactos extends AppCompatActivity {

    private List<User> listPerson = new ArrayList<User>();
    ArrayAdapter<User> arrayAdapterPersona;
    ListView listV_persona;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contactos);
        listV_persona = (ListView) findViewById(R.id.listView1);
        inicializarFirebase();
        listarDatos();
    }

    private void listarDatos() {
        databaseReference.child("Amigos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : snapshot.getChildren()) {
                    User p = objSnaptshot.getValue(User.class);
                    //Agregamos los datos del objto
                    listPerson.add(p);
                    //A-Mostrar un fragmento de la lista- los datos que se muestren en el layout.
                    arrayAdapterPersona = new ArrayAdapter<User>(VerContactos.this, android.R.layout.simple_list_item_1, listPerson);
                    //mostrar elementos de la lista
                    listV_persona.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Contacto").child(FirebaseAuth.getInstance().getUid());

    }
}