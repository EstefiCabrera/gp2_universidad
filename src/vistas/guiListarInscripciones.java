/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import entidades.Alumno;
import entidades.Materia;
import java.security.Principal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import persistencia.AlumnoData;
import persistencia.MateriaData;
import persistencia.miConexion;

/**
 *
 * @author crn70
 */
public class guiListarInscripciones extends javax.swing.JInternalFrame {
    
  private DefaultTableModel modelo;
   
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Principal.class.getName());
    /**
     * Creates new form VistaListarInscripciones
     */
    public guiListarInscripciones() {
        initComponents();
        cargarCabecera();
        cargarComboboxMaterias();
        cargarTabla();
    }


    private void cargarTabla() {
      if (jcbInscripciones.getSelectedItem()==null){
          return;
      }
      String materiaSelec = jcbInscripciones.getSelectedItem().toString();
      modelo.setRowCount(0);
      
      miConexion conexion = new miConexion();
      AlumnoData ad = new AlumnoData(conexion);
      List<Alumno> alumnos = ad.listarAlumnosXmateria(materiaSelec);

    for (Alumno alu : alumnos) {
        modelo.addRow(new Object[]{
            alu.getIdAlumno(),
            alu.getDni(),
            alu.getNombre()
        });
    }
}

    
   private void cargarComboboxMaterias() {
    DefaultComboBoxModel<String> modeloCombo = new DefaultComboBoxModel<>();
    miConexion conexion = new miConexion();
    MateriaData materia = new MateriaData(conexion);

    List<Materia> materias = materia.listarMaterias();
    for (Materia m : materias) {
        modeloCombo.addElement(m.getNombre());  
    }
    jcbInscripciones.setModel(modeloCombo);
    jcbInscripciones.setSelectedIndex(0);
}

     
  private void cargarCabecera(){   
     modelo = new DefaultTableModel();
     modelo.addColumn("ID");
     modelo.addColumn("DNI");
     modelo.addColumn("Nombre");

     jtbAlumnos.setModel(modelo);
     }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtbAlumnos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jcbInscripciones = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jtbAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id inscripcion", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbAlumnos);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Listado de Alumnos por Materia");

        jcbInscripciones.addActionListener(this::jcbInscripcionesActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Seleccione una materia");

        jButton2.setText("Salir");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbInscripciones, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbInscripciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbInscripcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbInscripcionesActionPerformed
        cargarTabla();
    }//GEN-LAST:event_jcbInscripcionesActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbInscripciones;
    private javax.swing.JTable jtbAlumnos;
    // End of variables declaration//GEN-END:variables
}
