/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.FacturaLogica;
import logica.LogAuditoriaLogica;


/**
 *
 * @author Marcio Martinez
 */
public class LogAuditoriaDao {
     private final Connection cn;

    public LogAuditoriaDao() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
     public List<LogAuditoriaLogica> getListaLogAuditoria(String filtro) throws SQLException{
        String sql = "{call sp_mostrarTodoLogAuditoria(?)}";
          
        List<LogAuditoriaLogica> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){       
            ps.setString(1, filtro);
            
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            while(rs.next()){
                LogAuditoriaLogica lal = new LogAuditoriaLogica();
                lal.setIdLog(rs.getInt(1));
                lal.setAccion(rs.getString(2));
                lal.setFecha(rs.getString(3));
                lal.setUsuario(rs.getString(4));
                miLista.add(lal);
            }
            
        }
        return miLista;
    }
    
    
}
