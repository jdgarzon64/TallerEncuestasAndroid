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


public class NucleoFamiliarTab extends Fragment {

View view;
    Spinner comboParentescoNucleo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_nucleo_familiar_tab, container, false);
        comboParentescoNucleo=view.findViewById(R.id.comboParentescoNucleo);
        cargarCombos();
        return view;
    }
    public void cargarCombos(){
        String[] opciones1 = { "Seleccione una opcion", "Madre","Padre","Abuela"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, opciones1);
        comboParentescoNucleo.setAdapter(adapter1);
    }
}
