package me.candidate.miniproject;

import me.candidate.miniproject.command.EggHuntCommands;
import me.candidate.miniproject.config.EggHuntConfig;
import me.candidate.miniproject.listener.BlockInteractListener;
import me.candidate.miniproject.scaffold.command.CommandScaffold;
import me.candidate.miniproject.scaffold.command.example.ExampleCommand;
import me.candidate.miniproject.scaffold.config.ConfigScaffold;
import me.candidate.miniproject.scaffold.config.example.ExampleConfig;
import me.candidate.miniproject.scaffold.sql.SqlScaffold;
import me.candidate.miniproject.scaffold.sql.example.ExampleSql;
import me.candidate.miniproject.storage.EggHuntStorageHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * EggHunt Mini Project Scaffold
 *
 * This scaffold is here to help you. You can change it in any way, shape or form you'd like (or even write in Kotlin if
 * preferred)! This is your chance to impress us and show us you're the right candidate for the job.
 *
 * The main goal of egghunt is simple:
 *
 * When a player right-clicks a pre-determined Minecraft Head, they will be given an award by a command
 * in the config. A message is sent to tell the player they have claimed the head.
 *
 * There is a Chest UI (using triumph-gui) which shows current progress
 *
 * 'Bonus Tasks'
 * These are tasks you may want to tackle to enhance your project. These are not required steps.
 *
 * - When the player claims the head, a 'hologram is placed above the head. This can be done by using
 * FancyHolograms: https://modrinth.com/plugin/fancyholograms
 * - When finding 'all' the heads in the world, you get a secondary award defined in the configuration file
 * - Adding the ability to 'customize' the skull texture to an actual egg
 *
 * Example Implementation of another project 'postman': https://github.com/SynxGames/student-interview-minecraft-example
 *
 * Stuck? Not got Minecraft and/or a PC at home?
 * Email us at jobs@synx.games and one of our team members will arrange for you to come and use a machine in our office and/or
 * get 1-to-1 support on your problem - It's better to ask for help than to get stuck (It's also not a bad thing - everyone
 * here gets stuck sometimes!)
 */
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

        commandScaffold.registerAnnotatedCommand(new EggHuntCommands(this));
        configScaffold.initConfigs(EggHuntConfig.class);

        EggHuntStorageHandler storageHandler = new EggHuntStorageHandler(this);

        getServer().getPluginManager().registerEvents(
                new BlockInteractListener(this, storageHandler),
                this
        );

        EggHuntManager eggManager = new EggHuntManager(this);
        getServer().getScheduler().runTaskLater(this, () -> {
            eggManager.placeAllEggs();
        }, 20L); // Wait 1 second (20 ticks)

        getLogger().info("EggHunt plugin enabled!");

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
