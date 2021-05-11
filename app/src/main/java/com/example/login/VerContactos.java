package com.example.login;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
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
    //ListView listV_persona;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private RecyclerView recyclerView;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contactos);
        /*listV_persona = (ListView) findViewById(R.id.listView1);*/
        inicializarFirebase();
        /*listarDatos();*/
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>().setQuery(databaseReference, User.class).build();
        adapter = new  UserAdapter(options);
        recyclerView.setAdapter(adapter);
    }
    /*
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
    */
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Contacto").child(FirebaseAuth.getInstance().getUid()).child("Amigos");

    }


}