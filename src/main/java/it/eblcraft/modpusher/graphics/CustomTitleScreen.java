package it.eblcraft.modpusher.graphics;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CustomTitleScreen extends Screen {
    public CustomTitleScreen() {
        super(Text.of("Custom Title Screen"));
    }

    @Override
    protected void init() {
        // Inizializza gli elementi della schermata personalizzata
    }



    /*@Override
    public void render(int mouseX, int mouseY, float delta) {
        // Disegna gli elementi della schermata personalizzata
        super.render(mouseX, mouseY, delta);
    }*/
}
