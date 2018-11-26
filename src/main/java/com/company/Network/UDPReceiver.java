package com.company.Network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver extends Thread {

    private int port;
    public UDPReceiver(int port) {
        this.port = port;
        System.out.println("Client udp receiver is running");
    }

    @Override
    public void run() {
        while (true) {
            try {
                DatagramSocket ds = new DatagramSocket(port);
                while (true) {
                    DatagramPacket pack = new DatagramPacket(new byte[1024], 1024);
                    ds.receive(pack);
                    //todo message
                    System.out.println(new String(pack.getData()));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
