package com.example.papay.tallerencuestas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class InformacionBasicaTab extends Fragment {
Spinner ComboTipoDocumentoInfoBasica;
View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_informacion_basica_tab, container, false);
        ComboTipoDocumentoInfoBasica=view.findViewById(R.id.ComboTipoDocumentoInfoBasica);
        cargarCombos();

        return view;
    }


    public void cargarCombos(){
        String[] opciones1 = { "Seleccione una opcion", "C.C","T.I","PASAPORTE"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, opciones1);
        ComboTipoDocumentoInfoBasica.setAdapter(adapter1);
    }

}