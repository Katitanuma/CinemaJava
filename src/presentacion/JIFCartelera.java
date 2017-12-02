/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dao.CarteleraDao;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import logica.CarteleraLogica;

/**
 *
 * @author Katy Nuñez
 */
public class JIFCartelera extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFCartelera
     */
    public JIFCartelera() throws SQLException {
        initComponents();
        fondo();
        habilitarControles(true, false, false, false, false, true);
        llenarTablaCartelera(0, "");
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
    
    private void habilitarControles(boolean nuevo, boolean guardar, boolean actualizar, boolean cancelar, boolean panel, boolean tabla){
        jBtnNuevo.setEnabled(nuevo);
        jBtnGuardar.setEnabled(guardar);
        jBtnActualizar.setEnabled(actualizar);
        jBtnCancelar.setEnabled(cancelar);
        jTblCartelera.setEnabled(tabla);
        habilitarPanel(jPnlCartelera, panel);        
    }
    
     
    private void limpiar(){
        jTFCodCartelera.setText("");
        jFFEstreno.setText("");
        jFFFinal.setText("");            
    }
    
    private boolean validar(){
        boolean estado;
        
        if(jFFEstreno.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de estreno");
            jFFEstreno.requestFocus();
            estado = false;    
        }else if(jFFFinal.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese la fecha final");
            jFFFinal.requestFocus();
            estado = false;    
        }else{
            estado = true;
        }
        return estado;
    }
    
    private void investigarCorrelativoCartelera() throws SQLException{
        CarteleraDao cd = new CarteleraDao();
        CarteleraLogica cl = new CarteleraLogica();
        cl.setIdCartelera(cd.autoIncrementarCodCartelera());
        jTFCodCartelera.setText(String.valueOf(cl.getIdCartelera()));
    }
    
    private void limpiarTablaCartelera(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblCartelera.getModel(); 
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }    
    
    private void llenarTablaCartelera(int tipoBusqueda, String filtro) throws SQLException{
        limpiarTablaCartelera();
        CarteleraDao ud = new CarteleraDao();
        List<CarteleraLogica> miLista = ud.getListaCartelera(tipoBusqueda, filtro);  
        DefaultTableModel temp = (DefaultTableModel) this.jTblCartelera.getModel(); 
        
        for(CarteleraLogica cl: miLista){ 
            Object[] fila = new Object[3];   
            fila[0] = cl.getIdCartelera();
            fila[1] = cl.getFechaEstreno();
            fila[2] = cl.getFechaFinal();
            temp.addRow(fila);
        }   
    }
    
    private void filaSeleccionada() {
        if (this.jTblCartelera.getSelectedRow() != -1) {
            if (this.jTblCartelera.isEnabled() == true) {
                this.jTFCodCartelera.setText(String.valueOf(this.jTblCartelera.getValueAt(jTblCartelera.getSelectedRow(), 0)));
                this.jFFEstreno.setText(String.valueOf(this.jTblCartelera.getValueAt(jTblCartelera.getSelectedRow(), 1)));
                this.jFFFinal.setText(String.valueOf(this.jTblCartelera.getValueAt(jTblCartelera.getSelectedRow(), 2)));                
            }
        } else {
            limpiar();
        }
    }
    
     private void guardarCartelera(){
        try {
            CarteleraLogica cl = new CarteleraLogica();
            CarteleraDao cd = new CarteleraDao();
            cl.setIdCartelera(Integer.parseInt(this.jTFCodCartelera.getText().trim()));
            cl.setFechaEstreno(java.sql.Date.valueOf(this.jFFEstreno.getText()));
            cl.setFechaFinal(java.sql.Date.valueOf(this.jFFFinal.getText()));
            cd.insertarCartelera(cl);
            JOptionPane.showMessageDialog(null, "Registro almacenado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar la cartelera: " + e);
        }
    }
    
    private void actualizarCartelera(){
        try {
            CarteleraLogica cl = new CarteleraLogica();
            CarteleraDao cd = new CarteleraDao();
            cl.setIdCartelera(Integer.parseInt(this.jTFCodCartelera.getText().trim()));
            cl.setFechaEstreno(java.sql.Date.valueOf(this.jFFEstreno.getText()));
            cl.setFechaFinal(java.sql.Date.valueOf(this.jFFFinal.getText()));    
            cd.actualizarCartelera(cl);
            JOptionPane.showMessageDialog(null, "Registro actualizado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la cartelera: " + e);
        }
    }
    
    private void eliminarCartelera(){
        try {
            CarteleraLogica cl = new CarteleraLogica();
            CarteleraDao cd = new CarteleraDao();
            cl.setIdCartelera(Integer.parseInt(this.jTFCodCartelera.getText().trim()));    
            cd.eliminarCartelera(cl);
            JOptionPane.showMessageDialog(null, "Registro eliminado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la cartelera: " + e);
        }
    }
    
    private void busquedaCartelera(){
        if(!jTFBusqueda.getText().equals("")){
            if(jRBEstreno.isSelected() == true){
                try {
                    llenarTablaCartelera(1, jTFBusqueda.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(JIFCartelera.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    llenarTablaCartelera(2, jTFBusqueda.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(JIFCartelera.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
                try {
                    llenarTablaCartelera(0, "");
                } catch (SQLException ex) {
                    Logger.getLogger(JIFCartelera.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMIEditar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMIEliminar = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jRBEstreno = new javax.swing.JRadioButton();
        jRBCartelera = new javax.swing.JRadioButton();
        jTFBusqueda = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblCartelera = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jBtnNuevo = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jBtnActualizar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jBtnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jBtnCancelar = new javax.swing.JButton();
        jPnlCartelera = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFCodCartelera = new javax.swing.JTextField();
        jFFFinal = new javax.swing.JFormattedTextField();
        jFFEstreno = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();

        jMIEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_Restart_30px.png"))); // NOI18N
        jMIEditar.setText("Editar");
        jMIEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIEditarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMIEditar);
        jPopupMenu1.add(jSeparator1);

        jMIEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_Cancel_30px.png"))); // NOI18N
        jMIEliminar.setText("Eliminar");
        jMIEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMIEliminar);

        setClosable(true);
        setTitle("CinemaEvolution");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda por", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel2.setOpaque(false);

        buttonGroup1.add(jRBEstreno);
        jRBEstreno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBEstreno.setSelected(true);
        jRBEstreno.setText("Fecha Estreno");

        buttonGroup1.add(jRBCartelera);
        jRBCartelera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBCartelera.setText("Cod. Cartelera");

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
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFBusqueda)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRBEstreno)
                        .addGap(33, 33, 33)
                        .addComponent(jRBCartelera)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRBEstreno)
                    .addComponent(jRBCartelera))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(7, 140, 215));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(7, 140, 215));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(0, 128, 166));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 128, 166));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTblCartelera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdCartelera", "Fecha de Estreno", "Fecha Final"
            }
        ));
        jTblCartelera.setComponentPopupMenu(jPopupMenu1);
        jTblCartelera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblCarteleraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblCartelera);

        jPanel8.setBackground(new java.awt.Color(0, 128, 166));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 128, 166));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_1.png"))); // NOI18N
        jBtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNuevoActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(7, 140, 215));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/update_1.png"))); // NOI18N
        jBtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActualizarActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(0, 128, 166));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save_1.png"))); // NOI18N
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 26)); // NOI18N
        jLabel1.setText("Cartelera");

        jPanel11.setBackground(new java.awt.Color(0, 128, 166));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel_1.png"))); // NOI18N
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        jPnlCartelera.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPnlCartelera.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Código Cartelera");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Fecha de Estreno");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Fecha Final");

        try {
            jFFFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFFEstreno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPnlCarteleraLayout = new javax.swing.GroupLayout(jPnlCartelera);
        jPnlCartelera.setLayout(jPnlCarteleraLayout);
        jPnlCarteleraLayout.setHorizontalGroup(
            jPnlCarteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCarteleraLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPnlCarteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(jPnlCarteleraLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPnlCarteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFFEstreno, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFFFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFCodCartelera, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPnlCarteleraLayout.setVerticalGroup(
            jPnlCarteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlCarteleraLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFCodCartelera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFFEstreno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFFFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel6.setBackground(new java.awt.Color(0, 133, 216));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(745, 745, 745))
                    .addGroup(layout.createSequentialGroup()
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
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addComponent(jLabel1))
                                    .addComponent(jPnlCartelera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jBtnNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39))))
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
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jPnlCartelera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jBtnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jBtnActualizar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jBtnGuardar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jBtnCancelar))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNuevoActionPerformed
        habilitarControles(false, true, false, true, true, false);
        limpiar();
        try {
            investigarCorrelativoCartelera();
        } catch (SQLException ex) {
            Logger.getLogger(JIFCartelera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnNuevoActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        if(validar() == true){
            guardarCartelera(); 
            try {
                llenarTablaCartelera(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFCartelera.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarControles(true, false, false, false, false, true);
            jRBEstreno.setSelected(true);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActualizarActionPerformed
        if(validar() == true){
            actualizarCartelera(); 
            try {
                llenarTablaCartelera(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFCartelera.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarControles(true, false, false, false, false, true); 
            jRBEstreno.setSelected(true);
        }
    }//GEN-LAST:event_jBtnActualizarActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de cancelar el proceso?","Cinema Evolution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            limpiar();
            habilitarControles(true, false, false, false, false, true);
            try {
                llenarTablaCartelera(0,"");
            } catch (SQLException ex) {
                Logger.getLogger(JIFCartelera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jTFBusqueda.setText("");
        jRBEstreno.setSelected(true);
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jMIEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEditarActionPerformed
        habilitarControles(false, false, true, true, true, false);
        jTFBusqueda.setText("");
    }//GEN-LAST:event_jMIEditarActionPerformed

    private void jMIEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEliminarActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de eliminar la cartelera?","Cinema Evolution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            eliminarCartelera(); 
            try {
                llenarTablaCartelera(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFCartelera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMIEliminarActionPerformed

    private void jTblCarteleraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblCarteleraMouseClicked
        filaSeleccionada();
    }//GEN-LAST:event_jTblCarteleraMouseClicked

    private void jTFBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFBusquedaKeyReleased
        busquedaCartelera();
    }//GEN-LAST:event_jTFBusquedaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnActualizar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JFormattedTextField jFFEstreno;
    private javax.swing.JFormattedTextField jFFFinal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JPanel jPnlCartelera;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRBCartelera;
    private javax.swing.JRadioButton jRBEstreno;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTFBusqueda;
    private javax.swing.JTextField jTFCodCartelera;
    private javax.swing.JTable jTblCartelera;
    // End of variables declaration//GEN-END:variables
}
