package com.company.Services;

import com.company.models.OrdinaryRabbit;
import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class CommandParser extends Thread {

    private PipedReader pr;

    public CommandParser(PipedWriter pw) {
        try {
            pr = new PipedReader(pw);
        } catch (IOException e) {
            System.err.println("From Target(): " + e);
        }
    }

    PipedReader getStream() {
        return pr;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
                String msg = "";
                while (pr.ready()) {
                    //String msg += (char)pr.read();
                    msg += (char) pr.read();
                }
                if (msg != "") {
                    System.out.println("Reading: " + msg);
                    Integer x = Integer.parseInt(msg);
                    x = (int)(OrdinaryRabbit.Rabbits.size() * x / 100.0);
                    for (int i = 0; i < x; i++) {
                        OrdinaryRabbit.Rabbits.get(i).kill();

                    }

                }

            } catch (IOException e) {
                System.out.println("The job's finished.");
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
