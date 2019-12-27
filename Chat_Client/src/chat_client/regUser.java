
package chat_client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class regUser extends javax.swing.JFrame implements Runnable {

    DatagramSocket ds;
    InetAddress serverIP;
    int port;
    public regUser(DatagramSocket ds, InetAddress serverIP, int port) {
        initComponents();
        this.ds=ds;
        this.serverIP=serverIP;
        this.port= port;
        super.setVisible(true);
    }
    
    @Override
    public void run() {
        //System.out.println("sent"+" "+);
        while(true){
            String stream;
            byte buffer[] = new byte[1024];
            DatagramPacket dp1 = new DatagramPacket(buffer,buffer.length);
            try {
                ds.receive(dp1);
            } catch (IOException ex) {
                Logger.getLogger(regUser.class.getName()).log(Level.SEVERE, null, ex);
            }

            stream= new String(dp1.getData());
            stream=stream.trim();
            System.out.println("rec : "+stream);
            //System.out.println(username+" "+stream);
            if(stream.equals("5")){
                System.out.println("Inside if reggister");
                JOptionPane.showMessageDialog(null,"Registration SuccessFul!");
                super.dispose();  
                break;
            }
            else if(stream.equals("9")){
                JOptionPane.showMessageDialog(null,"Failure : Username already taken!");
                super.dispose();
                break;
            }
            
        }
        //Thread.stop();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tf_login = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tf_pass = new javax.swing.JPasswordField();
        tf_cpass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        bt_reg = new javax.swing.JButton();
        bt_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel1.setText("Username");

        tf_login.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel2.setText("Password");

        tf_pass.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N

        tf_cpass.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel3.setText("Confirm Password");

        bt_reg.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_reg.setText("Register");
        bt_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_regActionPerformed(evt);
            }
        });

        bt_cancel.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_cancel.setText("Cancel");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_reg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_cancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_cpass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bt_cancel, bt_reg, jLabel1, jLabel2, jLabel3, tf_cpass, tf_login, tf_pass});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tf_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_cpass, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_reg)
                    .addComponent(bt_cancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bt_cancel, bt_reg, jLabel1, jLabel2, jLabel3, tf_cpass, tf_login, tf_pass});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_regActionPerformed
        // TODO add your handling code here:
        String username=tf_login.getText();
        String pass=(String)tf_pass.getText();
        String cpass=(String)tf_cpass.getText();
        if(!pass.equals(cpass))
            JOptionPane.showMessageDialog(null,"Password Missmatch!");
        else{
            byte[] data = ( "Reg:" +username+":"+pass).getBytes();
            DatagramPacket  dp= new DatagramPacket(data, data.length, serverIP, port );
            try { 
                ds.send(dp);
            } catch (IOException ex) {
                Logger.getLogger(regUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }//GEN-LAST:event_bt_regActionPerformed

    private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
        super.dispose();
    }//GEN-LAST:event_bt_cancelActionPerformed

    
    public static void main(String args[]) {      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new regUser(ds).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cancel;
    private javax.swing.JButton bt_reg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField tf_cpass;
    private javax.swing.JTextField tf_login;
    private javax.swing.JPasswordField tf_pass;
    // End of variables declaration//GEN-END:variables

    
}
