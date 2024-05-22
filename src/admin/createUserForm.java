/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import config.dbConnector;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static testappseiji.regForm.email;
import static testappseiji.regForm.usname;

/**
 *
 * @author Andrade Family
 */
public class createUserForm extends javax.swing.JFrame {

    /**
     * Creates new form createUserForm
     */
    public createUserForm() {
        initComponents();
    }
    
    public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;
    
    public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/userimages", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }
    
    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }
    
    public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
    ImageIcon MyImage = null;
        if(ImagePath !=null){
            MyImage = new ImageIcon(ImagePath);
        }else{
            MyImage = new ImageIcon(pic);
        }
        
    int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

    Image img = MyImage.getImage();
    Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(newImg);
    return image;
    }   
    
     public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }
    
     public boolean duplicateCheck(){ 
        dbConnector dbc = new dbConnector();
        
        try{
         String query = "SELECT * FROM tbl_user  WHERE u_username = '" + un.getText()+ "' OR u_email = '" + em.getText() + "'";
         ResultSet resultSet = dbc.getData(query);
         
         if(resultSet.next()){
             email = resultSet.getString("u_email");
             if(email.equals(em.getText())){
             JOptionPane.showMessageDialog(null,"Email is Already Used!");
             em.setText("");
             }
             usname = resultSet.getString("u_username");
             if(usname.equals(un.getText())){
             JOptionPane.showMessageDialog(null,"Username is Already Used!");
             un.setText("");
             }
             return true;
         }else{
             return false;
         }
        }catch(SQLException ex){
        System.out.println(""+ex);
        return false;
        }
        
    }
     
     public boolean updateCheck(){
        
        dbConnector dbc = new dbConnector();
        
        try{
         String query = "SELECT * FROM tbl_user  WHERE (u_username = '" + un.getText()+ "' OR u_email = '" + em.getText() + "')AND u_id != '"+uid.getText()+"'";
         ResultSet resultSet = dbc.getData(query);
         
         if(resultSet.next()){
             email = resultSet.getString("u_email");
             if(email.equals(em.getText())){
             JOptionPane.showMessageDialog(null,"Email is Already Used!");
             em.setText("");
             }
             usname = resultSet.getString("u_username");
             if(usname.equals(un.getText())){
             JOptionPane.showMessageDialog(null,"Username is Already Used!");
             un.setText("");
             }
             return true;
         }else{
             return false;
         }
        }catch(SQLException ex){
        System.out.println(""+ex);
        return false;
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

        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        em = new javax.swing.JTextField();
        un = new javax.swing.JTextField();
        ps = new javax.swing.JTextField();
        ut = new javax.swing.JComboBox<>();
        add = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        us = new javax.swing.JComboBox<>();
        update = new javax.swing.JButton();
        del = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        uid = new javax.swing.JTextField();
        select = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        jButton6.setText("jButton6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Account Type:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 270, 80, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("User ID:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 110, 80, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Email:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 230, 80, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Username:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 150, 80, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Password:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, 190, 80, 20);
        jPanel1.add(em);
        em.setBounds(120, 220, 230, 30);
        jPanel1.add(un);
        un.setBounds(120, 140, 230, 30);
        jPanel1.add(ps);
        ps.setBounds(120, 180, 230, 30);

        ut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        jPanel1.add(ut);
        ut.setBounds(120, 260, 230, 30);

        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel1.add(add);
        add.setBounds(40, 40, 70, 23);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("User Status:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, 310, 80, 14);

        us.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending" }));
        jPanel1.add(us);
        us.setBounds(120, 300, 230, 30);

        update.setText("Update");
        update.setEnabled(false);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel1.add(update);
        update.setBounds(120, 40, 70, 23);

        del.setText("Delete");
        del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delActionPerformed(evt);
            }
        });
        jPanel1.add(del);
        del.setBounds(200, 40, 70, 23);

        clear.setText("Clear");
        jPanel1.add(clear);
        clear.setBounds(280, 40, 70, 23);

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel1.add(cancel);
        cancel.setBounds(360, 40, 70, 23);

        refresh.setText("Refresh");
        jPanel1.add(refresh);
        refresh.setBounds(440, 40, 70, 23);

        uid.setEnabled(false);
        jPanel1.add(uid);
        uid.setBounds(120, 100, 230, 30);

        select.setText("SELECT");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel1.add(select);
        select.setBounds(370, 370, 100, 30);

        remove.setText("REMOVE");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel1.add(remove);
        remove.setBounds(550, 370, 100, 30);

        jPanel4.setLayout(null);
        jPanel4.add(image);
        image.setBounds(10, 10, 260, 250);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(370, 90, 280, 270);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 680, 430));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

        if( em.getText().isEmpty()||un.getText().isEmpty()|| ps.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"All fields are required!");
        }else if(ps.getText().length()<8){
            JOptionPane.showMessageDialog(null,"Password character should be 8 above!");
            ps.setText("");
        }else if(duplicateCheck()){
            System.out.println("Duplicate Exist");
        }else{
            dbConnector dbc = new dbConnector();
            if( dbc.insertData ("INSERT INTO tbl_user(u_email, u_username, u_password, u_type, u_status, u_image)"
                + "VALUES('"+em.getText()+"','"+un.getText()+"','"+ps.getText()+"','"+ut.getSelectedItem()+"','"+us.getSelectedItem()+"','"+destination+"')"))
        {
            try{
            Files.copy(selectedFile.toPath(),new File(destination).toPath(),StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(null,"Registration Success!");
            usersForm uf = new usersForm();
            uf.setVisible(true);
            this.dispose();
            
            }catch(IOException ex){
                System.out.println("Insert Image Error: "+ex);
            }
            
        }else{
            JOptionPane.showMessageDialog(null,"Connection Error!");
        }

        }
    }//GEN-LAST:event_addActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
      adminForm af = new adminForm();
      af.setVisible(true);
      this.dispose();     
    }//GEN-LAST:event_cancelActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
     
        if (em.getText().isEmpty()||un.getText().isEmpty()|| ps.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"All fields are required!");
        }else if(ps.getText().length()<8){
            JOptionPane.showMessageDialog(null,"Password character should be 8 above!");
            ps.setText("");
        }else if(updateCheck()){
            System.out.println("Duplicate Exist");
        }else{
        dbConnector dbc = new dbConnector();
        dbc.updateData("UPDATE tbl_user SET u_fname = u_email = '"+em.getText()+"',u_username = '"+un.getText()+"',u_password = '"+ps.getText()+"',u_type = '"+ut.getSelectedItem()+"',u_status ='"+us.getSelectedItem()+"',u_image = '"+destination+"'WHERE u_id ='"+uid.getText()+"'");
  
        
        if(destination.isEmpty()){
            File existionFile = new File(oldpath);
            if(existingFile.exists()){
               existingFile.delete();
            }
        }else{
            if(!(oldpath.equals(path))) {
                imageUpdater(oldpath,path);
            }
        }
            
        usersForm uf = new usersForm();
        uf.setVisible(true);
        this.dispose();
        }

    }//GEN-LAST:event_updateActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
    remove.setEnabled(false);
    select.setEnabled(true);
    image.setIcon(null);
    destination ="";
    path ="";
        
        
    }//GEN-LAST:event_removeActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
    JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        selectedFile = fileChooser.getSelectedFile();
                        destination = "src/images/" + selectedFile.getName();
                        path  = selectedFile.getAbsolutePath();
                        
                        
                        if(FileExistenceChecker(path) == 1){
                          JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                            destination = "";
                            path="";
                        }else{
                            image.setIcon(ResizeImage(path, null, image));
                            select.setEnabled(false);
                            remove.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        System.out.println("File Error!");
                    }
                }
        
    }//GEN-LAST:event_selectActionPerformed

    private void delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delActionPerformed

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
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createUserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createUserForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton add;
    private javax.swing.JButton cancel;
    private javax.swing.JButton clear;
    private javax.swing.JButton del;
    public javax.swing.JTextField em;
    public javax.swing.JLabel image;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JTextField ps;
    private javax.swing.JButton refresh;
    public javax.swing.JButton remove;
    public javax.swing.JButton select;
    public javax.swing.JTextField uid;
    public javax.swing.JTextField un;
    public javax.swing.JButton update;
    public javax.swing.JComboBox<String> us;
    public javax.swing.JComboBox<String> ut;
    // End of variables declaration//GEN-END:variables

    private static class existingFile {

        private static void delete() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static boolean exists() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public existingFile() {
        }
    }
}
