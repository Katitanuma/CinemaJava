/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import logica.PeliculaLogica;
import org.omg.CORBA.portable.InputStream;

/**
 *
 * @author Alexis
 */
public class PeliculaDao {
    
    private final Connection cn;

    public PeliculaDao() throws SQLException {
        this.cn = Conexion.conectar();
    }
    public void insertarPelicula(PeliculaLogica el) throws SQLException, FileNotFoundException{
        String sql="{call sp_insertarPelicula(?,?,?,?,?,?,?,?,?)}";
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
            if(el.getImagen().equals("")){
                el.setImagen("src/imagenes/NULLA.png");
            }
            File image = new File(el.getImagen());
            FileInputStream miImagen = new FileInputStream (image);
            
            ps.setString(1, el.getNombre());
            ps.setString(2, el.getSinopsis());
            ps.setString(3, el.getfLanzamiento());
            ps.setString(4, el.getDuracion());
            ps.setBinaryStream(5, miImagen, (int)image.length());
            ps.setString(6, el.getUrl());
            ps.setInt(7, el.getIdGenero());
            ps.setInt(8, el.getIdClasificacion());
            ps.setInt(9, el.getIdUsuario());
            ps.execute();
        }
    }
    
     public void eliminarPelicula(PeliculaLogica el)throws SQLException{
        String sql="{call sp_eliminarPelicula(?)}";
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
            ps.setInt(1, el.getIdPelicula());
            ps.execute();
        }
    } 
     public void actualizarPelicula(PeliculaLogica el) throws SQLException, FileNotFoundException{
        String sql="{call sp_actualizarPelicula(?,?,?,?,?,?,?,?,?,?)}";
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
           File image = null;
           FileInputStream miImagen = null;
           
           if(el.getImagen().equals("abc")){
               el.setImagen("src/imagenes/NULLA.png");
               image = new File(el.getImagen());
               miImagen = new FileInputStream (image);
           }else if(!el.getImagen().equals("")){
               image = new File(el.getImagen());
               miImagen = new FileInputStream (image);
           }
             
            ps.setInt(1,el.getIdPelicula());
            ps.setString(2, el.getNombre());
            ps.setString(3, el.getSinopsis());
            ps.setString(4, el.getfLanzamiento());
            ps.setString(5, el.getDuracion());
            if(!el.getImagen().equals("")){
                ps.setBinaryStream(6,miImagen, (int)image.length());
            }else if(el.getImagen().equals("")){
                ps.setNull(6, java.sql.Types.BLOB);
            }
            ps.setString(7, el.getUrl());
            ps.setInt(8, el.getIdGenero());
            ps.setInt(9, el.getIdClasificacion());
            ps.setInt(10, el.getIdUsuario());
            ps.execute();
        }
    }
    
    public int autoIncrementarPelicula() throws SQLException{
        int idpelicula= 0;    
        String sql = "{call sp_autoIncrementarPelicula}";
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.first();
        
        idpelicula = rs.getInt("idpelicula");
        
        if(idpelicula == 0){
            idpelicula = 1;
        }
            
       return idpelicula;
    }
     public int obtenerIdClasificacion(String clasificacion) throws SQLException{
        int idclasificacion=0;  
        String sql = "{call sp_obtenerIdClasificacion(?)}";
        
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, clasificacion);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            idclasificacion = rs.getInt("idclasificacion");
        }  
       return idclasificacion;
    }   
    
    public ArrayList<String> mostrarClasificacion() throws SQLException{
        String sql = "{call sp_mostrarClasificacion}";
        
        ArrayList<String> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql);      
            ResultSet rs = ps.executeQuery()){
            
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while(rs.next()){      
                miLista.add(rs.getString("clasificacion"));
            }
            
        }
        return miLista;
    }
    
    public int obtenerIdGenero(String genero) throws SQLException{
        int idgenero=0;  
        String sql = "{call sp_obtenerIdGenero(?)}";
        
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, genero);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            idgenero = rs.getInt("idgenero");
        }  
       return idgenero;
    }   
    
    public ArrayList<String> mostrarGenero() throws SQLException{
        String sql = "{call sp_mostrarGenero}";
        
        ArrayList<String> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql);      
            ResultSet rs = ps.executeQuery()){
            
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while(rs.next()){      
                miLista.add(rs.getString("generopelicula"));
            }
            
        }
        return miLista;
    }
    
    public List<PeliculaLogica> getListaPelicula(int tipoBusqueda, String filtro) throws SQLException{
        String sql;
        if(tipoBusqueda == 0){
            sql = "{call sp_mostrarPelicula}";
        }else if(tipoBusqueda == 1){
            sql = "{call sp_mostrarPeliculaporCod(?)}";
        }else{
            sql = "{call sp_mostrarPeliculaporNombre(?)}";
        }
           
        List<PeliculaLogica> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql)){       
            
            if(tipoBusqueda != 0){
                ps.setString(1, filtro);
            }
            ResultSet rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            while(rs.next()){
                PeliculaLogica ul = new PeliculaLogica();
                ul.setIdPelicula(rs.getInt("idpelicula"));
                ul.setNombre(rs.getString("nombrepelicula"));
                ul.setSinopsis(rs.getString("sinopsis"));
                ul.setfLanzamiento(rs.getString("lanzamiento"));
                ul.setDuracion(rs.getString("duracion"));
                ul.setImg(rs.getBytes("imagenpelicula"));
                ul.setUrl(rs.getString("url"));
                ul.setGenero(rs.getString("generopelicula"));
                ul.setClasificacion(rs.getString("clasificacion"));
                miLista.add(ul);
            }
            
        }
        return miLista;
    }
}
