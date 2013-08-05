/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snvi;

import catchaction.ui.componant.BButton;

/**
 *
 * @author kanishka
 */
public class Logout extends catchaction.ui.componant.BFrame {

    BButton logout;
    BButton cancel;

    @Override
    public void doEnter() {
        getCurrentComponant().doEnter();
    }

    @Override
    public void doHome() {
        try {
            this.setVisible(false);
            this.dispose();
            System.out.println("Opening home");
            Home h = new Home();
            h.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Logout() throws Exception {
        super(2, 1);
        setFocusable(true);
        logout = new BButton() {

            @Override
            public void doEnter() {
                alert("good bye");
                System.exit(0);
            }
        };

        logout.setReadText("press enter to logout");
        add(logout, 0, 0);
        cancel = new BButton() {

            @Override
            public void doEnter() {
                doHome();
            }
        };
        cancel.setReadText("go to home back");
        add(cancel, 1, 0);

    }
}
