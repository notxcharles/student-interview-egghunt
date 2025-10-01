package me.candidate.miniproject.gui;

import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class EggHuntGui {

    /**
     * You can make a UI in this class here by passing in the player
     * Examples of UI: https://triumphteam.dev/docs/triumph-gui/gui
     */
    public static void open(Player player) {

        Gui gui = Gui.gui()
                .disableAllInteractions()
                .title(MiniMessage.miniMessage().deserialize("<red>Some UI"))
                .create();

        gui.open(player);

    }

}
