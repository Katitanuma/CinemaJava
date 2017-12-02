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
import logica.TecnologiaLogica;

/**
 *
 * @author Steven Lezama
 */
public class TecnologiaDao {
    
    private final Connection cn;

    public TecnologiaDao() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    public void insertarPeliculaTecnologia(TecnologiaLogica tl) throws SQLException{
        String sql = "{call sp_insertarPeliculaTecnologia(?,?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setInt(1,tl.getIdTecnologia());
            ps.setInt(2,tl.getIdPelicula());
            ps.setDouble(3,tl.getPrecio());
            ps.execute();
            
        }
    }
    
    public void actualizarPeliculaTecnologia(TecnologiaLogica tl) throws  SQLException{
        String sql = "{call sp_actualizarPeliculaTecnologia(?,?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setInt(1,tl.getIdTecnologia());
            ps.setInt(2,tl.getIdPelicula());
            ps.setDouble(3,tl.getPrecio());
            ps.execute();
        }
        
    }
    
    public void eliminarPeliculaTecnologia(TecnologiaLogica tl) throws  SQLException{
        String sql = "{call sp_eliminarPeliculaTecnologia(?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setInt(1,tl.getIdTecnologia());
            ps.setInt(2,tl.getIdPelicula());
            ps.execute();
        }
        
    }
     
    
    public List<TecnologiaLogica> getListaPeliculaTecnologia(int tipoBusqueda, String filtro) throws SQLException{
        String sql;
        if(tipoBusqueda == 0){
            sql = "{call sp_mostrarTodoPeliculaTecnologia}";
        }else if(tipoBusqueda == 1){
            sql = "{call sp_mostrarTodoPeliculaTecnologiaPorTecnologia(?)}";
        }else{
            sql = "{call sp_mostrarTodoPeliculaTecnologiaPorPelicula(?)}";
        }
           
        List<TecnologiaLogica> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){       
            
            if(tipoBusqueda != 0){
                ps.setString(1, filtro);
            }
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                TecnologiaLogica tl = new TecnologiaLogica();
                
                tl.setNombrePelicula(rs.getString("nombrepelicula"));
                tl.setTecnologia(rs.getString("tipotecnologia"));
                tl.setPrecio(rs.getDouble("precio"));
                miLista.add(tl);
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
