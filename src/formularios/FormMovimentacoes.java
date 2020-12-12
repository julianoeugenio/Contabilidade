package formularios;

import controlador.CarteiraDAO;
import controlador.CategoriaDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Carteira;
import model.Usuario;

public class FormMovimentacoes extends javax.swing.JFrame {

    private Usuario u;
    private FormPrincipal fp;

    public FormMovimentacoes() {
        initComponents();
        configuraTabela();
        this.setLocationRelativeTo(null);
    }

    public FormMovimentacoes(Usuario u, FormPrincipal fp) {
        initComponents();
        this.u = u;
        this.fp = fp;
        preencheTabela();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                fechaForm();
            }
        });
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        btn_deleta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabela);

        btn_deleta.setText("Deletar");
        btn_deleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(btn_deleta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(btn_deleta)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_deletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletaActionPerformed
        int linha = tabela.getSelectedRow();//pegando linha selecionada
        if (linha >= 0) {
            int id = Integer.parseInt(tabela.getValueAt(linha, 0).toString());

            if (new CarteiraDAO().removerCarteira(id) == 1) {
                preencheTabela();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover registro.");
            }
        }
    }//GEN-LAST:event_btn_deletaActionPerformed

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
            java.util.logging.Logger.getLogger(FormMovimentacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMovimentacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMovimentacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMovimentacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMovimentacoes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_deleta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables

    public void configuraTabela() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("Categoria");
        model.addColumn("Comentario");
        model.addColumn("Valor");

        tabela.setModel(model);
    }

    public void preencheTabela() {
        ArrayList res = null;
        res = new CarteiraDAO().consultarCarteira(this.u.getId());

        if (res.size() > 0) {
            configuraTabela();
            DefaultTableModel m = (DefaultTableModel) tabela.getModel();

            for (int i = 0; i < res.size(); i++) {
                Carteira c = (Carteira) res.get(i);
                m.addRow(new Object[]{c.getId(), new CategoriaDAO().consultarCategoria(c.getId_categaria()), c.getComentario(), c.getValor()});
            }
            tabela.setModel(m);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum Registro Encontrado.");
        }
    }

    public void fechaForm() {
        this.fp.atualizaSaldo();
        this.fp.setVisible(true);
        this.dispose();
    }

}
