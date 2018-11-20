package com.company.Services;

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
                while (pr.ready()){
                    //String msg += (char)pr.read();
                }
                System.out.println("Reading: " + (char)pr.read());
            } catch (IOException e) {
                System.out.println("The job's finished.");
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
