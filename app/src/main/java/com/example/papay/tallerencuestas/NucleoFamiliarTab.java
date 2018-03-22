package com.example.papay.tallerencuestas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import modelo.Familiar;
import modelo.NucleoFamiliar;


public class NucleoFamiliarTab extends Fragment {

    View view;
    Spinner comboParentescoNucleo;
    EditText txtNombreIntegranteNucleo;
    EditText txtFechaNacimientoNucleo;
    ListView listaNucleoFamiliar;
    Button btnRegistrarNucleo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_nucleo_familiar_tab, container, false);
        comboParentescoNucleo = view.findViewById(R.id.comboParentescoNucleo);
        txtNombreIntegranteNucleo = view.findViewById(R.id.txtNombreIntegranteNucleo);
        txtFechaNacimientoNucleo = view.findViewById(R.id.txtFechaNacimientoNucleo);
        listaNucleoFamiliar = view.findViewById(R.id.listaNucleoFamiliar);
        btnRegistrarNucleo = view.findViewById(R.id.btnRegistrarNucleo);
        btnRegistrarNucleo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarNucleoFamiliar();
            }
        });
        cargarCombos();
        return view;
    }

    public void cargarCombos() {
        String[] opciones1 = {"Seleccione una opcion", "Madre", "Padre", "Abuela"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, opciones1);
        comboParentescoNucleo.setAdapter(adapter1);
    }

    public void guardarNucleoFamiliar() {
        if (txtNombreIntegranteNucleo.getText().toString().isEmpty() ||
                txtFechaNacimientoNucleo.getText().toString().isEmpty() ||
                comboParentescoNucleo.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "debe llenar todos los campos", Toast.LENGTH_LONG).show();
        } else {
            String nombre = txtNombreIntegranteNucleo.getText().toString();
            String fecha = txtFechaNacimientoNucleo.getText().toString();
            String parentesco = comboParentescoNucleo.getSelectedItem().toString();

            Familiar familiar = new Familiar(nombre,parentesco,fecha);
        }
    }
}
