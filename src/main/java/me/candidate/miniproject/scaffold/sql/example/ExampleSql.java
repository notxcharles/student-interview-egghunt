package me.candidate.miniproject.scaffold.sql.example;

import me.candidate.miniproject.MiniProjectPlugin;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExampleSql {

    public ExampleSql(MiniProjectPlugin plugin) {

        // We run the task asynchronously to not block the main thread
        // so the server doesn't hang whilst waiting for a response!
        // Whilst it says 'asynchronously' - everything in this task is
        // sequential, meaning that we can create-insert-get without issue here.
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            // This is not a representative example of how to store player data. It is used to show
            // How to create SQL statements properly
            final String CREATE_TABLE = """
                    CREATE TABLE IF NOT EXISTS minecraft_players(
                        some_id INT NOT NULL AUTO_INCREMENT,
                        somedata TEXT NOT NULL,
                        PRIMARY KEY(some_id)
                    );
                    """;

            final String INSERT_DATA = "INSERT INTO minecraft_players(somedata) VALUES (?);";

            final String GET_DATA = "SELECT * FROM minecraft_players;";

            // We place the connection and a PreparedStatement in the try-with-resources block to prevent
            // memory leaks (and ensures the connection closes once complete)
            try (Connection connection = plugin.sqlScaffold().connection();
                 PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE);
                 PreparedStatement insertStatement = connection.prepareStatement(INSERT_DATA);
                 PreparedStatement getStatement = connection.prepareStatement(GET_DATA)) {

                // Here, we create the Database
                preparedStatement.execute();

                //
                insertStatement.setString(1, "This is some data we inserted!");
                insertStatement.executeUpdate();

                // Lets get the data back from the Database
                ResultSet resultSet = getStatement.executeQuery();

                while(resultSet.next()) {
                    int id = resultSet.getInt("some_id");
                    String data = resultSet.getString("somedata");

                    plugin.getSLF4JLogger().info("ID: " + id + " data: " + data);
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        });

    }

}
