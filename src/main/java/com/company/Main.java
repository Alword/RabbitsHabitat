package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Пример");
        SimulationApplet applet = new SimulationApplet();
        frame.setSize(800, 600);
        frame.setLocation(60,100);
        frame.getContentPane().add(applet);
        applet.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        applet.start();


        /*


        SimulationApplet frame = new SimulationApplet();
        frame.setSize(1800, 800);
        frame.setLocation(60,100);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.start();
        */
    }
}
