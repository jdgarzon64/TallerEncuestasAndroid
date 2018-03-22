package modelo;

/**
 * Created by papay on 21/03/2018.
 */

public class Ciudadano {
    public InformacionBasica informacionBasica;
    public InformacionLaboral informacionLaboral;
    public NucleoFamiliar nucleoFamiliar;

    public Ciudadano(InformacionBasica informacionBasica, InformacionLaboral informacionLaboral, NucleoFamiliar nucleoFamiliar) {
        this.informacionBasica = informacionBasica;
        this.informacionLaboral = informacionLaboral;
        this.nucleoFamiliar = nucleoFamiliar;
    }

    public InformacionBasica getInformacionBasica() {
        return informacionBasica;
    }

    public void setInformacionBasica(InformacionBasica informacionBasica) {
        this.informacionBasica = informacionBasica;
    }

    public InformacionLaboral getInformacionLaboral() {
        return informacionLaboral;
    }

    public void setInformacionLaboral(InformacionLaboral informacionLaboral) {
        this.informacionLaboral = informacionLaboral;
    }

    public NucleoFamiliar getNucleoFamiliar() {
        return nucleoFamiliar;
    }

    public void setNucleoFamiliar(NucleoFamiliar nucleoFamiliar) {
        this.nucleoFamiliar = nucleoFamiliar;
    }
}
