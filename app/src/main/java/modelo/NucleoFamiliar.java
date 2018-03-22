package modelo;

import java.util.ArrayList;

/**
 * Created by papay on 21/03/2018.
 */

public class NucleoFamiliar {
    public String nombre;
    public String fechaDeNacimiento;
    public String parentesco;
    public ArrayList<String>listaFamiliares;

    public NucleoFamiliar(String nombre, String fechaDeNacimiento, String parentesco) {
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.parentesco = parentesco;
        this.listaFamiliares=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public ArrayList<String> getListaFamiliares() {
        return listaFamiliares;
    }

    public void setListaFamiliares(ArrayList<String> listaFamiliares) {
        this.listaFamiliares = listaFamiliares;
    }
}
