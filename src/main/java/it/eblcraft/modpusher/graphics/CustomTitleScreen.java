package it.eblcraft.modpusher.graphics;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class CustomTitleScreen extends Screen {
    public CustomTitleScreen() {
        super(Text.of("Custom Title Screen"));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

    }

    @Override
    protected void init() {
        // Inizializza gli elementi della schermata personalizzata
    }

}
