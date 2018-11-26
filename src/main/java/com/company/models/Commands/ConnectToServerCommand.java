package com.company.models.Commands;

import com.company.Habitat;

import javax.swing.*;
import java.io.IOException;

public class ConnectToServerCommand extends BaseCommand {

    JTextArea area = null;

    public ConnectToServerCommand(JTextArea area) {
        super("connect", "подключается к серверу ip port");
        this.area = area;
    }

    @Override
    public void action(String[] args) {
        try {
            Habitat.networkClient.connectToServer(args[0], Integer.parseInt(args[1]));
        } catch (IOException ex) {
            area.append(ex.getMessage());
        }
    }
}
