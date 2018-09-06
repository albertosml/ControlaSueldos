
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albertosml
 */
public class VerDiasTrabajados extends javax.swing.JFrame {

    private String nombre_directorio;
    
    /**
     * Creates new form Inicio
     */
    public VerDiasTrabajados() throws ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Días Trabajados  -  ControlaSueldos");
        
        OperacionesBD o = new OperacionesBD();
        
        ArrayList<String> a = o.obtainSpinnerList();
        if(a.get(0) != "error") {
            SpinnerListModel listModel = new SpinnerListModel(a);
        
            trabajadores.setModel(listModel);
            trabajadores.setValue(a.get(0));
        }
        else {
            SpinnerListModel listModel = new SpinnerListModel(new String[]{""});
            trabajadores.setModel(listModel);
        }
        
        ((JSpinner.DefaultEditor) trabajadores.getEditor()).getTextField().setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinField1 = new com.toedter.components.JSpinField();
        mes = new com.toedter.calendar.JMonthChooser();
        anio = new com.toedter.calendar.JYearChooser();
        tit_mes = new javax.swing.JLabel();
        tit_anio = new javax.swing.JLabel();
        btn_ver = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        trabajadores = new javax.swing.JSpinner();
        menubar = new javax.swing.JMenuBar();
        elem1 = new javax.swing.JMenu();
        elem1item1 = new javax.swing.JMenuItem();
        elem1item2 = new javax.swing.JMenuItem();
        elem2 = new javax.swing.JMenu();
        elem2item1 = new javax.swing.JMenuItem();
        elem3 = new javax.swing.JMenu();
        elem3item1 = new javax.swing.JMenuItem();
        elem3item2 = new javax.swing.JMenuItem();
        elem3item3 = new javax.swing.JMenuItem();
        elem3item4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tit_mes.setText("Mes:");

        tit_anio.setText("Año:");

