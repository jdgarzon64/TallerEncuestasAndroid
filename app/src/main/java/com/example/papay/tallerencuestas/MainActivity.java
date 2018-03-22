package com.example.papay.tallerencuestas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import modelo.Ciudadano;
import modelo.Encuestador;
import modelo.InformacionBasica;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public static Encuestador encuestador=new Encuestador("Invitado","","Invitado@hotmail.com");
    public static int indexCiudadano=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        cargarInfoEncuestador();
        return true;
    }

    public void cargarInfoEncuestador(){
        encuestador.getListadoCiudadanos().add(new Ciudadano(new InformacionBasica("hola","prueba","12/17/2018","1","2","3","4"),null,null));
        encuestador.getListadoCiudadanos().add(new Ciudadano(new InformacionBasica("kkas","dsgds","12/17/2018","1","2","3","4"),null,null));
        TextView profileName = (TextView) this.findViewById(R.id.NombreUsuarioPerfil);
        TextView profileCorreo = (TextView) this.findViewById(R.id.CorreoUsuarioPerfil);
        ImageView profileProyecto = (ImageView) this.findViewById(R.id.ImagenPerfil);



        if (encuestador != null) {
            profileName.setText(encuestador.getNombre());
            profileCorreo.setText(encuestador.getEmail());
            //profileProyecto.setText(user.getProyecto());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.RegistrarCiudadanosNav) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new RegistrarCiudadanosNav()).commit();
        } else if (id == R.id.ListadoRegistrosNav) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new ListadoRegistrosNav()).commit();
        } else if (id == R.id.InformacionNav) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new InformacionNav()).commit();
        } else if (id == R.id.SalirNav) {
            fragmentManager.beginTransaction().replace(R.id.content_main, new SalirNav()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
