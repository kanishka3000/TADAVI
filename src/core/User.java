/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import util.HttpConnect;

/**
 *username, dob, fname, lname, no, street, city, country, land_no, mobile_no

 * @author kanishka_
 */
public class User {

    public static final int SERVICE_REGISTER = 0;
    public static final int SERIVCE_USEREXIST = 1;
    private static User user = null;
    private String username;
    private String Id;
    private String FirstName;// this will be used as the name;
    private String Lastname;
    private String City;
    private String Phone;
    private String Bday;
    private int AuthID;
    private String Password;
    public String ErrorString = "format error";
    public boolean isPositonal=true;

    public boolean validate() {
        System.out.println(username + FirstName + Bday);
        if (username == null || username.equals("")) {
            ErrorString = "user name empty";
            return false;
        }
        if (Bday == null || Bday.equals("")) {
            ErrorString = "birth day is empty";
            return false;
        }
        String[] bdaysting = Bday.split(" ");
        try {
            int month = Integer.parseInt(bdaysting[1]);
            int year = Integer.parseInt(bdaysting[0]);
            int day = Integer.parseInt(bdaysting[2]);
            if (year > Calendar.getInstance().get(Calendar.YEAR) || year < 1970) {
                ErrorString = "wrong year";
                throw new Exception();
            }
            if (month > 12 || month < 1) {
                ErrorString = "wrong month";
                throw new Exception();
            }
            if (day > 31 || day < 0) {
                ErrorString = "wrong day";
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorString += " wrong date format";
            return false;
        }
        try {
            Integer.parseInt(Phone);
        } catch (Exception e) {
            ErrorString = "unacceptable phone number";
            return false;
        }
        return true;
    }

    public boolean register() {
        HttpConnect connect = new HttpConnect();
        try {
            Document in = connect.getHttp("/Registration?username=" +
                    username + "&service=" + SERVICE_REGISTER + "&password=" +
                    Password + "&name=" +
                    getFirstName() + "&birthday=" + Bday+"&phone="+Phone);
            String s = in.getElementsByTagName("status").item(0).getTextContent();
            if (s.equalsIgnoreCase("1")) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    private User() {
    }

    public boolean userNameExists() {
        //returning true means the user name exists
        HttpConnect connect = new HttpConnect();
        try {
            Document in = connect.getHttp("/Registration?username=" + username + "&service=" + SERIVCE_USEREXIST);
            String s = in.getElementsByTagName("status").item(0).getTextContent();
            if (s.equalsIgnoreCase("1")) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return the FirstName
     */
    public String getName() {
        return getFirstName();
    }

    /**
     * @param FirstName the FirstName to set
     */
    public void setName(String Name) {
        this.setFirstName(Name);
    }

    /**
     * @return the AuthID
     */
    public int getAuthID() {
        return AuthID;
    }

    /**
     * @param AuthID the AuthID to set
     */
    public void setAuthID(int AuthID) {
        this.AuthID = AuthID;
    }

    /**
     * @return the Bday
     */
    public String getBday() {
        return Bday;
    }

    /**
     * @param Bday the Bday to set
     */
    public void setBday(String Bday) {
        this.Bday = Bday;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    /**
     * @return the Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * @param Phone the Phone to set
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
}
