/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclientserverchattingapplication;

import DatabaseConnection.DataBaseConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaclientserverchattingapplication.communication.HandleClients;
import javax.swing.JOptionPane;
import myclasses.ContentType;
import myclasses.Message;
import myclasses.ReceiverType;

/**
 *
 * @author TOSHIBA
 */
public class JavaClientServerChattingApplication {

    // a 
    private ServerSocket server;
    private static final String HOST = "192.168.43.251";
    private static final int PORT = 1024;
    private Hashtable<String, Socket> clients;
    // output stream for client
    ObjectOutputStream writer;
    //input stream 
    ObjectInputStream reader;
    // Class Constructor
    public JavaClientServerChattingApplication() throws IOException {
        clients = new Hashtable<String, Socket>();
        try {
            server = new ServerSocket(PORT);
            new DataBaseConfig().update("UPDATE USERS SET IP_ADDRESS=''");//insert new client to database
           waiting.start();
            // reading.start();
           // sending.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "تعذر إنشاء سرفر " + ex.getMessage(), "خطأ", JOptionPane.ERROR_MESSAGE);

        }
    }
    /**
     * Main Function
     * @param args the command line arguments
     */
 
    public static void main(String[] args) throws UnknownHostException, IOException {
        JavaClientServerChattingApplication server = new JavaClientServerChattingApplication();

    }
    
