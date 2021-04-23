package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    TextView nuevoUsuario, bienvenidoLabel, continuarLabel;
    ImageView signUImageView;
    TextInputLayout usuarioSignUpTextField, contrasenaTextField;
    MaterialButton inicioSesion;
    TextInputEditText nombreEditText,emailEditText, passwordEditText, confirmPasswordEditText;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUImageView = findViewById(R.id.signUpImageView);
        bienvenidoLabel = findViewById(R.id.bienvenidoLabel);
        continuarLabel = findViewById(R.id.continuarLabel);
        //usuarioSignUpTextField = findViewById(R.id.usuarioSingUpTextField);
        contrasenaTextField = findViewById(R.id.contraseñaTextField);
        inicioSesion = findViewById(R.id.inicioSesion);
        nuevoUsuario = findViewById(R.id.nuevoUsuario);
        /*Para la base de datos*/
        nombreEditText = findViewById(R.id.nombreEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });


        nuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionBack();
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }

    private void validate() {
        String nombre = nombreEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Correo invalido");
        }else {
            emailEditText.setError(null);
        }

        if(password.isEmpty() || password.length()<8){
            passwordEditText.setError("Se necesita mas de 8 caracteres");
            return;

        }else if(!Pattern.compile("[0-9]").matcher(password).find()){
            passwordEditText.setError("Al menos un numero");
            return;
        }else{
            passwordEditText.setError(null);
        }

        if(!confirmPassword.equals(password)){
            confirmPasswordEditText.setError("Deben ser iguales");
            return;
        }
        else{
            registrar(email,password,nombre);
        }
    }

    private void registrar(String email, String password,String nombre) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Persona persona1 = new Persona(email,password,nombre);
                            FirebaseDatabase.getInstance().getReference("Contacto").child(FirebaseAuth.getInstance().getUid()).setValue(persona1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();


                                    }else {
                                        Toast.makeText(SignUpActivity.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                            Intent intent = new Intent(SignUpActivity.this, UserActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(SignUpActivity.this,"Fallo en registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public  void transitionBack(){
        Intent intent = new Intent( SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }


}