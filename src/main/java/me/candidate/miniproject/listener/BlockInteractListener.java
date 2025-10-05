package me.candidate.miniproject.listener;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import me.candidate.miniproject.MiniProjectPlugin;
import me.candidate.miniproject.storage.EggHuntStorageHandler;

public class BlockInteractListener implements Listener {
    private final MiniProjectPlugin plugin;
    private final EggHuntStorageHandler storageHandler;

    public BlockInteractListener(MiniProjectPlugin plugin, EggHuntStorageHandler storageHandler) {
        this.plugin = plugin;
        this.storageHandler = storageHandler;
        plugin.getLogger().info("BlockInteractListener loaded");
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {

        Block clicked = event.getClickedBlock();
        plugin.getLogger().info("block interact event");
        if (clicked == null) return; // There was no block clicked, move on
        plugin.getLogger().info("right click event");
        Location location = clicked.getLocation(); // Clicked Block Location


        // if location matches an egg hunt location & type is Material.PLAYER_HEAD...
        event.setCancelled(true);

        // Further logic to check if they have claimed the Egg, if they have not, give reward, otherwise return an error
        // message.
        var parsedMessage = MiniMessage.miniMessage().deserialize("<#ff0000>A block has been right clicked");
        event.getPlayer().sendMessage(parsedMessage);
    }

}
