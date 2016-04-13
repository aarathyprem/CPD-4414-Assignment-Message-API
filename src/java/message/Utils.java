/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0587637
 */
public class Utils {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        String host = "localhost";
        String port = "3306";
        String db = "chat";
        String user = "root";
        String pass = "";
        String jdbc = "jdbc:mysql://" + host + ":" + port + "/" + db;
        return DriverManager.getConnection(jdbc, user, pass);
    }
}
