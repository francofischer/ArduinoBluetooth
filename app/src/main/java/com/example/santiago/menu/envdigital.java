package com.example.santiago.menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class envdigital extends Fragment implements iConstantes {

    public String sMensaje = "";

    private boolean bLED1 = true;
    private boolean bLED2 = true;
    private boolean bLED3 = true;
    private boolean bLED4 = true;

    public envdigital() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        MenuTab.bHilo = true;

        final View rootView = inflater.inflate(R.layout.fragment_envdigital, container, false);

        final Button btn_led1 = (Button) rootView.findViewById(R.id.btn_led1);
        final Button btn_led2 = (Button) rootView.findViewById(R.id.btn_led2);
        final Button btn_led3 = (Button) rootView.findViewById(R.id.btn_led3);
        final Button btn_led4 = (Button) rootView.findViewById(R.id.btn_led4);


        btn_led1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if (MenuTab.bConexion) {
                    try {

                        if (bLED1 == true)  {
                            sMensaje = "" + START_CMD_CHAR + CMD_DIGITALWRITE + DIV_CMD_CHAR + PIN_LED1 + DIV_CMD_CHAR + 255 + END_CMD_CHAR;
                            bLED1 = false;
                        }
                        else {
                            sMensaje = "" + START_CMD_CHAR + CMD_DIGITALWRITE + DIV_CMD_CHAR + PIN_LED1 + DIV_CMD_CHAR + 0 + END_CMD_CHAR;
                            bLED1 = true;
                        }
                        MenuTab.outputStream.write(sMensaje.getBytes());

                    } catch (IOException e) {
                        Toast.makeText(getActivity(), "Fallo al enviar el dato" + e, Toast.LENGTH_LONG).show();
                    }
                 }

            }
        });
        btn_led2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MenuTab.bConexion) {
                    try {

                        if (bLED2 == true) {
                            sMensaje = "" + START_CMD_CHAR + CMD_DIGITALWRITE + DIV_CMD_CHAR + PIN_LED2 + DIV_CMD_CHAR + 255 + END_CMD_CHAR;
                            bLED2 = false;
                        }
                        else {
                            sMensaje = "" + START_CMD_CHAR + CMD_DIGITALWRITE + DIV_CMD_CHAR + PIN_LED2 + DIV_CMD_CHAR + 0 + END_CMD_CHAR;
                            bLED2 = true;
                        }

                        MenuTab.outputStream.write(sMensaje.getBytes());
                    } catch (IOException e) {
                        Toast.makeText(getActivity(), "Fallo al enviar el dato" + e, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn_led3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MenuTab.bConexion) {
                    try {

                        if (bLED3 == true) {
                            sMensaje = "" + START_CMD_CHAR + CMD_DIGITALWRITE + DIV_CMD_CHAR + PIN_LED3 + DIV_CMD_CHAR + 255 + END_CMD_CHAR;
                            bLED3 = false;
                        }
                        else {
                            sMensaje = "" + START_CMD_CHAR + CMD_DIGITALWRITE + DIV_CMD_CHAR + PIN_LED3 + DIV_CMD_CHAR + 0 + END_CMD_CHAR;
                            bLED3 = true;
                        }

                        MenuTab.outputStream.write(sMensaje.getBytes());
                    } catch (IOException e) {
                        Toast.makeText(getActivity(), "Fallo al enviar el dato" + e, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn_led4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MenuTab.bConexion) {
                    try {
                        if (bLED4 == true) {
                            sMensaje = "" + START_CMD_CHAR + CMD_DIGITALWRITE + DIV_CMD_CHAR + PIN_LED4 + DIV_CMD_CHAR + 255 + END_CMD_CHAR;
                            bLED4 = false;
                        }
                        else {
                            sMensaje = "" + START_CMD_CHAR + CMD_DIGITALWRITE + DIV_CMD_CHAR + PIN_LED4 + DIV_CMD_CHAR + 0 + END_CMD_CHAR;
                            bLED4 = true;
                        }

                        MenuTab.outputStream.write(sMensaje.getBytes());
                    } catch (IOException e) {
                        Toast.makeText(getActivity(), "Fallo al enviar el dato" + e, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return rootView;
    }

}
