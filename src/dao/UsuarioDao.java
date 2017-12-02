package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.UsuarioLogica;

/**
 *
 * @author Marcio Martinez
 */
public class UsuarioDao {
    private final Connection cn;

    public UsuarioDao() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    public void insertarUsuario(UsuarioLogica ul) throws SQLException{
        String sql = "{call sp_insertarUsuario(?,?,?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
          
            ps.setString(1, ul.getNombreUsuario());
            ps.setString(2, ul.getContrasena());
            ps.setInt(3, ul.getIdTipoUsuario());
            ps.setString(4, ul.getCodEmpleado());
            ps.execute();
        }
    }   
    
    public void actualizarUsuario(UsuarioLogica ul) throws SQLException{
        String sql = "{call sp_actualizarUsuario(?,?,?,?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1, ul.getIdUsuario());
            ps.setString(2, ul.getNombreUsuario());
            ps.setString(3, ul.getContrasena());
            ps.setInt(4, ul.getIdTipoUsuario());
            ps.setString(5, ul.getCodEmpleado());
            ps.execute();
        }
    } 
    
    public void eliminarUsuario(UsuarioLogica c1) throws SQLException{
        String sql = "{call sp_eliminarUsuario(?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1, c1.getIdUsuario());
            ps.execute();
        }
    }   
    
    public int autoIncrementarUsuario() throws SQLException{
        int idUsuario = 0;    
        String sql = "{call sp_autoIncrementarUsuario}";
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.first();
        
        idUsuario = rs.getInt("idusuario");
        
        if(idUsuario == 0){
            idUsuario = 1;
        }
            
       return idUsuario;
    }   
    
    public List<UsuarioLogica> getListaUsuario(int tipoBusqueda, String filtro) throws SQLException{
        String sql;
        if(tipoBusqueda == 0){
            sql = "{call sp_mostrarTodoUsuario}";
        }else if(tipoBusqueda == 1){
            sql = "{call sp_mostrarTodoUsuarioPorNombreUsuario(?)}";
        }else{
            sql = "{call sp_mostrarTodoUsuarioPorEmpleado(?)}";
        }
           
        List<UsuarioLogica> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){       
            
            if(tipoBusqueda != 0){
                ps.setString(1, filtro);
            }
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            while(rs.next()){
                UsuarioLogica ul = new UsuarioLogica();
                ul.setIdUsuario(rs.getInt("idusuario"));
                ul.setNombreUsuario(rs.getString("nombreusuario"));
                ul.setContrasena(rs.getString("contrasena"));
                ul.setTipoUsuario(rs.getString("tipousuario"));
                ul.setNombreEmpleado(rs.getString("nombrecompletoempleado"));
                miLista.add(ul);
            }
            
        }
        return miLista;
    }
    
    public int obtenerIdTipoUsuario(String tipoUsuario) throws SQLException{
        int idTipoUsuario = 0;    
        String sql = "{call sp_obtenerIdTipoUsuario(?)}";
        
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, tipoUsuario);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            idTipoUsuario = rs.getInt("idtipousuario");
        }  
       return idTipoUsuario;
    }  
    
    public String obtenerCodEmpleado(String nombreEmpleado) throws SQLException{
        String codEmpleado = "";    
        String sql = "{call sp_obtenerCodEmpleado(?)}";
        
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, nombreEmpleado);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            codEmpleado = rs.getString("codempleado");
        }  
       return codEmpleado;
    }   
    
     public ArrayList<String> mostrarTipoUsuarios() throws SQLException{
        String sql = "{call sp_mostrarTipoUsuario}";
        
        ArrayList<String> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql);      
            ResultSet rs = ps.executeQuery()){
            
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while(rs.next()){      
                miLista.add(rs.getString("tipousuario"));
            }
            
        }
        return miLista;
    }
    public ArrayList<String> mostrarEmpleados() throws SQLException{
        String sql = "{call sp_mostrarEmpleado}";
        
        ArrayList<String> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql);      
            ResultSet rs = ps.executeQuery()){
            
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while(rs.next()){      
                miLista.add(rs.getString("nombreempleado"));
            }
            
        }
        return miLista;
    }
}
