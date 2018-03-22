package modelo;

/**
 * Created by papay on 21/03/2018.
 */

public class InformacionLaboral {
    public String nombreEmpresa;
    public String direccion;
    public String salario;
    public String cargo;
    public String direccionFoto;

    public InformacionLaboral(String nombreEmpresa, String direccion, String salario, String cargo, String direccionFoto) {
        this.nombreEmpresa = nombreEmpresa;
        this.direccion = direccion;
        this.salario = salario;
        this.cargo = cargo;
        this.direccionFoto = direccionFoto;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDireccionFoto() {
        return direccionFoto;
    }

    public void setDireccionFoto(String direccionFoto) {
        this.direccionFoto = direccionFoto;
    }
}
