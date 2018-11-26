package com.company.adaptors;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;

//This is to check event when hit Enter button
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
                String areaText = area.getText();
                String x = areaText.split("\\n")[area.getLineCount() - 1];
                if (x.length() > 0) {
                    printWriter.write(x);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
