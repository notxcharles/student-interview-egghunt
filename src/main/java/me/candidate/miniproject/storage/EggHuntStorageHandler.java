package me.candidate.miniproject.storage;

import com.google.common.collect.Maps;
import me.candidate.miniproject.MiniProjectPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * Data handling is the trickiest part of working on a Minecraft Server due to it's thread-sensitive nature
 * Make sure that when tackling storage, you do look at the example implementation:
 *
 * https://github.com/SynxGames/student-interview-minecraft-example/tree/master
 *
 * An example solution to getting their data on Login is also found here:
 * https://github.com/SynxGames/student-interview-minecraft-example/blob/master/src/main/java/me/candidate/miniproject/event/subscribe/PlayerConnectionEvents.java
 */
public class EggHuntStorageHandler {

    private final MiniProjectPlugin plugin;

    private final Map<?,?> playerData = Maps.newConcurrentMap(); // This needs to be setup!

    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS egghunt_claims (
                player_uuid VARCHAR(32) NOT NULL,
                egg_id VARCHAR(255) NOT NULL,
                claimed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                PRIMARY KEY (player_uuid, egg_id)
            )
            """;

    private static final String INSERT_REWARD = """
            INSERT INTO egghunt_claims (player_uuid, egg_id)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE claimed_at = CURRENT_TIMESTAMP
            """;

    private static final String GET_DATA = """
            SELECT egg_id FROM egghunt_claims
            WHERE player_uuid = ?
            """;

    public EggHuntStorageHandler(MiniProjectPlugin plugin) {
        this.plugin = plugin;

        try (Connection connection = plugin.sqlScaffold().connection();
             PreparedStatement ps = connection.prepareStatement(CREATE_TABLE)) {

            ps.execute(); // Create the table schema
            plugin.getLogger().info("egghunt_claims table created");
        } catch (SQLException exception) {
            // Error handling?

            plugin.getLogger().severe("Failed to create egghunt_claims table: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

}
