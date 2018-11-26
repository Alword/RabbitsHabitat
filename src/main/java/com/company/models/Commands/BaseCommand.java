package com.company.models.Commands;

import com.company.interfaces.ICommand;

import java.util.Vector;

public class BaseCommand implements ICommand {
    public static Vector<BaseCommand> Commands = null;

    private String description = null;
    private String name = null;

    public BaseCommand(String name, String description) {
        this.name = name;
        this.description = description;
        if (Commands == null) {
            Commands = new Vector<>();
        }
        Commands.add(this);
    }

    public boolean invoke(String name, String[] args) {
        boolean isMyName = this.name.equals(name);
        if (isMyName) {
            action(args);
        }
        return  isMyName;
    }

    protected void action(String[] args) {
        //@Override this method
        //TODO YOUR COMMAND
        //See also RabbitsCleanUpCommand
    }

    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}