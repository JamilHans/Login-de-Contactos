package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AgregarContactos extends AppCompatActivity {

    private TextInputEditText editTextNombres,editTextCelular;
    private Button buttonAgregarContactos;
    /*BD*/
    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contactos);
        editTextNombres = (TextInputEditText) findViewById(R.id.etNombre);
        editTextCelular = (TextInputEditText) findViewById(R.id.etCelular);
        buttonAgregarContactos = (Button)findViewById(R.id.btnAgregarContactos);
        //Configuracion de la BD
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        buttonAgregarContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombres.getText().toString().trim();
                String celular = editTextCelular.getText().toString().trim();

                Map<String, Object> personaMap = new HashMap<>();
                personaMap.put("Nombre", nombre);
                personaMap.put("Numero", celular);
                mDatabase.child("Contacto").child(userID).child("Amigos").push().setValue(personaMap);
                limpiarDatos();
            }
        });



    }

    public void limpiarDatos(){
        editTextCelular.setText("");
        editTextNombres.setText("");
    }

}