package com.example.papay.tallerencuestas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_informacion_laboral_tab, container, false);
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
                recuperarFoto(getView());
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

        return view;
    }

    public void tomarFoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String filePath = Environment.getExternalStorageDirectory() + txtNombreEmpresaInfoLaboral.getText().toString() + ".jpg";
        File foto = new File(getActivity().getExternalFilesDir(null), txtNombreEmpresaInfoLaboral.getText().toString() + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
        startActivity(intent);
        dirFoto = getActivity().getExternalFilesDir(null) + "/" + txtNombreEmpresaInfoLaboral.getText().toString() + ".jpg";

        startActivity(intent);

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


        }
    }
}
