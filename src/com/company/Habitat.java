package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Habitat {

    static final String dir = "file:///C:/Users/aasle/Downloads/";


    public static Image getOrdinaryRabbitPic() {
        return loadImg(dir + "lab4OrdinaryRabbit.png");
    }

    public static Image getAlbinoRabbitPic() {
        return loadImg(dir + "lab4AlbinoRabbit.png");
    }

    //util
    private static Image loadImg(String imagePath) {
        Image im = null;
        URL url = null;
        try {
            url = new URL(imagePath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            im = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return im;
    }

}
