package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Marcio Martinez
 */
public class Conexion {
    public static Connection conectar() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection("jdbc:mysql://192.168.1.11:3306/cineevolution?zeroDateTimeBehavior=convertToNull","root","root");
        

        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
