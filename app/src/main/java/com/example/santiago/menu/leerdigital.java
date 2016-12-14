package com.example.santiago.menu;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class leerdigital extends Fragment implements iConstantes {

    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    //int counter;

    ImageView led1digital;
    ImageView led2digital;
    ImageView led3digital;
    ImageView led4digital;

    //TextView mylabel;

    public leerdigital() {
        // Required empty public constructor
    }

    void beginListenForData()
    {
        final Handler handler = new Handler();
        final byte delimiter = 10; //This is the ASCII code for a newline character

        MenuTab.bHilo = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable()
        {
            boolean bLed1 = false;
            boolean bLed2 = false;
            boolean bLed3 = false;
            boolean bLed4 = false;

            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !MenuTab.bHilo)
                {
                    try
                    {
                        int bytesAvailable = MenuTab.inStream.available();

                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            MenuTab.inStream.read(packetBytes);

                            for(int i=0 ; i<bytesAvailable ; i++)
                            {
                                byte b = packetBytes[i];

                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;

                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                                            switch (data)
                                            {

                                                case "*13|30|255#":
                                                    if(bLed1) {
                                                        led1digital.setImageResource(R.mipmap.lediconoff);
                                                        bLed1 = false;
                                                    }
                                                    else {
                                                        led1digital.setImageResource(R.mipmap.ledicon);
                                                        bLed1 = true;
                                                    }
                                                    break;

                                                case "*13|31|255#":
                                                    if(bLed2) {
                                                        led2digital.setImageResource(R.mipmap.lediconoff);
                                                        bLed2 = false;
                                                    }
                                                    else {
                                                        led2digital.setImageResource(R.mipmap.ledicon);
                                                        bLed2 = true;
                                                    }
                                                    break;

                                                case "*13|32|255#":
                                                    if(bLed3) {
                                                        led3digital.setImageResource(R.mipmap.lediconoff);
                                                        bLed3 = false;
                                                    }
                                                    else {
                                                        led3digital.setImageResource(R.mipmap.ledicon);
                                                        bLed3 = true;
                                                    }
                                                    break;

                                                case "*13|33|255#":
                                                    if(bLed4) {
                                                        led4digital.setImageResource(R.mipmap.lediconoff);
                                                        bLed4 = false;
                                                    }
                                                    else {
                                                        led4digital.setImageResource(R.mipmap.ledicon);
                                                        bLed4 = true;
                                                    }
                                                    break;

                                            }

                                            //mylabel.setText(data);

                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        MenuTab.bHilo = true;
                    }
                }
            }
        });

        if (MenuTab.bConexion) workerThread.start();
        //Toast.makeText(getActivity(), "Corriendo hilo", Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_leerdigital, container, false);

        led1digital = (ImageView) rootView.findViewById(R.id.img_led1);
        led2digital = (ImageView) rootView.findViewById(R.id.img_led2);
        led3digital = (ImageView) rootView.findViewById(R.id.img_led3);
        led4digital = (ImageView) rootView.findViewById(R.id.img_led4);

        //mylabel = (TextView) rootView.findViewById(R.id.mylabel);

        beginListenForData();

        return rootView;
    }
}
