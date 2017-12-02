/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Alexis
 */
public class CarteleraPeliculaLogica {

    private int idCartelera,idPelicula;
    private String fechaExtreno,fechaFinal, nombrePelicula;

    public String getFechaExtreno() {
        return fechaExtreno;
    }

    public void setFechaExtreno(String fechaExtreno) {
        this.fechaExtreno = fechaExtreno;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
 
   
    public int getIdCartelera() {
        return idCartelera;
    }

    public void setIdCartelera(int idCartelera) {
        this.idCartelera = idCartelera;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }
    
}
