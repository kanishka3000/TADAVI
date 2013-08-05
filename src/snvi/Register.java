/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snvi;

import catchaction.ui.componant.BFrame;
import catchaction.ui.componant.BLabel;
import catchaction.ui.componant.BText;
import core.User;
import util.BLog;

/**
 *
 * @author kanishka_
 */
public class Register extends BFrame {

    public String ErrorString = null;
    User user = User.getUser();

    @Override
    public void doHome() {
        try {
            this.setVisible(false);
            this.dispose();
            System.out.println("Opening home");
            Login h = new Login();
            h.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onFormFocus() {
        alert("register form");

    }

    @Override
    public void doEnter() {
        System.out.println("enter");
        if (!Password.getText().equals(PasswordConfirm.getText())) {
            System.out.println("not equal passwords");
            alert("Passwords are not equal");
            return;
        }
        user.setName(Name.getText());
        user.setUsername(UserName.getText());
        user.setBday(Bday.getText());
        user.setPhone(ContactNo.getText());
        user.setPassword(Password.getText());
        if (!user.validate()) {
            System.out.println(user.ErrorString);
            alert(user.ErrorString);
            return;
        }
        if (user.userNameExists()) {
            System.out.println("username duplicate");
            alert("Please select a different user name");
            return;
        }

        if (user.register()) {
            doHome();
        } else {
            alert("Error, please try again");
        }
        BLog.writeLog("End registration");
    }
    BText PasswordConfirm;
    BText Password;
    BText Name;
    BText Bday;
    BText UserName;
    BText ContactNo;

    public Register() throws Exception {
        super(6, 1);
        setFocusable(true);
        BLabel instructions = new BLabel();
        instructions.setReadText("Enter the required details and submit, this will enable you to use our system");
        //add(instructions, 5, 0);
        Name = new BText();
        Name.setReadText("Please Enter the name");
        add(Name, 5, 0);
        Bday = new BText();
        Bday.setReadText("Please enter your birth day, in the order of year, month and day");
        add(Bday, 4, 0);
        ContactNo = new BText();
        ContactNo.setIsLongCharBreak(true);
        ContactNo.setReadText("please enter the contact number");
        add(ContactNo, 3, 0);

        UserName = new BText();
        UserName.setReadText("Please enter the user name");
        add(UserName, 2, 0);
        Password = new BText();
        Password.isPassword = true;
        Password.setReadText("Please Enter the password");
        add(Password, 1, 0);
        PasswordConfirm = new BText();
        PasswordConfirm.isPassword = true;
        PasswordConfirm.setReadText("Please confirm the password you entered");
        add(PasswordConfirm, 0, 0);

        revalidateAudioPosition();
        BLog.writeLog("start registration");
    }

    public static void main(String args[]) throws Exception {
        Register register = new Register();
        register.Welcome_Speech = "Welcome to registration";
        register.setVisible(true);
    }
}
