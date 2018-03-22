package com.example.papay.tallerencuestas;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import Dao.EncuestadorDAO;
import modelo.Ciudadano;
import modelo.Familiar;
import modelo.NucleoFamiliar;


public class NucleoFamiliarTab extends Fragment {

    View view;
    Spinner comboParentescoNucleo;
    EditText txtNombreIntegranteNucleo;
    EditText txtFechaNacimientoNucleo;
    ListView listaNucleoFamiliar;
    Button btnRegistrarNucleo;
    EncuestadorDAO dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dao = new EncuestadorDAO();
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
        cargarLista();
        txtFechaNacimientoNucleo.setOnTouchListener(new View.OnTouchListener() {

                                                        @Override
                                                        public boolean onTouch(View v, MotionEvent event) {

                                                            if (event.getAction() == MotionEvent.ACTION_UP) {

                                                                int cyear = 2018;
                                                                int cmonth = 3;
                                                                int cday = 22;
                                                                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                                                                    @Override
                                                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                                                        txtFechaNacimientoNucleo.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                                                    }
                                                                }, cyear, cmonth, cday);
                                                                datePickerDialog.show();
                                                            }

                                                            return true;

                                                        }
                                                    }

        );
        return view;
    }

    public void cargarLista(){
        if(MainActivity.indexCiudadano!=0) {
            if (MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getNucleoFamiliar().getListaFamiliares().size() > 0) {
                Toast.makeText(getContext(), "Cargando Lista de familiares", Toast.LENGTH_SHORT)
                        .show();
                ArrayAdapter<Familiar> adapter = new ArrayAdapter<Familiar>(getContext(),
                        android.R.layout.simple_list_item_1, MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getNucleoFamiliar().getListaFamiliares());
                listaNucleoFamiliar.setAdapter(adapter);
            } else {
                Toast.makeText(getContext(), "No tiene Registros", Toast.LENGTH_SHORT)
                        .show();
            }
        }
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

            Familiar familiar = new Familiar(nombre, parentesco, fecha);
            Boolean res =dao.guardarFamiliar(familiar);
            if (res){
                Toast.makeText(getActivity(), "Registro el familiar con exito!!", Toast.LENGTH_LONG).show();
                cargarLista();
            }else{
                Toast.makeText(getActivity(), "Ocurrio un error al registrar!", Toast.LENGTH_LONG).show();

            }

        }
    }
}
