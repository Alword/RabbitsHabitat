package com.company.Services.Commands;

import com.company.models.OrdinaryRabbit;

public class RabbitsCleanUpCommand extends BaseCommand {
    
    public RabbitsCleanUpCommand() {
        super("cleanup");
    }

    @Override
    protected void action(String args[]) {
        Integer x = Integer.parseInt(args[0]);
        x = (int) (OrdinaryRabbit.Rabbits.size() * x / 100.0);
        for (int i = 0; i < x; i++) {
            OrdinaryRabbit.Rabbits.get(i).kill();
        }
    }
}