package me.candidate.miniproject.scaffold.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlScaffold implements Closeable {

    private final HikariDataSource source;

    public SqlScaffold() {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/mysql");
        config.setUsername("root");
        config.setPassword("password");

        this.source = new HikariDataSource(config);
    }

    public Connection connection() throws SQLException {
        return this.source.getConnection();
    }

    @Override
    public void close() {
        this.source.close();
    }

}