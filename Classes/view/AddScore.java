/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.UserController;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.DAO.AnimalDAO;
import model.DAO.ImageDAO;
import model.DAO.ScoreDAO;
import model.DAO.UserDAO;
import model.business.Animal;
import model.business.Image;
import model.business.Score;
import model.business.User;

/**
 *
 * @author Alexandre
 */
public class AddScore extends javax.swing.JFrame {

    /**
     * Creates new form DarNota
     */
    public AddScore() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        
        User atual = UserController.getInstance().getUser();
        Score score = new Score();
        Animal animal = new Animal();
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Score> scoresByUser = new ArrayList<>();
        
        ImageDAO imageDAO = new ImageDAO();
        UserDAO userDAO = new UserDAO();
        ScoreDAO scoreDAO = new ScoreDAO();
        AnimalDAO animalDAO = new AnimalDAO();
        
        
        
        String name = atual.getName();
        
        while( name == null ){
            name = JOptionPane.showInputDialog(null, "Insira o seu nome: ", "Primeiro Acesso", 0);
            
            if( name != null ){
                try {
                    atual.setName(name);
                    userDAO.update(atual);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
            }
        }
        
        try {
            animals = animalDAO.retrieveAll();
            scoresByUser = scoreDAO.retrieveByUser(atual.getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        for(int i = 0; i < scoresByUser.size(); i++){
            if( animals.contains(scoresByUser.get(i).getAnimal()) ){
                animals.remove(scoresByUser.get(i).getAnimal());
            }
        }
        
        if( animals.size() > 0 ){
            animal = animals.get(0);
            
            idAnimal.setText("" + animal.getId());
            imageTest.setIcon(getImage(animal.getId()));
        } else {
            JOptionPane.showMessageDialog(null, "Você já deu nota em todos os animais.", "Nenhum animal disponível", 0);
        }
        
        //jScrollPane1.updateUI();   
    }
    
    public ImageIcon getImage(int id){
        
        ImageDAO imageDAO = new ImageDAO();
        Image imageObj = new Image();
        BufferedImage image = null;
        
        try {
            imageObj = imageDAO.retrieveByAnimal(id);
        } catch (SQLException ex) {
            Logger.getLogger(AddScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            image = ImageDAO.convertBlobToImage(imageObj.getImage());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        ImageIcon icon = new ImageIcon(image);
        
        return icon;
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        imageTest = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        spinnerNota = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idAnimal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(500, 500));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(600, 600));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        imageTest.setText("<imagem>");
        jPanel2.add(imageTest, new java.awt.GridBagConstraints());

        jScrollPane1.setViewportView(jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        btnSair.setText("Sair");
        btnSair.setPreferredSize(new java.awt.Dimension(150, 30));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnSair, gridBagConstraints);

        btnSalvar.setText("Salvar");
        btnSalvar.setPreferredSize(new java.awt.Dimension(150, 30));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(btnSalvar, gridBagConstraints);

        spinnerNota.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 5.0d, 0.25d));
        spinnerNota.setMinimumSize(new java.awt.Dimension(50, 30));
        spinnerNota.setPreferredSize(new java.awt.Dimension(80, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(spinnerNota, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nota:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel1.setText("Código do animal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel1, gridBagConstraints);

        idAnimal.setText("<codigo>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(idAnimal, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        
        int option = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmar logout", JOptionPane.YES_NO_OPTION);
        
        if( option == 0 ){
            
            UserController.getInstance().logout();

            new Login().setVisible(true);
            this.setVisible(false);
            this.dispose();
            
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        
        AnimalDAO animalDAO = new AnimalDAO();
        ScoreDAO scoreDAO = new ScoreDAO();
        
        User user = UserController.getInstance().getUser();
        Double value = (Double) spinnerNota.getValue();
        
        Score score = new Score();
        Animal animal = new Animal();
        
        try {
            animal = animalDAO.retrieveById(Integer.parseInt(idAnimal.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(AddScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(animal != null){
            score = new Score(animal, value, user);
        }
        
        if(score != null){
            try {
                scoreDAO.insert(score);
            } catch (SQLException ex) {
                Logger.getLogger(AddScore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        new AddScore().setVisible(true);
        this.setVisible(false);
        this.dispose();
        

    }//GEN-LAST:event_btnSalvarActionPerformed

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
            java.util.logging.Logger.getLogger(AddScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddScore.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddScore().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel idAnimal;
    private javax.swing.JLabel imageTest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spinnerNota;
    // End of variables declaration//GEN-END:variables
}
