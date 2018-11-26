package com.company.Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {

    private String host;
    private int port;

    public UDPSender(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public void sendMessage(String msg) {
        try {
            byte[] data = msg.getBytes();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket pack = new DatagramPacket(data, data.length, address, port);
            DatagramSocket ds = new DatagramSocket();
            ds.send(pack);
            ds.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}