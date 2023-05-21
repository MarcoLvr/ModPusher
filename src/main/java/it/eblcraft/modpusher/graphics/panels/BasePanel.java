package it.eblcraft.modpusher.graphics.panels;

import it.eblcraft.modpusher.graphics.GraphicsUtil;

import javax.swing.*;

public class BasePanel extends JPanel {

    private JLabel background;

    public BasePanel(){
        background=new JLabel();
        background.setIcon(new ImageIcon(GraphicsUtil.loadImageFromResources("bg.jpg")));
    }


}
