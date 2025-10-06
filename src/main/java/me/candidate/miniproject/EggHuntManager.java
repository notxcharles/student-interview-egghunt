package me.candidate.miniproject;

import me.candidate.miniproject.config.EggHuntConfig;
import me.candidate.miniproject.config.Hunt;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;

public class EggHuntManager {
    private final MiniProjectPlugin plugin;

    public EggHuntManager(MiniProjectPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Places all configured egg heads in the world
     */
    public void placeAllEggs() {
        EggHuntConfig config = plugin.configScaffold().getConfig(EggHuntConfig.class);
        if (config == null) {
            plugin.getLogger().warning("Could not load EggHuntConfig!");
            return;
        }
        plugin.getLogger().info("Config has " + config.getHunts().size() + " hunts configured");
        int placed = 0;
        for (Hunt hunt : config.getHunts()) {
            plugin.getLogger().info("loaded one egg heads!");
            Location location = config.getLocationFromSerialized(
                    hunt.GetWorld(),
                    hunt.GetX(),
                    hunt.GetY(),
                    hunt.GetZ()
            );

            if (location == null) {
                plugin.getLogger().warning("World not found for egg: " + hunt.GetEggID());
                continue;
            }

            if (placeEggHead(location)) {
                placed++;
                plugin.getLogger().info("Placed egg '" + hunt.GetEggID() + "' at " +
                        location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ());
            }
        }

        plugin.getLogger().info("Placed " + placed + " egg heads!");
    }

    /**
     * Places a player head at the specified location
     */
    private boolean placeEggHead(Location location) {
        Block block = location.getBlock();

        // Set the block to a player head
        block.setType(Material.PLAYER_HEAD);

        // Optional: Set the head to face upward
        if (block.getState() instanceof Skull skull) {
//            skull.setRotation(BlockFace.UP);
            skull.update();
            return true;
        }

        return false;
    }

    /**
     * Removes all configured egg heads from the world
     */
    public void removeAllEggs() {
        EggHuntConfig config = plugin.configScaffold().getConfig(EggHuntConfig.class);
        if (config == null) return;

        for (Hunt hunt : config.getHunts()) {
            Location location = config.getLocationFromSerialized(
                    hunt.GetWorld(),
                    hunt.GetX(),
                    hunt.GetY(),
                    hunt.GetZ()
            );

            if (location != null) {
                location.getBlock().setType(Material.AIR);
            }
        }

        plugin.getLogger().info("Removed all egg heads!");
    }
}