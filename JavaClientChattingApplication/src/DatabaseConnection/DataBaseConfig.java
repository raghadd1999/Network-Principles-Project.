/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Osama Abdullah
 */
public class DataBaseConfig {
   static Connection conn;
   static Statement stm;
    
   public DataBaseConfig(){
        try {
            conn=DriverManager.getConnection("jdbc:ucanaccess://G:\\Work\\JavaClientChattingApplication\\LocalChatDB.accdb");
            stm=conn.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"خطأ في الاتصال بقاعدة البيانات "+ex.getMessage(),"خطأ", JOptionPane.ERROR_MESSAGE);
        }
    }
   
   
    
    public ResultSet execute(String query) {
       try {
           
           return stm.executeQuery(query);
       } catch (SQLException ex) {
           Logger.getLogger(DataBaseConfig.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
    }
    
   public int update(String query) {
        
       try {
           return stm.executeUpdate(query);
       } catch (SQLException ex) {
           Logger.getLogger(DataBaseConfig.class.getName()).log(Level.SEVERE, null, ex);
           return 0;
       }
    }
    
}
