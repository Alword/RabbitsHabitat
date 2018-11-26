package com.company.Network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver extends BaseMessenger implements Runnable {

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
                    String msg = new String(pack.getData());
                    System.out.println(msg);
                    onMessageReceived(this, msg);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}