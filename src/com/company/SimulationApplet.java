package com.company;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class SimulationApplet extends Applet {

    int currentRabbitsCount = 0;
    boolean isStartSimulation = false;
    boolean isWriteText = false;
    boolean isWriteLog = false;
    boolean isMoving = false;
    boolean isStarted = false;

    private MoveTask moveTask = null;
    private SpawnTask spawnTask = null;

    private Thread moveThread;
    private Thread spawnThread;

    public SimulationApplet() {

        KeyAdapter textSimulationButtonHandler = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                System.out.println(e);
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_T:
                        isWriteText = !isWriteText;
                        break;
                }
            }
        };
        this.addKeyListener(textSimulationButtonHandler);

        KeyAdapter moveSimulationButtonHandler = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                System.out.println(e);
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_M:
                        isMoving = !isMoving;
                        break;
                }
            }
        };
        this.addKeyListener(moveSimulationButtonHandler);

        KeyAdapter startSimulationButtonHandler = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                System.out.println(e);
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_B:
                        isStartSimulation = true;
                        break;
                }
            }
        };
        this.addKeyListener(startSimulationButtonHandler);

        KeyAdapter stopSimulationButtonHandler = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                System.out.println(e);
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_E:
                        isStartSimulation = false;
                        isWriteLog = true;
                        break;
                }
            }
        };
        this.addKeyListener(stopSimulationButtonHandler);
    }

    public void init() {
        moveTask = new MoveTask();
        moveThread = new Thread(moveTask);

        spawnTask = new SpawnTask(); //Начинаем популяцию кроликов
        spawnThread = new Thread(spawnTask);
    }

    public void paint(Graphics g) {
        AppSettings.WindowHeight = getHeight();
        AppSettings.WindowWidth = getWidth();
        float width = AppSettings.WindowHeight;
        float height = AppSettings.WindowWidth;

        Image offScreenImage = createImage((int)width, (int)height);
        Graphics offScreenGraphics = offScreenImage.getGraphics();

        if (BaseRabbit.Rabbits != null) {
            currentRabbitsCount = BaseRabbit.Rabbits.size();
            try {
                Vector<BaseRabbit> baseRabbits = (Vector<BaseRabbit>) BaseRabbit.Rabbits.clone();
                for (BaseRabbit rabbit : baseRabbits) {
                    Image currentImage = rabbit.getMyImage();
                    int posX = rabbit.getMargin().x;
                    int posY = rabbit.getMargin().y;
                    offScreenGraphics.drawImage(currentImage, posX, posY, AppSettings.ImgScale, AppSettings.ImgScale, this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            currentRabbitsCount = 0;
        }

        if (isWriteText || isWriteLog) {
            isWriteLog = false;
            statistic(g);
        }


        g.drawImage(offScreenImage, 0, 0, null);

        //logicSleep();
        //repaint();

    }


    public void start() {
        moveThread.start();
        spawnThread.start();
        isStarted = true;
        while (isStarted) {
            switchSpawn();
            switchMove();
            repaint();
        }
        //repaint();
    }

    private void logicSleep() {
        try {
            Thread.sleep(1000 / 40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void statistic(Graphics g) {
        Point pos = new Point(getWidth() / 2, 0);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        g.drawString("Обычных кроликов:" + (OrdinaryRabbit.OrdinaryCount - AlbinoRabbit.AlbinoCount), pos.x, pos.y += 20);

        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Verdana", Font.PLAIN, 24));
        g.drawString("Кроликов альбиносов:" + AlbinoRabbit.AlbinoCount, pos.x, pos.y += 20);

        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Всего кроликов:" + OrdinaryRabbit.OrdinaryCount, pos.x, pos.y += 20);

        g.setColor(Color.red);
        g.setFont(new Font("Comic Sans", Font.PLAIN, 24));
        g.drawString("Движение:" + isMoving, pos.x, pos.y += 20);
    }

    private void switchMove() {
        moveTask.isCancel = !isMoving;
    }

    private void switchSpawn() {
        spawnTask.iSsimulated = isStartSimulation;
    }
}