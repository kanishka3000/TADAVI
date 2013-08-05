/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.InputStream;

import javax.lang.model.element.Element;
import org.w3c.dom.Document;
import util.HttpConnect;

/**
 *
 * @author kanishka_
 */
public class Login {

    public static final int NOT_AUTHORIZED = 0;
    public static final int AUTHORIZED = 1;
    public static final int USER_NORMAL = 1;
    public String Name = "not_auth";
    public int Access = 0;
    public int Auth = NOT_AUTHORIZED;

    public Login getLogin(String username, String password) throws Exception {
        HttpConnect connection = new HttpConnect();
        Document in = connection.getHttp("/Login?username=" + username + "&password=" + password);
        /*parse*/
        Login flog = new Login();
        flog.Name = in.getElementsByTagName("name").item(0).getTextContent();
        flog.Access = Integer.parseInt(in.getElementsByTagName("accesslevel").item(0).getTextContent());
        flog.Auth = Integer.parseInt(in.getElementsByTagName("tempid").item(0).getTextContent());
        if (flog.Access != NOT_AUTHORIZED) {
            User user = User.getUser();
            user.setUsername(username);
            user.setAuthID(flog.Access);
        }

        return flog;
    }
}
