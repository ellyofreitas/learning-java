package br.com.ellyofreitas.learning.infra.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class MySQLConnection {

    private static final String DB_URL = System.getenv("DATABASE_URL");
    private static final String DB_USER = System.getenv("DATABASE_USER");
    private static final String DB_PASSWORD = System.getenv("DATABASE_PASSWORD");
    private static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final int MAX_TOTAL_CONNECTIONS = 5;
    private static final int MAX_IDLE_CONNECTIONS = 3;

    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName(DB_DRIVER_CLASS);
            ds.setUrl(DB_URL);
            ds.setUsername(DB_USER);
            ds.setPassword(DB_PASSWORD);
            ds.setMaxTotal(MAX_TOTAL_CONNECTIONS);
            ds.setMaxIdle(MAX_IDLE_CONNECTIONS);
            dataSource = ds;
        }
        return dataSource;
    }

    private MySQLConnection() {}

    public static Connection getConnection() throws SQLException {
        BasicDataSource dataSource = getDataSource();
        return dataSource.getConnection();
    }

    public static void close() {
        try {
            if (dataSource != null) {
                dataSource.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            close();
        }));
    }
}
