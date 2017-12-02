/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dao.EmpleadoDao;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import logica.EmpleadoLogica;

/**
 *
 * @author Katy Nuñez
 */
public class JIFEmpleado extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFEmpleado
     */
    public JIFEmpleado() throws SQLException {
        initComponents();
        fondo();
        llenarTablaEmpleado(0,"");
        habilitarControles(true, false, false, false, false, true);
        jTADireccion.setEnabled(false);
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
        jTblEmpleado.setEnabled(tabla);
        habilitarPanel(jPnlEmpleado, panel);        
    }
    
    
    private void limpiar(){
        jFFCodEmpleado.setText("");
        jTFNombre.setText("");
        jTFApellidos.setText("");
        jTADireccion.setText("");
        jFFTelefono.setText("");
        jTFCorreo.setText("");
    }
    
       private boolean validar(){
        boolean estado;
        
        if(jFFCodEmpleado.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese el código del empleado");
            jFFCodEmpleado.requestFocus();
            estado = false;    
        }else if(jTFNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del empleado");
            jTFNombre.requestFocus();
            estado = false;    
        }else if(jTFApellidos.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese los apellidos del empleado");
            jTFApellidos.requestFocus();
            estado = false;    
        }else if(jTADireccion.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese la dirección del empleado");
            jTADireccion.requestFocus();
            estado = false; 
        }else if(jFFTelefono.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese el teléfono del empleado");
            jFFTelefono.requestFocus();
            estado = false; 
        }else if(jTFCorreo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese el correo del empleado");
            jTFCorreo.requestFocus();
            estado = false;    
        }else{
            estado = true;
        }
        return estado;
    }
       
    private void limpiarTablaEmpleado(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblEmpleado.getModel(); 
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    } 
    
    private void llenarTablaEmpleado(int tipoBusqueda, String filtro) throws SQLException{
        limpiarTablaEmpleado();
        EmpleadoDao ed = new EmpleadoDao();
        List<EmpleadoLogica> miLista = ed.getListaEmpleado(tipoBusqueda, filtro);  
        DefaultTableModel temp = (DefaultTableModel) this.jTblEmpleado.getModel(); 
        
        for(EmpleadoLogica el: miLista){ 
            Object[] fila = new Object[6];   
            fila[0] = el.getCodEmpleado();
            fila[1] = el.getNombreEmpleado();
            fila[2] = el.getApellidosEmpleado();
            fila[3] = el.getDireccion();
            fila[4] = el.getTelefono();
            fila[5] = el.getCorreo();
            temp.addRow(fila);
        }   
    }
    
    private void filaSeleccionada() {
        if (this.jTblEmpleado.getSelectedRow() != -1) {
            if (this.jTblEmpleado.isEnabled() == true) {
                this.jFFCodEmpleado.setText(String.valueOf(this.jTblEmpleado.getValueAt(jTblEmpleado.getSelectedRow(), 0)));
                this.jTFNombre.setText(String.valueOf(this.jTblEmpleado.getValueAt(jTblEmpleado.getSelectedRow(), 1)));
                this.jTFApellidos.setText(String.valueOf(this.jTblEmpleado.getValueAt(jTblEmpleado.getSelectedRow(), 2)));
                this.jTADireccion.setText(String.valueOf(this.jTblEmpleado.getValueAt(jTblEmpleado.getSelectedRow(), 3)));
                this.jFFTelefono.setText(String.valueOf(this.jTblEmpleado.getValueAt(jTblEmpleado.getSelectedRow(), 4)));
                this.jTFCorreo.setText(String.valueOf(this.jTblEmpleado.getValueAt(jTblEmpleado.getSelectedRow(), 5)));
            }
        } else {
            limpiar();
        }
    }
    
    private void guardarEmpleado(){
        try {
            EmpleadoLogica el = new EmpleadoLogica();
            EmpleadoDao ed = new EmpleadoDao();
            el.setCodEmpleado(this.jFFCodEmpleado.getText().trim());
            el.setNombreEmpleado(this.jTFNombre.getText().trim());
            el.setApellidosEmpleado(this.jTFApellidos.getText().trim());
            el.setDireccion(this.jTADireccion.getText().trim());
            el.setTelefono(this.jFFTelefono.getText().trim());
            el.setCorreo(this.jTFCorreo.getText().trim());
            ed.insertarEmpleado(el);
            JOptionPane.showMessageDialog(null, "Registro almacenado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();   
            jTADireccion.setEnabled(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar el empleado: " + e);
        }
    }
    
    private void actualizarEmpleado(){
        try {
            EmpleadoLogica el = new EmpleadoLogica();
            EmpleadoDao ed = new EmpleadoDao();            
            el.setNombreEmpleado(this.jTFNombre.getText().trim());
            el.setApellidosEmpleado(this.jTFApellidos.getText().trim());
            el.setDireccion(this.jTADireccion.getText().trim());
            el.setTelefono(this.jFFTelefono.getText().trim());
            el.setCorreo(this.jTFCorreo.getText().trim());
            el.setCodEmpleado(this.jFFCodEmpleado.getText().trim());
            ed.actualizarEmpleado(el);
            JOptionPane.showMessageDialog(null, "Registro actualizado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar(); 
            jTADireccion.setEnabled(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el empleado: " + e);
        }
    }
    
    private void eliminarEmpleado(){
        try {
            EmpleadoLogica el = new EmpleadoLogica();
            EmpleadoDao ed = new EmpleadoDao();            
            el.setCodEmpleado(this.jFFCodEmpleado.getText().trim());
            ed.eliminarEmpleado(el);
            JOptionPane.showMessageDialog(null, "Registro eliminado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            limpiar();   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el empleado: " + e);
        }
    }
    
    private void busquedaEmpleado(){
        if(!jTFBusqueda.getText().equals("")){
            if(jRBCodEmpleado.isSelected() == true){
                try {
                    llenarTablaEmpleado(1, jTFBusqueda.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(JIFEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    llenarTablaEmpleado(2, jTFBusqueda.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(JIFEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            try {
                    llenarTablaEmpleado(0, "");
                } catch (SQLException ex) {
                    Logger.getLogger(JIFEmpleado.class.getName()).log(Level.SEVERE, null, ex);
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
        jRBCodEmpleado = new javax.swing.JRadioButton();
        jRBNombreEmpleado = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jTFBusqueda = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblEmpleado = new javax.swing.JTable();
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
        jPnlEmpleado = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFApellidos = new javax.swing.JTextField();
        jTFNombre = new javax.swing.JTextField();
        jFFCodEmpleado = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTADireccion = new javax.swing.JTextArea();
        jTFCorreo = new javax.swing.JTextField();
        jFFTelefono = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
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

        buttonGroup1.add(jRBCodEmpleado);
        jRBCodEmpleado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBCodEmpleado.setSelected(true);
        jRBCodEmpleado.setText("Cod. Empleado");

        buttonGroup1.add(jRBNombreEmpleado);
        jRBNombreEmpleado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBNombreEmpleado.setText("Nombre");

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
                .addComponent(jRBCodEmpleado)
                .addGap(44, 44, 44)
                .addComponent(jRBNombreEmpleado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
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
                            .addComponent(jRBCodEmpleado)
                            .addComponent(jRBNombreEmpleado))
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

        jTblEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Empleado", "Nombre Empleado", "Apellidos Empleado", "Dirección", "Teléfono", "Correo"
            }
        ));
        jTblEmpleado.setComponentPopupMenu(jPopupMenu1);
        jTblEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblEmpleadoMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTblEmpleadoMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTblEmpleado);

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
        jLabel1.setText("Empleado");

        jPanel11.setBackground(new java.awt.Color(0, 128, 166));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel_1.png"))); // NOI18N
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        jPnlEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPnlEmpleado.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Código Empleado");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Apellidos");

        try {
            jFFCodEmpleado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####-#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Dirección");

        jTADireccion.setColumns(20);
        jTADireccion.setRows(5);
        jScrollPane2.setViewportView(jTADireccion);

        try {
            jFFTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Teléfono");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Correo");

        javax.swing.GroupLayout jPnlEmpleadoLayout = new javax.swing.GroupLayout(jPnlEmpleado);
        jPnlEmpleado.setLayout(jPnlEmpleadoLayout);
        jPnlEmpleadoLayout.setHorizontalGroup(
            jPnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlEmpleadoLayout.createSequentialGroup()
                .addGroup(jPnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPnlEmpleadoLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(jPnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jFFCodEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                                .addComponent(jTFNombre)))
                        .addGroup(jPnlEmpleadoLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlEmpleadoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4)
                            .addGap(207, 207, 207)))
                    .addGroup(jPnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPnlEmpleadoLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlEmpleadoLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(202, 202, 202))
                            .addGroup(jPnlEmpleadoLayout.createSequentialGroup()
                                .addGroup(jPnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addGroup(jPnlEmpleadoLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jFFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(105, 105, 105)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPnlEmpleadoLayout.setVerticalGroup(
            jPnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlEmpleadoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFFCodEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(4, 4, 4)
                .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                                .addGap(43, 43, 43)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPnlEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(138, 138, 138))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jBtnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPnlEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jBtnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jBtnActualizar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jBtnGuardar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jBtnCancelar))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNuevoActionPerformed
        habilitarControles(false, true, false, true, true, false);
        limpiar();
        jTADireccion.setEnabled(true);
    }//GEN-LAST:event_jBtnNuevoActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de cancelar el proceso?","Cinema Evolution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            limpiar();
            habilitarControles(true, false, false, false, false, true);
            jTADireccion.setEnabled(false);
            try {
                llenarTablaEmpleado(0,"");
            } catch (SQLException ex) {
                Logger.getLogger(JIFEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
            jRBCodEmpleado.setSelected(true);
        }
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        if(validar() == true){
            guardarEmpleado(); 
            try {
                llenarTablaEmpleado(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarControles(true, false, false, false, false, true);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActualizarActionPerformed
        if(validar() == true){
            actualizarEmpleado(); 
            try {
                llenarTablaEmpleado(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarControles(true, false, false, false, false, true); 
        }
    }//GEN-LAST:event_jBtnActualizarActionPerformed

    private void jMIEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEditarActionPerformed
        jTADireccion.setEnabled(true);
        habilitarControles(false, false, true, true, true, false); 
        jTFBusqueda.setText("");
    }//GEN-LAST:event_jMIEditarActionPerformed

    private void jTblEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblEmpleadoMouseClicked
        filaSeleccionada();
    }//GEN-LAST:event_jTblEmpleadoMouseClicked

    private void jMIEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEliminarActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de eliminar el empleado?","Cinema Evolution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            eliminarEmpleado(); 
            jTFBusqueda.setText("");
            try {
                llenarTablaEmpleado(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMIEliminarActionPerformed

    private void jTblEmpleadoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblEmpleadoMouseReleased
        filaSeleccionada();
    }//GEN-LAST:event_jTblEmpleadoMouseReleased

    private void jTFBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFBusquedaKeyReleased
        busquedaEmpleado();
    }//GEN-LAST:event_jTFBusquedaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnActualizar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JFormattedTextField jFFCodEmpleado;
    private javax.swing.JFormattedTextField jFFTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JPanel jPnlEmpleado;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRBCodEmpleado;
    private javax.swing.JRadioButton jRBNombreEmpleado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextArea jTADireccion;
    private javax.swing.JTextField jTFApellidos;
    private javax.swing.JTextField jTFBusqueda;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTable jTblEmpleado;
    // End of variables declaration//GEN-END:variables
}
