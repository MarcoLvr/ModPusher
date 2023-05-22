package it.eblcraft.modpusher.mixin;

import it.eblcraft.modpusher.graphics.CustomTitleScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SplashOverlay;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.ResourceReload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(SplashOverlay.class)
public class SplashScreenMixin {

    boolean executed = false;

    @Inject(method = "render", at=@At("HEAD"), cancellable = true)
    public void c(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci){
        if(executed) return;
        executed=true;
        System.out.println("PORCO DIO ODIO LE MOD");
        MinecraftClient.getInstance().setScreenAndRender(new CustomTitleScreen());
    }
}
