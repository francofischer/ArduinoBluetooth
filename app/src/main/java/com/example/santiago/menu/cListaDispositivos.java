package com.example.santiago.menu;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

public class cListaDispositivos extends ListActivity {

    private BluetoothAdapter myBluetoothAdapter2 = null;

    static String DIRECCION_MAC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        myBluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> DispositivosApareados = myBluetoothAdapter2.getBondedDevices();

        if (DispositivosApareados.size() > 0) {
            for (BluetoothDevice dispositivo : DispositivosApareados) {

                String sNombreBluetooth = dispositivo.getName();
                String sMAC = dispositivo.getAddress();

                ArrayBluetooth.add(sNombreBluetooth + "\n" + sMAC);
            }
        }

        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String sDispositivoSeleccionado = ((TextView) v).getText().toString();
        String sDireccionMAC = sDispositivoSeleccionado.substring(sDispositivoSeleccionado.length() - 17); //Recupero la direccion MAC

        //Toast.makeText(getApplicationContext(), "Info: " + sDispositivoSeleccionado, Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), "MAC: " + sDireccionMAC, Toast.LENGTH_LONG).show();

        Intent devolverMAC = new Intent();
        devolverMAC.putExtra(DIRECCION_MAC, sDireccionMAC);
        setResult(RESULT_OK, devolverMAC);
        finish();
    }

}

