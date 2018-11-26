package com.company.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCPServerConnectionProcessor extends Thread {
    private Socket sock;

    public TCPServerConnectionProcessor(Socket s) {
        sock = s;
    }

    public void run() {
        try {
            // Получает запрос
            DataInputStream inStream = new DataInputStream(sock.getInputStream());
            int operationId = inStream.readInt();
            int arg1 = inStream.readInt();
            int arg2 = inStream.readInt();
            // Выполняет расчет
            int result = 0;
            if (operationId == 0) {
                result = arg1 + arg2;
            } else if (operationId == 1) {
                result = arg1 * arg2;
            }
            // Отправляет ответ
            DataOutputStream outStream = new DataOutputStream(
                    sock.getOutputStream());
            outStream.writeInt(result);
            // Подождем немного и завершим поток
            sleep(1000);
            inStream.close();
            outStream.close();
            sock.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}