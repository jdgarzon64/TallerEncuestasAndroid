package Dao;

import com.example.papay.tallerencuestas.MainActivity;

import java.util.ArrayList;

import modelo.Ciudadano;
import modelo.Familiar;
import modelo.InformacionBasica;
import modelo.InformacionLaboral;
import modelo.NucleoFamiliar;

/**
 * Created by Jhohanns on 3/22/2018.
 */

public class EncuestadorDAO {


    public Boolean setearActual(String doc){
        ArrayList<Ciudadano> var = MainActivity.encuestador.getListadoCiudadanos();
        for (int i = 0; i < var.size(); i++) {
            if (var.get(i).getInformacionBasica().getNumeroDocumento().equals(doc)) {
                MainActivity.indexCiudadano = i;
                return true;
            }

        }
        return false;
    }

    public Boolean existeCiudadano(String doc){
        ArrayList<Ciudadano> var = MainActivity.encuestador.getListadoCiudadanos();
        for (int i = 0; i < var.size(); i++) {
            if (var.get(i).getInformacionBasica().getNumeroDocumento().equals(doc)) {

                return true;
            }

        }
        return false;
    }

    public int guardarInformacionBasica(InformacionBasica info) {
        if (MainActivity.indexCiudadano == 0) {
            MainActivity.encuestador.getListadoCiudadanos().add(new Ciudadano(info,null, new NucleoFamiliar()));
            Boolean res=setearActual(info.getNumeroDocumento());
            if(res){
                return 0;
            }else{
                return 2;
            }


        } else {
            MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).setInformacionBasica(info);
            return 1;
        }


    }

    public Boolean guardarInformacionLaboral(InformacionLaboral info) {
        if (MainActivity.indexCiudadano != 0) {
            MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).setInformacionLaboral(info);
            return true;
        }
        return false;

    }


    public Boolean guardarFamiliar(Familiar info) {
        if (MainActivity.indexCiudadano != 0) {
            MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getNucleoFamiliar().getListaFamiliares().add(info);
            return true;
        }
        return false;

    }

}
