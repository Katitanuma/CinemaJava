/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dao.CarteleraPeliculaDao;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import logica.CarteleraPeliculaLogica;

/**
 *
 * @author Katy Nuñez
 */
public class JIFPeliculaCartelera extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFPeliculaCartelera
     */
    public JIFPeliculaCartelera() throws SQLException {
        initComponents();       
        fondo();
        llenarTablaCarteleraPelicula(0, "");
        llenarComboBoxFechaEstreno();
        llenarComboBoxPelicula();
        habilitarControles(true, false, false, false, false, true);
    }
     private void habilitarControles(boolean nuevo, boolean guardar, boolean actualizar, boolean cancelar, boolean panel, boolean tabla){
        jBtnNuevo.setEnabled(nuevo);
        jBtnGuardar.setEnabled(guardar);
        jBtnActualizar.setEnabled(actualizar);
        jBtnCancelar.setEnabled(cancelar);
        jTblCarteleraPelicula.setEnabled(tabla);
        habilitarPanel(jPnlCarteleraPelicula, panel);        
    }
      private void habilitarPanel(JPanel panel, Boolean habilitar) {
        panel.setEnabled(habilitar);

        Component[] components = panel.getComponents();

        for (int i = 0; i < components.length; i++) {
            if (components[i].getClass().getName() == "javax.swing.JPanel") {
                habilitarPanel((JPanel) components[i], habilitar);
            }
            components[i].setEnabled(habilitar);
        }
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
    
    
    private void llenarComboBoxFechaEstreno() throws SQLException{
        CarteleraPeliculaDao ud = new CarteleraPeliculaDao();
        String[] fechaEstreno = new String[ud.mostrarFechaEstreno().size()];
        fechaEstreno = ud.mostrarFechaEstreno().toArray(fechaEstreno);
        DefaultComboBoxModel modeloFechaEstreno = new DefaultComboBoxModel(fechaEstreno);
        jCboCartelera.setModel(modeloFechaEstreno);
    }
     private void llenarComboBoxPelicula() throws SQLException{
        CarteleraPeliculaDao ud = new CarteleraPeliculaDao();
        String[] pelicula = new String[ud.mostrarPelicula().size()];
        pelicula = ud.mostrarPelicula().toArray(pelicula);
        DefaultComboBoxModel modeloPelicula = new DefaultComboBoxModel(pelicula);
        jCboPelicula.setModel(modeloPelicula);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMIEditar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMIEliminar = new javax.swing.JMenuItem();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jRBEstreno = new javax.swing.JRadioButton();
        jRBPelicula = new javax.swing.JRadioButton();
        jTFBusqueda = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jBtnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblCarteleraPelicula = new javax.swing.JTable();
        jPnlCarteleraPelicula = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCboCartelera = new javax.swing.JComboBox<>();
        jCboPelicula = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jBtnNuevo = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jBtnActualizar = new javax.swing.JButton();

        jMIEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_Restart_30px.png"))); // NOI18N
        jMIEditar.setText("Editar");
        jMIEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIEditarActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMIEditar);
        jPopupMenu2.add(jSeparator1);

        jMIEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_Cancel_30px.png"))); // NOI18N
        jMIEliminar.setText("Eliminar");
        jMIEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIEliminarActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMIEliminar);

        setClosable(true);
        setTitle("CinemaEvolution");

        jPanel4.setBackground(new java.awt.Color(0, 128, 166));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(7, 140, 215));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda por", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel2.setOpaque(false);

        buttonGroup1.add(jRBEstreno);
        jRBEstreno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBEstreno.setSelected(true);
        jRBEstreno.setText("Fecha Estreno");
        jRBEstreno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBEstrenoActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRBPelicula);
        jRBPelicula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBPelicula.setText("Película");

        jTFBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFBusquedaActionPerformed(evt);
            }
        });
        jTFBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFBusquedaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRBEstreno)
                        .addGap(71, 71, 71)
                        .addComponent(jRBPelicula)))
                .addContainerGap(368, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBEstreno)
                    .addComponent(jRBPelicula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jTFBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save_1.png"))); // NOI18N
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(7, 140, 215));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 26)); // NOI18N
        jLabel1.setText("Cartelera de Películas");

        jPanel6.setBackground(new java.awt.Color(0, 133, 216));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel_1.png"))); // NOI18N
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        jTblCarteleraPelicula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cartelera", "Fecha de Estreno", "Fecha Final", "Nombre de Película"
            }
        ));
        jTblCarteleraPelicula.setComponentPopupMenu(jPopupMenu2);
        jTblCarteleraPelicula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTblCarteleraPeliculaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTblCarteleraPelicula);

        jPnlCarteleraPelicula.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPnlCarteleraPelicula.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Cartelera");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre de Película");

        javax.swing.GroupLayout jPnlCarteleraPeliculaLayout = new javax.swing.GroupLayout(jPnlCarteleraPelicula);
        jPnlCarteleraPelicula.setLayout(jPnlCarteleraPeliculaLayout);
        jPnlCarteleraPeliculaLayout.setHorizontalGroup(
            jPnlCarteleraPeliculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCarteleraPeliculaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPnlCarteleraPeliculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(jPnlCarteleraPeliculaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPnlCarteleraPeliculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCboPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCboCartelera, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPnlCarteleraPeliculaLayout.setVerticalGroup(
            jPnlCarteleraPeliculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCarteleraPeliculaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCboCartelera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCboPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 128, 166));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(0, 128, 166));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(0, 128, 166));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_1.png"))); // NOI18N
        jBtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNuevoActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(7, 140, 215));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(0, 128, 166));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 128, 166));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/update_1.png"))); // NOI18N
        jBtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPnlCarteleraPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jBtnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jPnlCarteleraPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jBtnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jBtnActualizar, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jBtnGuardar, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jBtnCancelar)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limpiar(){
        jCboCartelera.setSelectedIndex(0);
        jCboPelicula.setSelectedIndex(0);
    }
    private void jTFBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFBusquedaActionPerformed

    private void jBtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNuevoActionPerformed
        habilitarControles(false, true, false, true, true, false);
        limpiar();    
    }//GEN-LAST:event_jBtnNuevoActionPerformed

     private void guardarCarteleraPelicula(){
        try {
            CarteleraPeliculaLogica ul = new CarteleraPeliculaLogica();
            CarteleraPeliculaDao ud = new CarteleraPeliculaDao();
            ul.setIdCartelera(ud.obtenerIdCartelera(jCboCartelera.getSelectedItem().toString()));
            ul.setIdPelicula(ud.obtenerIdPelicula(jCboPelicula.getSelectedItem().toString()));
            ud.insertarCarteleraPelicula(ul);
            JOptionPane.showMessageDialog(null, "Registro almacenado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();  
            llenarTablaCarteleraPelicula(0,"");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar la Pelicula en la cartelera: " + e);
        }
    }
       private void actualizarCarteleraPelicula(){
        try {
            CarteleraPeliculaLogica ul = new CarteleraPeliculaLogica();
            CarteleraPeliculaDao ud = new CarteleraPeliculaDao();
            ul.setIdCartelera(ud.obtenerIdCartelera(jCboCartelera.getSelectedItem().toString()));
            ul.setIdPelicula(ud.obtenerIdPelicula(jCboPelicula.getSelectedItem().toString()));      
            ud.actualizarCarteleraPelicula(ul);
            JOptionPane.showMessageDialog(null, "Registro actualizado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();       
            llenarTablaCarteleraPelicula(0,"");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + e);
        }
    }
    
    private void eliminarCarteleraPelicula(){
        try {
             CarteleraPeliculaLogica ul = new CarteleraPeliculaLogica();
            CarteleraPeliculaDao ud = new CarteleraPeliculaDao();
            ul.setIdCartelera(ud.obtenerIdCartelera(jCboCartelera.getSelectedItem().toString()));
            ul.setIdPelicula(ud.obtenerIdPelicula(jCboPelicula.getSelectedItem().toString()));  
            ud.eliminarCarteleraPelicula(ul);
            JOptionPane.showMessageDialog(null, "Registro eliminado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + e);
        }
    }
     private void filaSeleccionada() {
        if (this.jTblCarteleraPelicula.getSelectedRow() != -1) {
            if (this.jTblCarteleraPelicula.isEnabled() == true) {
                this.jCboCartelera.setSelectedItem(String.valueOf(this.jTblCarteleraPelicula.getValueAt(jTblCarteleraPelicula.getSelectedRow(), 1)));
                this.jCboPelicula.setSelectedItem(String.valueOf(this.jTblCarteleraPelicula.getValueAt(jTblCarteleraPelicula.getSelectedRow(), 3)));
            }
        } else {
            limpiar();
        }
    }
    private void jTFBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFBusquedaKeyReleased
      busquedaCarteleraPelicula();
    }//GEN-LAST:event_jTFBusquedaKeyReleased

    private void jRBEstrenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBEstrenoActionPerformed
  
    }//GEN-LAST:event_jRBEstrenoActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        guardarCarteleraPelicula();
         habilitarControles(true, false, false, false, false, true);
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de cancelar el proceso?","Cinema Evolution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            limpiar();
            habilitarControles(true, false, false, false, false, true);
        }
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActualizarActionPerformed
       actualizarCarteleraPelicula();
        habilitarControles(true, false, false, false, false, true);
    }//GEN-LAST:event_jBtnActualizarActionPerformed

    private void jMIEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEditarActionPerformed
        habilitarControles(false, false, true, true, true, false);
        jTFBusqueda.setText("");
    }//GEN-LAST:event_jMIEditarActionPerformed

    private void jMIEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEliminarActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de eliminar el usuario?","Cinema Evolution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            eliminarCarteleraPelicula(); 
            try {
                llenarTablaCarteleraPelicula(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jTFBusqueda.setText("");
    }//GEN-LAST:event_jMIEliminarActionPerformed

    private void jTblCarteleraPeliculaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblCarteleraPeliculaMouseReleased
         filaSeleccionada();
    }//GEN-LAST:event_jTblCarteleraPeliculaMouseReleased
    private void busquedaCarteleraPelicula(){
              
        if(!jTFBusqueda.getText().equals("")){
            if(jRBEstreno.isSelected() == true){
               
                try {
                 
                     llenarTablaCarteleraPelicula(1, jTFBusqueda.getText());
                    
                    } catch (Exception ex) {
                     Logger.getLogger(JIFPeliculaCartelera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }else{
                try {
                    llenarTablaCarteleraPelicula(2, jTFBusqueda.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            try {
                    llenarTablaCarteleraPelicula(0, "");
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    private void llenarTablaCarteleraPelicula(int tipoBusqueda, String filtro) throws SQLException{
        limpiarTablaCarteleraPelicula();
        CarteleraPeliculaDao ud = new CarteleraPeliculaDao();
        List<CarteleraPeliculaLogica> miLista = ud.getListaCarteleraPelicula(tipoBusqueda, filtro);  
        DefaultTableModel temp = (DefaultTableModel) this.jTblCarteleraPelicula.getModel(); 
        
        for(CarteleraPeliculaLogica ul: miLista){ 
            Object[] fila = new Object[4];   
            fila[0] = ul.getIdCartelera();
            fila[1] = ul.getFechaExtreno();
            fila[2] = ul.getFechaFinal();
            fila[3] = ul.getNombrePelicula();
            temp.addRow(fila);
        }   
        
    }
        
    private void limpiarTablaCarteleraPelicula(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblCarteleraPelicula.getModel(); 
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnActualizar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JComboBox<String> jCboCartelera;
    private javax.swing.JComboBox<String> jCboPelicula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMIEditar;
    private javax.swing.JMenuItem jMIEliminar;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPnlCarteleraPelicula;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JRadioButton jRBEstreno;
    private javax.swing.JRadioButton jRBPelicula;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTFBusqueda;
    private javax.swing.JTable jTblCarteleraPelicula;
    // End of variables declaration//GEN-END:variables
}
