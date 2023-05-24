package it.puppabusters.modpusher.util;

import it.puppabusters.modpusher.Modpusher;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FolderUtil {
    public static Collection<String> getModsHashes() {
        List<String> hashes = new ArrayList<>();
        File modsFolder = FabricLoader.getInstance().getGameDir().resolve("mods").toFile();
        for (String mod : modsFolder.list()) {
            if (!mod.endsWith(".jar")) continue;
            File modFile = new File(modsFolder, mod);
            hashes.add(Modpusher.calcHash(modFile));
            System.out.println(mod);
        }
        return hashes;
    }
}
