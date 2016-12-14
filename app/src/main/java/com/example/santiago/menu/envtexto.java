package com.example.santiago.menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class envtexto extends Fragment implements iConstantes {

    String sMensaje = "";

    public envtexto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MenuTab.bHilo = true;

        final View rootView = inflater.inflate(R.layout.fragment_envtexto, container, false);

        final Button btn_EnviarMensaje = (Button) rootView.findViewById(R.id.btn_EnviarMensaje);
        final SeekBar sb_Contraste = (SeekBar) rootView.findViewById(R.id.sb_Contraste);
        //final EditText txt_Mensaje = (EditText) rootView.findViewById(R.id.txt_Mensaje);


        btn_EnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MenuTab.bConexion) {
                    try {

                        sMensaje = "" + START_CMD_CHAR + CMD_TEXT  + DIV_CMD_CHAR + ((EditText) rootView.findViewById(R.id.txt_Mensaje)).getText().toString() + END_CMD_CHAR;
                        MenuTab.outputStream.write(sMensaje.getBytes());

                    } catch (IOException e) {
                        Toast.makeText(getActivity(), "Fallo al enviar el dato" + e, Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        sb_Contraste.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int iPosicion, boolean b) {
                if (MenuTab.bConexion) {
                    try {

                        sMensaje = "" + START_CMD_CHAR + CMD_ANALOGWRITE + DIV_CMD_CHAR + PIN_CONTRAST + DIV_CMD_CHAR + (100 - iPosicion) + END_CMD_CHAR;

                        MenuTab.outputStream.write(sMensaje.getBytes());

                    } catch (IOException Error) {
                        Toast.makeText(getActivity(), "Fallo al enviar el dato" + Error, Toast.LENGTH_LONG).show();
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
    }

}
