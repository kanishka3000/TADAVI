/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import util.HttpConnect;

/**
 *
 * @author kanishka_
 */
public class Friend {

    public static final int FRIENDS_EXISTING = 0;// search in existing friends
    public static final int FRIENDS_ALL = 1;//search in all friends
    public static final int FRIENDS_NOTSORTED = 2;//get all friends without search
    public static final int FRIENDS_REQUESTS = 5;// get all friend requests
    public static final int ADD_AS_FRIEND = 3;
    public static final int INDEVIDUAL_INFO = 4;
    private String UserName;
    private String ID;
    private String Name;
    private String BirthDay;
    private String City;
    private String ContactNo;
    private String[] friends;
    /*
    username is the key and always has to be assigened. 
     */

    public boolean sendMassage(String message, User user, String friend) {
        Message me = new Message();
        me.setMessage(message);
        me.setSender(friend);
        me.setRecepeant(user.getUsername());
        try {
            return me.sendMassage();
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean acceptFriend(boolean accept, String friendUserName) {
        String operation;
        if (accept) {
            operation = "1";
        } else {
            operation = "0";
        }
        HttpConnect connection = new HttpConnect();
        try {
            Document doc = connection.getHttp("/Friend?username=" + User.getUser().getUsername() + "&friend=" + friendUserName + "&service=" + FRIENDS_REQUESTS + "&operation=" + operation);
            String s = doc.getElementsByTagName("status").item(0).getTextContent();
            if (s.equalsIgnoreCase("1")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean addAsFriend(String username, User user) {
        /*
         * //working on dummy real code
        HttpConnect connection = new HttpConnect();
        try {
        Document doc = connection.getHttp("/Friend?username=" + user.getUsername() + "&friend=" + username + "7service=" + ADD_AS_FRIEND);
        String s = doc.getElementsByTagName("status").item(0).getTextContent();
        if (s.equalsIgnoreCase("1")) {
        return true;
        } else {
        return false;
        }
        } catch (Exception ex) {
        return false;
        }
         */
        return true;
    }

    public static ArrayList<Friend> getFriends(User user, int service, String searchtermi) {

        // real code working on dummy yet to get the back end
        String servi;
        String searchterm = "/Friend?username=" + user.getUsername() + "&service=" + service;
        if (service == FRIENDS_ALL || service == FRIENDS_EXISTING) {
            searchterm += "&searchstring=" + searchtermi;
        }

        HttpConnect connection = new HttpConnect();
        Document doc = null;
        try {
            doc = connection.getHttp(searchterm);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        NodeList friends = doc.getElementsByTagName("friend");
        ArrayList list = new ArrayList();
        for (int i = 0; i < friends.getLength(); i++) {
            Friend frid = new Friend();
            Element e = (Element) friends.item(i);
            frid.setUsername(e.getAttribute("username"));
            frid.setName(e.getAttribute("name"));
            frid.setBday(e.getAttribute("bday"));
            //frid.setBday("birth day");
            String phone=e.getAttribute("phone");
            if(phone.equals("")){
            phone="not applicable";
            }
            frid.setContactNo(phone);//needs to be extracted from the xml soon
            //frid.setContactNo("contact");
            frid.setCity("city");//needs to be extracted from the xml soon. 
            //setting up mutual friends for the child nodes
            NodeList mutualfriends = e.getElementsByTagName("mutfriend");
            String[] mutifiends = new String[mutualfriends.getLength()];
            for (int j = 0; j < mutualfriends.getLength(); j++) {
                mutifiends[j] = ((Element) mutualfriends.item(j)).getAttribute("name");
                System.out.println(mutifiends[j]);
            }
            frid.setFriends(mutifiends);
            list.add(frid);
        }
        return list;
    }

    public static Friend getFriendInfo(String userName) {
        String serarchString = "/Friend?service=" + INDEVIDUAL_INFO + "&inituser=" + User.getUser().getUsername() + "&username=" + userName;
        HttpConnect connect = new HttpConnect();
        Document doc = null;
        try {
            doc = connect.getHttp(serarchString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Element friend = (Element) doc.getElementsByTagName("person").item(0);
        Friend fri = new Friend();
        fri.setUsername(userName);
        fri.setName(friend.getAttribute("name"));
        fri.setBday(friend.getAttribute("bday"));
        fri.setContactNo(friend.getAttribute("contact"));
        fri.setCity("city");
        NodeList mutulaFriends = friend.getElementsByTagName("friend");
        String[] mut = new String[mutulaFriends.getLength()];

        for (int i = 0; i < mutulaFriends.getLength(); i++) {
            mut[i] = ((Element) mutulaFriends.item(i)).getAttribute("name");
        }
        fri.setFriends(mut);
        return fri;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getBday() {
        return BirthDay;
    }

    public void setBday(String bday) {
        this.BirthDay = bday;
    }

    public String[] getFriends() {
        return friends;
    }

    public void setFriends(String[] friends) {
        this.friends = friends;
    }

    public String getUsername() {
        return UserName;
    }

    public void setUsername(String username) {
        this.UserName = username;
    }

    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
    }

    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City the City to set
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     * @return the ContactNo
     */
    public String getContactNo() {
        return ContactNo;
    }

    /**
     * @param ContactNo the ContactNo to set
     */
    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }
}
