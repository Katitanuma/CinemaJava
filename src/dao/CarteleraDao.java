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
import logica.CarteleraLogica;

/**
 *
 * @author Katy Nuñez
 */
public class CarteleraDao {
    private final Connection cn;
    
    public CarteleraDao()throws SQLException {
        this.cn=Conexion.conectar();
    }
    
    public void insertarCartelera(CarteleraLogica cl)throws SQLException{
        String sql="{call sp_insertarCartelera(?,?)}";
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
            ps.setDate(1, cl.getFechaEstreno());
            ps.setDate(2, cl.getFechaFinal());
            ps.execute();
        }
    }
    
    public void actualizarCartelera(CarteleraLogica cl)throws SQLException{
        String sql="{call sp_actualizarCartelera(?,?,?)}";
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
            ps.setDate(1, cl.getFechaEstreno());
            ps.setDate(2, cl.getFechaFinal());
            ps.setInt(3, cl.getIdCartelera());
            ps.execute();
        }
    }
    
    public void eliminarCartelera(CarteleraLogica cl)throws SQLException{
        String sql="{call sp_eliminarCartelera(?)}";
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
            ps.setInt(1, cl.getIdCartelera());
            ps.execute();
        }
    }
    
     public int autoIncrementarCodCartelera() throws SQLException{
        int idCartelera = 0;    
        String sql = "{call sp_autoIncrementarCodCartelera}";
        
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.first();
        
        idCartelera= rs.getInt("idcartelera");
        
        if(idCartelera == 0){
            idCartelera = 1;
        }
            
        return idCartelera;
    }   
    
    public List<CarteleraLogica> getListaCartelera(int tipoBusqueda,String filtro) throws SQLException{
        String sql;
        if(tipoBusqueda==0){
            sql="{call sp_mostrarTodoCartelera}";
        }else if(tipoBusqueda==1){
            sql="{call sp_mostrarTodoCarteleraFecha(?)}";
        }else{
            sql="{call sp_mostrarTodoCarteleraCod(?)}";
        }
        
        List<CarteleraLogica>miLista;
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
            if(tipoBusqueda!=0){
                ps.setString(1, filtro);
            }
            
            ResultSet rs=ps.executeQuery();
            
            miLista=new ArrayList<>();            
            while(rs.next()){
                CarteleraLogica cl=new CarteleraLogica();
                cl.setIdCartelera(rs.getInt("idcartelera"));
                cl.setFechaEstreno(rs.getDate("fechaestreno"));
                cl.setFechaFinal(rs.getDate("fechafinal"));
                miLista.add(cl);
            }
        }
        return miLista;
    }
}
