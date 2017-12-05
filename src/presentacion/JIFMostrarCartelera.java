/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dao.EstrenoDao;
import dao.PeliculaDao;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class JIFMostrarCartelera extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFMostrarCartelera
     */
    public JIFMostrarCartelera() throws SQLException {
        initComponents();
        fondo();
        mostrarEstreno();
        jLblLink1.setVisible(false);
        jLblLink2.setVisible(false);
        jLblLink3.setVisible(false);
        jLblSinopsis1.setVisible(false);
        jLblSinopsis2.setVisible(false);
        jLblSinopsis3.setVisible(false);
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
    private void mostrarEstreno() throws SQLException{
         int i = 1;
         EstrenoDao ed = new EstrenoDao();
         List<EstrenoLogica> miLista = ed.getListaEstreno();  
     
         for(EstrenoLogica el: miLista){ 
             if(i == 1){
                 jLblNombrePelicula1.setText(el.getNombrePelicula());
                 jLblFinal1.setText(el.getFechaFinal());
                 jLblGenero1.setText(el.getGeneroPelicula());
                 jLblCla1.setText(el.getClasificacion());
                 jLblSinopsis1.setText(el.getSinopsis());
                 jLblLink1.setText(el.getUrl());
                 
                 byte[] img = el.getImagen();    
                 try {

                        Image miImagen = convertirImagen(img);               
                        ImageIcon icon = new ImageIcon(miImagen);
                        Image imgs = icon.getImage();
                        Image newimg = imgs.getScaledInstance(151, 163, java.awt.Image.SCALE_SMOOTH);
                        ImageIcon newIcon = new ImageIcon(newimg);
                        jLblImg1.setIcon(newIcon);
                        jLblImg1.setSize(151, 163);
                 } catch (Exception ex) {
                        jLblImg1.setIcon(null);
                 }
                 
             }else if(i == 2){
                 jLblNombrePelicula2.setText(el.getNombrePelicula());
                 jLblFinal2.setText(el.getFechaFinal());
                 jLblGenero2.setText(el.getGeneroPelicula());
                 jLblCla2.setText(el.getClasificacion());
                 jLblSinopsis2.setText(el.getSinopsis());
                 jLblLink2.setText(el.getUrl());
                 
                 byte[] img = el.getImagen();    
                 try {

                        Image miImagen = convertirImagen(img);               
                        ImageIcon icon = new ImageIcon(miImagen);
                        Image imgs = icon.getImage();
                        Image newimg = imgs.getScaledInstance(151, 163, java.awt.Image.SCALE_SMOOTH);
                        ImageIcon newIcon = new ImageIcon(newimg);
                        jLblImg2.setIcon(newIcon);
                        jLblImg2.setSize(151, 163);
                 } catch (Exception ex) {
                        jLblImg2.setIcon(null);
                 }
             }else if(i == 3){
                 jLblNombrePelicula3.setText(el.getNombrePelicula());
                 jLblFinal3.setText(el.getFechaFinal());
                 jLblGenero3.setText(el.getGeneroPelicula());
                 jLblCla3.setText(el.getClasificacion());
                 jLblSinopsis3.setText(el.getSinopsis());
                 jLblLink3.setText(el.getUrl());
                 
                 byte[] img = el.getImagen();    
                 try {

                        Image miImagen = convertirImagen(img);               
                        ImageIcon icon = new ImageIcon(miImagen);
                        Image imgs = icon.getImage();
                        Image newimg = imgs.getScaledInstance(151, 163, java.awt.Image.SCALE_SMOOTH);
                        ImageIcon newIcon = new ImageIcon(newimg);
                        jLblImg3.setIcon(newIcon);
                        jLblImg3.setSize(151, 163);
                 } catch (Exception ex) {
                        jLblImg3.setIcon(null);
                 }
             }
             i++;
         }   
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

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLblImg2 = new javax.swing.JLabel();
        jLblImg3 = new javax.swing.JLabel();
        jLblImg1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLblFinal1 = new javax.swing.JLabel();
        jLblNombrePelicula1 = new javax.swing.JLabel();
        jLblGenero1 = new javax.swing.JLabel();
        jLblCla1 = new javax.swing.JLabel();
        jLblNombrePelicula3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLblFinal2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLblGenero2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLblCla2 = new javax.swing.JLabel();
        jLblNombrePelicula2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLblFinal3 = new javax.swing.JLabel();
        jLblGenero3 = new javax.swing.JLabel();
        jLblCla3 = new javax.swing.JLabel();
        jLblSinopsis1 = new javax.swing.JLabel();
        jLblLink1 = new javax.swing.JLabel();
        jLblSinopsis2 = new javax.swing.JLabel();
        jLblLink2 = new javax.swing.JLabel();
        jLblSinopsis3 = new javax.swing.JLabel();
        jLblLink3 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("CinemaEvolution");

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 36)); // NOI18N
        jLabel1.setText("Cartelera");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/movies.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartel.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cartel.png"))); // NOI18N

        jPanel7.setBackground(new java.awt.Color(0, 123, 119));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(0, 63, 74));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(0, 123, 119));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jLblImg2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLblImg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblImg2MouseClicked(evt);
            }
        });

        jLblImg3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLblImg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblImg3MouseClicked(evt);
            }
        });

        jLblImg1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jLblImg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblImg1MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Fecha Final");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Género");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Clasificación");

        jLblFinal1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLblNombrePelicula1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblNombrePelicula1.setForeground(new java.awt.Color(0, 0, 153));

        jLblGenero1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLblCla1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLblNombrePelicula3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblNombrePelicula3.setForeground(new java.awt.Color(0, 0, 153));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Fecha Final");

        jLblFinal2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Género");

        jLblGenero2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Clasificación");

        jLblCla2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLblNombrePelicula2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblNombrePelicula2.setForeground(new java.awt.Color(0, 0, 153));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Fecha Final");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Género");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Clasificación");

        jLblFinal3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLblGenero3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLblCla3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLblSinopsis1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblSinopsis1.setText("Clasificación");

        jLblLink1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblLink1.setText("Clasificación");

        jLblSinopsis2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblSinopsis2.setText("Clasificación");

        jLblLink2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblLink2.setText("Clasificación");

        jLblSinopsis3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblSinopsis3.setText("Clasificación");

        jLblLink3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblLink3.setText("Clasificación");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(2, 2, 2))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLblImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel6)
                                    .addComponent(jLblSinopsis1)
                                    .addComponent(jLblLink1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLblFinal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblGenero1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblCla1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLblNombrePelicula1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLblImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLblSinopsis2)
                                .addComponent(jLblLink2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLblGenero2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(jLblFinal2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblCla2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblNombrePelicula2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLblImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblNombrePelicula3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(21, 21, 21))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLblGenero3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblFinal3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblCla3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLblSinopsis3)
                            .addComponent(jLblLink3))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblImg3, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblImg2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblImg1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblNombrePelicula1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblNombrePelicula3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblNombrePelicula2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLblFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel9))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLblFinal2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addComponent(jLblGenero2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(jLblCla2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel15)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel16))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLblFinal3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addComponent(jLblGenero3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(jLblCla3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel12)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel13)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(jLblGenero1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLblCla1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblSinopsis1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblLink1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblSinopsis2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblLink2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblSinopsis3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblLink3)))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLblImg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblImg1MouseClicked
        int w, h;
        JFraInformacionPelicula miFormulario = new JFraInformacionPelicula();  
        w = miFormulario.jLblImg.getWidth();
        h = miFormulario.jLblImg.getHeight();
        ImageIcon icon = (ImageIcon) jLblImg1.getIcon();
        Image imgs = icon.getImage();
        Image newimg = imgs.getScaledInstance(w -5, h, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        miFormulario.jLblImg.setIcon(newIcon);
        miFormulario.jLblImg.setSize(w, h);
        miFormulario.jLblNombrePelicula.setText(jLblNombrePelicula1.getText());
        miFormulario.jLblSinopsis.setText(jLblSinopsis1.getText());
        miFormulario.jLblLink.setText(jLblLink1.getText());
        miFormulario.show();
     
    }//GEN-LAST:event_jLblImg1MouseClicked

    private void jLblImg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblImg2MouseClicked
        int w, h;
        JFraInformacionPelicula miFormulario = new JFraInformacionPelicula();  
        w = miFormulario.jLblImg.getWidth();
        h = miFormulario.jLblImg.getHeight();
        ImageIcon icon = (ImageIcon) jLblImg2.getIcon();
        Image imgs = icon.getImage();
        Image newimg = imgs.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        miFormulario.jLblImg.setIcon(newIcon);
        miFormulario.jLblImg.setSize(w + 5, h);
        miFormulario.jLblNombrePelicula.setText(jLblNombrePelicula2.getText());
        miFormulario.jLblSinopsis.setText(jLblSinopsis2.getText());
        miFormulario.jLblLink.setText(jLblLink2.getText());
        miFormulario.show();
    }//GEN-LAST:event_jLblImg2MouseClicked

    private void jLblImg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblImg3MouseClicked
       int w, h;
        JFraInformacionPelicula miFormulario = new JFraInformacionPelicula();  
        w = miFormulario.jLblImg.getWidth();
        h = miFormulario.jLblImg.getHeight();
        ImageIcon icon = (ImageIcon) jLblImg3.getIcon();
        Image imgs = icon.getImage();
        Image newimg = imgs.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        miFormulario.jLblImg.setIcon(newIcon);
        miFormulario.jLblImg.setSize(w + 5, h);
        miFormulario.jLblNombrePelicula.setText(jLblNombrePelicula3.getText());
        miFormulario.jLblSinopsis.setText(jLblSinopsis3.getText());
        miFormulario.jLblLink.setText(jLblLink3.getText());
        miFormulario.show();
    }//GEN-LAST:event_jLblImg3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblCla1;
    private javax.swing.JLabel jLblCla2;
    private javax.swing.JLabel jLblCla3;
    private javax.swing.JLabel jLblFinal1;
    private javax.swing.JLabel jLblFinal2;
    private javax.swing.JLabel jLblFinal3;
    private javax.swing.JLabel jLblGenero1;
    private javax.swing.JLabel jLblGenero2;
    private javax.swing.JLabel jLblGenero3;
    private javax.swing.JLabel jLblImg1;
    private javax.swing.JLabel jLblImg2;
    private javax.swing.JLabel jLblImg3;
    private javax.swing.JLabel jLblLink1;
    private javax.swing.JLabel jLblLink2;
    private javax.swing.JLabel jLblLink3;
    private javax.swing.JLabel jLblNombrePelicula1;
    private javax.swing.JLabel jLblNombrePelicula2;
    private javax.swing.JLabel jLblNombrePelicula3;
    private javax.swing.JLabel jLblSinopsis1;
    private javax.swing.JLabel jLblSinopsis2;
    private javax.swing.JLabel jLblSinopsis3;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
