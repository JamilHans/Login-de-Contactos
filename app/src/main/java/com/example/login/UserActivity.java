package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {
    private TextView emailTextView;
    private MaterialButton logoutButton;
    private Button BotonContactos, BotonAgregarContactos, BotonMiPerfil, BotonSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        BotonContactos = (Button)findViewById(R.id.btnContactos);
        BotonAgregarContactos = (Button)findViewById(R.id.btnAgregarContactos);
        BotonMiPerfil = (Button)findViewById(R.id.btnMiPerfil);
        BotonSalida = (Button)findViewById(R.id.btnSalida);

        BotonSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setTitle("Titulo");
                builder.setMessage("Quieres cerrar sesion?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"Cerrando Sesion",Toast.LENGTH_SHORT).show();
                                Toast.makeText(UserActivity.this, "Boton salida", Toast.LENGTH_LONG).show();
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent (UserActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Cancel...",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }).show();

            }
        });

        BotonContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "Boton Contactos", Toast.LENGTH_LONG).show();
                startActivity(new Intent (UserActivity.this, VerContactos.class));
            }
        });

        BotonMiPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "Boton Perfil", Toast.LENGTH_LONG).show();
                startActivity(new Intent (UserActivity.this, MiPerfil.class));
            }
        });

        BotonAgregarContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserActivity.this, "Boton Agregar Contactos", Toast.LENGTH_LONG).show();
                startActivity(new Intent(UserActivity.this, AgregarContactos.class));


            }
        });

        }
    }