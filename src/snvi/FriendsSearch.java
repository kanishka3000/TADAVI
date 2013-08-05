/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snvi;

import catchaction.ui.componant.BButton;
import catchaction.ui.componant.BFrame;
import catchaction.ui.componant.BText;
import core.Friend;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.BLog;

/**
 *
 * @author kanishka_
 */
public class FriendsSearch extends BFrame {

    @Override
    public void doEnter() {

        if (getCurrentComponant() == friendsRequests) {
            alert("please wait for me to load friend requests");
            try {
                this.setVisible(false);
                this.dispose();
                BLog.writeLog("enter friend request");
                Friends.startFriends(Friend.FRIENDS_REQUESTS, null);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (selection.getTraverse().getCurrentComponant() == newFriends) {
            if (searchbox.getText().equals("")) {
                alert("Please enter a search term");
                return;
            }
            try {
                this.setVisible(false);
                this.dispose();
                BLog.writeLog("enter friends all ");
                Friends.startFriends(Friend.FRIENDS_ALL, searchbox.getText());
                
                System.out.println("all");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (selection.getTraverse().getCurrentComponant() == oldFriends) {
            if (searchbox.getText().equals("")) {
                alert("Please enter a search term");
                return;
            }
            try {
                this.setVisible(false);
                this.dispose();
                BLog.writeLog("enter existing friends");
                Friends.startFriends(Friend.FRIENDS_EXISTING, searchbox.getText());
                System.out.println("existing");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    BText searchbox;
    BButton selection;
    BButton newFriends;
    BButton oldFriends;
    BButton friendsRequests;

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

    public static void main(String args[]) throws Exception {
        FriendsSearch me = new FriendsSearch();
        me.setVisible(true);
    }

    public FriendsSearch() throws Exception {
        super(1, 3);
        this.setFocusable(true);
        friendsRequests = new BButton();
        friendsRequests.setReadText("view your friend requests");
        add(friendsRequests, 0, 2);
        searchbox = new BText();
        searchbox.setReadText("Enter the name to search");
        add(searchbox, 0, 0);
        selection = new BButton(2);
        selection.setReadText("select an option to search");
        newFriends = new BButton() {

            @Override
            public void doEnter() {
            }
        };
        newFriends.setReadText("search for new friends");
        selection.add(newFriends, 0, 0);
        oldFriends = new BButton() {

            @Override
            public void doEnter() {
            }
        };
        oldFriends.setReadText("existing friends");
        selection.add(oldFriends, 1, 0);
        add(selection, 0, 1);
        revalidateAudioPosition();
        BLog.writeLog("enter friend search");
    }
     @Override
    public void onFormFocus(){
    alert("friend search form");

    }
}
