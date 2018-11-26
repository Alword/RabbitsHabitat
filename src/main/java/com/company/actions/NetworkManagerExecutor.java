package com.company.actions;

import com.company.adaptors.NewLineAdaptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NetworkManagerExecutor implements ActionListener {

    private Frame frame;

    public NetworkManagerExecutor(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Сетевой менеджер");

        JPanel labelPanel = new JPanel();
        JTextArea textArea = new JTextArea("Сервер недоступен");
        textArea.setSize(350,350);
        textArea.setBackground(Color.getHSBColor(0.0F,0.0F,0.937F));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        labelPanel.add(textArea);
        frame.add(labelPanel);

        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setLocation(200, 200);
    }
}
