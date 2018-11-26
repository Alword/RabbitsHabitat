package com.company.models.Commands;

import com.company.models.OrdinaryRabbit;

import javax.swing.*;

public class RabbitsCleanUpCommand extends BaseCommand {

    private JTextArea area;

    public RabbitsCleanUpCommand(JTextArea area) {
        super("cleanup", "Убирает N% кроликов");
        this.area = area;
    }

    @Override
    protected void action(String args[]) {
        Integer x = Integer.parseInt(args[0]);
        x = (int) (OrdinaryRabbit.Rabbits.size() * x / 100.0);
        for (int i = 0; i < x; i++) {
            OrdinaryRabbit.Rabbits.get(i).kill();
        }
        area.append("Убрано: " + x + " кроликов\n");
    }
}