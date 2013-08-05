/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import util.HttpConnect;

/**
 *
 * @author kanishka_
 */
public class Message {

    public static final int MESSAGE_SEND = 0;
    public static final int MESSAGE_GET = 1;
    private String Sender;
    private String Recepeant;
    private String Message;
    private String Date;

    public boolean sendMassage() throws Exception {
        HttpConnect connect = new HttpConnect();
        Document doc = connect.getHttp("/Message?service=" + MESSAGE_SEND + "&message=" + this.getMessage() + "&username=" + this.getRecepeant() + "&friend=" + this.getSender());
        String s = doc.getElementsByTagName("status").item(0).getTextContent();
        if (s.equalsIgnoreCase("1")) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Message> getMessages() {
        HttpConnect connect = new HttpConnect();
        Document doc = null;
        try {
            doc = connect.getHttp("/Message?service=" + MESSAGE_GET + "&username=" + User.getUser().getUsername());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        NodeList messagesn = doc.getElementsByTagName("message");
        ArrayList<Message> message = new ArrayList<Message>();
        for (int i = 0; i < messagesn.getLength(); i++) {
            Message messagie = new Message();
            Element et = (Element) messagesn.item(i);
            messagie.setSender(et.getAttribute("sender"));
            messagie.setMessage(et.getAttribute("value"));
            messagie.setDate(et.getAttribute("date"));
            message.add(messagie);
        }
        return message;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String Sender) {
        this.Sender = Sender;
    }

    public String getRecepeant() {
        return Recepeant;
    }

    public void setRecepeant(String Recepeant) {
        this.Recepeant = Recepeant;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }
}
