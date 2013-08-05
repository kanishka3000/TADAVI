/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snvi;

import catchaction.ui.componant.BButton;
import catchaction.ui.componant.BFrame;
import catchaction.ui.componant.BLabel;
import catchaction.ui.componant.BText;
import core.Friend;
import core.Status;
import core.User;
import java.util.ArrayList;
import util.BLog;

/**
 *
 * @author kanishka_
 */
public class ViewFriend extends BFrame {

    public int status = 0;
    public static int STATUS_PROFILE = 0;
    public static int STATUS_FRIENDVIEW = 1;

    @Override
    public void doHome() {
        try {
            this.setVisible(false);
            this.dispose();

            if (status == STATUS_PROFILE) {
                System.out.println("Opening home");
                Home h = new Home();
                h.Welcome_Speech = "welcome home";
                h.setVisible(true);
            } else {
                System.out.println("Opening search");
                Friends.startFriends(500, null);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doEnter() {
        if (!message.getText().equals("")) {
            friend.sendMassage(message.getText(), User.getUser(), friend.getUsername());
        }
    }
    BText message;
    Friend friend;

    public ViewFriend(Friend friend, ArrayList<Status> status) throws Exception {

        super(4 + status.size(), 2);
        setFocusable(true);
        int i = 0;
        for (Status st : status) {
            BLabel statusla = new BLabel();
            statusla.setReadText(st.getStatus());
            add(statusla, i, 0);
            BButton commentbutton = new BButton();
            commentbutton.setReadText("comments");
            add(commentbutton, i, 1);

            i++;
        }
        //i--;

        System.out.println("st1" + i);
        this.friend = friend;

        BButton tasks = new BButton(2);
        tasks.setReadText("tasks");
        message = new BText();
        message.setReadText("type a message to send");
        tasks.add(message, 0, 0);
        BButton poke = new BButton();
        poke.setReadText("poke");
        tasks.add(poke, 1, 0);
        add(tasks, i, 0);

        BLabel notassigned = new BLabel();
        notassigned.setReadText("not assigned");
        add(notassigned, i++, 1);


//        BLabel cityLabel = new BLabel();
//        cityLabel.setReadText("city");
//        add(cityLabel, i, 0);
//        BLabel cityTextLabel = new BLabel();
//        cityTextLabel.setReadText(friend.getBday());
//        add(cityTextLabel, i++, 1);

        BLabel contactLabel = new BLabel();
        contactLabel.setReadText("contact number");
        add(contactLabel, i, 0);
        BLabel contactTextLabel = new BLabel();
        contactTextLabel.setReadText(friend.getBday());
        add(contactTextLabel, i++, 1);

        BLabel birthDayLabel = new BLabel();
        birthDayLabel.setReadText("birth day");
        add(birthDayLabel, i, 0);
        BLabel bithDayTextLabel = new BLabel();
        bithDayTextLabel.setReadText(friend.getBday());
        add(bithDayTextLabel, i++, 1);

        BLabel nameLabel = new BLabel();
        nameLabel.setReadText("name of friend");
        add(nameLabel, i, 0);
        BLabel nameTextLable = new BLabel();
        nameTextLable.setReadText(friend.getName());
        add(nameTextLable, i++, 1);

        //======= general profile information


        revalidateAudioPosition();
        BLog.writeLog("enter view friends");
    }

    public static void viewFriend(String username, int type) throws Exception {


        Friend fr = Friend.getFriendInfo(username);
        ArrayList status = Status.getStatus(username);
        ViewFriend fioe = new ViewFriend(fr, status);
        fioe.status = type;
        fioe.Welcome_Speech = "personal information";


        fioe.setVisible(true);
    }

    public static void main(String args[]) throws Exception {
        ViewFriend.viewFriend("dama", 0);

    }
}
