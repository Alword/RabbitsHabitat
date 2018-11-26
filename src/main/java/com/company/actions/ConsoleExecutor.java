package com.company.actions;

import com.company.Services.ConsoleCommandParser;
import com.company.adaptors.NewLineAdaptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JFrame consoleWindow = new JFrame();
        JTextArea displayOutput = new JTextArea(120, 120);
        displayOutput.addKeyListener(new NewLineAdaptor(displayOutput,pipedWriter));
        consoleWindow.add(displayOutput);
        consoleWindow.setVisible(true);
        consoleWindow.setSize(400, 400);
        consoleWindow.setLocation(200, 200);
        new ConsoleCommandParser(pipedWriter,displayOutput).start();
    }
}