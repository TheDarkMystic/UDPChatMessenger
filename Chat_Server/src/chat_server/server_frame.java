package chat_server;

import java.io.IOException;
import java.net.*;
import java.util.*;

class ClientStruct{
    InetAddress ip;
    int port;
    String name;
}

public class server_frame extends javax.swing.JFrame 
{
    ArrayList<ClientStruct> onlineClients = new ArrayList();
    ArrayList<String> users = new ArrayList();
    Map<String, String> userList = new HashMap<>();  

    public server_frame(){
        initComponents();       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chat_ta = new javax.swing.JTextArea();
        bt_start = new javax.swing.JButton();
        bt_end = new javax.swing.JButton();
        bt_users = new javax.swing.JButton();
        bt_clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Server's frame");
        setName("server"); // NOI18N
        setResizable(false);

        chat_ta.setColumns(20);
        chat_ta.setFont(new java.awt.Font("Calibri Light", 0, 15)); // NOI18N
        chat_ta.setRows(5);
        jScrollPane1.setViewportView(chat_ta);

        bt_start.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_start.setText("Start Server");
        bt_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_startActionPerformed(evt);
            }
        });

        bt_end.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_end.setText("Stop Server");
        bt_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_endActionPerformed(evt);
            }
        });

        bt_users.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_users.setText("Available Users");
        bt_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_usersActionPerformed(evt);
            }
        });

        bt_clear.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        bt_clear.setText("Clear Screen");
        bt_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_end, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_start, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_users, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bt_clear, bt_end, bt_start, bt_users});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_users)
                    .addComponent(bt_start, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_clear)
                    .addComponent(bt_end))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bt_clear, bt_end, bt_start, bt_users});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_endActionPerformed
        try{
            Thread.sleep(5000);                 
        } 
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        } 
        chat_ta.append("Server stopping.... \n");
        chat_ta.setText("");
    }//GEN-LAST:event_bt_endActionPerformed

    private void bt_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_startActionPerformed
        Thread starter = new Thread(new ServerThreadClass());
        starter.start();        
        chat_ta.append("Server Started Successfully!\n");
    }//GEN-LAST:event_bt_startActionPerformed

    private void bt_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_usersActionPerformed
        chat_ta.append("\n Online users : \n");
        for (String current_user : users){
            chat_ta.append(current_user);
            chat_ta.append("\n");
        }    
        
    }//GEN-LAST:event_bt_usersActionPerformed

    private void bt_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_clearActionPerformed
        chat_ta.setText("");
    }//GEN-LAST:event_bt_clearActionPerformed

    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(() -> {
            new server_frame().setVisible(true);
        });
    }
    
    public class ServerThreadClass implements Runnable{
        @Override
        public void run(){
            populateClientList();            
            try{                              
                DatagramSocket ds= new DatagramSocket(9893);
                while (true){   
                    byte b[] = new byte[1024];
                    DatagramPacket dp = new DatagramPacket(b,b.length);
                    ds.receive(dp);                    
                    String recPack = new String(dp.getData(),0, dp.getLength());                    
                    String data[] = recPack.split(":");
                    String operation=data[0].trim();
                    switch (operation) {
                        case "Connect":
                            {
                                String username=data[1].trim();
                                String password=data[2].trim();
                                authenticate(username,password, ds, dp);
                                break;
                            }
                        case "Chat":
                            {
                                String[] stream = recPack.split("\\$");
                                String msgStr=stream[0];
                                String ctrlStr= stream[1];
                                boolean bdCastflag=(ctrlStr.split(":")[0].equals("1"));
                                String[] msgContainer=msgStr.split(":");
                                String username=msgContainer[1].trim();
                                String message=msgContainer[2].trim();
                                if(bdCastflag==true){
                                    tellEveryone(message,ds, username);
                                }
                                else{
                                    String[] userList=ctrlStr.split(":")[1].split("#");
                                    multiCast(message, ds, username, userList);
                                }       break;
                            }
                        case "UPOL":
                            bdCastOlUsers(ds);
                            break;
                        case "Discon":
                            {
                                String userName= data[1];
                                removeUser(userName, ds);
                                bdCastOlUsers(ds);
                                break;
                            }
                        case "Port":
                            {
                                InetAddress clientIP= dp.getAddress();
                                int clientPort=dp.getPort();
                                byte b1[] = ("@"+String.valueOf(clientPort)).getBytes();
                                DatagramPacket dp1= new DatagramPacket(b1, b1.length, clientIP, clientPort);
                                ds.send(dp1);
                                break;
                            }
                        case "Reg":
                        {
                                InetAddress clientIP= dp.getAddress();
                                int clientPort=dp.getPort();
                                String username=data[1].trim();
                                String password=data[2].trim();
                                if(!userList.containsKey(username)){
                                    userList.put(username, password);
                                    //send success
                                    byte b1[] = (String.valueOf(5)).getBytes(); 
                                    DatagramPacket dp1= new DatagramPacket(b1, b1.length, clientIP, clientPort);
                                    ds.send(dp1);
                                    ds.send(dp1);
                                    System.out.println("Success sent");
                                }
                                else{
                                    byte b1[] = (String.valueOf(9)).getBytes(); 
                                    DatagramPacket dp1= new DatagramPacket(b1, b1.length, clientIP, clientPort);
                                    ds.send(dp1);
                                    ds.send(dp1);
                                    System.out.println("failure sent");
                                }
                                
                        }
                        default:
                            break;
                    }
                }
            }
            catch (Exception ex){
                chat_ta.append("Error While Connecting! \n");
            }
        }
    }
    
    public void authenticate(String username, String password, DatagramSocket ds, DatagramPacket dp) throws Exception{       
        InetAddress clientIP= dp.getAddress();
        int clientPort=dp.getPort();
        if(users.contains(username)){
            byte b1[] = String.valueOf(0).getBytes();           
            DatagramPacket  dp1= new DatagramPacket(b1, b1.length, clientIP, clientPort);
            ds.send(dp1);
        }
        else if(userList.containsKey(username) && userList.get(username).equals(password)){
                    byte b1[] = String.valueOf(1).getBytes();                    
                    String clientName=username;
                    DatagramPacket dp1= new DatagramPacket(b1, b1.length, clientIP, clientPort);
                    ds.send(dp1);
                    //add to the clients list
                    ClientStruct cl= new ClientStruct();
                    cl.ip=clientIP;
                    cl.port=clientPort;
                    cl.name=clientName;
                    onlineClients.add(cl);
                    users.add(clientName);            
                    chat_ta.append("New Client Connected! \n");
                    bdCastOlUsers(ds);            
            }
            else{
                    byte b1[] = String.valueOf(2).getBytes();           
                    DatagramPacket  dp1= new DatagramPacket(b1, b1.length, clientIP, clientPort);
                    ds.send(dp1);
            }
    }
    
    public void bdCastOlUsers(DatagramSocket ds){
        StringBuilder sb= new StringBuilder();        
        users.forEach((u) -> {
            if(sb.length()==0)
                sb.append(u);
            else{
                sb.append(":");
                sb.append(u);
            }
        });
        String message=sb.toString();
        onlineClients.forEach((cl) -> {
            try{  
                //# is code for online user broadcast
                byte[] data = ("#:"+message).getBytes();
                InetAddress recIP =cl.ip;
                int recPort = cl.port;                
                DatagramPacket  dp= new DatagramPacket(data, data.length, recIP, recPort );
                ds.send(dp);
                chat_ta.append("Sending: " + message + "\n");
                chat_ta.setCaretPosition(chat_ta.getDocument().getLength());
            } 
            catch (IOException ex){
                chat_ta.append("Error broadcasting the online users list. \n");
            }
        }); 
    }
    
    public void populateClientList(){
        userList.put("u1","abcd");
        userList.put("u2","abcd");
        userList.put("u3","abcd"); 
        userList.put("u4","abcd"); 
        userList.put("u5","abcd"); 
    }  
    
    public void removeUser (String userName,DatagramSocket ds ){ 
        users.remove(userName);
        for(ClientStruct cl: onlineClients){
            if(cl.name.equals(userName)){
                onlineClients.remove(cl);
                break;
            }
        } 
    }
    
    public void tellEveryone(String message, DatagramSocket ds, String ClName){
        onlineClients.forEach((cl) -> {
            try{    //^ is code for chat message
                byte[] data = ("^"+ClName+": "+message+"\n").getBytes();
                InetAddress recIP =cl.ip;
                int recPort = cl.port;
                if(!ClName.equals(cl.name)){
                    DatagramPacket  dp= new DatagramPacket(data, data.length, recIP, recPort );
                    ds.send(dp);
                    chat_ta.append("Sending: " + message + "\n");
                    chat_ta.setCaretPosition(chat_ta.getDocument().getLength());
                }
            }
            catch (IOException ex){
                chat_ta.append("Error While Broadcasting! \n");
            }
        }); 
    }
    
    public void multiCast(String message, DatagramSocket ds, String ClName, String[] userList){
        onlineClients.stream().filter((cl) -> (Arrays.asList(userList).contains(cl.name))).forEachOrdered((cl) -> {
            try{    //^ is code for chat message
                byte[] data = ("^"+ClName+": "+message+"\n").getBytes();
                InetAddress recIP =cl.ip;
                int recPort = cl.port;
                if(!ClName.equals(cl.name)){
                    DatagramPacket  dp= new DatagramPacket(data, data.length, recIP, recPort );
                    ds.send(dp);
                    chat_ta.append("Sending: " + message + "\n");
                    chat_ta.setCaretPosition(chat_ta.getDocument().getLength());
                }
            }
            catch (IOException ex){
                chat_ta.append("Error While Multicast!\n");
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_clear;
    private javax.swing.JButton bt_end;
    private javax.swing.JButton bt_start;
    private javax.swing.JButton bt_users;
    private javax.swing.JTextArea chat_ta;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
