package me.candidate.miniproject.scaffold.config;

import com.google.common.collect.Maps;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.configurate.ConfigurateException;

import java.io.Closeable;
import java.nio.file.Path;
import java.util.Map;

public class ConfigScaffold implements Closeable {

    private static final Logger LOGGER = LoggerFactory.getLogger("ConfigScaffold");
    private static final Map<Class<?>, ConfigHandler<?>> CONFIGS = Maps.newConcurrentMap();

    private final Path dir;

    public ConfigScaffold(JavaPlugin plugin) {
        var dataFolder = plugin.getDataFolder();

        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        this.dir = plugin.getDataFolder().toPath();
    }

    @Override
    public void close() {
        for(ConfigHandler<?> configHandler : CONFIGS.values()) {
            try {
                configHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveConfig(Class<?> config) {
        try {
            CONFIGS.get(config).saveToFile();
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    public void initConfigs(Class<?>... configs) {
        for(Class<?> config : configs) {
            initConfig(dir, config);
        }
    }

    private void initConfig(Path dir, Class<?> config) {
        LOGGER.info("Initialising Configuration: {}", config.getSimpleName());
        String fileName = config.getSimpleName().toLowerCase() + ".yml";
        CONFIGS.put(config, new ConfigHandler<>(dir, fileName, config));
    }

    public <T> T getConfig(Class<T> config) {
        return (T) CONFIGS.get(config).getConfig();
    }

}