/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snvi;

import catchaction.ui.componant.BButton;
import catchaction.ui.componant.BFrame;
import catchaction.ui.componant.BLabel;
import catchaction.ui.componant.BText;
import core.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kanishka
 */
public class Messages extends BFrame {

    public void doEnter() {
        getCurrentComponant().doEnter();
    }
     @Override
    public void onFormFocus(){
    alert("message form");

    }

    public Messages(ArrayList<core.Message> mesa) throws Exception {
        super(mesa.size(), 2);
        setFocusable(true);
        int i = 0;
        for (final core.Message ms : mesa) {
            final BText msgtouser = new BText();
            BButton userbutton = new BButton(1) {

                public void doEnter() {
                    System.out.println(msgtouser.getText() + "is there"+ms.getSender());
                   ms.setRecepeant(User.getUser().getUsername());
                   ms.setMessage(msgtouser.getText());
                    try {
                        ms.sendMassage();
                    } catch (Exception ex) {
                       alert("error sending message");
                    }

                }
            };
            userbutton.setReadText(ms.getSender() + "'s options");
            msgtouser.setReadText("send a message to " + ms.getSender());
            //add options to the buttons;
            userbutton.add(msgtouser,0,0);
            add(userbutton, i, 0);
            BLabel usermassage = new BLabel();
            usermassage.setReadText(ms.getMessage());
            add(usermassage, i, 1);
           i++;
        }
        revalidateAudioPosition();

    }

    public static void startMassages() throws Exception {
        ArrayList<core.Message> mesa = new core.Message().getMessages();
        System.out.println(mesa);
        Messages msg = new Messages(mesa);
        msg.setVisible(true);
    }

}
