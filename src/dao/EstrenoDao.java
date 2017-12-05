package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.EstrenoLogica;
import logica.PeliculaLogica;

/**
 *
 * @author Marcio Martinez
 */
public class EstrenoDao {
    
    private final Connection cn;

    public EstrenoDao() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    public List<EstrenoLogica> getListaEstreno() throws SQLException{
        String sql = "{call sp_mostrarCarteleraActual}";
      
        List<EstrenoLogica> miLista;
        
        try(PreparedStatement ps = cn.prepareStatement(sql);       
            ResultSet rs = ps.executeQuery()){
            
            miLista = new ArrayList<>();
            while(rs.next()){
                EstrenoLogica el = new EstrenoLogica();
                el.setImagen(rs.getBytes("ImagenPelicula"));
                el.setNombrePelicula(rs.getString("NombrePelicula"));
                el.setSinopsis(rs.getString("Sinopsis"));
                el.setFechaFinal(rs.getString("FechaFinal"));
                el.setGeneroPelicula(rs.getString("GeneroPelicula"));
                el.setClasificacion(rs.getString("Clasificacion"));
                el.setUrl(rs.getString("Url"));
                miLista.add(el);
            }
            
        }
        return miLista;
    }
}
