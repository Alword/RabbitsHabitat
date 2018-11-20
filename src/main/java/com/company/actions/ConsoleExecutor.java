package com.company.actions;

import com.company.models.BaseRabbit;
import com.company.models.OrdinaryRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsoleExecutor implements ActionListener {

    private Frame frame = null;

    public ConsoleExecutor(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea displayOutput = new JTextArea(120, 120);
        frame.add(displayOutput);
        //System.exit(0);
    }

    private void removeRabbits(double percent) {
        int count = (int) (OrdinaryRabbit.OrdinaryRabbits.size() * percent);
        for (int i = 0; i < count; i++) {
            OrdinaryRabbit rabbit = OrdinaryRabbit.OrdinaryRabbits.firstElement();
            OrdinaryRabbit.OrdinaryRabbits.remove(rabbit);
            BaseRabbit.Rabbits.remove(rabbit);
        }
    }
}
