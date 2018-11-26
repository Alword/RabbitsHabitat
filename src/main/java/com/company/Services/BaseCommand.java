package com.company.Services;

import java.util.Vector;

public class BaseCommand {
    public static Vector<BaseCommand> Commands = null;

    public final String name = "BaseCommand";

    public BaseCommand(){
        if(Commands == null){
            Commands = new Vector<>();
        }
        Commands.add(this);
    }

    public void Execute(String name, String[] args){

    }
}
