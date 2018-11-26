package com.company.Server;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Hub implements Runnable {

    private int tcp = 0;
    private int udp = 0;

    public Hub(int TCP_port, int UDP_port) {
        this.tcp = TCP_port;
        this.udp = UDP_port;
    }

    private void listenPorts() throws IOException {
        ServerSocket ss = new ServerSocket(tcp);
        // Ждет клиентов и для каждого создает отдельный поток
        while (true) {
            Socket s = ss.accept();
            new TCPServerConnectionProcessor(s).start();
        }
    }

    @Override
    public void run() {
        System.out.println("The Server Is Running");
        try {
            listenPorts();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Server down: 500");
        }
    }

    /*
    Вариант 14
    TCP: Реализовать возможность передачи своих настроек симуляции одному из подключенных клиентов.
    UDP: Команда перезагрузки (отключения, а затем повтороного подключения) клиента.
    */
}