package com.company.Network;

import com.company.Network.Interfaces.IObjectListener;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketConnectionProcessor extends Thread {

    private Socket socket;

    private List<IObjectListener> listeners = null;

    public SocketConnectionProcessor(Socket s) {
        socket = s;
        listeners = new ArrayList<>();
    }

    public void addListener(IObjectListener toAdd) {
        listeners.add(toAdd);
    }

    public void run() {
        try {
            while (true) {
                DataInputStream inStream = new DataInputStream(socket.getInputStream());
                String msg = inStream.readUTF();
                for (IObjectListener listener : listeners) {
                    listener.onMessageReceived(socket, msg);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}