/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import java.io.Serializable;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
/**
 *
 * @author Osama Abdullah
 */
public class Message implements Serializable{
   private static final long serialVersionUID = 6529685098267757690L;
     String sender;
     String receiver;
     String content;
     String send_date;
     ContentType content_type;
     MessageStatus message_status;
    ReceiverType receiver_type;
    
//---------------Constructor---------------------
     public Message(){
      SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
      Date da=new Date();
     send_date=formatter.format(da);
    }
     public Message(String sender,String receiver,String content,ContentType content_type,ReceiverType receiver_type){
     this.sender=sender;
     this.receiver=receiver;
     this.content=content;
     SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
     Date da=new Date();
     this.send_date=formatter.format(da);
     this.content_type=content_type;
     this.message_status=MessageStatus.SENT;
     this.receiver_type=receiver_type;
 }
//---------------Setters-------------------------   
    public void setSender(String s){sender=s;}
    public void setReceiver(String d){receiver=d;}
    public void setContent(String c){content=c;}
    public void setDate(String d){send_date=d;}
    public void setContentType(ContentType ct){content_type=ct;}
    public void setMessageStatus( MessageStatus s){message_status=s;}
    public void setReceiverType(ReceiverType dt){receiver_type=dt;}
//---------------Getters--------------------------------
    public String getSender(){return sender;}
    public String getReceiver(){return receiver;}
    public String getContent(){return content;}
    public String getDate(){return send_date;}
    public ContentType getContentType(){return content_type;}
    public  MessageStatus getMessageStatus(){return message_status;}
    public ReceiverType getReceiverType(){return receiver_type;} 
//----------------------------------------------------------

}
