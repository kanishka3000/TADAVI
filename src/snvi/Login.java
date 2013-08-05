package snvi;

import catchaction.ui.componant.BButton;
import catchaction.ui.componant.BText;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.HttpConnect;

/**
 *
 * @author kanishka
 */
class Login extends catchaction.ui.componant.BFrame {

    BText nameText;
    BText passText;
    BButton le;



    @Override
    public void doEnter() {

        if (getCurrentComponant() == le) {
            //handling the registration
            getCurrentComponant().doEnter();
            return;
        }
        core.Login loi = null;
        try {
            loi = new core.Login().getLogin(nameText.getText(), passText.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (loi != null && loi.Access != core.Login.NOT_AUTHORIZED) {
                //  if (true) {//always log in
                this.setVisible(false);
                this.dispose();
                Home a = new Home();
                a.Welcome_Speech = "Welcome " + loi.Name;
                a.setVisible(true);
            } else {
                alert("Wrong user name or password, please try again");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doEscape() {
        // System.exit(0);
    }

    private void doRegister() {
        this.setVisible(false);
        try {
            Register register = new Register();
            register.Welcome_Speech = "Welcome to registration";
            register.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Login() throws Exception {
        super(1, 3);
        setFocusable(true);
        nameText = new BText();
        nameText.setReadText("Please Enter the name");
        passText = new BText();
        passText.setReadText("Please Enter the password");
        passText.isPassword = true;
        le = new BButton() {

            @Override
            public void doEnter() {
                doRegister();
            }
        };
        le.setReadText("Register for an account");
        add(nameText, 0, 0);
        add(passText, 0, 1);
        add(le, 0, 2);
        revalidateAudioPosition();
        // glipseForm();
    }
    @Override
    public void onFormFocus(){
    alert("login form");

    }
}
