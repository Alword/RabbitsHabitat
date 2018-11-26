package com.company.Services;

import com.company.Services.Commands.BaseCommand;
import com.company.Services.Commands.ICommand;
import com.company.Services.Commands.RabbitsCleanUpCommand;
import com.company.models.OrdinaryRabbit;
import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.lang.reflect.Array;

public class CommandParser extends Thread {

    private PipedReader pr;

    PipedReader getStream() {
        return pr;
    }

    public CommandParser(PipedWriter pw) {
        connectPipes(pw);
        initializeCommands();
    }

    private void initializeCommands() {
        new RabbitsCleanUpCommand();
        //TODO more command
    }

    private void connectPipes(PipedWriter pw) {
        try {
            pr = new PipedReader(pw);
        } catch (IOException e) {
            System.err.println("From Target(): " + e);
        }
    }

    public void run() {

        while (true) {
            try {
                readCommand();
            } catch (IOException e) {
                System.out.println("The job's finished.");
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //This is to read command from pipe stream
    private void readCommand() throws IOException, InterruptedException {
        Thread.sleep(10);//sleep is thread interruption

        String msg = readPipeMsg();

        if (msg != "") {

            System.out.println("Reading: " + msg);

            invokeCommand(msg);
        }
    }

    private String readPipeMsg() throws IOException {
        String msg = "";
        while (pr.ready()) {
            msg += (char) pr.read();
        }
        return msg;
    }

    private void invokeCommand(String msg) {
        String name = null;
        String[] args = null;

        String[] stack = msg.split(" ");
        name = stack[0];
        args = new String[stack.length - 1];
        System.arraycopy(stack, 1, args, 0, args.length);

        for (ICommand command :
                BaseCommand.Commands) {
            command.invoke(name, args);
        }
    }
}
