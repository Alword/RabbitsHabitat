package com.company.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectToServerAction implements ActionListener {

    private NetworkManagerExecutor networkManager = null;
    public ConnectToServerAction(NetworkManagerExecutor networkManager) {
        this.networkManager = networkManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
