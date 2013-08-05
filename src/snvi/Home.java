package snvi;

import catchaction.ui.componant.BButton;
import core.Friend;
import core.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.BLog;

/**
 *
 * @author kanishka
 */
public class Home extends catchaction.ui.componant.BFrame {

    @Override
    public void doEnter() {
        getCurrentComponant().doEnter();
    }

    private void doFriends() {
        FriendsSearch frsearch;
        try {
            this.setVisible(false);
            this.dispose();
            frsearch = new FriendsSearch();
            frsearch.Welcome_Speech="welcome to friend search";
            frsearch.setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doGridFriends() {
        setVisible(false);
        dispose();
        try {
            FriendsExtra.startFriends(Friend.FRIENDS_NOTSORTED, null);
        } catch (Exception ex) {
            alert("failure");
        }
    }

    private void doAllFriends() {
        setVisible(false);
        dispose();
        try {
            Friends.startFriends(Friend.FRIENDS_NOTSORTED, null);
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doNotification() {
        this.setVisible(false);
        this.dispose();
        try {
            Notifications.startNotification();

        } catch (Exception ex) {
            alert("error starting notifications");
        }
    }

    public Home() throws Exception {

        super(3, 3);
        setFocusable(true);
        //======== 3 rows and 3 colomns
        BButton friendsSearch = new BButton() {

            @Override
            public void doEnter() {
                doFriends();
            }
        };
        friendsSearch.setReadText("Friends search");
        add(friendsSearch, 0, 0);
        BButton profile = new BButton() {

            @Override
            public void doEnter() {
                this.setVisible(false);
                dispose();
                try {
                    ViewFriend.viewFriend(User.getUser().getUsername(), 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        };
        profile.setReadText("Profile");
        add(profile, 0, 1);
        BButton notification = new BButton() {

            @Override
            public void doEnter() {
                doNotification();
            }
        };
        notification.setReadText("Notification");
        add(notification, 0, 2);
        //=========
        BButton friends = new BButton() {

            @Override
            public void doEnter() {
                doAllFriends();
            }
        };
        friends.setReadText("Friends");
        add(friends, 1, 0);
        BButton status = new BButton() {

            @Override
            public void doEnter() {
            }
        };
        status.setReadText("Status");
        add(status, 1, 1);
        BButton settings = new BButton() {

            @Override
            public void doEnter() {
                doGridFriends();
            }
        };
        settings.setReadText("friends summery");
        add(settings, 1, 2);
        BButton notassi1 = new BButton();
        notassi1.setReadText("about us");
        add(notassi1, 2, 0);
        BButton notassi2 = new BButton() {

            @Override
            public void doEnter() {
                try {
                    doMessages();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        notassi2.setReadText("Messages");
        add(notassi2, 2, 1);
        BButton logout = new BButton() {
            @Override
            public void doEnter() {
                doLogout();
            }
        };
        logout.setReadText("log out");
        add(logout, 2, 2);
        revalidateAudioPosition();
        BLog.writeLog("Entered home");
    }

    private void doMessages() throws Exception {
        setVisible(false);
        dispose();
        Messages.startMassages();
    }

    private void doLogout() {
        setVisible(false);
        dispose();
        try {
            Logout log = new Logout();
            log.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String args[]) throws Exception {
        Home h = new Home();
        h.setVisible(true);
    }
}
