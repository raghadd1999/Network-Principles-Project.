/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclientchattingapplication.database;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author TOSHIBA
 */
public class Connections {
    private static final String DB_URL="jdbc:mysql://localhost:3306/chattingapp?zeroDateTimeBehavior=convertToNull";
    private static final String DB_USER="root";
    private static final String DB_PASSWORD="123abc";
    private String UserName;
    private String UserIP;
    private String UserMSG;
    
    private Socket client;
    private InetAddress serverIP;
    private BufferedReader input;
    private PrintWriter output;
    private Thread clientThread;

    public Connections(String UserName, String UserIP) {
        this.UserName = UserName;
        this.UserIP = UserIP;
    }

    public Connections() {
    }
    
    /**
     * function that will connect the user to server and change the state to connected
     */
    public void Connect(){
        
    }
    
    public void SendMessage(String msg,String Destination, String IP){
        
    }
    
    
}
