package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

///The Super Rabbit's class
public abstract class BaseRabbit implements IBehaviour {

    protected float speed = 1;
    protected Point margin = null;

    public static Vector<BaseRabbit> Rabbits = null;

    protected int lifeTime;

    protected Image myImage = null;

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
        return myImage;
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
