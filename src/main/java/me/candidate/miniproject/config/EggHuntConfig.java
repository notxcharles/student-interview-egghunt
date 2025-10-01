package me.candidate.miniproject.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class EggHuntConfig {

    /** Your config Declarations go here
     * @see me.candidate.miniproject.scaffold.config.example.ExampleConfig
     * TIP: It would be worth making a 'Hunt' ConfigSerializable class which is put into a map in this class
     * It will allow you to remove a lot of duplicated code!
     **/

    /**
     * This method may help you with getting the defined config location of the
     * Egg, and allow you to serialize primitive types
     * @param world The minecraft world name (with runServer, this is just "world")
     * @param x the xPos of the block
     * @param y the yPos of the block
     * @param z the zPos of the block
     * @return the Bukkit Location
     * TIP: You may wish to make a ConfigSerializable for locations specifically. This is up to you!
     */
    public @Nullable Location getLocationFromSerialized(String world, int x, int y, int z) {

        World bukkitWorld = Bukkit.getWorld(world);

        if(bukkitWorld == null) return null;

        return new Location(
                bukkitWorld,
                x,
                y,
                z
        );
    }

}
