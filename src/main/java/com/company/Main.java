package com.company;

import com.company.actions.ConsoleExecutor;
import com.company.actions.LoadFileActionListener;
import com.company.actions.NetworkManagerExecutor;
import com.company.actions.SaveFileActionListener;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        //This is to init Main frame config
        JFrame frame = new JFrame("Пример");

        SimulationApplet applet = new SimulationApplet();
        frame.setSize(AppSettings.WindowWidth, AppSettings.WindowHeight);
        frame.setLocation(60, 100);
        frame.getContentPane().add(applet);

        //This is to add top menu
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        //Next group
        //This group to manipulate file
        JMenu file = new JMenu("Файл");
        menuBar.add(file);

        JMenuItem save = new JMenuItem("Сохранить");
        save.addActionListener(new SaveFileActionListener(frame));
        file.add(save);

        JMenuItem load = new JMenuItem("Загрузить");
        load.addActionListener(new LoadFileActionListener(frame));
        file.add(load);

        //Next group to execute windows
        //This is to group of additional windows
        JMenu windowsMenu = new JMenu("Окна");
        menuBar.add(windowsMenu);

        //This is to add Console
        JMenuItem consoleWindowButton = new JMenuItem("Консоль");
        windowsMenu.add(consoleWindowButton);
        consoleWindowButton.addActionListener(new ConsoleExecutor(frame));

        //This is to add NetworkManager
        JMenuItem networkWindowButton = new JMenuItem("Сетевое взаимодействие");
        windowsMenu.add(networkWindowButton);
        networkWindowButton.addActionListener(new NetworkManagerExecutor(frame));


        //This is to start applet
        applet.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        applet.start();
    }
}
