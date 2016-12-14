package com.example.santiago.menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class controlservo extends Fragment implements iConstantes {

    String sMensaje = "";

    public controlservo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        MenuTab.bHilo = true;

        final View rootView = inflater.inflate(R.layout.fragment_controlservo, container, false);

        final SeekBar sb_Posicion = (SeekBar) rootView.findViewById(R.id.sb_Posicion); // you have to use rootview object..
        final TextView txt_PosicionActual = (TextView) rootView.findViewById(R.id.txt_PosicionActual);
        final ToggleButton btn_EnviarDatos = (ToggleButton) rootView.findViewById(R.id.btn_Enviar);


        sb_Posicion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int iPosicion, boolean b) {

                if (btn_EnviarDatos.isChecked()) {
                    txt_PosicionActual.setText(iPosicion + "°");

                    if (MenuTab.bConexion) {
                        try {

                            sMensaje = "" + START_CMD_CHAR + CMD_MOVE_SERVO + DIV_CMD_CHAR + PIN_SERVO + DIV_CMD_CHAR + iPosicion + END_CMD_CHAR;

                            MenuTab.outputStream.write(sMensaje.getBytes());

                        } catch (IOException Error) {
                            Toast.makeText(getActivity(), "Fallo al enviar el dato" + Error, Toast.LENGTH_LONG).show();
                        }
                    }

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return rootView;

        //return inflater.inflate(R.layout.fragment_controlservo, container, false);


    }

}
