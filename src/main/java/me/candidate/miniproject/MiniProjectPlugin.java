package me.candidate.miniproject;

import me.candidate.miniproject.scaffold.command.CommandScaffold;
import me.candidate.miniproject.scaffold.command.example.ExampleCommand;
import me.candidate.miniproject.scaffold.config.ConfigScaffold;
import me.candidate.miniproject.scaffold.config.example.ExampleConfig;
import me.candidate.miniproject.scaffold.sql.SqlScaffold;
import me.candidate.miniproject.scaffold.sql.example.ExampleSql;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiniProjectPlugin extends JavaPlugin {

    private CommandScaffold commandScaffold;
    private ConfigScaffold configScaffold;
    private SqlScaffold sqlScaffold;

    /**
     * This is the entrypoint to the project. Your code definitions should go in here, below the scaffolding!
     */
    @Override
    public void onEnable() {

        // Scaffolding Initialisation
        commandScaffold = new CommandScaffold(this); // CommandScaffold handles all command registration using incendo/cloud
        configScaffold = new ConfigScaffold(this); // ConfigScaffold handles creating config files (which reload at runtime)
        sqlScaffold = new SqlScaffold();

        // --- EXAMPLES ---

        // Register the ExampleCommand provided within the scaffolding
        commandScaffold.registerAnnotatedCommand(new ExampleCommand(this));

        // Register the Example Config provided within the scaffolding
        configScaffold.initConfigs(ExampleConfig.class);

        // Check SQL is working with the example
        new ExampleSql(this);

        // INSERT YOUR CODE HERE

    }

    @Override
    public void onDisable() {
        // Close all WatchFiles
        configScaffold.close();
    }

    public CommandScaffold commandScaffold() {
        return this.commandScaffold;
    }

    public ConfigScaffold configScaffold() {
        return this.configScaffold;
    }

    public SqlScaffold sqlScaffold() {
        return this.sqlScaffold;
    }
}
