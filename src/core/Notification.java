 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import util.HttpConnect;

/**
 *
 * @author kanishka_
 */
public class Notification {

    private String Notificationid;
    private String SourceUser;
    private String NotificationPost;
    private Date Date;
    private String[][] comments;
    public static final int REQUEST_GET = 0;
    public static final int REQUEST_SET = 1;
    public static final int REQUEST_STATUS = 2;

    /*
    comments the first element is the user second is the post
     */
    public static boolean saveStatus(String status, User user) {
        try {
            Document doc = new HttpConnect().getHttp("/Notification?username=" + user.getUsername() + "&status=" + status + "&service="+REQUEST_STATUS);
            String s = doc.getElementsByTagName("status").item(0).getTextContent();
            if (s.equalsIgnoreCase("1")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            return false;
        }


    }

    public static boolean saveComment(String notificationid, String comment, User user) {
        try {
            Document doc = new HttpConnect().getHttp("/Notification?username=" + user.getUsername() + "&service=" + REQUEST_SET + "&nid=" + notificationid + "&comment=" + comment);
            String s = doc.getElementsByTagName("status").item(0).getTextContent();
            if (s.equalsIgnoreCase("1")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Notification> getNotifications(User user) {
        ArrayList list = new ArrayList();
        try {
            Document doc = new HttpConnect().getHttp("/Notification?username=" + user.getUsername() + "&tempid=" + user.getAuthID() + "&service=" + Notification.REQUEST_GET);
            NodeList notificationss = doc.getElementsByTagName("notification");
            for (int i = 0; i < notificationss.getLength(); i++) {
                Notification noti = new Notification();
                Element et = (Element) notificationss.item(i);
                noti.setNotificationid(et.getAttribute("nid"));
                String sourceuser = et.getElementsByTagName("sourceuser").item(0).getTextContent();
                noti.setSourceUser(sourceuser);
                String post = et.getElementsByTagName("post").item(0).getTextContent();
                noti.setNotificationPost(post);
                NodeList comments = et.getElementsByTagName("comment");
                String[][] comts = new String[comments.getLength()][2];
                for (int j = 0; j < comments.getLength(); j++) {
                    //get comments.
                    comts[j][1] = comments.item(j).getAttributes().getNamedItem("commentator").getTextContent();
                    comts[j][0] = comments.item(j).getTextContent();
                }
                noti.setComments(comts);
                list.add(noti);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return list;

    }

    public String getSourceUser() {
        return SourceUser;
    }

    public void setSourceUser(String SourceUser) {
        this.SourceUser = SourceUser;
    }

    public String getNotificationPost() {
        return NotificationPost;
    }

    public void setNotificationPost(String NotificationPost) {
        this.NotificationPost = NotificationPost;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String[][] getComments() {
        return comments;
    }


    public void setComments(String[][] comments) {
        this.comments = comments;
    }

    public String getNotificationid() {
        return Notificationid;
    }

    public void setNotificationid(String Notificationid) {
        this.Notificationid = Notificationid;
    }
}
