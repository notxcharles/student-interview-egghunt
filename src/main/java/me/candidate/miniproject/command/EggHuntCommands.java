package me.candidate.miniproject.command;

import me.candidate.miniproject.MiniProjectPlugin;
import me.candidate.miniproject.scaffold.command.AnnotationCommand;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.CommandDescription;
import org.incendo.cloud.annotations.Permission;

public class EggHuntCommands implements AnnotationCommand {

    private final MiniProjectPlugin plugin;

    public EggHuntCommands(MiniProjectPlugin plugin) {
        this.plugin = plugin;
    }

    // Your command implementation here
    // This should open the Progress UI
    @Command("egghunt progress")
    @Permission("miniproject.egghunt.progress") //luckperms user MarkDonovan permission set miniproject.egghunt.progress true
    @CommandDescription("View current progress in the egghunt")
    public void onEggHuntCommand(CommandSender sender) {
        var parsedMessage = MiniMessage.miniMessage().deserialize("<#ff0000>user wants to view their egg hunt progress!");

        // Send the message to the player
        sender.sendMessage(parsedMessage);

        // TODO
        // Open the Egg Hunt UI
    }

}
