package it.eblcraft.modpusher.graphics;

import it.eblcraft.modpusher.graphics.panels.BasePanel;
import it.eblcraft.modpusher.graphics.panels.LeftPanel;

import javax.swing.*;

public class ModPusherGui extends JFrame {
    public ModPusherGui(String playerName, String packName){
        setTitle("");
        setSize(900,600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new BasePanel());
        add(new LeftPanel());
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        new ModPusherGui("test", "test");
    }
}
