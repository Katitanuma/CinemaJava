package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.FacturaLogica;
import logica.UsuarioLogica;

/**
 *
 * @author Marcio Martinez
 */
public class FacturaDao {
    private final Connection cn;

    public FacturaDao() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    public void insertarUsuario(FacturaLogica fl) throws SQLException{
        String sql = "{call sp_insertarFactura(?,?,?,?,?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
          
            ps.setDate(1, fl.getFecha());
            ps.setInt(2, fl.getCantidad());
            ps.setDouble(3, fl.getPrecio());
            ps.setInt(4, fl.getIdPelicula());
            ps.setInt(5, fl.getIdTecnologia());
            ps.setInt(6, fl.getIdUsuario());
            ps.execute();
        }
    }   
    
    public int autoIncrementarFactura() throws SQLException{
        int idFactura = 0;    
        String sql = "{call sp_autoIncrementarFactura}";
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.first();
        
        idFactura = rs.getInt("idfactura");
        
        if(idFactura == 0){
            idFactura = 1;
        }
            
       return idFactura;
    }   
    
    public List<FacturaLogica> getListaFactura(int tipoBusqueda, String filtro) throws SQLException{
        String sql = "{call sp_mostrarTodoFactura(?,?)}";
          
        List<FacturaLogica> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){       
            
            ps.setInt(1, tipoBusqueda);
            ps.setString(2, filtro);
            
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            while(rs.next()){
                FacturaLogica fl = new FacturaLogica();
                fl.setIdFactura(rs.getInt("idfactura"));
                fl.setFecha(rs.getDate("fecha"));
                fl.setPelicula(rs.getString("nombrepelicula"));
                fl.setTecnologia(rs.getString("tipotecnologia"));
                fl.setCantidad(rs.getInt("cantidaboleto"));
                fl.setPrecio(rs.getDouble("precioboleto"));
                fl.setTotal(rs.getDouble("total"));
                miLista.add(fl);
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
