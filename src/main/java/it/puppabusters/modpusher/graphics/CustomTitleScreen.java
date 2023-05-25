package it.puppabusters.modpusher.graphics;

import com.mojang.blaze3d.systems.RenderSystem;
import it.puppabusters.modpusher.Modpusher;
import it.puppabusters.modpusher.util.ModUtil;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CustomTitleScreen extends Screen {
    @Setter
    @Getter
    private int totalMods = 0;
    @Setter
    @Getter
    private int currentMod = 0;
    @Setter
    private String currentModName = "";
    private MinecraftClient client;
    private Identifier background;
    private Modpusher modpusher;

    public CustomTitleScreen() {
        super(Text.of("ModPusher - Preparing mods..."));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBg(matrices);
        drawCenteredText(matrices, textRenderer, "Mod Pusher", width / 2, height / 2 - 30, 0xFFFFFF);
        if (currentModName.equals("modpusher:ended")) {
            drawCenteredText(matrices, textRenderer, "Download of " + totalMods + " mods  completed", width / 2, height / 2 - 20, 0xFFFFFF);
            drawCenteredText(matrices, textRenderer, "You can restart minecraft now", width / 2, height / 2 + 30, 0xFFFFFF);
        } else {
            drawCenteredText(matrices, textRenderer, "Downloading mods", width / 2, height / 2 - 20, 0xFFFFFF);
            drawCenteredText(matrices, textRenderer, currentModName + " (" + currentMod + "/" + totalMods + ")", width / 2, height / 2 + 30, 0xFFFFFF);
        }
        pageController();
        checkEnded();
    }

    private void pageController() {
        client.getMusicTracker().stop();
    }

    @Override
    protected void init() {
        modpusher = Modpusher.getInstance();
        // Inizializza gli elementi della schermata personalizzata
        client = MinecraftClient.getInstance();
        client.getMusicTracker().stop();
        client.getWindow().setWindowedSize(900, 600);

        background = new Identifier("backgrounds/modpusherbg.png");

        if (modpusher.getMissingOrDifferentMods().isEmpty())
            return;

        setTotalMods(modpusher.getMissingOrDifferentMods().size());
        new Thread(() -> modpusher.getMissingOrDifferentMods().forEach(mod -> {
            setCurrentModName(mod.getName());
            ModUtil.installMod(mod.getUrl(), mod.getName());
            setCurrentMod(getCurrentMod() + 1);
        })).start();
    }

    private void checkEnded() {
        if (getCurrentMod() >= getTotalMods()) {
            setCurrentModName("modpusher:ended");
            Modpusher.getInstance().getMissingOrDifferentMods().clear();
        }
    }

    private void renderBg(MatrixStack matrixStack) {
        super.renderBackground(matrixStack);
        RenderSystem.setShaderTexture(0, background);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        bufferBuilder.vertex(0.0, (double) this.height, 0.0).texture(0.0F, 1.0F).color(255, 255, 255, 255).next();
        bufferBuilder.vertex((double) this.width, (double) this.height, 0.0).texture(1.0F, 1.0F).color(255, 255, 255, 255).next();
        bufferBuilder.vertex((double) this.width, 0.0, 0.0).texture(1.0F, 0.0F).color(255, 255, 255, 255).next();
        bufferBuilder.vertex(0.0, 0.0, 0.0).texture(0.0F, 0.0F).color(255, 255, 255, 255).next();
        tessellator.draw();
    }

}
