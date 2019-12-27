package chat_client;

import java.awt.HeadlessException;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class client_frame extends javax.swing.JFrame{
    String username,password, address = "localhost";
    DefaultListModel model= new DefaultListModel();
    ArrayList<String> users = new ArrayList();    
    Boolean isConnected = false;
    DatagramSocket ds;
    InetAddress serverIP;  
    int port = 9893;
    
    public client_frame(){
        try{
            this.ds= new DatagramSocket();  
            serverIP =InetAddress.getByName("localhost");
            port=9893;
        }
        catch(SocketException | UnknownHostException e){}
        initComponents();
    } 
    
    public void ListenThread(){
         Thread ListenerClass = new Thread(new ListenerClass());
         ListenerClass.start();
    }       
        
    public void getPort(){
        try{
            byte data[] = ("Port:"+username).getBytes();         
            DatagramPacket  dp= new DatagramPacket(data, data.length, serverIP, port );
            ds.send(dp);
        } catch (IOException e){
            chat_ta.append("Could not send Disconnect message.\n");
        }
    }
    
    public void sendDisconnect(){
        try{
            byte data[] = ("Discon:"+username).getBytes();
            DatagramPacket  dp= new DatagramPacket(data, data.length, serverIP, port );
            ds.send(dp);            
        } catch (IOException e){
            chat_ta.append("Sending Disconnect message Failed.\n");
        }
    }
    
    public void Disconnect(){
        try{
            chat_ta.append("Disconnected.\n");       
            Thread.sleep(4000);
            System.exit(0);
        } catch(InterruptedException ex) {
            chat_ta.append("Failed to disconnect. \n");
        }
        isConnected = false;
        username_tf.setEditable(true);
    }
    
    public class ListenerClass implements Runnable{
        @Override
        public void run(){            
            String stream;          
            try{
                String ipAddress= String.valueOf(InetAddress.getLocalHost());                
                address_tf.setText(ipAddress);
                getPort();             
                while (true){
                    // receive reply from the server
                    byte buffer[] = new byte[1024];
                    DatagramPacket dp1 = new DatagramPacket(buffer,buffer.length);
                    ds.receive(dp1);
                    stream= new String(dp1.getData(),0, dp1.getLength());
                    stream=stream.trim();
                    char opCode=stream.charAt(0);
                    String message= stream.substring(1);
                     
                    switch (opCode) {
                        case '^'://code for chat message
                            chat_ta.append(message + "\n");
                            chat_ta.setCaretPosition(chat_ta.getDocument().getLength());
                            break;
                        case '#'://code for update online users list
                            model.removeAllElements();
                            jlist.setModel(model);
                            for(String user: message.split(":")){
                                model.addElement(user);
                            }  
                            break;
                        case '@'://code for getting port number of current client
                            port_tf.setText(message);
                            break;                        
                        default:
                            break;
                    }
                }
           }catch(IOException ex) { }
        }
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        lbl_address = new javax.swing.JLabel();
        address_tf = new javax.swing.JTextField();
        lbl_port = new javax.swing.JLabel();
        port_tf = new javax.swing.JTextField();
        lbl_username = new javax.swing.JLabel();
        username_tf = new javax.swing.JTextField();
        lbl_password = new javax.swing.JLabel();
        bt_connect = new javax.swing.JButton();
        bt_discon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat_ta = new javax.swing.JTextArea();
        chat_tf = new javax.swing.JTextField();
        bt_send = new javax.swing.JButton();
        rd_bdcast = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlist = new javax.swing.JList<>();
        bt_olUserButton = new javax.swing.JButton();
        bt_reg = new javax.swing.JButton();
        password_tf = new javax.swing.JPasswordField();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        lbl_address.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        lbl_address.setText("IP Address :");

        address_tf.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N

        lbl_port.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_port.setText("Port  No :");

        port_tf.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N

        lbl_username.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        lbl_username.setText("Login ID :");

        username_tf.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N

        lbl_password.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        lbl_password.setText("Password : ");

        bt_connect.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_connect.setText("Connect");
        bt_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_connectActionPerformed(evt);
            }
        });

        bt_discon.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_discon.setText("Disconnect");
        bt_discon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_disconActionPerformed(evt);
            }
        });

        chat_ta.setColumns(20);
        chat_ta.setFont(new java.awt.Font("Calibri Light", 1, 13)); // NOI18N
        chat_ta.setRows(5);
        jScrollPane1.setViewportView(chat_ta);

        chat_tf.setFont(new java.awt.Font("Calibri Light", 1, 13)); // NOI18N

        bt_send.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_send.setText("SEND");
        bt_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sendActionPerformed(evt);
            }
        });

        rd_bdcast.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        rd_bdcast.setText("To All");

        jlist.setFont(new java.awt.Font("Calibri Light", 1, 13)); // NOI18N
        jlist.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(jlist);

        bt_olUserButton.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_olUserButton.setText("Online Users");
        bt_olUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_olUserButtonActionPerformed(evt);
            }
        });

        bt_reg.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_reg.setText("New User");
        bt_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_regActionPerformed(evt);
            }
        });

        password_tf.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chat_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bt_olUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rd_bdcast))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_username, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(lbl_address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(username_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_password)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(password_tf)
                                .addGap(18, 18, 18)
                                .addComponent(bt_connect))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(address_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_port, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(port_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_discon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_reg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(address_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_port)
                        .addComponent(port_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_reg))
                    .addComponent(lbl_address))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_username, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_password)
                    .addComponent(bt_connect)
                    .addComponent(bt_discon)
                    .addComponent(password_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_olUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rd_bdcast))
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_send, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(chat_tf))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bt_connect, bt_discon, bt_reg, password_tf, username_tf});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {address_tf, lbl_address, lbl_password, lbl_port, lbl_username, port_tf});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_connectActionPerformed
        if (isConnected == false){
            username = username_tf.getText();
            username_tf.setEditable(false);
            password=password_tf.getText();
            password_tf.setEditable(false);
            byte[] data = ("Connect:"+username+":"+password).getBytes();
            try{
                isConnected = true; 
                DatagramPacket  dp= new DatagramPacket(data, data.length, serverIP, port );
                ds.send(dp);               

                byte buffer[] = new byte[1024];
                DatagramPacket dp1 = new DatagramPacket(buffer,buffer.length);
                ds.receive(dp1);
                String auth = new String(dp1.getData(),0, dp1.getLength());
                auth=auth.trim();
                
                if(auth.equals(String.valueOf(0))){
                    JOptionPane.showMessageDialog(null,"This user is already connected!");
                    System.exit(0);
                }
                else if(auth.equals(String.valueOf(1))){
                        JOptionPane.showMessageDialog(null,"Login SuccessFul!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Login Failed!");
                        username_tf.setEditable(true);
                        password_tf.setEditable(true);
                        isConnected=false;
                        bt_connect.setEnabled(true);
                        System.exit(0);
                    }
            } 
            catch (HeadlessException | IOException ex){
                chat_ta.append("Exception Occured: Cannot Connect! \n");
                username_tf.setEditable(true);
            }            
            ListenThread();            
        } else if (isConnected == true){
            chat_ta.append("You are connected to the Server. \n");
        }
    }//GEN-LAST:event_bt_connectActionPerformed

    private void bt_disconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_disconActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_bt_disconActionPerformed

    private void bt_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sendActionPerformed
        List<String> selUser = jlist.getSelectedValuesList();
        String bdcFlag= (rd_bdcast.isSelected()?"1":"0");
        if(selUser.isEmpty() && !rd_bdcast.isSelected()){
            JOptionPane.showMessageDialog(null,"No Reciever Selected!");
        }
        else{
            StringBuilder userList= new StringBuilder();
            selUser.forEach((str) -> {
                if(userList.length()==0)
                    userList.append(str);
                else{
                    userList.append("#");
                    userList.append(str);
                }
            });
            String uList=userList.toString();
            String nothing = "";
            if ((chat_tf.getText()).equals(nothing)) {
                chat_tf.setText("");
                chat_tf.requestFocus();
            } 
            else {
                    try {   
                            bdcFlag= (rd_bdcast.isSelected()?"1":"0");
                            byte[] data = ( "Chat:" +username + ":" + chat_tf.getText()+"$"+bdcFlag+":"+uList).getBytes();
                            DatagramPacket  dp= new DatagramPacket(data, data.length, serverIP, port );
                            ds.send(dp);
                            chat_ta.append(username + ":" + chat_tf.getText()+"\n");
                    } catch (IOException ex) {
                            chat_ta.append("Message was not sent. \n");
                    }
                    chat_tf.setText("");
                    chat_tf.requestFocus();
                }
                chat_tf.setText("");
                chat_tf.requestFocus();
        }   
    }//GEN-LAST:event_bt_sendActionPerformed

    private void bt_olUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_olUserButtonActionPerformed
        try{
            byte[] data = ( "UPOL:" +username).getBytes();
            DatagramPacket  dp= new DatagramPacket(data, data.length, serverIP, port );
            ds.send(dp);            
        }
        catch(IOException e){
            System.out.println("Exception occured while updating online users list!");
        }
    }//GEN-LAST:event_bt_olUserButtonActionPerformed

    private void bt_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_regActionPerformed
        regUser reg= new regUser(ds, serverIP, port);
        Thread newreg= new Thread(reg);
        newreg.start();
    }//GEN-LAST:event_bt_regActionPerformed
  
    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(() -> {
            new client_frame().setVisible(true);
        });    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address_tf;
    private javax.swing.JButton bt_connect;
    private javax.swing.JButton bt_discon;
    private javax.swing.JButton bt_olUserButton;
    private javax.swing.JButton bt_reg;
    private javax.swing.JButton bt_send;
    private javax.swing.JTextArea chat_ta;
    private javax.swing.JTextField chat_tf;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> jlist;
    private javax.swing.JLabel lbl_address;
    private javax.swing.JLabel lbl_password;
    private javax.swing.JLabel lbl_port;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JPasswordField password_tf;
    private javax.swing.JTextField port_tf;
    private javax.swing.JRadioButton rd_bdcast;
    private javax.swing.JTextField username_tf;
    // End of variables declaration//GEN-END:variables
}
