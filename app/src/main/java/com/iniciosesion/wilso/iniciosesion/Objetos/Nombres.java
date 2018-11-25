package com.iniciosesion.wilso.iniciosesion.Objetos;

/**
 * Created by wilso on 24/11/2018.
 */

public class Nombres {
    String nombre;
    String Imagen;
    public Nombres(String nombre, String imagen) {
        this.nombre = nombre;
        Imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }


    public Nombres(){
    }

}
