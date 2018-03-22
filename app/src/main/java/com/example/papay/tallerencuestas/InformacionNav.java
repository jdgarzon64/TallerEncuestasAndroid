package com.example.papay.tallerencuestas;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.jar.Manifest;


public class InformacionNav extends Fragment {
    Button btnFotoProyecto;
    View view;
    EditText txtNombreProyectoInformacion;
    ImageView imvFotoProyecto;
    static final int CAMERA = 3;
    static final int READSD = 4;
    String dir="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // recuperarFoto(getView());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_informacion_nav, container, false);
        btnFotoProyecto = view.findViewById(R.id.btnFotoProyecto);
        btnFotoProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomarFoto(getView());
                recuperarFoto(getView());
            }
        });
        txtNombreProyectoInformacion = view.findViewById(R.id.txtNombreProyectoInformacion);
        imvFotoProyecto = view.findViewById(R.id.imvFotoProyecto);
        imvFotoProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarFoto(getView());
            }
        });
        return view;
    }


    public void tomarFoto(View view) {

        if (txtNombreProyectoInformacion.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "debe llenar todos los campos", Toast.LENGTH_LONG).show();
        } else {

            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                askForPermission(android.Manifest.permission.CAMERA, CAMERA);
            }

            if(ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 dir = getActivity().getExternalFilesDir(null) + "/" + txtNombreProyectoInformacion.getText().toString() + ".jpg";
                File foto = new File(getActivity().getExternalFilesDir(null), txtNombreProyectoInformacion.getText().toString() + ".jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
                startActivityForResult(intent,1);
            }
        }
    }

    public void recuperarFoto(View view) {

        Bitmap bitmap1 = BitmapFactory.decodeFile(dir);
        imvFotoProyecto.setImageBitmap(bitmap1);
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
