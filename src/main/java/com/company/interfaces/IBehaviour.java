package com.company.interfaces;

import java.awt.*;
import java.io.IOException;

public interface IBehaviour {
    public boolean isAppear();

    public Image getMyImage() throws IOException;

    public void move();
}