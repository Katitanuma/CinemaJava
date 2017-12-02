package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logica.LoginLogica;

/**
 *
 * @author Marcio Martinez
 */
public class LoginDao {
    private final Connection cn;

    public LoginDao() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
     public int iniciarSesion(LoginLogica ll) throws SQLException{
        String sql = "{call sp_iniciarSesion(?,?)}";
        int idUsuario = 0;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){     
            ps.setString(1, ll.getUsuario());
            ps.setString(2, ll.getContrasena());
            ResultSet rs = ps.executeQuery();
                 
            if(rs.next()){      
                idUsuario = rs.getInt("idusuario");
            }
            
        }
        return idUsuario;
    }
}
