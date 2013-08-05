/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snvi;

import catchaction.ui.componant.BFrame;
import catchaction.ui.componant.BLabel;
import core.Friend;
import core.User;
import java.util.ArrayList;

/**
 *
 * @author kanishka
 */
public class FriendsExtra extends BFrame {

    public static String SearchTerm = null;
    public static int SearchStatus = 500;
     @Override
    public void doHome() {
        try {
            this.setVisible(false);
            this.dispose();
            System.out.println("Opening home");
            Home h = new Home();
             h.Welcome_Speech="welcome home";
            h.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public FriendsExtra(ArrayList<Friend> friends) throws Exception {
        super(friends.size() + 1, 4);
        setFocusable(true);
        int i = 0;
        for (Friend fri : friends) {
            BLabel name = new BLabel();
            name.setReadText(fri.getName());
            add(name, i, 0);

            BLabel contactno = new BLabel();
            contactno.setReadText(fri.getContactNo());
            contactno.setNumberSeparated(true);
            add(contactno, i, 1);

            BLabel city = new BLabel();
            city.setReadText(fri.getBday());
            add(city, i, 2);

            BLabel username = new BLabel();
            username.setReadText(fri.getUsername());
            add(username, i, 3);
            i++;
        }
        //setting the heading values;
        BLabel hname = new BLabel();
        hname.setReadText("name");
        add(hname, i, 0);

        BLabel hcontact = new BLabel();
        hcontact.setReadText("contact number");
        add(hcontact, i, 1);

        BLabel hcity = new BLabel();
        hcity.setReadText("address");
        add(hcity, i, 2);

        BLabel husername = new BLabel();
        husername.setReadText("user name");
        add(husername, i, 3);
        revalidateAudioPosition();
    }

    public static void startFriends(int status, String searcherm) throws Exception {
        if (searcherm != null) {
            SearchTerm = searcherm;
        } else {
            System.out.println("history search term" + SearchTerm);
        }
        if (status != 500) {
            SearchStatus = status;
        } else {
            System.out.println("history search status" + SearchStatus);
        }
        ArrayList fri = Friend.getFriends(User.getUser(), SearchStatus, SearchTerm);
        FriendsExtra fr = new FriendsExtra(fri);
        fr.Welcome_Speech = "view and comment on your friends";
        fr.setVisible(true);
    }
}
