package com.company;

import com.company.actions.ConsoleExecutor;
import com.company.actions.LoadFileActionListener;
import com.company.actions.SaveFileActionListener;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Пример");
        SimulationApplet applet = new SimulationApplet();
        frame.setSize(AppSettings.WindowWidth, AppSettings.WindowHeight);
        frame.setLocation(60, 100);
        frame.getContentPane().add(applet);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu file = new JMenu("Файл");
        menuBar.add(file);

        JMenuItem save = new JMenuItem("Сохранить");
        save.addActionListener(new SaveFileActionListener(frame));
        file.add(save);

        JMenuItem load = new JMenuItem("Загрузить");
        load.addActionListener(new LoadFileActionListener(frame));
        file.add(load);

        JMenu commands = new JMenu("Команды");
        menuBar.add(commands);

        JMenuItem console = new JMenuItem("Открыть консоль");
        commands.add(console);
        console.addActionListener(new ConsoleExecutor(frame));

        applet.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        applet.start();
    }
}
