package com.example.papay.tallerencuestas;

import android.app.DatePickerDialog;
import android.content.Context;
import android.hardware.Sensor;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import Dao.EncuestadorDAO;
import modelo.Ciudadano;
import modelo.InformacionBasica;


public class InformacionBasicaTab extends Fragment {
    Spinner ComboTipoDocumentoInfoBasica;
    View view;
    EditText txtNombreInfoBasica;
    EditText txtApellidoInfoBasica;
    EditText txtFechaNacimientoInfoBasica;
    EditText txtDocumentoInfoBasica;
    EditText txtNumeroTelefonoInfoBasica;
    RadioButton rbtnMasculinoInfoBasica;
    RadioButton rbtnOtroInfoBasica;
    RadioButton rbtnFemeninoInfoBasica;
    Button btnGuardarInfoBasica;
    EncuestadorDAO dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_informacion_basica_tab, container, false);
        dao = new EncuestadorDAO();
        ComboTipoDocumentoInfoBasica = view.findViewById(R.id.ComboTipoDocumentoInfoBasica);
        txtNombreInfoBasica = view.findViewById(R.id.txtNombreInfoBasica);
        txtApellidoInfoBasica = view.findViewById(R.id.txtApellidoInfoBasica);
        txtFechaNacimientoInfoBasica = view.findViewById(R.id.txtFechaNacimientoInfoBasica);
        txtDocumentoInfoBasica = view.findViewById(R.id.txtDocumentoInfoBasica);
        txtNumeroTelefonoInfoBasica = view.findViewById(R.id.txtNumeroTelefonoInfoBasica);
        rbtnMasculinoInfoBasica = view.findViewById(R.id.rbtnMasculinoInfoBasica);
        rbtnOtroInfoBasica = view.findViewById(R.id.rbtnOtroInfoBasica);
        rbtnFemeninoInfoBasica = view.findViewById(R.id.rbtnFemeninoInfoBasica);
        btnGuardarInfoBasica = view.findViewById(R.id.btnGuardarInfoBasica);
        btnGuardarInfoBasica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarInfo(getView());
            }
        });
        txtFechaNacimientoInfoBasica.setOnTouchListener(new View.OnTouchListener() {

                                                            @Override
                                                            public boolean onTouch(View v, MotionEvent event) {

                                                                if (event.getAction() == MotionEvent.ACTION_UP) {

                                                                    int cyear = 2018;
                                                                    int cmonth = 3;
                                                                    int cday = 22;
                                                                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                                                                        @Override
                                                                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                                                            txtFechaNacimientoInfoBasica.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                                                        }
                                                                    }, cyear, cmonth, cday);
                                                                    datePickerDialog.show();
                                                                }

                                                                return true;

                                                            }
                                                        }

        );
        cargarCombos();
        setAtributos();

        return view;
    }

    public void setAtributos() {
        if (MainActivity.indexCiudadano != 0) {
            txtNombreInfoBasica.setText(MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionBasica().getNombre());
            txtApellidoInfoBasica.setText(MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionBasica().getApellido());
            txtFechaNacimientoInfoBasica.setText(MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionBasica().getFechaDeNacimiento());
            txtDocumentoInfoBasica.setText(MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionBasica().getNumeroDocumento());
            txtNumeroTelefonoInfoBasica.setText(MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionBasica().getNumeroTelefono());
            ComboTipoDocumentoInfoBasica.setSelection(1);
            if (MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionBasica().getGenero().equals("femenino")) {
                rbtnFemeninoInfoBasica.setChecked(true);
            } else if (MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionBasica().getGenero().equals("masculino")) {
                rbtnMasculinoInfoBasica.setChecked(true);
            } else {
                rbtnOtroInfoBasica.setChecked(true);
            }
        }
    }

    public void cargarCombos() {
        String[] opciones1 = {"Seleccione una opcion", "C.C", "T.I", "PASAPORTE"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, opciones1);
        ComboTipoDocumentoInfoBasica.setAdapter(adapter1);
    }

    public void guardarInfo(View view) {


        if (txtNombreInfoBasica.getText().toString().isEmpty() ||
                ComboTipoDocumentoInfoBasica.getSelectedItemPosition() == 0 ||
                txtApellidoInfoBasica.getText().toString().isEmpty() ||
                txtFechaNacimientoInfoBasica.getText().toString().isEmpty() ||
                txtDocumentoInfoBasica.getText().toString().isEmpty() ||
                txtNumeroTelefonoInfoBasica.getText().toString().isEmpty()
                ) {
            Toast.makeText(getActivity(), "debe llenar todos los campos", Toast.LENGTH_LONG).show();
        } else {
            String nombre = txtNombreInfoBasica.getText().toString();
            String apellido = txtApellidoInfoBasica.getText().toString();
            String fecha = txtFechaNacimientoInfoBasica.getText().toString();
            String documento = txtDocumentoInfoBasica.getText().toString();
            String telefono = txtNumeroTelefonoInfoBasica.getText().toString();
            String tipoDocumento = ComboTipoDocumentoInfoBasica.getSelectedItem().toString();
            String genero = "";
            if (rbtnFemeninoInfoBasica.isChecked()) {
                genero = "femenino";
            } else if (rbtnMasculinoInfoBasica.isChecked()) {
                genero = "masculino";
            } else {
                genero = "otro";
            }
            InformacionBasica informacionBasica = new InformacionBasica(nombre, apellido, fecha, tipoDocumento, documento, telefono, genero);
            int res = dao.guardarInformacionBasica(informacionBasica);
            if (res == 0) {

                Toast.makeText(getActivity(), "Guardo con exito!!", Toast.LENGTH_LONG).show();
                dao.setearActual(informacionBasica.getNumeroDocumento());

            } else if (res == 1) {
                Toast.makeText(getActivity(), "Se actualizo con exito!!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Ha ocurrido un error al guardar", Toast.LENGTH_LONG).show();
            }

        }

    }


}