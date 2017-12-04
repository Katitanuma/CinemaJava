/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dao.PeliculaDao;
import java.awt.Image;
import java.sql.Blob;

/**
 *
 * @author Alexis
 */
public class PeliculaLogica {

  private int idPelicula,IdGenero,IdClasificacion,IdUsuario;
  private String nombre,fLanzamiento,sinopsis,genero,clasificacion,duracion,url;
  private Blob imagen;

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public int getIdGenero() {
        return IdGenero;
    }

    public void setIdGenero(int IdGenero) {
        this.IdGenero = IdGenero;
    }

    public int getIdClasificacion() {
        return IdClasificacion;
    }

    public void setIdClasificacion(int IdClasificacion) {
        this.IdClasificacion = IdClasificacion;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }
 
  
    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getfLanzamiento() {
        return fLanzamiento;
    }

    public void setfLanzamiento(String fLanzamiento) {
        this.fLanzamiento = fLanzamiento;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
  
    
}
