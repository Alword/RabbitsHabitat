package com.company;

import com.company.models.OrdinaryRabbit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Habitat {

















    //This is to configure pictures
    public static Image OrdinaryRabbitImage = null;

    public static Image AlbinoRabbitImage = null;

    Habitat() {
        try {
            OrdinaryRabbitImage = getOrdinaryRabbitPic();
            AlbinoRabbitImage = getAlbinoRabbitPic();
        } catch (IOException e) {
        }
    }

    public static Image getOrdinaryRabbitPic() throws IOException {
        return loadImg(Main.class.getClassLoader().getResource("lab4OrdinaryRabbit.png"));
    }

    public static Image getAlbinoRabbitPic() throws IOException {
        return loadImg(Main.class.getClassLoader().getResource("lab4AlbinoRabbit.png"));
    }

    private static Image loadImg(URL imageURL) throws IOException {
        Image im = null;
        im = ImageIO.read(imageURL);
        return im;
    }
}