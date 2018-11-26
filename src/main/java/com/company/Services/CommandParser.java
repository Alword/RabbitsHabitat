package com.company.Services;

import com.company.Services.Commands.ICommand;
import com.company.Services.Commands.RabbitsCleanUpCommand;;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Vector;

public class CommandParser extends Thread {

    private Vector<ICommand> RabbitsCommands = null;
    private PipedReader pr;

    public CommandParser(PipedWriter pw) {
        connectPipes(pw);
        initializeCommands();
    }

    private void initializeCommands() {
        RabbitsCommands = new Vector<>();
        RabbitsCommands.add(new RabbitsCleanUpCommand());
        //TODO more command
    }

    private void connectPipes(PipedWriter pw) {
        try {
            pr = new PipedReader(pw);
        } catch (IOException e) {
            System.err.println("From Target(): " + e);
        }
    }

    //this is to read command
    public void run() {

        while (true) {
            try {
                receiveCommand();
            } catch (IOException e) {
                System.out.println("The job's finished.");
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //This is to invoke command from pipe stream
    private void receiveCommand() throws IOException, InterruptedException {
        Thread.sleep(10);//sleep is thread interruption
        String msg = readPipeMsg();
        if (msg != "") {
            System.out.println("Reading: " + msg);
            invokeCommand(msg);
        }
    }

    //this is to read string message from pipeStream
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

        //this is to convert message to command args and name format
        String[] stack = msg.split(" ");
        name = stack[0];
        args = new String[stack.length - 1];
        System.arraycopy(stack, 1, args, 0, args.length);

        //Check the command name if matches then invoke
        for (ICommand command :
                RabbitsCommands) {
            command.invoke(name, args);
        }
    }

    //Stream interface
    PipedReader getStream() { return pr;}
}