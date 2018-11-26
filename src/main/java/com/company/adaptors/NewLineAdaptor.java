package com.company.adaptors;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;

public class NewLineAdaptor extends KeyAdapter {

    private JTextArea area = null;
    private PipedWriter printWriter = null;


    public NewLineAdaptor(JTextArea area, PipedWriter printWriter) {
        this.area = area;
        this.printWriter = printWriter;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String x = area.getText().split("\\n")[area.getLineCount() - 1];
                printWriter.write(x);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
