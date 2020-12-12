package formularios;

import controlador.CarteiraDAO;
import controlador.CategoriaDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Carteira;
import model.Categoria;
import model.Usuario;

public class FormAddSaldo extends javax.swing.JFrame {

    private Usuario u;
    private FormPrincipal fp;

    public FormAddSaldo() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public FormAddSaldo(Usuario u, FormPrincipal fp) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.fp = fp;
        this.u = u;

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                fechaForm();
            }
        });
        preencheComboCategoria();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_categoria = new javax.swing.JLabel();
        lbl_valor = new javax.swing.JLabel();
        lbl_comentario = new javax.swing.JLabel();
        txf_comentario = new javax.swing.JTextField();
        txf_valor = new javax.swing.JTextField();
        btn_salvar = new javax.swing.JButton();
        cmb_categoria = new javax.swing.JComboBox();
        btn_addCategoria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbl_categoria.setText("Categoria:");

        lbl_valor.setText("Valor:");

        lbl_comentario.setText("Comentario:");

        btn_salvar.setText("Salvar");
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        cmb_categoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_addCategoria.setText("Add Categoria");
        btn_addCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_salvar)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_comentario)
                            .addComponent(lbl_valor)
                            .addComponent(lbl_categoria))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_addCategoria))
                            .addComponent(txf_comentario, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txf_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_categoria)
                    .addComponent(cmb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addCategoria))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_comentario)
                    .addComponent(txf_comentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_valor)
                    .addComponent(txf_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_salvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addCategoriaActionPerformed
        String nome = JOptionPane.showInputDialog("Nome da Categoria:");

        if (nome.isEmpty() == false) {
            CategoriaDAO cDAO = new CategoriaDAO();
            Categoria c = new Categoria((cDAO.UltimoId() + 1), nome);
            if (cDAO.inserirCategoria(c) == 1) {
                JOptionPane.showMessageDialog(null, "Categoria Inserida com sucesso.");
                preencheComboCategoria();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir categoria.");
            }
        }
    }//GEN-LAST:event_btn_addCategoriaActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        int id_categoria = cmb_categoria.getSelectedIndex();
        String comentario = txf_comentario.getText();
        double valor = Double.parseDouble(txf_valor.getText());

        if (valor > 0 && comentario.isEmpty() == false) {
            CarteiraDAO cDAO = new CarteiraDAO();
            Carteira c = new Carteira((cDAO.UltimoId() + 1), u.getId(), (id_categoria+1), valor, comentario);
            if (cDAO.inserirDinheiro(c) == 1) {
                JOptionPane.showMessageDialog(null, "Dinheiro Inserido com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir dinheiro.");
            }
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

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
            java.util.logging.Logger.getLogger(FormAddSaldo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAddSaldo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAddSaldo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAddSaldo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAddSaldo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addCategoria;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JComboBox cmb_categoria;
    private javax.swing.JLabel lbl_categoria;
    private javax.swing.JLabel lbl_comentario;
    private javax.swing.JLabel lbl_valor;
    private javax.swing.JTextField txf_comentario;
    private javax.swing.JTextField txf_valor;
    // End of variables declaration//GEN-END:variables

    public void preencheComboCategoria() {
        ArrayList<Categoria> c = new CategoriaDAO().consultarTodasCategorias();
        cmb_categoria.removeAllItems();
        if (c.size() > 0) {

            for (int i = 0; i < c.size(); i++) {
                cmb_categoria.addItem(c.get(i).getNome());
            }
        }
    }

    public void fechaForm() {
        this.fp.atualizaSaldo();
        this.fp.setVisible(true);
        this.dispose();
    }

}
