/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dao.EstrenoDao;
import dao.PeliculaDao;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import logica.EstrenoLogica;
import logica.PeliculaLogica;

/**
 *
 * @author Katy Nuñez
 */
public class JIFInformacionPelicula extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFMostrarCartelera
     */
    public JIFInformacionPelicula() throws SQLException {
        initComponents();
        fondo();
    }
    
    public void fondo(){
        ((JPanel) getContentPane()).setOpaque(false);
        ImageIcon imagen=new ImageIcon(this.getClass().getResource("/imagenes/Imagen12.png"));
        JLabel fondo=new JLabel();
        ImageIcon escala=new ImageIcon(imagen.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
        fondo.setIcon(escala);
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0,0,getWidth(),getHeight());
        this.add(fondo, BorderLayout.CENTER);
        this.setSize(getWidth() + 10,fondo.getHeight() + 35);
    }
    
     private Image convertirImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; // File or InputStream
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLblImg = new javax.swing.JLabel();
        jLblNombrePelicula = new javax.swing.JLabel();
        jSinopsis = new javax.swing.JLabel();
        jLbLink = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);
        setTitle("CinemaEvolution");

        jLblImg.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLblNombrePelicula.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLblNombrePelicula.setForeground(new java.awt.Color(0, 0, 153));
        jLblNombrePelicula.setText("dsad");

        jSinopsis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSinopsis.setText("Fecha Final");
        jSinopsis.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLbLink.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLbLink.setForeground(new java.awt.Color(0, 0, 153));
        jLbLink.setText("dsad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbLink, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblNombrePelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSinopsis, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblNombrePelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jSinopsis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLblImg, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbLink)
                .addGap(174, 174, 174))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLbLink;
    public javax.swing.JLabel jLblImg;
    private javax.swing.JLabel jLblNombrePelicula;
    private javax.swing.JLabel jSinopsis;
    // End of variables declaration//GEN-END:variables
}
