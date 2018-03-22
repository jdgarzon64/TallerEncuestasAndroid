package modelo;

import java.util.ArrayList;

/**
 * Created by papay on 21/03/2018.
 */

public class NucleoFamiliar {

    public ArrayList<Familiar>listaFamiliares;


    public NucleoFamiliar() {

        this.listaFamiliares=new ArrayList<>();
    }

    public ArrayList<Familiar> getListaFamiliares() {
        return listaFamiliares;
    }
}
