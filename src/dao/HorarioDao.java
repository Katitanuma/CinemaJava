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
import logica.HorarioLogica;

/**
 *
 * @author Steven Lezama
 */
public class HorarioDao {
    public Connection cn;

    public HorarioDao() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    public void insertarHorario(HorarioLogica ul) throws SQLException{
        String sql = "{call sp_insertarPeliculaHorario(?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
          
            ps.setInt(1,ul.getIdPelicula());
            ps.setInt(2,ul.getIdHorario());
            ps.execute();
        }
    }
    
    
    public void actualizarHorario(HorarioLogica ul) throws SQLException{
        String sql = "{call sp_actualizarPeliculaHorario(?,?,?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
        
            ps.setInt(1,ul.getIdPelicula());
            ps.setInt(2,ul.getIdHorario());
            ps.setInt(3,ul.getIdPelicula2());
            ps.setInt(4,ul.getIdHorario2());
            ps.execute();
        }
    }
    
    public void eliminarHorario(HorarioLogica ul) throws SQLException{
        String sql = "{call sp_eliminarPeliculaHorario(?,?)}";
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){
          
            
            ps.setInt(1,ul.getIdHorario());
            ps.setInt(2,ul.getIdPelicula());
            
            ps.execute();
        }
    }
    
    
    public List<HorarioLogica> getListaHorario(int tipoBusqueda, String filtro) throws SQLException{
        String sql;
        if(tipoBusqueda == 0){
            sql = "{call sp_mostrarTodoPeliculaHorario}";
        }else if(tipoBusqueda == 1){
            sql = "{call sp_mostrarTodoPeliculaHorarioPorHorario(?)}";
        }else{
            sql = "{call sp_mostrarTodoPeliculaHorarioPorPelicula(?)}";
        }
           
        List<HorarioLogica> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){       
            
            if(tipoBusqueda != 0){
                ps.setString(1, filtro);
            }
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            while(rs.next()){
                HorarioLogica ul = new HorarioLogica();
                ul.setNombrePelicula(rs.getString("nombrePelicula"));
                ul.setHorario(rs.getString("horariopelicula"));
                ul.setSala(rs.getString("descripcionsala"));
                miLista.add(ul);
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
    
    
    public ArrayList<String> mostrarHorarioPelicula() throws SQLException{
        String sql = "{call sp_mostrarHorarioPelicula}";
        
        ArrayList<String> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql);      
            ResultSet rs = ps.executeQuery()){
            
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while(rs.next()){      
                miLista.add(rs.getString("horariopelicula"));
            }
            
        }
        return miLista;
    }
    
    
    
    public int obtenerIdHorario(String horarioPelicula) throws SQLException{
        int idHorario = 0;    
        String sql = "{call sp_obtenerIdHorario(?)}";
        
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, horarioPelicula);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            idHorario = rs.getInt("idhorario");
        }  
       return idHorario;
    }
    
    
    
    
}
