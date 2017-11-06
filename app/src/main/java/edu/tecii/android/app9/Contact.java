package edu.tecii.android.app9;

import java.io.Serializable;

/**
 * Created by Adrian on 16/10/2017.
 */

public class Contact implements Serializable {

    String Nombre, Apellido, Telefono;
    Contact(){

    }

    public Contact(String Nombre, String Apellidos, String Telefono){
        this.Nombre = Nombre;
        this.Apellido = Apellidos;
        this.Telefono = Telefono;
    }
}
