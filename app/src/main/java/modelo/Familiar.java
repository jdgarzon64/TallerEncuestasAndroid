package modelo;

/**
 * Created by papay on 22/03/2018.
 */

public class Familiar {
    public String nombre;
    public String fechaDeNacimiento;
    public String parentesco;

    public Familiar(String nombre, String parentesco, String fechaDeNacimiento) {
        this.nombre = nombre;
        this.parentesco = parentesco;
        this.fechaDeNacimiento = fechaDeNacimiento;
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

    @Override
    public String toString() {
        return "Nombre: "+nombre+"\nParentesco: "+parentesco;
    }
}
