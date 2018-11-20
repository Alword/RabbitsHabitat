package com.company.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsoleExecutor implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
