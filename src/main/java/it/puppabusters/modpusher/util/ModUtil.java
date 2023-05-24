package it.puppabusters.modpusher.util;

import net.fabricmc.loader.api.FabricLoader;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ModUtil {

    public static void installMod(String u, String modName) {
        try {
            URL url = new URL(u);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            Files.copy(inputStream, FabricLoader.getInstance().getGameDir().resolve("mods").resolve(modName + ".jar"), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
