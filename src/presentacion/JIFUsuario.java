/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dao.UsuarioDao;
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
import logica.UsuarioLogica;

/**
 *
 * @author Katy Nuñez
 */
public class JIFUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFUsuario
     */
    public JIFUsuario() throws SQLException{
        initComponents();
        fondo();
        llenarTablaUsuario(0, "");
        llenarComboBoxTipoUsuario();    
        llenarComboBoxEmpleado();
        habilitarControles(true, false, false, false, false, true);
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
    private void habilitarControles(boolean nuevo, boolean guardar, boolean actualizar, boolean cancelar, boolean panel, boolean tabla){
        jBtnNuevo.setEnabled(nuevo);
        jBtnGuardar.setEnabled(guardar);
        jBtnActualizar.setEnabled(actualizar);
        jBtnCancelar.setEnabled(cancelar);
        jTblUsuario.setEnabled(tabla);
        habilitarPanel(jPnlUsuario, panel);
        
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
    private void limpiar(){
        jTFIdUsuario.setText("");
        jTFUsuario.setText("");
        jPFContrasena.setText("");
        jCboTipoUsuario.setSelectedIndex(0);
        jCboEmpleado.setSelectedIndex(0);
    }
    private void investigarCorrelativoUsuario() throws SQLException{
        UsuarioDao ud = new UsuarioDao();
        UsuarioLogica ul = new UsuarioLogica();
        ul.setIdUsuario(ud.autoIncrementarUsuario());
        jTFIdUsuario.setText(String.valueOf(ul.getIdUsuario()));
    }
    private boolean validar(){
        boolean estado;
        
        if(jTFUsuario.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese el nombre de usuario");
            jTFUsuario.requestFocus();
            estado = false;    
        }else if(jPFContrasena.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese la contraseña del usuario");
            jPFContrasena.requestFocus();
            estado = false;    
        }else if(jCboEmpleado.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione el empleado");
            jCboEmpleado.requestFocus();
            estado = false;    
        }else if(jCboTipoUsuario.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione el tipo de usuario");
            jCboTipoUsuario.requestFocus();
            estado = false;    
        }else{
            estado = true;
        }
        return estado;
    }
    private void llenarComboBoxTipoUsuario() throws SQLException{
        UsuarioDao ud = new UsuarioDao();
        String[] tipoUsuarios = new String[ud.mostrarTipoUsuarios().size()];
        tipoUsuarios = ud.mostrarTipoUsuarios().toArray(tipoUsuarios);
        DefaultComboBoxModel modeloTipoUsuarios = new DefaultComboBoxModel(tipoUsuarios);
        jCboTipoUsuario.setModel(modeloTipoUsuarios);
    }
    private void llenarComboBoxEmpleado() throws SQLException{
        UsuarioDao ud = new UsuarioDao();
        String[] empleados = new String[ud.mostrarEmpleados().size()];
        empleados = ud.mostrarEmpleados().toArray(empleados);
        DefaultComboBoxModel modeloEmpleados = new DefaultComboBoxModel(empleados);
        jCboEmpleado.setModel(modeloEmpleados);
    }
    private void limpiarTablaUsuario(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblUsuario.getModel(); 
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }      
    private void llenarTablaUsuario(int tipoBusqueda, String filtro) throws SQLException{
        limpiarTablaUsuario();
        UsuarioDao ud = new UsuarioDao();
        List<UsuarioLogica> miLista = ud.getListaUsuario(tipoBusqueda, filtro);  
        DefaultTableModel temp = (DefaultTableModel) this.jTblUsuario.getModel(); 
        
        for(UsuarioLogica ul: miLista){ 
            Object[] fila = new Object[5];   
            fila[0] = ul.getIdUsuario();
            fila[1] = ul.getNombreUsuario();
            fila[2] = ul.getContrasena();
            fila[3] = ul.getTipoUsuario();
            fila[4] = ul.getNombreEmpleado();
            temp.addRow(fila);
        }   
        jTblUsuario.getColumnModel().getColumn(2).setMinWidth(0);
        jTblUsuario.getColumnModel().getColumn(2).setMaxWidth(0);
        jTblUsuario.getColumnModel().getColumn(2).setPreferredWidth(0);
    }
    private void filaSeleccionada() {
        if (this.jTblUsuario.getSelectedRow() != -1) {
            if (this.jTblUsuario.isEnabled() == true) {
                this.jTFIdUsuario.setText(String.valueOf(this.jTblUsuario.getValueAt(jTblUsuario.getSelectedRow(), 0)));
                this.jTFUsuario.setText(String.valueOf(this.jTblUsuario.getValueAt(jTblUsuario.getSelectedRow(), 1)));
                this.jPFContrasena.setText(String.valueOf(this.jTblUsuario.getValueAt(jTblUsuario.getSelectedRow(), 2)));
                this.jCboTipoUsuario.setSelectedItem(String.valueOf(this.jTblUsuario.getValueAt(jTblUsuario.getSelectedRow(), 3)));
                this.jCboEmpleado.setSelectedItem(String.valueOf(this.jTblUsuario.getValueAt(jTblUsuario.getSelectedRow(), 4)));
            }
        } else {
            limpiar();
        }
    }
    private void guardarUsuario(){
        try {
            UsuarioLogica ul = new UsuarioLogica();
            UsuarioDao ud = new UsuarioDao();
            ul.setNombreUsuario(this.jTFUsuario.getText().trim());
            ul.setContrasena(this.jPFContrasena.getText().toString());
            ul.setIdTipoUsuario(ud.obtenerIdTipoUsuario(jCboTipoUsuario.getSelectedItem().toString()));
            ul.setCodEmpleado(ud.obtenerCodEmpleado(jCboEmpleado.getSelectedItem().toString()));
            ud.insertarUsuario(ul);
            JOptionPane.showMessageDialog(null, "Registro almacenado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar el usuario: " + e);
        }
    }
    private void actualizarUsuario(){
        try {
            UsuarioLogica ul = new UsuarioLogica();
            UsuarioDao ud = new UsuarioDao();
            ul.setIdUsuario(Integer.parseInt(this.jTFIdUsuario.getText().trim()));
            ul.setNombreUsuario(this.jTFUsuario.getText().trim());
            ul.setContrasena(this.jPFContrasena.getText().toString());
            ul.setIdTipoUsuario(ud.obtenerIdTipoUsuario(jCboTipoUsuario.getSelectedItem().toString()));
            ul.setCodEmpleado(ud.obtenerCodEmpleado(jCboEmpleado.getSelectedItem().toString()));      
            ud.actualizarUsuario(ul);
            JOptionPane.showMessageDialog(null, "Registro actualizado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + e);
        }
    }
    private void eliminarUsuario(){
        try {
            UsuarioLogica ul = new UsuarioLogica();
            UsuarioDao ud = new UsuarioDao();
            ul.setIdUsuario(Integer.parseInt(this.jTFIdUsuario.getText().trim()));    
            ud.eliminarUsuario(ul);
            JOptionPane.showMessageDialog(null, "Registro eliminar satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminae el usuario: " + e);
        }
    }
    private void busquedaUsuario(){
        if(!jTFBusqueda.getText().equals("")){
            if(jRBUsuario.isSelected() == true){
                try {
                    llenarTablaUsuario(1, jTFBusqueda.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    llenarTablaUsuario(2, jTFBusqueda.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            try {
                    llenarTablaUsuario(0, "");
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMIEditar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMIEliminar = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jRBUsuario = new javax.swing.JRadioButton();
        jRBEmpleado = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jTFBusqueda = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblUsuario = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jBtnNuevo = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jBtnActualizar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jBtnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPnlUsuario = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTFIdUsuario = new javax.swing.JTextField();
        jTFUsuario = new javax.swing.JTextField();
        jCboEmpleado = new javax.swing.JComboBox<>();
        jCboTipoUsuario = new javax.swing.JComboBox<>();
        jPFContrasena = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        jBtnCancelar = new javax.swing.JButton();

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

        buttonGroup1.add(jRBUsuario);
        jRBUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBUsuario.setSelected(true);
        jRBUsuario.setText("Usuario");

        buttonGroup1.add(jRBEmpleado);
        jRBEmpleado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBEmpleado.setText("Empleado");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search_flat.png"))); // NOI18N

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
                .addGap(18, 18, 18)
                .addComponent(jRBUsuario)
                .addGap(77, 77, 77)
                .addComponent(jRBEmpleado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRBUsuario)
                            .addComponent(jRBEmpleado))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jTFBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(7, 140, 215));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(7, 140, 215));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(0, 128, 166));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 128, 166));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre Usuario", "Contrasena", "Tipo Usuario", "Nombre Empleado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblUsuario.setComponentPopupMenu(jPopupMenu1);
        jTblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblUsuarioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTblUsuarioMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTblUsuarioMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTblUsuario);

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
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Usuario");

        jPanel11.setBackground(new java.awt.Color(0, 128, 166));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPnlUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPnlUsuario.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Código Usuario");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre Usuario");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Contraseña");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nombre Empleado");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Tipo Usuario");

        jTFIdUsuario.setEditable(false);

        javax.swing.GroupLayout jPnlUsuarioLayout = new javax.swing.GroupLayout(jPnlUsuario);
        jPnlUsuario.setLayout(jPnlUsuarioLayout);
        jPnlUsuarioLayout.setHorizontalGroup(
            jPnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlUsuarioLayout.createSequentialGroup()
                .addGroup(jPnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnlUsuarioLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jTFIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPnlUsuarioLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlUsuarioLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCboTipoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPnlUsuarioLayout.createSequentialGroup()
                                        .addGroup(jPnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCboEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jPFContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTFUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPnlUsuarioLayout.createSequentialGroup()
                                .addGroup(jPnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(30, 30, 30))
        );
        jPnlUsuarioLayout.setVerticalGroup(
            jPnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlUsuarioLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPFContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCboEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCboTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 133, 216));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel_1.png"))); // NOI18N
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
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
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPnlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jBtnNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnCancelar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)))
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
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
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
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPnlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBtnCancelar)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jBtnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBtnActualizar)
                                .addComponent(jBtnGuardar)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNuevoActionPerformed
        habilitarControles(false, true, false, true, true, false);
        limpiar();
        try {
            investigarCorrelativoUsuario();
        } catch (SQLException ex) {
            Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnNuevoActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de cancelar el proceso?","Cinema Evolution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            limpiar();
            habilitarControles(true, false, false, false, false, true);
        }
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        if(validar() == true){
            guardarUsuario(); 
            try {
                llenarTablaUsuario(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarControles(true, false, false, false, false, true);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActualizarActionPerformed
        if(validar() == true){
            actualizarUsuario(); 
            try {
                llenarTablaUsuario(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarControles(true, false, false, false, false, true); 
        }
    }//GEN-LAST:event_jBtnActualizarActionPerformed

    private void jMIEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEditarActionPerformed
        habilitarControles(false, false, true, true, true, false);
    }//GEN-LAST:event_jMIEditarActionPerformed

    private void jTblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblUsuarioMouseClicked
        filaSeleccionada();
    }//GEN-LAST:event_jTblUsuarioMouseClicked

    private void jTblUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblUsuarioMousePressed
        if(evt.getClickCount() == 2){     
        }
    }//GEN-LAST:event_jTblUsuarioMousePressed

    private void jMIEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEliminarActionPerformed
         if(JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de eliminar el usuario?","Cinema Evolution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            eliminarUsuario(); 
            try {
                llenarTablaUsuario(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }//GEN-LAST:event_jMIEliminarActionPerformed

    private void jTblUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblUsuarioMouseReleased
        filaSeleccionada();
    }//GEN-LAST:event_jTblUsuarioMouseReleased

    private void jTFBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFBusquedaKeyReleased
        busquedaUsuario();
    }//GEN-LAST:event_jTFBusquedaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnActualizar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JComboBox<String> jCboEmpleado;
    private javax.swing.JComboBox<String> jCboTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMIEditar;
    private javax.swing.JMenuItem jMIEliminar;
    private javax.swing.JPasswordField jPFContrasena;
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
    private javax.swing.JPanel jPnlUsuario;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRBEmpleado;
    private javax.swing.JRadioButton jRBUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTFBusqueda;
    private javax.swing.JTextField jTFIdUsuario;
    private javax.swing.JTextField jTFUsuario;
    private javax.swing.JTable jTblUsuario;
    // End of variables declaration//GEN-END:variables
}
