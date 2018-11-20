package com.company.models;

import com.company.AppSettings;
import com.company.Habitat;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class AlbinoRabbit extends OrdinaryRabbit {

    public static Vector<AlbinoRabbit> AlbinoRabbits = null;
    public static int AlbinoCount = 0;

    public boolean moveRight = true;

    public AlbinoRabbit() throws IOException {
        AlbinoCount++;
        lifeTime = (int) ((Math.random() * 40) + 10);
        speed = 2;

        if (AlbinoRabbits == null) {
            AlbinoRabbits = new Vector<AlbinoRabbit>();
        }
        AlbinoRabbits.add(this);
        OrdinaryRabbits.remove(this);
    }

    @Override
    public void move() {
        if (moveRight) {
            moveRight();
            moveRight = margin.x + 1 < AppSettings.getWidthPadding();
        } else {
            moveLeft();
            moveRight = margin.x - 1 < 0;
        }
    }

    //IBehavior
    @Override
    public Image getMyImage() {
        return Habitat.AlbinoRabbitImage;
    }

    @Override
    public boolean isAppear() {
        boolean isAppear = lifeTime-- > 0;
        if (!isAppear) {
            AlbinoCount--;
            AlbinoRabbits.remove(this);
        }
        return isAppear;
    }
}