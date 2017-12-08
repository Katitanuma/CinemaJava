/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dao.Conexion;
import dao.FacturaDao;
import dao.TecnologiaDao;
import dao.UsuarioDao;
import java.awt.BorderLayout;
import java.awt.Component;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import logica.FacturaLogica;
import logica.UsuarioLogica;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Katy Nuñez
 */
public class JIFFactura extends javax.swing.JInternalFrame {

    /**
     * Creates new form JIFFactura
     */
    public JIFFactura() throws SQLException {
        initComponents();
        fondo();
        llenarTablaFactura(0, "");
        llenarComboBoxPelicula();    
        llenarComboBoxTecnologia();
        habilitarControles(true, false, false, false, true);
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
     private void habilitarControles(boolean nuevo, boolean guardar, boolean cancelar,boolean panel, boolean tabla){
        jBtnNuevo.setEnabled(nuevo);
        jBtnGuardar.setEnabled(guardar);
        jBtnCancelar.setEnabled(cancelar);
        jTblFactura.setEnabled(tabla);
        habilitarPanel(jPnlFactura, panel); 
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
        jTFIdFactura.setText("");
        jTFFecha.setText("");
        jSpCantidad.setValue(0);
        jTFPrecio.setText("");
        jCboPelicula.setSelectedIndex(0);
        jCboTecnologia.setSelectedIndex(0);
    }
    private void investigarCorrelativoFactura() throws SQLException{
        FacturaDao fd = new FacturaDao();
        FacturaLogica fl = new FacturaLogica();
        fl.setIdFactura(fd.autoIncrementarFactura());
        jTFIdFactura.setText(String.valueOf(fl.getIdFactura()));
    }
    private boolean validar(){
        boolean estado;
        
        if(jCboPelicula.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione la pelicula");
            jCboPelicula.requestFocus();
            estado = false;    
        }else if(jCboTecnologia.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione la tecnologia");
            jCboTecnologia.requestFocus();
            estado = false;    
        }else if(Integer.parseInt(jSpCantidad.getValue().toString()) <= 0){
            JOptionPane.showMessageDialog(null, "Ingrese la cantidad mayor que 0");
            jSpCantidad.requestFocus();
            estado = false;    
        }else if(jTFPrecio.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese el precio de los boletos");
            jTFPrecio.requestFocus();
            estado = false;    
        }else{
            estado = true;
        }
        return estado;
    }
    private void limpiarTablaFactura(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblFactura.getModel(); 
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }      
   
    private void llenarTablaFactura(int tipoBusqueda, String filtro) throws SQLException{
        limpiarTablaFactura();
        FacturaDao fd = new FacturaDao();
        List<FacturaLogica> miLista = fd.getListaFactura(tipoBusqueda, filtro);  
        DefaultTableModel temp = (DefaultTableModel) this.jTblFactura.getModel(); 
        
        for(FacturaLogica fl: miLista){ 
            Object[] fila = new Object[7];   
            fila[0] = fl.getIdFactura();
            fila[1] = fl.getFecha();
            fila[2] = fl.getPelicula();
            fila[3] = fl.getTecnologia();
            fila[4] = fl.getCantidad();
            fila[5] = fl.getPrecio();
            fila[6] = fl.getTotal();
            
            temp.addRow(fila);
        }   
       
    }
    private void guardarFactura(){
        try {
            FacturaLogica fl = new FacturaLogica();
            FacturaDao fd = new FacturaDao();
           
            Date date = Calendar.getInstance().getTime();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
            
            fl.setFecha(sqlDate);
            fl.setCantidad(Integer.parseInt(jSpCantidad.getValue().toString()));
            fl.setPrecio(Double.parseDouble(jTFPrecio.getText()));
            fl.setIdPelicula(fd.obtenerIdPelicula(jCboPelicula.getSelectedItem().toString()));
            fl.setIdTecnologia(fd.obtenerIdTecnologia(jCboTecnologia.getSelectedItem().toString()));
            fl.setIdUsuario(JMDI.idUsuario);
            fd.insertarFactura(fl);
            JOptionPane.showMessageDialog(null, "Registro almacenado satisfactoriamente","Cinema Evolution",JOptionPane.INFORMATION_MESSAGE);
            if(JOptionPane.showConfirmDialog(rootPane, "¿Desea visualizar la factura","Cinema Evolution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                reporteFactura(String.valueOf(jTFIdFactura.getText()));
            }
            limpiar();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar el usuario: " + e);
        }
    }
    private void busquedaFactura(){
        if(!jTFBusqueda.getText().equals("")){
                try {
                    llenarTablaFactura(1, jTFBusqueda.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
          }else{
            try {
                    llenarTablaFactura(0, "");
                } catch (SQLException ex) {
                    Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
          }
    }
    private void llenarComboBoxTecnologia() throws SQLException{
        FacturaDao fd = new FacturaDao();
        String[] tecnologia = new String[fd.mostrarTecnologia().size()];
        tecnologia = fd.mostrarTecnologia().toArray(tecnologia);
        DefaultComboBoxModel modeloTecnologia = new DefaultComboBoxModel(tecnologia);
        jCboTecnologia.setModel(modeloTecnologia);
    }

    private void llenarComboBoxPelicula() throws SQLException{
        FacturaDao fd = new FacturaDao();
        String[] tecnologia = new String[fd.mostrarNombrePelicula().size()];
        tecnologia = fd.mostrarNombrePelicula().toArray(tecnologia);
        DefaultComboBoxModel modeloPelicula = new DefaultComboBoxModel(tecnologia);
        jCboPelicula.setModel(modeloPelicula);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBtnGuardar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jRBNombreEmpleado = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jTFBusqueda = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jBtnNuevo = new javax.swing.JButton();
        jPnlFactura = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFIdFactura = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTFPrecio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFFecha = new javax.swing.JTextField();
        jCboPelicula = new javax.swing.JComboBox<>();
        jCboTecnologia = new javax.swing.JComboBox<>();
        jSpCantidad = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblFactura = new javax.swing.JTable();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jRBCodEmpleado1 = new javax.swing.JRadioButton();
        jRBNombreEmpleado1 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jTFBusqueda1 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTFNombre2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTFCorreo2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTFNombre3 = new javax.swing.JTextField();
        jTFCorreo3 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTDatos1 = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jBtnCancelar = new javax.swing.JButton();

        setClosable(true);
        setTitle("CinemaEvolution");

        jPanel8.setBackground(new java.awt.Color(0, 128, 166));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 26)); // NOI18N
        jLabel1.setText("Factura");

        jBtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save_1.png"))); // NOI18N
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(0, 128, 166));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda por", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel2.setOpaque(false);

        buttonGroup1.add(jRBNombreEmpleado);
        jRBNombreEmpleado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBNombreEmpleado.setSelected(true);
        jRBNombreEmpleado.setText("Película");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search_flat.png"))); // NOI18N

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
                .addGap(24, 24, 24)
                .addComponent(jRBNombreEmpleado)
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jRBNombreEmpleado, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jTFBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 128, 166));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(0, 133, 216));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(0, 128, 166));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_1.png"))); // NOI18N
        jBtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNuevoActionPerformed(evt);
            }
        });

        jPnlFactura.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPnlFactura.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Código Factura");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Fecha");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Película");

        jTFIdFactura.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tecnología");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Cantidad");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Precio");

        jTFFecha.setEditable(false);

        javax.swing.GroupLayout jPnlFacturaLayout = new javax.swing.GroupLayout(jPnlFactura);
        jPnlFactura.setLayout(jPnlFacturaLayout);
        jPnlFacturaLayout.setHorizontalGroup(
            jPnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlFacturaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addGroup(jPnlFacturaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCboTecnologia, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCboPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(jPnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPnlFacturaLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTFIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPnlFacturaLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jTFPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel8))
                            .addComponent(jSpCantidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel7)
                        .addComponent(jTFFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPnlFacturaLayout.setVerticalGroup(
            jPnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlFacturaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCboPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCboTecnologia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSpCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(7, 140, 215));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 128, 166));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(7, 140, 215));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTblFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Factura", "Fecha", "Nombre Película", "Tecnología", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTblFactura);

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setTitle("CinemaEvolution");

        jPanel9.setBackground(new java.awt.Color(0, 128, 166));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Palatino Linotype", 1, 26)); // NOI18N
        jLabel9.setText("Factura");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save_1.png"))); // NOI18N

        jPanel14.setBackground(new java.awt.Color(0, 128, 166));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda por", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel15.setOpaque(false);

        jRBCodEmpleado1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBCodEmpleado1.setText("Cod. Factura");

        jRBNombreEmpleado1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRBNombreEmpleado1.setText("Pelìcula");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search_flat.png"))); // NOI18N

        jTFBusqueda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFBusqueda1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jRBCodEmpleado1)
                .addGap(44, 44, 44)
                .addComponent(jRBNombreEmpleado1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFBusqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRBCodEmpleado1)
                            .addComponent(jRBNombreEmpleado1))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jTFBusqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(0, 128, 166));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/update_1.png"))); // NOI18N

        jPanel17.setBackground(new java.awt.Color(0, 133, 216));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBackground(new java.awt.Color(0, 128, 166));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_1.png"))); // NOI18N

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel19.setOpaque(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Código Factura");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Fecha");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Película");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Tecnología");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Cantidad");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Precio");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTFNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTFCorreo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addGroup(jPanel19Layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jTFCorreo2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jLabel15)
                        .addComponent(jTFNombre3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFCorreo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFCorreo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(7, 140, 215));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel_1.png"))); // NOI18N

        jPanel21.setBackground(new java.awt.Color(0, 128, 166));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(7, 140, 215));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTDatos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código Factura", "Fecha", "Nombre Pelìcula", "Tecnologìa", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane2.setViewportView(jTDatos1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(jLabel9)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton8))))))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jInternalFrame1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(0, 128, 166));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel24.setBackground(new java.awt.Color(0, 128, 166));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel25.setBackground(new java.awt.Color(0, 133, 216));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(0, 133, 216));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPnlFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jBtnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnCancelar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPnlFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBtnCancelar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jBtnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnGuardar)))
                .addGap(16, 16, 16))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 263, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 263, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFBusquedaActionPerformed

    private void jTFBusqueda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFBusqueda1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFBusqueda1ActionPerformed

    private void jBtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNuevoActionPerformed
        habilitarControles(false, true, true, true, false);
        limpiar();
        Date date = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        jTFFecha.setText(sqlDate.toString());
        try {
            investigarCorrelativoFactura();
        } catch (SQLException ex) {
            Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnNuevoActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
         if(JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro de cancelar el proceso?","Cinema Evolution",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            limpiar();
            habilitarControles(true, false, false, false, true);
         }
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        if(validar() == true){
            guardarFactura(); 
            try {
                llenarTablaFactura(0, "");
            } catch (SQLException ex) {
                Logger.getLogger(JIFUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarControles(true, false, false, false, true);
            
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed
    private void reporteFactura(String id){
        String path = "";
        try {
            path = getClass().getResource("/reportes/RptFactura.jasper").getPath();
            path = URLDecoder.decode(path,"UTF-8");
            Connection cn = Conexion.conectar();
            Map parametros = new HashMap();  
            parametros.put("pIdFactura",Integer.parseInt(id));
         
            JasperReport reporte = (JasperReport)JRLoader.loadObject(path);
            JasperPrint imprimir = JasperFillManager.fillReport(reporte,parametros,cn);
            JasperViewer visor = new JasperViewer(imprimir,false);
          
            visor.setTitle("Reporte de factura de venta de boletos");
            visor.setExtendedState(MAXIMIZED_BOTH);
            visor.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al Mostrar el reporte: "+e.getMessage());
        }
    }
    private void jTFBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFBusquedaKeyReleased
        busquedaFactura();
    }//GEN-LAST:event_jTFBusquedaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jCboPelicula;
    private javax.swing.JComboBox<String> jCboTecnologia;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JInternalFrame jInternalFrame1;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPnlFactura;
    private javax.swing.JRadioButton jRBCodEmpleado1;
    private javax.swing.JRadioButton jRBNombreEmpleado;
    private javax.swing.JRadioButton jRBNombreEmpleado1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpCantidad;
    private javax.swing.JTable jTDatos1;
    private javax.swing.JTextField jTFBusqueda;
    private javax.swing.JTextField jTFBusqueda1;
    private javax.swing.JTextField jTFCorreo2;
    private javax.swing.JTextField jTFCorreo3;
    private javax.swing.JTextField jTFFecha;
    private javax.swing.JTextField jTFIdFactura;
    private javax.swing.JTextField jTFNombre2;
    private javax.swing.JTextField jTFNombre3;
    private javax.swing.JTextField jTFPrecio;
    private javax.swing.JTable jTblFactura;
    // End of variables declaration//GEN-END:variables
}
