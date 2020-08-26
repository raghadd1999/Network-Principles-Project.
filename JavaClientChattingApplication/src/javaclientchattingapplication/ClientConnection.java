/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclientchattingapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author TOSHIBA
 */
public class ClientConnection {

    private Socket client;
    private String ClientName;
    private InetAddress IP;
    private InetAddress myIPAddress;
    private String Host;
    private int PORT;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientConnection(String Name, String Host, int PORT) throws IOException {
        //create a client socket when the connection is successed
        client = new Socket(Host, PORT);
        //save the host name when it's local and port number
        this.Host = Host;
        this.PORT = PORT;
//get the local ip address of the client
        myIPAddress = client.getLocalAddress();
        //save the client name 
        this.ClientName = Name;
        //get the input and output stream from the server
        processStreamConnections();

    }

    public ClientConnection(String Name, Socket client, InetAddress IP) throws IOException {
        //create client socket when is success and save the configurations
        client = new Socket(IP, PORT);
        // save the ip address of the server
        this.IP = IP;
        //save the port number of the server
        this.PORT = PORT;
        //get the server input/output stream to communicate
        processStreamConnections();
        //get the local ip address of the client
        myIPAddress = client.getLocalAddress();
        //save the client name 
        this.ClientName = Name;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public InetAddress getIP() {
        return IP;
    }

    public void setIP(InetAddress IP) {
        this.IP = IP;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public InetAddress getMyIPAddress() {
        return myIPAddress;
    }

    public void setMyIPAddress(InetAddress myIPAddress) {
        this.myIPAddress = myIPAddress;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String Host) {
        this.Host = Host;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    /**
     * Function that link the client and server stream together to allow the
     * communication this function is called after the client socket is made
     *
     * @throws IOException
     */
    private void processStreamConnections() throws IOException {
        //get the output stream as writer from the server
        this.writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
        //get the input stream as writer from the server
        this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // still connecting with the server 
        writer.write(ClientName);
        while (true) {

        }

    }

}
