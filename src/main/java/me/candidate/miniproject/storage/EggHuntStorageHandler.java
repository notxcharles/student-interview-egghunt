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
            """;

    private static final String INSERT_REWARD = """
            """;

    private static final String GET_DATA = """
            """;

    public EggHuntStorageHandler(MiniProjectPlugin plugin) {
        this.plugin = plugin;

        try (Connection connection = plugin.sqlScaffold().connection();
             PreparedStatement ps = connection.prepareStatement(CREATE_TABLE)) {

            ps.execute(); // Create the table schema
        } catch (SQLException exception) {
            // Error handling?
        }
    }

}