    // thread variable that will wait for the client connection
    private Thread waiting = new Thread() {
     DatabaseConnection.DataBaseConfig db=new DataBaseConfig();
        @Override
        public void run() {

            try {
                while (true) {
                    System.out.println("waiting to connections");
                    Socket client = server.accept();
                    System.out.println(client.getInetAddress().getHostName() + " : accepted");
                    //clients.put(client.getInetAddress().getHostName(),client);
                    reading r= new reading(client);
                    r.start();
                   new sendingOnlineUsers(client).start();
                }
            } catch (IOException ex) {
                System.out.println("hh : " + ex.getMessage());
                //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
    // thread for check destination of message and routing it
    private class sending extends Thread{
        DatabaseConnection.DataBaseConfig db=new DataBaseConfig();
        Message msg;
        Socket Sender;
        Socket receiver;
        String userName;
        ResultSet rs;
        public sending(Message msg,Socket s){
           this.msg=msg;
           this.Sender=s;
           }
        public void run() {
        switch(msg.getReceiverType()){
            case SERVER:
               new serverMessageProcessing(msg,Sender).start();
            break;
            //>>>>>>>>>>>>>>>>>>>>>>>>>>.
            case PERSONAL:
            try {
                System.out.println("message has received from "+msg.getSender()+" to "+msg.getReceiver()+" with : "+msg.getContent());
                System.out.println(clients.size());
                System.out.println(msg.getReceiver());
                 receiver=clients.get(msg.getReceiver());
                 writer=new ObjectOutputStream(receiver.getOutputStream());
                 writer.writeObject(msg);
               } catch (Exception ex) {
                   System.out.println("kkkkk");
             //   Logger.getLogger(JavaClientServerChattingApplication.class.getName()).log(Level.SEVERE, null, ex);
            } break;
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>
            case GROUP:
                
                
            break;
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        
        }
        
        }
    
    }
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    class serverMessageProcessing extends Thread{
      Message msg; 
      Socket Sender;
     public serverMessageProcessing(Message m,Socket s){
     msg=m;
     this.Sender=s;
     }

     @Override
        public void run() {
            DataBaseConfig db=new DataBaseConfig();
            ResultSet rs;
            Message m;
            switch(msg.getContentType()){
       case USERNAME:
         try {
            if(msg.getContent().equals("0")){ 
            //if client connect fro first time,the first message will be its id and  its id will be 0 
            String userName=generateRandomUserName();
             db.update("INSERT INTO USERS(USERNAME,IP_ADDRESS) VALUES('"+userName+"','"+Sender.getInetAddress().getHostAddress()+"')");//insert new client to database
            }
            db.update("UPDATE USERS SET IP_ADDRESS='"+Sender.getInetAddress().getHostAddress()+"' WHERE USER_ID="+msg.getContent());
            rs=db.execute("SELECT * FROM USERS WHERE IP_ADDRESS='"+Sender.getInetAddress().getHostAddress()+"'");//get new user id from database
            if(rs.next()) 
                clients.put(rs.getString("USER_ID"),Sender);//insert new user to connected clients list
                
            m=new Message("Server",rs.getString("USER_ID"),rs.getString("USER_ID")+","+rs.getString("USERNAME"),ContentType.USERNAME,ReceiverType.SERVER);
            writer=new ObjectOutputStream(Sender.getOutputStream());
            writer.writeObject(m);
            } catch (Exception ex) {
                System.out.println(ex.getMessage()+"nnnnn");
            } break;
            //..............................
        case GROUP_NAME:// when user make new group and notify all users to show group name
                db.update("INSERT INTO GROUPS(GROUP_NAME) VALUES('"+msg.getContent()+"')");
                m=new Message("Server","*",msg.getContent(), ContentType.GROUP_NAME, ReceiverType.GLOBAL);
                for(String id:clients.keySet())
                try {
                    writer=new ObjectOutputStream(clients.get(id).getOutputStream());
                    writer.writeObject(m);
                } catch (IOException ex) {
                    System.out.println("javaclientserverchattingapplication.JavaClientServerChattingApplication.serverMessageProcessing.run()");
                 //   Logger.getLogger(JavaClientServerChattingApplication.class.getName()).log(Level.SEVERE, null, ex);
                }break;

            
            }
        }

   }
    /**
     * Class that will be used for reading data from clients
     */
    class reading extends Thread {
        Socket me;//this is the client which thread will read messages coming from it
       
    public reading(Socket s) {
            me = s;
            
         }

        @Override
        public void run() {
            while (true) {
               
                try {
                   reader= new ObjectInputStream(me.getInputStream());
                   Message msg=(Message) reader.readObject();
                   new sending(msg,me).start();
                  }
                 catch (Exception ex) {
                     System.out.println("before : "+clients.size()+" "+me.getInetAddress().getHostAddress());
                    String removedID="";
                    for(String id:clients.keySet()){
                    if(me==clients.get(id)){
                       removedID=id;break; 
                    }
                    }
                    clients.remove(removedID);
                     System.out.println(ex.getMessage()+" after "+clients.size());
                     
                     break;
                 //  Logger.getLogger(JavaClientServerChattingApplication.class.getName()).log(Level.SEVERE, null, ex);
            
                }
           }
        }
    }
    /**
     * Class that will be used for sending online clients
     */
    class sendingOnlineUsers extends Thread {
        DataBaseConfig db=new DataBaseConfig();
        Socket receiver;
        Message msg;

        public sendingOnlineUsers(Socket s) {
            receiver = s;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    sleep(1000);//delay fro 1 second to refrersh every second
                    String onlineClients="";
                    for(String key:clients.keySet()){
                        ResultSet rs=db.execute("SELECT * FROM USERS WHERE USER_ID='"+key+"'");
                       if(rs.next())
                       onlineClients+=rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+";";
                    }
                    writer = new ObjectOutputStream(receiver.getOutputStream());
                    msg=new Message("Server",receiver.getInetAddress().getHostAddress(),onlineClients,ContentType.USERS,ReceiverType.SERVER);
                    writer.writeObject(msg);
                } catch (IOException ex) {
                    clients.remove(receiver);
                    break;
                } catch (Exception ex) {
                      clients.remove(receiver);
                     System.out.println(ex.getMessage()+" uuuuuuuuu");break;
                }
            }
        }

    }
 // methd for generate Random username for clients
     private String generateRandomUserName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return (generatedString);

    }
}
