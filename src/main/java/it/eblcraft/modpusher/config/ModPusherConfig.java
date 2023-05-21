package it.eblcraft.modpusher.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ModPusherConfig {

    private String packName = "Unnamed pack";
    private List<ModToPush> mods = new ArrayList<>();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ModToPush {

        private String url = "";
        private String hash = "";
        private String name = "";

    }
}
