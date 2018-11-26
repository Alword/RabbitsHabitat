package com.company.Services.Commands;

import java.util.Vector;

public class BaseCommand implements ICommand {
    public static Vector<BaseCommand> Commands = null;

    private String name = null;

    public BaseCommand(String name) {
        this.name = name;
        if (Commands == null) {
            Commands = new Vector<>();
        }
        Commands.add(this);
    }

    public void invoke(String name, String[] args) {
        if (name.equals(name)) {
            action(args);
        }
    }

    protected void action(String[] args) {
        //@Override this method
        //TODO YOUR COMMAND
        //See also RabbitsCleanUpCommand
    }
}