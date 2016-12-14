package com.example.santiago.menu;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.io.InputStream;
import java.io.OutputStream;

public class MenuTab extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static boolean bHilo = false;            //Utilizado para detener el hilo de recepcion digital

    public static boolean bConexion = false;        //Utilizado para almacenar el estado de la conexion Bluetooth

    public static BluetoothSocket mySocket = null;  //Socket para la conexion Bluetooth

    public static OutputStream outputStream;        //Stream de salida donde se envian los datos
    public static InputStream inStream;             //Stream de entrada donde se reciben los datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Acercade acercadeFragment = new Acercade();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(
                R.id.content_menu_tab,
                acercadeFragment,
                acercadeFragment.getTag()
        ).commit();

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_envdigital) {
            envdigital envdigitalFragment = new envdigital();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_menu_tab,
                    envdigitalFragment,
                    envdigitalFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_envanalogico) {
            envanalogico envanalogicoFragment = new envanalogico();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_menu_tab,
                    envanalogicoFragment,
                    envanalogicoFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_servo) {
            controlservo controlservoFragment = new controlservo();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_menu_tab,
                    controlservoFragment,
                    controlservoFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_envtexto) {
            envtexto envtextoFragment = new envtexto();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_menu_tab,
                    envtextoFragment,
                    envtextoFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_leerdigital) {
            leerdigital leerdigitalFragment = new leerdigital();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_menu_tab,
                    leerdigitalFragment,
                    leerdigitalFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_leeranalogico) {
            leeranalogico leeranalogicoFragment = new leeranalogico();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_menu_tab,
                    leeranalogicoFragment,
                    leeranalogicoFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_config) {
            configuracion configuracionFragment = new configuracion();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_menu_tab,
                    configuracionFragment,
                    configuracionFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_Acercade) {
            Acercade acercadeFragment = new Acercade();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_menu_tab,
                    acercadeFragment,
                    acercadeFragment.getTag()
            ).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
