package com.company.Network;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HabitatClient {

    private int port = 0;
    private String IPEndpoint = "";
    private Socket socket = null;

    public HabitatClient() {
        //connectToServer();
        //new UDPReceiver();
    }

    public void Send() {
        throw new NotImplementedException();
    }

    public void Send(int clientID) {
        throw new NotImplementedException();
    }

    public void connectToServer(String ipEndpoint, int port) throws IOException {
        this.port = port;
        this.IPEndpoint = ipEndpoint;
        socket = new Socket(IPEndpoint, this.port);
        SocketConnectionProcessor scp = new SocketConnectionProcessor(socket);
        Thread socketThread = new Thread(scp);
        socketThread.start();
    }

    public String getServerIP() {
        return IPEndpoint;
    }

    public int getServerPort() {
        return port;
    }
}