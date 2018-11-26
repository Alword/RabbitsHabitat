package com.company;

import com.company.Services.MoveTask;
import com.company.Services.SpawnTask;
import com.company.models.AlbinoRabbit;
import com.company.models.BaseRabbit;
import com.company.models.OrdinaryRabbit;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Vector;

public class SimulationApplet extends Applet {

    int currentRabbitsCount = 0;
    boolean isStartSimulation = false;
    boolean isWriteText = false;
    boolean isWriteLog = false;
    boolean isMoving = false;
    boolean isStarted = false;
    float fps = 0;
    LocalDateTime lastFrame = LocalDateTime.now();

    private MoveTask moveTask = null;
    private SpawnTask spawnTask = null;

    private Thread moveThread = null;
    private Thread spawnThread = null;

    public SimulationApplet() {

        Habitat habitat = new Habitat();

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
                        isStartSimulation = !isStartSimulation;
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
        float width = AppSettings.WindowWidth;
        float height = AppSettings.WindowHeight;

        Image offScreenImage = createImage((int) width, (int) height);
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
        } else {
            currentRabbitsCount = 0;
        }

        if (isWriteText || isWriteLog) {
            isWriteLog = false;
            statistic(offScreenGraphics);
        }


        g.drawImage(offScreenImage, 0, 0, null);

        updateFPSCounter();
        repaint();

    }

    private void updateFPSCounter() {
        Duration fpsD = Duration.between(lastFrame, LocalDateTime.now());
        fps = 1f / (fpsD.getSeconds() + fpsD.getNano() / 1000000000f);
        lastFrame = LocalDateTime.now();
    }

    /**
     * Updates the container.  This forwards the update to any lightweight
     * components that are children of this container.  If this method is
     * reimplemented, super.update(g) should be called so that lightweight
     * components are properly rendered.  If a child component is entirely
     * clipped by the current clipping setting in g, update() will not be
     * forwarded to that child.
     *
     * @param g the specified Graphics window
     * @see Component#update(Graphics)
     */
    @Override
    public void update(Graphics g) {
        switchSpawn();
        switchMove();
        paint(g);
    }


    public void start() {
        moveThread.start();
        spawnThread.start();
        isStarted = true;
    }

    private void logicSleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void statistic(Graphics g) {

        int padding = 30;
        g.setFont(new Font("Arial", Font.PLAIN, 24));


        Point pos = new Point(getWidth() / 2, 0);

        if (OrdinaryRabbit.OrdinaryRabbits != null) {
            //g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Обычных кроликов:" + (OrdinaryRabbit.OrdinaryRabbits.size()), pos.x, pos.y += padding);
        }

        if (AlbinoRabbit.AlbinoRabbits != null) {
            //g.setColor(Color.MAGENTA);
            //g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Кроликов альбиносов:" + AlbinoRabbit.AlbinoRabbits.size(), pos.x, pos.y += padding);
        }

        if (BaseRabbit.Rabbits != null) {
            //g.setColor(Color.GREEN);
            //g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Всего кроликов:" + BaseRabbit.Rabbits.size(), pos.x, pos.y += padding);
        }

        //g.setColor(Color.red);
        //g.setFont(new Font("Comic Sans", Font.PLAIN, 24));
        g.drawString("Движение:" + isMoving, pos.x, pos.y += padding);

        //g.setColor(Color.YELLOW);
        //g.setFont(new Font("Comic Sans", Font.PLAIN, 24));
        g.drawString("FPS: " + Math.round(fps / 10) * 10, pos.x, pos.y += padding);

        //g.setColor(Color.red);
        //g.setFont(new Font("Comic Sans", Font.PLAIN, 24));
        g.drawString("Симуляция:" + isStartSimulation, pos.x, pos.y += padding);
    }

    private void switchMove() {
        moveTask.isCancel = !isMoving;
    }

    private void switchSpawn() {
        spawnTask.iSimulated = isStartSimulation;
    }
}