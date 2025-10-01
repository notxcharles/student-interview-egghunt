package me.candidate.miniproject.scaffold.command.example;

import me.candidate.miniproject.MiniProjectPlugin;
import me.candidate.miniproject.scaffold.command.AnnotationCommand;
import me.candidate.miniproject.scaffold.config.example.ExampleConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class ExampleCommand implements AnnotationCommand {

    private final MiniProjectPlugin plugin;

    public ExampleCommand(MiniProjectPlugin plugin) {
        this.plugin = plugin;
    }

    @Command("scaffold test")
    @Permission("miniproject.scaffold.test")
    @CommandDescription("An example command to demonstrate the command scaffold.")
    public void onTestCommand(CommandSender sender) { // CommandSender could also be org.bukkit.entity.Player to make cmd player-only.

        // Deserialize the message into a Minecraft component using MiniMessage.
        // We do this to use the 'modern' text formatting system with RGB.
        var parsedMessage = MiniMessage.miniMessage().deserialize("<#ff0000>This is a test command from the Command Scaffold!");

        // Send the message to the player
        sender.sendMessage(parsedMessage);

        // Maybe we want to send a configuration-based message instead?
        plugin.configScaffold().getConfig(ExampleConfig.class).someMessage().send(sender);

    }


}
