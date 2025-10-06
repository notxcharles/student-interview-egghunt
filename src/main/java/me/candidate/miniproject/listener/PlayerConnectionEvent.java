package me.candidate.miniproject.listener;

import me.candidate.miniproject.MiniProjectPlugin;
import me.candidate.miniproject.storage.EggHuntStorageHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionEvent implements Listener {
    private final MiniProjectPlugin plugin;
    private final EggHuntStorageHandler storageHandler;

    public PlayerConnectionEvent(MiniProjectPlugin plugin, EggHuntStorageHandler storageHandler) {
        this.plugin = plugin;
        this.storageHandler = storageHandler;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Load player's claimed eggs when they join
        storageHandler.loadPlayerData(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Unload player data to save memory
        storageHandler.unloadPlayerData(event.getPlayer().getUniqueId());
    }

}
