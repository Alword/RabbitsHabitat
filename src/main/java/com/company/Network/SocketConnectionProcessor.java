package com.company.Network;

import com.company.Network.Interfaces.IObjectListener;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketConnectionProcessor extends BaseMessenger implements Runnable {

    private Socket socket;

    private List<IObjectListener> listeners = null;

    public SocketConnectionProcessor(Socket s) {
        socket = s;
        listeners = new ArrayList<>();
    }

    public void run() {
        try {
            while (true) {
                DataInputStream inStream = new DataInputStream(socket.getInputStream());
                String msg = inStream.readUTF();
                onMessageReceived(socket, msg);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}