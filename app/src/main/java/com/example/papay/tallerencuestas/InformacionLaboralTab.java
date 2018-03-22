package com.example.papay.tallerencuestas;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

import Dao.EncuestadorDAO;
import modelo.InformacionLaboral;


public class InformacionLaboralTab extends Fragment {
    EditText txtNombreEmpresaInfoLaboral;
    EditText txtDireccionEmpresaInfoLaboral;
    EditText txtSalarioInfoLaboral;
    View view;
    ImageView imvFotoEmpresaInfoLaboral;
    Button btnFotoInfoLaboral;
    Button btnRegistrarInfoLaboral;
    Spinner comboCargoInfoLaboral;
    String dirFoto;
    EncuestadorDAO dao;
    static final int CAMERA = 3;
    static final int READSD = 4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_informacion_laboral_tab, container, false);
        dao = new EncuestadorDAO();
        txtNombreEmpresaInfoLaboral = view.findViewById(R.id.txtNombreEmpresaInfoLaboral);
        txtDireccionEmpresaInfoLaboral = view.findViewById(R.id.txtDireccionEmpresaInfoLaboral);
        txtSalarioInfoLaboral = view.findViewById(R.id.txtSalarioInfoLaboral);
        comboCargoInfoLaboral = view.findViewById(R.id.comboCargoInfoLaboral);
        imvFotoEmpresaInfoLaboral = view.findViewById(R.id.imvFotoEmpresaInfoLaboral);
        imvFotoEmpresaInfoLaboral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarFoto(getView());
            }
        });
        btnFotoInfoLaboral = view.findViewById(R.id.btnFotoInfoLaboral);
        btnFotoInfoLaboral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomarFoto(getView());
               // recuperarFoto(getView());
            }
        });
        btnRegistrarInfoLaboral = view.findViewById(R.id.btnRegistrarInfoLaboral);
        btnRegistrarInfoLaboral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarInfoLab();
            }
        });

        cargarCombos();
        settributos();
        recuperarFoto(view);
        return view;
    }

    public void settributos() {
        if (MainActivity.indexCiudadano != 0 && MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionLaboral()!=null) {


            txtNombreEmpresaInfoLaboral.setText(MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionLaboral().getNombreEmpresa());
            txtDireccionEmpresaInfoLaboral.setText(MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionLaboral().getDireccion());
            txtSalarioInfoLaboral.setText(MainActivity.encuestador.getListadoCiudadanos().get(MainActivity.indexCiudadano).getInformacionLaboral().getSalario());
            comboCargoInfoLaboral.setSelection(1);

        }
    }

    public void tomarFoto(View view) {

        if (txtNombreEmpresaInfoLaboral.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "debe llenar todos los campos", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "entro al else", Toast.LENGTH_LONG).show();
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                askForPermission(android.Manifest.permission.CAMERA, CAMERA);
            }

            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);



                File foto = new File(getActivity().getExternalFilesDir(null), txtNombreEmpresaInfoLaboral.getText().toString() + ".jpg");

                dirFoto = getActivity().getExternalFilesDir(null) + "/" + txtNombreEmpresaInfoLaboral.getText().toString() + ".jpg";

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
                startActivityForResult(intent, 1);
            }
        }
    }

    public void recuperarFoto(View view) {

        Bitmap bitmap1 = BitmapFactory.decodeFile(getActivity().getExternalFilesDir(null) + "/" + txtNombreEmpresaInfoLaboral.getText().toString() + ".jpg");
        imvFotoEmpresaInfoLaboral.setImageBitmap(bitmap1);
    }

    public void cargarCombos() {
        String[] opciones1 = {"Seleccione una opcion", "Programador", "QA", "Project Manager"};

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, opciones1);
        comboCargoInfoLaboral.setAdapter(adapter1);
    }

    public void registrarInfoLab() {
        if (txtNombreEmpresaInfoLaboral.getText().toString().isEmpty() ||
                txtDireccionEmpresaInfoLaboral.getText().toString().isEmpty() ||
                txtSalarioInfoLaboral.getText().toString().isEmpty() ||
                comboCargoInfoLaboral.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "debe llenar todos los campos", Toast.LENGTH_LONG).show();
        } else if (dirFoto != null) {
            String empresa = txtNombreEmpresaInfoLaboral.getText().toString();
            String direccion = txtDireccionEmpresaInfoLaboral.getText().toString();
            String salario = txtSalarioInfoLaboral.getText().toString();
            String cargo = comboCargoInfoLaboral.getSelectedItem().toString();

            InformacionLaboral informacionLaboral = new InformacionLaboral(empresa, direccion, salario, cargo, dirFoto);
            Boolean res = dao.guardarInformacionLaboral(informacionLaboral);
            if (res) {
                Toast.makeText(getActivity(), "Guardo con exito!!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Ha ocurrido un error!!", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void askForPermission(String permit, int requestCode) {

        if (ContextCompat.checkSelfPermission(getActivity(), permit) !=
                PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permit)) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{permit}, requestCode);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{permit}, requestCode);

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        recuperarFoto(getView());
    }
}
