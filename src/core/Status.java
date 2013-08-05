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
public class Status {

    private String id;
    private String Status;
    private Comment[] Comments;
    private String User;
    public static int SERVICE_STATUS = 3;
    private String date;

    public static ArrayList<Status> getStatus(String username) {
        ArrayList list = new ArrayList();
        Document doc = null;
        try {
            doc = new HttpConnect().getHttp("/Notification?username=" + username + "&service=" + SERVICE_STATUS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        NodeList statusl = doc.getElementsByTagName("status");
        for (int i = 0; i < statusl.getLength(); i++) {
            Status stu = new Status();
            Element et = (Element) statusl.item(i);
            stu.setId(et.getAttribute("id"));
            stu.setStatus(et.getAttribute("value"));
            stu.setDate(et.getAttribute("date"));
            NodeList commets = et.getElementsByTagName("comment");
            Comment[] comments = new Comment[commets.getLength()];
            for (int j = 0; i < commets.getLength(); j++) {
                Element etcom = (Element) commets.item(j);
                Comment com = new Comment();
                com.setCommenter(etcom.getAttribute("friend"));
                com.setValue(etcom.getAttribute("value"));
                comments[i] = com;
            }
            stu.setComments(comments);
            list.add(stu);
        }

        return list;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Comment[] getComments() {
        return Comments;
    }

    public void setComments(Comment[] Comments) {
        this.Comments = Comments;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
}
