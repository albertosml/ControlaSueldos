
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author albertosml
 */
public class TablaResultado extends javax.swing.JFrame {
    
    /**
     * Creates new form Inicio
     * @param inicio
     * @param fin
     */
    public TablaResultado(int inicio, int fin) throws ClassNotFoundException  {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Resumen Salario  -  ControlaSueldos");
        
        titulo.setText("CAMPAÑA " + inicio + " - " + fin);
        
        OperacionesBD o = new OperacionesBD();
        ArrayList<Vector<String>> a = o.fillTable(inicio, fin);
        
        if(a.get(0).get(0) != "error") {
           DefaultTableModel tabla = (DefaultTableModel) table.getModel();
           for(int i=0;i<(a.size()-1);i++) tabla.addRow(a.get(i));
           total.setText("Total acumulado: " + a.get(a.size()-1).get(0));
        }
        else JOptionPane.showMessageDialog(rootPane,"No está conectado a la base de datos");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        titulo = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        btn_imprimir_pantalla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trabajador", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto"
            }
        ));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(600);
            table.getColumnModel().getColumn(1).setPreferredWidth(250);
            table.getColumnModel().getColumn(2).setPreferredWidth(250);
            table.getColumnModel().getColumn(3).setPreferredWidth(250);
            table.getColumnModel().getColumn(4).setPreferredWidth(250);
            table.getColumnModel().getColumn(5).setPreferredWidth(250);
            table.getColumnModel().getColumn(6).setPreferredWidth(250);
            table.getColumnModel().getColumn(7).setPreferredWidth(250);
            table.getColumnModel().getColumn(8).setPreferredWidth(250);
            table.getColumnModel().getColumn(9).setPreferredWidth(250);
            table.getColumnModel().getColumn(10).setPreferredWidth(250);
            table.getColumnModel().getColumn(11).setPreferredWidth(250);
            table.getColumnModel().getColumn(12).setPreferredWidth(250);
        }

        titulo.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 135, 214));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        total.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        btn_imprimir_pantalla.setText("Imprimir pantalla");
        btn_imprimir_pantalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimir_pantallaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 502, Short.MAX_VALUE)
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(211, 211, 211)
                        .addComponent(btn_imprimir_pantalla, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(453, 453, 453))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btn_imprimir_pantalla, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    }//GEN-LAST:event_formWindowClosing

    private void btn_imprimir_pantallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimir_pantallaActionPerformed
        JFileChooser elector = new JFileChooser();
        elector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int r = elector.showOpenDialog(null);
        boolean capturar = false;
        if(r == JFileChooser.APPROVE_OPTION) capturar = true;
        
        if(capturar) {
            // Duermo hebra para que el elector se cierre y no estorbe en la captura
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TablaResultado.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Captura cap = new Captura();
            try {
                cap.capturarPantalla(elector.getSelectedFile().getAbsolutePath(),
                        this.getLocation().x,this.getLocation().y,this.getWidth(),
                        this.getHeight());
                JOptionPane.showMessageDialog(rootPane,"Captura realizada con éxito.");
            } catch (IOException ex) {
                Logger.getLogger(TablaResultado.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane,"Problema al realizar la captura");
            }
        }
    }//GEN-LAST:event_btn_imprimir_pantallaActionPerformed

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
                    Logger.getLogger(TablaResultado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_imprimir_pantalla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
