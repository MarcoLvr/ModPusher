package it.eblcraft.modpusher.graphics;

import javax.swing.*;

public class ModPusherGui extends JFrame {
    public ModPusherGui(String playerName, String packName){
        setTitle("");
        setSize(900,600);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(GraphicsUtil.loadImageFromResources("bg.png")));

        add(label);
        setVisible(true);
    }
}
