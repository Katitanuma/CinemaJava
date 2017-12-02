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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.CarteleraPeliculaLogica;

/**
 *
 * @author Alexis
 */
public class CarteleraPeliculaDao {
   private final Connection cn;

    public CarteleraPeliculaDao() throws SQLException {
        this.cn = Conexion.conectar();
        
    }
    public void insertarCarteleraPelicula(CarteleraPeliculaLogica ul) throws SQLException{
        String sql = "{call sp_insertarCarteleraPelicula(?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
          
            ps.setInt(1, ul.getIdCartelera());
            ps.setInt(2, ul.getIdPelicula());
            ps.execute();
        }
    }   
      public void actualizarCarteleraPelicula(CarteleraPeliculaLogica ul) throws SQLException{
        String sql = "{call sp_actualizarCarteleraPelicula(?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1, ul.getIdCartelera());
            ps.setInt(2, ul.getIdPelicula());
            
            ps.execute();
        }
    } 
    
    public void eliminarCarteleraPelicula(CarteleraPeliculaLogica c1) throws SQLException{
        String sql = "{call sp_eliminarCarteleraPelicula(?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1, c1.getIdCartelera());
            ps.setInt(2,c1.getIdPelicula());
            ps.execute();
        }
    }   
    public int obtenerIdCartelera(String fechaEstreno) throws SQLException{
        int idCartelera = 0;    
        String sql = "{call sp_obtenerIdCartelera(?)}";
        
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, fechaEstreno);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            idCartelera = rs.getInt("idcartelera");
        }  
       return idCartelera;
    }
    
    public ArrayList<String> mostrarFechaEstreno() throws SQLException{
        String sql = "{call sp_mostrarFechaCartelera}";
        
        ArrayList<String> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql);      
            ResultSet rs = ps.executeQuery()){
            
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while(rs.next()){      
                miLista.add(rs.getString("fechaestreno"));
            }
            
        }
        return miLista;
    }
     public int obtenerIdPelicula(String pelicula) throws SQLException{
        int idCartelera = 0;    
        String sql = "{call sp_obtenerIdPelicula(?)}";
        
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, pelicula);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            idCartelera = rs.getInt("idpelicula");
        }  
       return idCartelera;
    }
    
    public ArrayList<String> mostrarPelicula() throws SQLException{
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
    
    public List<CarteleraPeliculaLogica> getListaCarteleraPelicula(int tipoBusqueda, String filtro) throws SQLException{
        String sql;
        if(tipoBusqueda == 0){
            sql = "{call sp_mostrarTodoCarteleraPelicula}";
        }else if(tipoBusqueda == 1){
            sql = "{call sp_mostrarTodoCarteleraPeliculaporFecha(?)}";
        }else{
            sql = "{call sp_mostrarTodoCarteleraPeliculaporPelicula (?)}";
        }
           
        List<CarteleraPeliculaLogica> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){       
            
            if(tipoBusqueda != 0){
                ps.setString(1, filtro);
            }
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            while(rs.next()){
                CarteleraPeliculaLogica ul = new CarteleraPeliculaLogica();
                ul.setIdCartelera(rs.getInt("idcartelera"));
                ul.setFechaExtreno(rs.getString("fechaestreno"));
                ul.setFechaFinal(rs.getString("fechafinal"));
                ul.setNombrePelicula(rs.getString("nombrepelicula"));
                
                miLista.add(ul);
            }
            
        }
        return miLista;
    }
}
