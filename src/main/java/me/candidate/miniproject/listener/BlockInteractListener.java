package me.candidate.miniproject.listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockInteractListener implements Listener {

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {

        Block clicked = event.getClickedBlock();
        if (clicked == null) return; // There was no block clicked, move on

        Location location = clicked.getLocation(); // Clicked Block Location

        // if location matches an egg hunt location & type is Material.PLAYER_HEAD...
        event.setCancelled(true);

        // Further logic to check if they have claimed the Egg, if they have not, give reward, otherwise return an error
        // message.
    }

}
