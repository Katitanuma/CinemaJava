/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Katy Nuñez
 */
public class JMDI extends javax.swing.JFrame {

    /**
     * Creates new form JMDI
     */
    public static int idUsuario;
    public JMDI() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/estrella1.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDPMdi = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        jMUsuario = new javax.swing.JMenu();
        jMIGestionUsuario = new javax.swing.JMenuItem();
        jMICambiarUsuario = new javax.swing.JMenuItem();
        jMCartelera = new javax.swing.JMenu();
        jMIGestionCartelera = new javax.swing.JMenuItem();
        jMPelicula = new javax.swing.JMenu();
        jMIGestionPelicula = new javax.swing.JMenuItem();
        jMHorario = new javax.swing.JMenu();
        jMIGestionHorario = new javax.swing.JMenuItem();
        jMTecnologia = new javax.swing.JMenu();
        jMIGestionTecnologia = new javax.swing.JMenuItem();
        jMCarteleraPelicula = new javax.swing.JMenu();
        jMIGestionCartePeli = new javax.swing.JMenuItem();
        jMEstrenos = new javax.swing.JMenu();
        jMIVisualizarEstreno = new javax.swing.JMenuItem();
        jMFactura = new javax.swing.JMenu();
        jMIGestionFactura = new javax.swing.JMenuItem();
        jMEmpleado = new javax.swing.JMenu();
        jMIGestionEmpleado = new javax.swing.JMenuItem();
        jMReportes = new javax.swing.JMenu();
        jMIVisualizarReportes = new javax.swing.JMenuItem();
        jMLog = new javax.swing.JMenu();
        jMIVisualizarLog = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CinemaEvolution");
        setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        setResizable(false);

        jDPMdi.setBackground(new java.awt.Color(0, 153, 153));

        menuBar.setPreferredSize(new java.awt.Dimension(89, 77));

        jMUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Usuario.png"))); // NOI18N
        jMUsuario.setText("Usuario");
        jMUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMUsuario.setPreferredSize(new java.awt.Dimension(70, 68));
        jMUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIGestionUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIGestionUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/if_pencil_173067.png"))); // NOI18N
        jMIGestionUsuario.setMnemonic('o');
        jMIGestionUsuario.setText("Gestión");
        jMIGestionUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGestionUsuarioActionPerformed(evt);
            }
        });
        jMUsuario.add(jMIGestionUsuario);

        jMICambiarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMICambiarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cambio.png"))); // NOI18N
        jMICambiarUsuario.setText("Cambiar Usuario");
        jMICambiarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMICambiarUsuarioActionPerformed(evt);
            }
        });
        jMUsuario.add(jMICambiarUsuario);

        menuBar.add(jMUsuario);

        jMCartelera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Mostrar.png"))); // NOI18N
        jMCartelera.setText("Cartelera");
        jMCartelera.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMCartelera.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMCartelera.setMaximumSize(new java.awt.Dimension(82, 32767));
        jMCartelera.setPreferredSize(new java.awt.Dimension(82, 68));
        jMCartelera.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIGestionCartelera.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIGestionCartelera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/if_pencil_173067.png"))); // NOI18N
        jMIGestionCartelera.setText("Gestión");
        jMIGestionCartelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGestionCarteleraActionPerformed(evt);
            }
        });
        jMCartelera.add(jMIGestionCartelera);

        menuBar.add(jMCartelera);

        jMPelicula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Pelicula_1.png"))); // NOI18N
        jMPelicula.setText("Película");
        jMPelicula.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMPelicula.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMPelicula.setPreferredSize(new java.awt.Dimension(70, 68));
        jMPelicula.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIGestionPelicula.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIGestionPelicula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/if_pencil_173067.png"))); // NOI18N
        jMIGestionPelicula.setText("Gestión");
        jMIGestionPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGestionPeliculaActionPerformed(evt);
            }
        });
        jMPelicula.add(jMIGestionPelicula);

        menuBar.add(jMPelicula);

        jMHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Horario.png"))); // NOI18N
        jMHorario.setText("Horario");
        jMHorario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMHorario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMHorario.setPreferredSize(new java.awt.Dimension(70, 68));
        jMHorario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIGestionHorario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIGestionHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/if_pencil_173067.png"))); // NOI18N
        jMIGestionHorario.setText("Gestión");
        jMIGestionHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGestionHorarioActionPerformed(evt);
            }
        });
        jMHorario.add(jMIGestionHorario);

        menuBar.add(jMHorario);

        jMTecnologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tecnologia.png"))); // NOI18N
        jMTecnologia.setText("Tecnología");
        jMTecnologia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMTecnologia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMTecnologia.setPreferredSize(new java.awt.Dimension(88, 68));
        jMTecnologia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIGestionTecnologia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIGestionTecnologia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/if_pencil_173067.png"))); // NOI18N
        jMIGestionTecnologia.setMnemonic('c');
        jMIGestionTecnologia.setText("Gestión");
        jMIGestionTecnologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGestionTecnologiaActionPerformed(evt);
            }
        });
        jMTecnologia.add(jMIGestionTecnologia);

        menuBar.add(jMTecnologia);

        jMCarteleraPelicula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cartelera_1.png"))); // NOI18N
        jMCarteleraPelicula.setText("Cartelera-Película");
        jMCarteleraPelicula.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMCarteleraPelicula.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMCarteleraPelicula.setMaximumSize(new java.awt.Dimension(138, 32767));
        jMCarteleraPelicula.setPreferredSize(new java.awt.Dimension(138, 68));
        jMCarteleraPelicula.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIGestionCartePeli.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIGestionCartePeli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/if_pencil_173067.png"))); // NOI18N
        jMIGestionCartePeli.setText("Gestión");
        jMIGestionCartePeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGestionCartePeliActionPerformed(evt);
            }
        });
        jMCarteleraPelicula.add(jMIGestionCartePeli);

        menuBar.add(jMCarteleraPelicula);

        jMEstrenos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/PeliculasCartelera.png"))); // NOI18N
        jMEstrenos.setText("Estrenos");
        jMEstrenos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMEstrenos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMEstrenos.setMaximumSize(new java.awt.Dimension(77, 32767));
        jMEstrenos.setPreferredSize(new java.awt.Dimension(77, 68));
        jMEstrenos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIVisualizarEstreno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIVisualizarEstreno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/32.png"))); // NOI18N
        jMIVisualizarEstreno.setText("Visualizar");
        jMIVisualizarEstreno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIVisualizarEstrenoActionPerformed(evt);
            }
        });
        jMEstrenos.add(jMIVisualizarEstreno);

        menuBar.add(jMEstrenos);

        jMFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Factura_1.png"))); // NOI18N
        jMFactura.setText("Factura");
        jMFactura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMFactura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMFactura.setPreferredSize(new java.awt.Dimension(70, 68));
        jMFactura.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIGestionFactura.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIGestionFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/if_pencil_173067.png"))); // NOI18N
        jMIGestionFactura.setText("Gestión");
        jMIGestionFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGestionFacturaActionPerformed(evt);
            }
        });
        jMFactura.add(jMIGestionFactura);

        menuBar.add(jMFactura);

        jMEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Empleado1.png"))); // NOI18N
        jMEmpleado.setText("Empleado");
        jMEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMEmpleado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMEmpleado.setMaximumSize(new java.awt.Dimension(83, 32767));
        jMEmpleado.setPreferredSize(new java.awt.Dimension(83, 68));
        jMEmpleado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIGestionEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIGestionEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/if_pencil_173067.png"))); // NOI18N
        jMIGestionEmpleado.setText("Gestión");
        jMIGestionEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIGestionEmpleadoActionPerformed(evt);
            }
        });
        jMEmpleado.add(jMIGestionEmpleado);

        menuBar.add(jMEmpleado);

        jMReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Report.png"))); // NOI18N
        jMReportes.setText("Reportes");
        jMReportes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMReportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMReportes.setMaximumSize(new java.awt.Dimension(80, 32767));
        jMReportes.setPreferredSize(new java.awt.Dimension(80, 68));
        jMReportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIVisualizarReportes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIVisualizarReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/32.png"))); // NOI18N
        jMIVisualizarReportes.setText("Visualizar");
        jMIVisualizarReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIVisualizarReportesActionPerformed(evt);
            }
        });
        jMReportes.add(jMIVisualizarReportes);

        menuBar.add(jMReportes);

        jMLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/if_log_199213.png"))); // NOI18N
        jMLog.setText("Log Auditoría");
        jMLog.setFocusable(false);
        jMLog.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMLog.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMLog.setPreferredSize(new java.awt.Dimension(100, 19));
        jMLog.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jMIVisualizarLog.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMIVisualizarLog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/32.png"))); // NOI18N
        jMIVisualizarLog.setText("Visualizar");
        jMIVisualizarLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIVisualizarLogActionPerformed(evt);
            }
        });
        jMLog.add(jMIVisualizarLog);

        menuBar.add(jMLog);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_Help_48px_4.png"))); // NOI18N
        jMenu1.setText("Ayuda");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDPMdi, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDPMdi, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMIGestionUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGestionUsuarioActionPerformed
        JIFUsuario user;
        try {
            user = new JIFUsuario();
            jDPMdi.add(user);
            user.show();
            Dimension dimDP=jDPMdi.getSize();
            Dimension dimF=user.getSize();
            user.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
        } catch (SQLException ex) {
            Logger.getLogger(JMDI.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_jMIGestionUsuarioActionPerformed

    private void jMIGestionCarteleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGestionCarteleraActionPerformed
        JIFCartelera carte;
        try {
            carte = new JIFCartelera();
            jDPMdi.add(carte);
            carte.show();
        
            Dimension dimDP=jDPMdi.getSize();
            Dimension dimF=carte.getSize();
            carte.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
        } catch (SQLException ex) {
            Logger.getLogger(JMDI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMIGestionCarteleraActionPerformed

    private void jMIGestionPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGestionPeliculaActionPerformed
        try {
            JIFPelicula peli=new JIFPelicula();
            jDPMdi.add(peli);
            peli.show();
            
            Dimension dimDP=jDPMdi.getSize();
            Dimension dimF=peli.getSize();
            peli.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
        } catch (SQLException ex) {
            Logger.getLogger(JMDI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMIGestionPeliculaActionPerformed

    private void jMIGestionHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGestionHorarioActionPerformed
        JIFHorario hora;
        try {
            hora = new JIFHorario();
                    jDPMdi.add(hora);
        hora.show();
        
        Dimension dimDP=jDPMdi.getSize();
        Dimension dimF=hora.getSize();
        hora.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
        } catch (SQLException ex) {
            Logger.getLogger(JMDI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMIGestionHorarioActionPerformed

    private void jMIGestionTecnologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGestionTecnologiaActionPerformed
       JIFTecnologia tec;
        try {
            tec = new JIFTecnologia();
            jDPMdi.add(tec);
            tec.show();
            Dimension dimDP=jDPMdi.getSize();
            Dimension dimF=tec.getSize();
            tec.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
        } catch (SQLException ex) {
            Logger.getLogger(JMDI.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_jMIGestionTecnologiaActionPerformed

    private void jMIGestionCartePeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGestionCartePeliActionPerformed
        try {
            JIFPeliculaCartelera pc=new JIFPeliculaCartelera();
            jDPMdi.add(pc);
            pc.show();
            
            Dimension dimDP=jDPMdi.getSize();
            Dimension dimF=pc.getSize();
            pc.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
        } catch (SQLException ex) {
            Logger.getLogger(JMDI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMIGestionCartePeliActionPerformed

    private void jMIVisualizarEstrenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIVisualizarEstrenoActionPerformed
        JIFMostrarCartelera mc;
        try {
            mc = new JIFMostrarCartelera();
            jDPMdi.add(mc);
            mc.show();

            Dimension dimDP=jDPMdi.getSize();
            Dimension dimF=mc.getSize();
            mc.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
        } catch (SQLException ex) {
            Logger.getLogger(JMDI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMIVisualizarEstrenoActionPerformed

    private void jMIGestionFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGestionFacturaActionPerformed
        JIFFactura fac;
        try {
            fac = new JIFFactura();
            jDPMdi.add(fac);
            fac.show();

            Dimension dimDP=jDPMdi.getSize();
            Dimension dimF=fac.getSize();
            fac.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
        } catch (SQLException ex) {
            Logger.getLogger(JMDI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMIGestionFacturaActionPerformed

    private void jMIGestionEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIGestionEmpleadoActionPerformed
        JIFEmpleado emp;
        try {
            emp = new JIFEmpleado();
            jDPMdi.add(emp);
            emp.show();
        
            Dimension dimDP=jDPMdi.getSize();
            Dimension dimF=emp.getSize();
            emp.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
        } catch (SQLException ex) {
            Logger.getLogger(JMDI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMIGestionEmpleadoActionPerformed

    private void jMIVisualizarReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIVisualizarReportesActionPerformed
        JIFReportes re=new JIFReportes();
        jDPMdi.add(re);
        re.show();
        
        Dimension dimDP=jDPMdi.getSize();
        Dimension dimF=re.getSize();
        re.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
    }//GEN-LAST:event_jMIVisualizarReportesActionPerformed

    private void jMIVisualizarLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIVisualizarLogActionPerformed
        JIFLogAuditoria log;
        try {
            log = new JIFLogAuditoria();
            jDPMdi.add(log);
            log.show();

            Dimension dimDP=jDPMdi.getSize();
            Dimension dimF=log.getSize();
            log.setLocation((dimDP.width-dimF.width)/2,(dimDP.height-dimF.height)/2);
        } catch (SQLException ex) {
            Logger.getLogger(JMDI.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }//GEN-LAST:event_jMIVisualizarLogActionPerformed

    private void jMICambiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMICambiarUsuarioActionPerformed
        setVisible(false);
        JFraLogin miFormulario = new JFraLogin();
        miFormulario.show();
    }//GEN-LAST:event_jMICambiarUsuarioActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        Desktop dsk = Desktop.getDesktop(); 
        try {
            File archivo = new File("src/ayuda/ManualCinemaEvolutionHelp.chm");
            dsk.open(archivo);
        } catch (Exception e) {
            
        }
        
    }//GEN-LAST:event_jMenu1MouseClicked

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(JMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JMDI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDPMdi;
    public javax.swing.JMenu jMCartelera;
    public javax.swing.JMenu jMCarteleraPelicula;
    public javax.swing.JMenu jMEmpleado;
    private javax.swing.JMenu jMEstrenos;
    private javax.swing.JMenu jMFactura;
    public javax.swing.JMenu jMHorario;
    private javax.swing.JMenuItem jMICambiarUsuario;
    private javax.swing.JMenuItem jMIGestionCartePeli;
    private javax.swing.JMenuItem jMIGestionCartelera;
    private javax.swing.JMenuItem jMIGestionEmpleado;
    private javax.swing.JMenuItem jMIGestionFactura;
    private javax.swing.JMenuItem jMIGestionHorario;
    private javax.swing.JMenuItem jMIGestionPelicula;
    private javax.swing.JMenuItem jMIGestionTecnologia;
    public javax.swing.JMenuItem jMIGestionUsuario;
    private javax.swing.JMenuItem jMIVisualizarEstreno;
    private javax.swing.JMenuItem jMIVisualizarLog;
    private javax.swing.JMenuItem jMIVisualizarReportes;
    public javax.swing.JMenu jMLog;
    public javax.swing.JMenu jMPelicula;
    private javax.swing.JMenu jMReportes;
    public javax.swing.JMenu jMTecnologia;
    public javax.swing.JMenu jMUsuario;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
