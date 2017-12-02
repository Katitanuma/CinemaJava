/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Katy Nuñez
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import logica.EmpleadoLogica;

public class EmpleadoDao {
    private final Connection cn;

    public EmpleadoDao() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    public void insertarEmpleado(EmpleadoLogica el) throws SQLException{
        String sql="{call sp_insertarEmpleado(?,?,?,?,?,?)}";
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
            ps.setString(1, el.getCodEmpleado());
            ps.setString(2, el.getNombreEmpleado());
            ps.setString(3, el.getApellidosEmpleado());
            ps.setString(4, el.getDireccion());
            ps.setString(5, el.getTelefono());
            ps.setString(6, el.getCorreo());
            ps.execute();
        }
    }
    
    public void actualizarEmpleado(EmpleadoLogica el) throws SQLException{
        String sql="{call sp_actualizarEmpleado(?,?,?,?,?,?)}";
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
            ps.setString(1, el.getNombreEmpleado());
            ps.setString(2, el.getApellidosEmpleado());
            ps.setString(3, el.getDireccion());
            ps.setString(4, el.getTelefono());
            ps.setString(5, el.getCorreo());
            ps.setString(6, el.getCodEmpleado());
            ps.execute();
        }
    }  
    
    public void eliminarEmpleado(EmpleadoLogica el)throws SQLException{
        String sql="{call sp_eliminarEmpleado(?)}";
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
            ps.setString(1, el.getCodEmpleado());
            ps.execute();
        }
    }    
    
    public List<EmpleadoLogica> getListaEmpleado(int tipoBusqueda,String filtro) throws SQLException{
        String sql;
        if(tipoBusqueda==0){
            sql="{call sp_mostrarTodoEmpleado}";
        }else if(tipoBusqueda==1){
            sql="{call sp_mostrarTodoEmpleadoCod(?)}";
        }else{
            sql="{call sp_mostrarTodoEmpleadoNombre(?)}";
        }
        
        List<EmpleadoLogica>miLista;
        
        try(PreparedStatement ps=cn.prepareStatement(sql)){
            if(tipoBusqueda!=0){
                ps.setString(1, filtro);
            }
            ResultSet rs=ps.executeQuery();
            
            miLista=new ArrayList<>();            
            while(rs.next()){
                EmpleadoLogica el=new EmpleadoLogica();
                el.setCodEmpleado(rs.getString("codempleado"));
                el.setNombreEmpleado(rs.getString("nombreempleado"));
                el.setApellidosEmpleado(rs.getString("apellidosempleado"));
                el.setDireccion(rs.getString("direccion"));
                el.setTelefono(rs.getString("telefono"));
                el.setCorreo(rs.getString("correo"));
                miLista.add(el);
            }
        }
        return miLista;
    }
    
    
    
}
