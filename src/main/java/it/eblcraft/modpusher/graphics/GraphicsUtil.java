package it.eblcraft.modpusher.graphics;

import it.eblcraft.modpusher.Modpusher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicsUtil {

    public static BufferedImage loadImageFromResources(String name){
        try {
            return ImageIO.read(Modpusher.class.getClassLoader().getResourceAsStream("assets/"+name));
        } catch (IOException e) {
            return null;
        }
    }
}
