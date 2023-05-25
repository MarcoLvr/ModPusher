package it.puppabusters.modpusher.mixin;

import it.puppabusters.modpusher.Modpusher;
import it.puppabusters.modpusher.graphics.CustomTitleScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class SplashScreenMixin {
    private boolean executed = false;
    private CustomTitleScreen modPusherTitleScreen;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void c(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (executed) {
            MinecraftClient.getInstance().setScreenAndRender(modPusherTitleScreen);
            return;
        }
        if(Modpusher.getInstance() == null || Modpusher.getInstance().getMissingOrDifferentMods().size() == 0) return;
        executed = true;
        modPusherTitleScreen = new CustomTitleScreen();
        MinecraftClient.getInstance().setScreenAndRender(modPusherTitleScreen);
        modPusherTitleScreen.setCurrentMod(0);
    }
}
