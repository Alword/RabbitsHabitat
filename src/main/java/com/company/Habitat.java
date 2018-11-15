package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Habitat {


    public static Image getOrdinaryRabbitPic() throws IOException {
        return loadImg(Main.class.getClassLoader().getResource("lab4OrdinaryRabbit.png"));
    }

    public static Image getAlbinoRabbitPic() throws IOException {
        return loadImg(Main.class.getClassLoader().getResource("lab4AlbinoRabbit.png"));
    }

    //util
    private static Image loadImg(String imagePath) throws IOException {
        URL url = null;
        url = new URL(imagePath);
        return loadImg(url);
    }

    private static Image loadImg(URL imageURL) throws IOException {
        Image im = null;
        im = ImageIO.read(imageURL);
        return im;
    }

}