        btn_ver.setText("Ver Días Trabajados");
        btn_ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verActionPerformed(evt);
            }
        });

        titulo.setText("Trabajador:");

        trabajadores.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                trabajadoresStateChanged(evt);
            }
        });

        elem1.setText("Inicio");
        elem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elem1ActionPerformed(evt);
            }
        });

        elem1item1.setText("Ir a Inicio");
        elem1item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elem1item1ActionPerformed(evt);
            }
        });
        elem1.add(elem1item1);

        elem1item2.setText("Salir");
        elem1item2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elem1item2ActionPerformed(evt);
            }
        });
        elem1.add(elem1item2);

        menubar.add(elem1);

        elem2.setText("Trabajador");

        elem2item1.setText("Añadir Trabajador");
        elem2item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elem2item1ActionPerformed(evt);
            }
        });
        elem2.add(elem2item1);

        menubar.add(elem2);

        elem3.setText("Otras Opciones");

        elem3item1.setText("Calcular Sueldo");
        elem3item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elem3item1ActionPerformed(evt);
            }
        });
        elem3.add(elem3item1);

        elem3item2.setText("Resumen Salario");
        elem3item2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elem3item2ActionPerformed(evt);
            }
        });
        elem3.add(elem3item2);

        elem3item3.setText("Registrar Trabajado");
        elem3item3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elem3item3ActionPerformed(evt);
            }
        });
        elem3.add(elem3item3);

        elem3item4.setText("Ver Días Trabajados");
        elem3item4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elem3item4ActionPerformed(evt);
            }
        });
        elem3.add(elem3item4);

        menubar.add(elem3);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tit_anio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(anio, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tit_mes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(96, 96, 96))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(trabajadores, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ver, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tit_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tit_anio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trabajadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titulo))
                .addGap(39, 39, 39)
                .addComponent(btn_ver)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void elem2item1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elem2item1ActionPerformed
        if(!"AnadirTrabajador".equals(this.getClass().getSimpleName())) {
            AnadirTrabajador at = new AnadirTrabajador();
            at.setVisible(true);
            this.dispose();   
        }
    }//GEN-LAST:event_elem2item1ActionPerformed

    private void elem3item2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elem3item2ActionPerformed
        if(!"ResumenSalario".equals(this.getClass().getSimpleName())) {
            ResumenSalario rs = new ResumenSalario();
            rs.setVisible(true);
            this.dispose();   
        }
    }//GEN-LAST:event_elem3item2ActionPerformed

    private void elem3item3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elem3item3ActionPerformed
        if(!"RegistrarTrabajado".equals(this.getClass().getSimpleName())) {
            RegistrarTrabajado rt = null;
            try {
                rt = new RegistrarTrabajado();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CalcularSueldo.class.getName()).log(Level.SEVERE, null, ex);
            }
            rt.setVisible(true);
            this.dispose();   
        }
    }//GEN-LAST:event_elem3item3ActionPerformed

    private void elem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elem1ActionPerformed
    }//GEN-LAST:event_elem1ActionPerformed

    private void elem3item1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elem3item1ActionPerformed
        if(!"CalcularSueldo".equals(this.getClass().getSimpleName())) {
            CalcularSueldo cs = new CalcularSueldo();
            cs.setVisible(true);
            this.dispose();   
        }
    }//GEN-LAST:event_elem3item1ActionPerformed

    private void elem1item2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elem1item2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_elem1item2ActionPerformed

    private void elem1item1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elem1item1ActionPerformed
        if(!"Inicio".equals(this.getClass().getSimpleName())) {
            Inicio i = null;
            try {
                i = new Inicio();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CalcularSueldo.class.getName()).log(Level.SEVERE, null, ex);
            }
            i.setVisible(true);
            this.dispose();   
        }
    }//GEN-LAST:event_elem1item1ActionPerformed

    private void btn_verActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verActionPerformed
        String t = (String) trabajadores.getValue();
        if(!t.isEmpty()){
            OperacionesBD o = new OperacionesBD();
            String [] v = t.split(":");
            TablaDias table = null;
            try {
                table = new TablaDias(v[0]);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VerDiasTrabajados.class.getName()).log(Level.SEVERE, null, ex);
            }
            table.setVisible(true);
        }
        else JOptionPane.showMessageDialog(rootPane,"No se ha especificado el trabajador");
    }//GEN-LAST:event_btn_verActionPerformed

    private void trabajadoresStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_trabajadoresStateChanged

    }//GEN-LAST:event_trabajadoresStateChanged

    private void elem3item4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elem3item4ActionPerformed
        if(!"VerDiasTrabajados".equals(this.getClass().getSimpleName())) {
            VerDiasTrabajados vd = null;
            try {
                vd = new VerDiasTrabajados();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AnadirTrabajador.class.getName()).log(Level.SEVERE, null, ex);
            }
            vd.setVisible(true);
            this.dispose();   
        }
    }//GEN-LAST:event_elem3item4ActionPerformed

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Inicio().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CalcularSueldo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser anio;
    private javax.swing.JButton btn_ver;
    private javax.swing.JMenu elem1;
    private javax.swing.JMenuItem elem1item1;
    private javax.swing.JMenuItem elem1item2;
    private javax.swing.JMenu elem2;
    private javax.swing.JMenuItem elem2item1;
    private javax.swing.JMenu elem3;
    private javax.swing.JMenuItem elem3item1;
    private javax.swing.JMenuItem elem3item2;
    private javax.swing.JMenuItem elem3item3;
    private javax.swing.JMenuItem elem3item4;
    private com.toedter.components.JSpinField jSpinField1;
    private javax.swing.JMenuBar menubar;
    private com.toedter.calendar.JMonthChooser mes;
    private javax.swing.JLabel tit_anio;
    private javax.swing.JLabel tit_mes;
    private javax.swing.JLabel titulo;
    private javax.swing.JSpinner trabajadores;
    // End of variables declaration//GEN-END:variables
}