package com.company.models;

import com.company.AppSettings;
import com.company.Habitat;
import com.company.models.BaseRabbit;

import java.io.IOException;
import java.util.Vector;

public class OrdinaryRabbit extends BaseRabbit {

    public static Vector<OrdinaryRabbit> OrdinaryRabbits = null;
    private final int MIN_STEPS = 40;
    private int dir = 0;
    private int steps = MIN_STEPS;

    public OrdinaryRabbit() throws IOException {
        lifeTime = (int) ((Math.random() * 20) + 20);

        if (OrdinaryRabbits == null) {
            OrdinaryRabbits = new Vector<OrdinaryRabbit>();
        }
        OrdinaryRabbits.add(this);
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
        if (margin.y > 0) {
            margin.y--;
        } else {
            margin.y++;
        }
    }

    protected void moveLeft() {
        if (margin.x > 0) {
            margin.x--;
        } else {
            margin.x++;
        }
    }

    protected void moveRight() {
        if (margin.x < AppSettings.getWidthPadding()) {
            margin.x++;
        } else {
            margin.x--;
        }
    }

    protected void moveDown() {
        if (margin.y < AppSettings.getHeightPadding()) {
            margin.y++;
        } else {
            margin.y--;
        }
    }

    @Override
    public boolean isAppear() {
        boolean isAppear = lifeTime-- > 0;
        if (!isAppear) {
            OrdinaryRabbits.remove(this);
        }
        return isAppear;
    }
}
