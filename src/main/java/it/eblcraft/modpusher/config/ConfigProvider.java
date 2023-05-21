package it.eblcraft.modpusher.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class ConfigProvider<T> {

    private final ObjectMapper mapper;
    private T config;
    private final Class<T> configClass;
    private final File filePath;

    public ConfigProvider(Path dataDirectory, String fileName, Class<T> configClass) {
        this.configClass = configClass;
        mapper = new ObjectMapper(new YAMLFactory());
        filePath = new File(dataDirectory.toFile(), fileName);
        if (!filePath.exists()) {
            filePath.getParentFile().mkdirs();
            try {
                config = configClass.getConstructor().newInstance();
                reloadConfig(true);
            } catch (NoSuchMethodException e) {
                System.out.println("Cannot create config: Missing no args constructor!");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            loadConfig();
        }

    }

    private boolean loadConfig() {
        try {
            config = mapper.readValue(filePath, configClass);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveConfig() {
        try {
            mapper.writeValue(filePath, config);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<T> getConfig() {
        return Optional.ofNullable(config);
    }


    public void reloadConfig(boolean save) {
        if (save) saveConfig();
        loadConfig();
    }
}
