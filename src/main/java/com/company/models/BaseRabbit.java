package com.company.models;

import com.company.AppSettings;
import com.company.Habitat;
import com.company.interfaces.IBehaviour;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

///The Super Rabbit's class
public abstract class BaseRabbit implements IBehaviour, Serializable {

    public static Vector<BaseRabbit> Rabbits = null;

    protected float speed = 1;
    protected Point margin = null;

    protected int lifeTime;
    protected String myImagePath = null;

    public BaseRabbit() {
        margin = new Point((int) (Math.random() * AppSettings.WindowWidth),
                (int) (Math.random() * AppSettings.WindowHeight ));
        if (Rabbits == null) {
            Rabbits = new Vector<BaseRabbit>();
        }
        Rabbits.add(this);
    }

    //IBehavior
    public boolean isAppear() {

        return lifeTime-- > 0;
    }

    //IBehavior
    public Image getMyImage() {
        return Habitat.OrdinaryRabbitImage;
    }

    //IBehavior
    public void move() {
        margin.x++;
        margin.y++;
    }

    public Point getMargin() {
        return margin;
    }
}