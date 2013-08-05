/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snvi;

import catchaction.ui.componant.BButton;
import catchaction.ui.componant.BFrame;
import catchaction.ui.componant.BLabel;
import catchaction.ui.componant.BText;
import core.Notification;
import core.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kanishka_
 */
public class Notifications extends BFrame {

    @Override
    public void doEnter() {
        //System.out.println("lal");
        if (!mycomment.getText().equals("")) {
            Notification.saveStatus(mycomment.getText(), User.getUser());
        } else {
            alert("you have not entered a status");
        }
        //saving the status information is over. now to save all the comment information.
        for (String id : noticomment.keySet()) {
            BText b = noticomment.get(id);
            if (!b.getText().equals("")) {
                if (!Notification.saveComment(id, b.getText(), User.getUser())) {
                    alert("error saving comment " + id);
                }else{
                alert("your comment was saved");
                }
            }
        }
    }
     @Override
    public void onFormFocus(){
    alert("notification form");

    }

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
    Map<String, BText> noticomment = new HashMap();
    BText mycomment;

    public Notifications(ArrayList<Notification> notifications) throws Exception {
        super(notifications.size() + 1, 2);
        setFocusable(true);
        /*
         * need to get information from the server
         * fix up to get comments
         */
        int i = 0;
        for (Notification s : notifications) {
            BLabel labb = new BLabel();
            labb.setReadText(s.getSourceUser());
            add(labb, i, 0);
            BButton button = new BButton(s.getComments().length + 1);
            button.setReadText(s.getNotificationPost());
            int j = 0;
            for (String[] sk : s.getComments()) {
                BLabel la = new BLabel();
                la.setReadText(sk[0] + " says " + sk[1]);
                button.add(la, j, 0);
                j++;
            }
            BText postcom = new BText();
            postcom.setReadText("enter what you think");
            noticomment.put(s.getNotificationid(), postcom);
            button.add(postcom, j, 0);
            add(button, i, 1);
            i++;
        }
        mycomment = new BText();
        mycomment.setReadText("Enter your status");
        add(mycomment, i, 0);
        BLabel mycommentsub = new BLabel();
        mycommentsub.setReadText("move");
        add(mycommentsub, i, 1);
        revalidateAudioPosition();
    }

    public static void startNotification() throws Exception {
        ArrayList<Notification> noti = Notification.getNotifications(User.getUser());
        Notifications notification = new Notifications(noti);
        notification.setVisible(true);
    }

    public static void main(String args[]) throws Exception {

        Notifications.startNotification();
    }
}
