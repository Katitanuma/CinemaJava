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
<<<<<<< HEAD
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/cineevolution?zeroDateTimeBehavior=convertToNull","root","salc");
=======
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/cineevolution?zeroDateTimeBehavior=convertToNull","root","root");
>>>>>>> 98e6bfe2f77664db43dada0201ea2abd3f9bac52
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
