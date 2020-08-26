/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclientchattingapplication;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author TOSHIBA
 */
public class JavaClientChattingApplication {

    private static ClientConnection connection;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //create a new client window and run it        
        String ip = JOptionPane.showInputDialog("Enter the server IP");
        

        Thread t = new Thread(new Runnable() {
            public void run() {
               // while (true) {
                    ClientMainWindow c = new ClientMainWindow(ip);
                    c.setVisible(true);
               // }
            }
        });
        t.start();

    }

}
