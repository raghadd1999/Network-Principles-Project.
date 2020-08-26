/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclientserverchattingapplication.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOSHIBA
 */
public class HandleClients extends Thread implements Runnable {

    private Socket client;
    private String IP;
    private String UserName;
    private BufferedReader input;
    private PrintWriter output;

    public HandleClients(Socket client) {
        this.client = client;
        IP = client.getInetAddress() + "";
        //
    }

    public HandleClients(Socket s, String ip, String username) {
        this.client = s;
        this.IP = ip;
        this.UserName = username;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public BufferedReader getInput() {
        return input;
    }

    public void setInput(BufferedReader input) {
        this.input = input;
    }

    public PrintWriter getOutput() {
        return output;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    /**
     * Override the run
     */
    @Override
    public void run() {
       // GetStream();
        try {
            System.out.println("Client Connected");
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
            
        } catch (IOException ex) {
            Logger.getLogger(HandleClients.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                UserName = input.readLine();
            } catch (IOException ex) {
                Logger.getLogger(HandleClients.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Client : " + UserName + " Is now Connected !");
        }
        //process the communication here

    }

    private void GetStream() {

        try {
            System.out.println("Client Connected");
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
            UserName = input.readLine();
            System.out.println("Client : " + UserName + " Is now Connected !");
        } catch (IOException ex) {
            Logger.getLogger(HandleClients.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void WriteData(String Data) {

    }

    public String ReadData() {

        return "";
    }

    public void GetAllGroupMessage(String group) {

    }

    public void GetAllPersonalMessage(String user) {

    }

    public void sendGroupMessage(String Data, String group, String sender) {

    }

    public void sendPersonalMessage(String Data, String source, String destination) {

    }
}

class Users {

    private String Name;
    private String IP;
    private boolean status;

    public Users(String Name, String IP, boolean status) {
        this.Name = Name;
        this.IP = IP;
        this.status = status;
    }

    public Users() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
