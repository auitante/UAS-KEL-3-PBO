package com.pbo3;

import javax.swing.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class Conn {
    private static Connection connect;

    public static Connection connect() throws SQLException {
        try {
            String DB = "jdbc:mysql://localhost/database_laundry";
            String user = "root";
            String pass = "";

            DriverManager.registerDriver(new Driver());
            connect = DriverManager.getConnection(DB, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No Database Connection", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return connect;
    }

    private record Driver() implements java.sql.Driver {
        @Override
        public Connection connect(String url, Properties info) throws SQLException {
            return null;
        }

        @Override
        public boolean acceptsURL(String url) throws SQLException {
            return false;
        }

        @Override
        public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
            return new DriverPropertyInfo[0];
        }

        @Override
        public int getMajorVersion() {
            return 0;
        }

        @Override
        public int getMinorVersion() {
            return 0;
        }

        @Override
        public boolean jdbcCompliant() {
            return false;
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }
    }
}