package com.company.models.Commands;

import com.company.interfaces.ICommand;

import javax.swing.*;
import java.util.List;

public class HelpCommand extends BaseCommand {

    private JTextArea area = null;
    private List<ICommand> commands;

    public HelpCommand(JTextArea area, List<ICommand> commands) {
        super("help", "Выводит список команд");//ПОМОГИТЕ!
        this.commands = commands;
        this.area = area;
    }

    @Override
    protected void action(String args[]) {
        for (ICommand command :
                commands) {
            area.append("#" + command.getName() + " - " + command.getDescription() + "\n");
        }
    }
}