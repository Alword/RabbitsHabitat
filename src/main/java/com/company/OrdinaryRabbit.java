package com.company;

import java.awt.*;
import java.io.IOException;

public class OrdinaryRabbit extends BaseRabbit {

    private final int MIN_STEPS = 4;
    private int dir = 0;
    private int steps = 4;

    public static int OrdinaryCount = 0;

    public OrdinaryRabbit() throws IOException {
        OrdinaryCount++;
        myImage = Habitat.getOrdinaryRabbitPic();
        lifeTime = (int) ((Math.random() * 20) + 1);
    }

    @Override
    public void move() {

        if (steps < 0) {
            dir = (int) (Math.random() * 4);
            steps = MIN_STEPS;
        }
        steps--;
        switch (dir) {
            case 0:
                moveDown();
                break;
            case 1:
                moveLeft();
                break;
            case 2:
                moveUp();
                break;
            case 3:
                moveRight();
                break;
        }
    }

    protected void moveUp() {
        margin.y--;
    }

    protected void moveLeft() {
        margin.x--;
    }

    protected void moveRight() {
        margin.x++;
    }

    protected void moveDown() {
        margin.y++;
    }

    @Override
    public boolean isAppear() {
        boolean isAppear = lifeTime-- > 0;
        if (!isAppear) {
            OrdinaryCount--;
        }
        return isAppear;
    }
}
