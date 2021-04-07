package com.example.omar.test_udp_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    private String ipAddress = "192.168.1.94";
    private int port = 2390;

    private DatagramSocket socket;
    private DatagramPacket packet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton toggle = findViewById(R.id.toggleButton);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    new Thread(new Runnable() {

                        @Override
                        public void run() {

                            try {
                                socket = new DatagramSocket();
                                socket.setBroadcast(true);
                                byte[] b = "1".getBytes();

                                packet = new DatagramPacket(b, b.length, InetAddress.getByName(ipAddress), port);
                                socket.send(packet);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }).start();

                } else {

                    new Thread(new Runnable() {

                        @Override
                        public void run() {

                            try {
                                socket = new DatagramSocket();
                                socket.setBroadcast(true);
                                byte[] b = "0".getBytes();

                                packet = new DatagramPacket(b, b.length, InetAddress.getByName(ipAddress), port);
                                socket.send(packet);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    }).start();

                }
            }
        });
    }
}