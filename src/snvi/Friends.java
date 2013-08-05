/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snvi;

import catchaction.ui.componant.BButton;
import catchaction.ui.componant.BFrame;
import catchaction.ui.componant.BLabel;
import core.Friend;
import core.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kanishka_
 */
public class Friends extends BFrame {

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

    @Override
    public void doEnter() {
        getCurrentComponant().doEnter();
    }

    private void openFriendInfo(String userName) {
        this.setVisible(false);
        try {
            ViewFriend.viewFriend(userName, SearchStatus);

        } catch (Exception ex) {
            alert("error error");
        }
    }

    public Friends(ArrayList<Friend> friends, int sttus) throws Exception {
        super(friends.size(), 2);
        setFocusable(true);
        int i = 0;
        for (final Friend fr : friends) {
            BButton friend = null;
            
            if (sttus == Friend.FRIENDS_ALL) {
                friend=new BButton(2);
                BButton addasFriend = new BButton() {

                    @Override
                    public void doEnter() {
                        alert("adding " + fr.getName() + " as a friend, please wait");
                        if (Friend.addAsFriend(fr.getUsername(), User.getUser())) {
                            alert("added");
                        } else {
                            alert("you added already or error occured");
                        }
                    }
                };
                addasFriend.setReadText("add as friend");
                friend.add(addasFriend, 1, 1);
           
            } else if (sttus == Friend.FRIENDS_EXISTING || sttus == Friend.FRIENDS_NOTSORTED) {
                friend=new BButton(1);
                //System.out.println("here");
//                BButton message = new BButton() {
//
//                    @Override
//                    public void doEnter() {
//                    }
//                };
//                message.setReadText("not assigned");
//                friend.add(message, 0, 1);
//                BButton messagei = new BButton() {
//
//                    @Override
//                    public void doEnter() {
//                    }
//                };
//                messagei.setReadText("not assigned");
//                friend.add(message, 2, 1);

            } else if (sttus == Friend.FRIENDS_REQUESTS) {
                friend=new BButton(3);
                BButton frienRequ = new BButton() {

                    @Override
                    public void doEnter() {
                        if (Friend.acceptFriend(true, fr.getUsername())) {
                            alert("adding succeeded");
                        } else {
                            alert("you already accepted this friend or error occured");
                        }
                    }
                };
                frienRequ.setReadText("confirm friend");
                friend.add(frienRequ, 1, 1);
                BButton friendReject = new BButton() {

                    @Override
                    public void doEnter() {
                        if (Friend.acceptFriend(false, fr.getUsername())) {
                            alert("rejecting succeeded");
                        } else {
                            alert("you already rejected this friend or error occured");
                        }
                    }
                };
                friendReject.setReadText("reject the friend");
                friend.add(friendReject, 2, 0);
            }
            BButton viewInfo = new BButton() {

                @Override
                public void doEnter() {
                    openFriendInfo(fr.getUsername());
                }
            };
            viewInfo.setReadText("view information");
            friend.add(viewInfo, 0, 0);
            friend.setReadText(fr.getName()+"'s options");
            add(friend, i, 0);
            BButton subFr = new BButton(fr.getFriends().length);
            subFr.setReadText(fr.getName() + "'s mutual friends");
            int j = 0;
            for (String s : fr.getFriends()) {
                BLabel lab = new BLabel();
                lab.setReadText(s);
                subFr.add(lab, j, 0);
                j++;
            }
            add(subFr, i, 1);
            i++;
        }
        revalidateAudioPosition();
    }

    public static void main(String args[]) throws Exception {
        //Friends.startFriends(Friend.FRIENDS_ALL);
    }

    public static void startFriends(int status, String searcherm) throws Exception {
        if (searcherm != null) {
            SearchTerm = searcherm;
        }else{
        System.out.println("history search term"+SearchTerm);
        }
        if (status != 500) {
            SearchStatus = status;
        }else{
        System.out.println("history search status"+SearchStatus);
        }
        ArrayList fri = Friend.getFriends(User.getUser(), SearchStatus, SearchTerm);
        Friends fr = new Friends(fri, SearchStatus);
        fr.Welcome_Speech = "view and comment on your friends";
        fr.setVisible(true);
    }
     @Override
    public void onFormFocus(){
    alert("friends form");

    }
}
