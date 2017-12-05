/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
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

/**
 *
 * @author Marcio Martinez
 */
public class JFraInformacionPelicula extends javax.swing.JFrame {

    /**
     * Creates new form JFraInformacionPelicula
     */
    public JFraInformacionPelicula() {
        initComponents();
        fondo();
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/estrella1.png")).getImage());
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
        jLblSinopsis = new javax.swing.JLabel();
        jLblLink = new javax.swing.JLabel();
        jLblNombrePelicula = new javax.swing.JLabel();

        setTitle("CinemaEvolution");

        jLblImg.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLblSinopsis.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblSinopsis.setText("Fecha Final");
        jLblSinopsis.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLblLink.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLblLink.setForeground(new java.awt.Color(0, 0, 153));
        jLblLink.setText("dsad");
        jLblLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblLinkMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLblLinkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLblLinkMouseExited(evt);
            }
        });

        jLblNombrePelicula.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLblNombrePelicula.setForeground(new java.awt.Color(0, 0, 153));
        jLblNombrePelicula.setText("dsad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLblLink, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLblSinopsis, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                            .addComponent(jLblNombrePelicula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblNombrePelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLblSinopsis, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLblImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLblLink)
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLblLinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblLinkMouseClicked
        try {
            Desktop.getDesktop().browse(new URI(jLblLink.getText()));
        } catch (URISyntaxException ex) {
            Logger.getLogger(JFraInformacionPelicula.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JFraInformacionPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLblLinkMouseClicked

    private void jLblLinkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblLinkMouseEntered
       jLblLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLblLinkMouseEntered

    private void jLblLinkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblLinkMouseExited
       jLblLink.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLblLinkMouseExited

    /**
     * @param args the command line arguMarments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFraInformacionPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraInformacionPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraInformacionPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraInformacionPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFraInformacionPelicula().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLblImg;
    public javax.swing.JLabel jLblLink;
    public javax.swing.JLabel jLblNombrePelicula;
    public javax.swing.JLabel jLblSinopsis;
    // End of variables declaration//GEN-END:variables
}
