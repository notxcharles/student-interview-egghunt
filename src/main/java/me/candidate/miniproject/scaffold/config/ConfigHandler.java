package me.candidate.miniproject.scaffold.config;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.reactive.Subscriber;
import org.spongepowered.configurate.reference.ConfigurationReference;
import org.spongepowered.configurate.reference.ValueReference;
import org.spongepowered.configurate.reference.WatchServiceListener;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;

public class ConfigHandler<T> implements AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger("ConfigScaffold/ConfigHandler");

    private WatchServiceListener listener;
    private ConfigurationReference<@NotNull CommentedConfigurationNode> base;
    private ValueReference<T, @NotNull CommentedConfigurationNode> config;

    private final Class<T> clazz;
    private final Path configFile;

    public ConfigHandler(Path applicationFolder, String configName, Class<T> clazz) {
        this.clazz = clazz;
        this.configFile = Paths.get(applicationFolder + File.separator + configName);

        try {
            this.listener = WatchServiceListener.create();
            this.base = this.listener.listenToConfiguration(file ->
                            YamlConfigurationLoader.builder()
                                    .defaultOptions(opts -> opts.shouldCopyDefaults(true))
                                    .nodeStyle(NodeStyle.BLOCK)
                                    .path(file)
                                    .build()
                    , configFile);

            this.listener.listenToFile(configFile, event -> LOGGER.info("Updated ConfigFile " + configFile.getFileName().toString()));

            this.config = this.base.referenceTo(clazz);
            this.base.save();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T getConfig() {
        return config.get();
    }

    public void saveToFile() throws ConfigurateException {
        this.base.node().set(clazz, clazz.cast(getConfig()));
        this.base.loader().save(this.base.node());
    }

    @Override
    public void close() throws Exception {
        try {
            this.listener.close();
            this.base.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addListener(Subscriber<WatchEvent<?>> listener) {
        try {
            this.listener.listenToFile(configFile, listener);
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }
}