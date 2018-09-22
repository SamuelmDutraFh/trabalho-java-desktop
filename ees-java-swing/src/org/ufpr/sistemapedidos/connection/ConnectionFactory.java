package org.ufpr.sistemapedidos.connection;

//import posjava.semana07_jdbc.exemplos.livroautor.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/sistemapedidos?useSSL=false", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
