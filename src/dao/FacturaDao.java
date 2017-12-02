package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.FacturaLogica;


/**
 *
 * @author Marcio Martinez
 */
public class FacturaDao {
    private final Connection cn;

    public FacturaDao() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    public void insertarFactura(FacturaLogica fl) throws SQLException{
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
                fl.setCantidad(rs.getInt("cantidadboleto"));
                fl.setPrecio(rs.getDouble("precioboleto"));
                fl.setTotal(rs.getDouble("total"));
                miLista.add(fl);
            }
            
        }
        return miLista;
    }
    
     public int obtenerIdPelicula(String nombrePelicula) throws SQLException{
        int idPelicula = 0;    
        String sql = "{call sp_obtenerIdPelicula(?)}";
        
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, nombrePelicula);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            idPelicula = rs.getInt("idpelicula");
        }  
       return idPelicula;
    }
    
    public int obtenerIdTecnologia(String tecnologia) throws SQLException{
        int idTecnologia = 0;    
        String sql = "{call sp_obtenerIdTecnologia(?)}";
        
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, tecnologia);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            idTecnologia = rs.getInt("idtecnologia");
        }  
       return idTecnologia;
    }
    
    public ArrayList<String> mostrarNombrePelicula() throws SQLException{
        String sql = "{call sp_mostrarNombrePelicula}";
        
        ArrayList<String> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql);      
            ResultSet rs = ps.executeQuery()){
            
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while(rs.next()){      
                miLista.add(rs.getString("nombrepelicula"));
            }
            
        }
        return miLista;
    }
    
    public ArrayList<String> mostrarTecnologia() throws SQLException{
        String sql = "{call sp_mostrarTecnologia}";
        
        ArrayList<String> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql);      
            ResultSet rs = ps.executeQuery()){
            
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while(rs.next()){      
                miLista.add(rs.getString("tipotecnologia"));
            }
            
        }
        return miLista;
    }
}
