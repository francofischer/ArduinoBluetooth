package com.example.santiago.menu;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class configuracion extends Fragment implements iConstantes {

    public configuracion() {
        // Required empty public constructor
    }

    BluetoothAdapter myBluetoothAdapter = null;
    BluetoothDevice myDispositivo = null;

    private static String MAC = null;
    UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    Button btn_Conectar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_configuracion, container, false);

        btn_Conectar = (Button) rootView.findViewById(R.id.btn_Conectar);
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (MenuTab.bConexion) btn_Conectar.setText("Desconectar");
        else btn_Conectar.setText("Conectar");

        MenuTab.bHilo = true;

        btn_Conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MenuTab.bConexion) {
                    //desconectar
                    try {

                        MenuTab.bHilo = true;
                        MenuTab.outputStream.close();
                        MenuTab.inStream.close();
                        MenuTab.mySocket.close();
                        MenuTab.bConexion = false;

                        btn_Conectar.setText("Conectar");

                        Toast.makeText(getActivity(), "Bluetooth desconectado", Toast.LENGTH_LONG).show();

                    } catch (IOException Error) {
                        Toast.makeText(getActivity(), "Error al desconectar: " + Error, Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    //conectar
                    Intent ListaDispositivos = new Intent(getActivity(), cListaDispositivos.class);
                    startActivityForResult(ListaDispositivos, REQUEST_CONECTION_BT);
                    //btn_Conectar.setText("Desconectar");
                }

            }
        });

        

        if (myBluetoothAdapter == null) Toast.makeText(getActivity(), "El dispositivo no tiene Bluetooth", Toast.LENGTH_LONG).show();
        else if (!myBluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        return rootView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_ENABLE_BT:
                if(resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getActivity(), "El Bluetooth fue activado", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(), "No se pudo activar el Bluetooth", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
                break;

            case REQUEST_CONECTION_BT:
                if(resultCode == Activity.RESULT_OK) {
                    MAC = data.getExtras().getString(cListaDispositivos.DIRECCION_MAC);

                    //Toast.makeText(getApplicationContext(), "Conectado a la MAC: " + MAC, Toast.LENGTH_LONG).show();

                    myDispositivo = myBluetoothAdapter.getRemoteDevice(MAC);

                    try {
                        MenuTab.mySocket = myDispositivo.createRfcommSocketToServiceRecord(myUUID);

                        MenuTab.mySocket.connect();
                        MenuTab.bConexion = true;
                        btn_Conectar.setText("Desconectar");

                        MenuTab.outputStream = MenuTab.mySocket.getOutputStream();
                        MenuTab.inStream = MenuTab.mySocket.getInputStream();

                        Toast.makeText(getActivity(), "Conectado con: " + MAC, Toast.LENGTH_LONG).show();

                    } catch(IOException Error) {

                        MenuTab.bConexion = false;
                        Toast.makeText(getActivity(), "Ocurrio un error: " + Error, Toast.LENGTH_LONG).show();
                    } ;

                }
                else {
                    Toast.makeText(getActivity(), "Fallo al obtener la MAC", Toast.LENGTH_LONG).show();
                }
        }
    }

}
