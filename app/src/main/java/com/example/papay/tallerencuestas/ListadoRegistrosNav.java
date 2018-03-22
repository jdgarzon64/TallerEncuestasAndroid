package com.example.papay.tallerencuestas;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import modelo.Ciudadano;


public class ListadoRegistrosNav extends Fragment {
    ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_listado_registros_nav, container, false);
        list = (ListView) v.findViewById(R.id.listViewRegistros);
        cargarListaRegistros();
        return v;
    }



    public void cargarListaRegistros(){


        Toast.makeText(getContext(), "No tiene Registros"+ MainActivity.encuestador.getListadoCiudadanos().size(), Toast.LENGTH_SHORT)
                .show();
            if (MainActivity.encuestador.getListadoCiudadanos().size() > 0) {
                Toast.makeText(getContext(), "Entro al if", Toast.LENGTH_SHORT)
                        .show();
                ArrayAdapter<Ciudadano> adapter = new ArrayAdapter<Ciudadano>( getContext(),
                        android.R.layout.simple_list_item_1, MainActivity.encuestador.getListadoCiudadanos());
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        MainActivity.indexCiudadano = position;
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content_main, new RegistrarCiudadanosNav()).commit();

                    }
                });
                list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
                   new AlertDialog.Builder(getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Eliminar Ciudadano")
                        .setMessage("¿Quieres eliminar este Ciudadano?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                MainActivity.encuestador.getListadoCiudadanos().remove(position);
                                cargarListaRegistros();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                        Toast.makeText(getContext(), "ADFFADD", Toast.LENGTH_SHORT)
                                .show();
                        return true;
                    }
                });

            } else {
                Toast.makeText(getContext(), "No tiene Registros", Toast.LENGTH_SHORT)
                        .show();
            }

    }



}
