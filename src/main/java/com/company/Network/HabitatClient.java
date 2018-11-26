package com.company.Network;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HabitatClient {

    private int port = 0;
    private String IPEndpoint = "";
    private Socket socket = null;

    public HabitatClient(String IPEndpoint, int port) {
        this.port = port;
        this.IPEndpoint = IPEndpoint;
        connectToServer();
        //new UDPReceiver();
    }

    public void Send() {
        throw new NotImplementedException();
    }

    public void Send(int clientID) {
        throw new NotImplementedException();
    }

    private void connectToServer() {
        try {
            socket = new Socket(IPEndpoint, port);
            new SocketConnectionProcessor(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}