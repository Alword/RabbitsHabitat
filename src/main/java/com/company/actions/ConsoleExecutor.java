package com.company.actions;

import com.company.Services.CommandParser;
import com.company.adaptors.NewLineAdaptor;
import com.company.models.BaseRabbit;
import com.company.models.OrdinaryRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class ConsoleExecutor implements ActionListener {

    private Frame frame = null;

    private PipedWriter pipedWriter = null;
    private PipedReader pipedReader = null;


    public ConsoleExecutor(Frame frame) {
        this.frame = frame;
        pipedWriter = new PipedWriter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CommandParser(pipedWriter).start();

        JFrame consoleWindow = new JFrame();
        JTextArea displayOutput = new JTextArea(120, 120);
        displayOutput.addKeyListener(new NewLineAdaptor(displayOutput,pipedWriter));
        consoleWindow.add(displayOutput);
        consoleWindow.setVisible(true);
        consoleWindow.setSize(400, 400);
        consoleWindow.setLocation(200, 200);

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