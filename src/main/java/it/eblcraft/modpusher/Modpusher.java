package it.eblcraft.modpusher;

import it.eblcraft.modpusher.config.ConfigProvider;
import it.eblcraft.modpusher.config.ModPusherConfig;
import it.eblcraft.modpusher.exception.RestartForModApplyException;
import it.eblcraft.modpusher.graphics.CustomTitleScreen;
import it.eblcraft.modpusher.graphics.ModPusherGui;
import it.eblcraft.modpusher.graphics.runner.GraphicsRunner;
import it.eblcraft.modpusher.util.FolderUtil;
import it.eblcraft.modpusher.util.ModUtil;
import lombok.Getter;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.apache.commons.compress.utils.Lists;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class Modpusher implements ClientModInitializer, ScreenEvents.AfterInit{

    private ConfigProvider<ModPusherConfig> configProvider;

    @Override
    public void onInitializeClient() {
        ScreenEvents.AFTER_INIT.register(this::afterInit);
        File f = new File(FabricLoader.getInstance().getConfigDir().toFile(), "modpusher");
        f.mkdirs();
        configProvider = new ConfigProvider<>(f.toPath(), "config.yml", ModPusherConfig.class);
        Collection<String> modsHash = FolderUtil.getModsHashes();
        List<ModPusherConfig.ModToPush> missingOrDifferentMods = new ArrayList<>();
        configProvider.getConfig().get().getMods().forEach(mod -> {
            if (!modsHash.contains(mod.getHash())) missingOrDifferentMods.add(mod);
        });

        /*new Thread(()->{
            ModPusherGui gui = new ModPusherGui(MinecraftClient.getInstance().getName(), configProvider.getConfig().get().getPackName());

        }).start();*/


        missingOrDifferentMods.forEach(mod -> {
            System.out.println("sto installando: " + mod.getUrl());
            ModUtil.installMod(mod.getUrl(), mod.getName());
        });
        if (!missingOrDifferentMods.isEmpty()) {
            System.out.println("Ho finito");
            throw new RestartForModApplyException();
        }

    }

    public static String calcHash(File f) {
        try {
            Path filePath = Path.of(f.getPath());

            byte[] data = Files.readAllBytes(Paths.get(filePath.toUri()));
            byte[] hash = MessageDigest.getInstance("MD5").digest(data);
            String checksum = new BigInteger(1, hash).toString(16);
            return checksum;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void afterInit(MinecraftClient client, Screen screen, int scaledWidth, int scaledHeight) {
        //TODO: grafica
    }
}
