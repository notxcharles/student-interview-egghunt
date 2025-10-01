package me.candidate.miniproject.command;

import me.candidate.miniproject.MiniProjectPlugin;
import me.candidate.miniproject.scaffold.command.AnnotationCommand;

public class EggHuntCommands implements AnnotationCommand {

    private final MiniProjectPlugin plugin;

    public EggHuntCommands(MiniProjectPlugin plugin) {
        this.plugin = plugin;
    }

    // Your command implementation here
    // This should open the Progress UI
    public void onEggHuntCommand() {
        // Open the Egg Hunt UI
    }

}
