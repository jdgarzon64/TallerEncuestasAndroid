package modelo;

import java.util.ArrayList;

/**
 * Created by papay on 21/03/2018.
 */

public class Encuestador {
    public String direccionFoto;
    public String email;
    public String nombre;
    ArrayList<Ciudadano>listadoCiudadanos;

    public Encuestador(String nombre, String direccionFoto, String email) {
        this.nombre = nombre;
        this.direccionFoto = direccionFoto;
        this.email = email;
        listadoCiudadanos= new ArrayList<>();
    }

    public ArrayList<Ciudadano> getListadoCiudadanos() {
        return listadoCiudadanos;
    }

    public void setListadoCiudadanos(ArrayList<Ciudadano> listadoCiudadanos) {
        this.listadoCiudadanos = listadoCiudadanos;
    }

    public String getDireccionFoto() {
        return direccionFoto;
    }

    public void setDireccionFoto(String direccionFoto) {
        this.direccionFoto = direccionFoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
