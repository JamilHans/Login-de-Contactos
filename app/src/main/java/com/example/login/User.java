package com.example.login;

public class User {
    String Nombre;
    String Numero;

    public User(String Nombre, String Numero) {
        this.Nombre = Nombre;
        this.Numero = Numero;
    }

    public User() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    @Override
    public String toString() {
        return "Nombres: "+ Nombre + "\n" +
                "Celular: " + Numero;
    }
}
