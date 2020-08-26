/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclientserverchattingapplication;

import java.net.ServerSocket;
import java.util.HashMap;
import javaclientserverchattingapplication.communication.HandleClients;

/**
 *
 * @author TOSHIBA
 */
public class ServerConnection extends Thread{
   private ServerSocket server;
   private HashMap<String,HandleClients> clients;
   
    
}
