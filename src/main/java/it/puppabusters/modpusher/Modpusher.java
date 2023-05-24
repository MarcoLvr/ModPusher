package it.puppabusters.modpusher;

import it.puppabusters.modpusher.config.ConfigProvider;
import it.puppabusters.modpusher.config.ModPusherConfig;
import it.puppabusters.modpusher.util.FolderUtil;
import lombok.Getter;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class Modpusher implements ClientModInitializer{
    @Getter
    private static Modpusher instance;
    @Getter
    private List<ModPusherConfig.ModToPush> missingOrDifferentMods;
    @Override
    public void onInitializeClient() {
        instance = this;

        File f = new File(FabricLoader.getInstance().getConfigDir().toFile(), "modpusher");
        f.mkdirs();
        ConfigProvider<ModPusherConfig> configProvider = new ConfigProvider<>(f.toPath(), "config.yml", ModPusherConfig.class);
        Collection<String> modsHash = FolderUtil.getModsHashes();
        missingOrDifferentMods = new ArrayList<>();
        configProvider.getConfig().get().getMods().forEach(mod -> {
            if (!modsHash.contains(mod.getHash())) missingOrDifferentMods.add(mod);
        });
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
}
