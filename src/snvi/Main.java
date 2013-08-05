/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snvi;

import catchaction.core.ASystem;
import util.BLog;

/**
 *
 * @author kanishka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        ASystem.systemPositional(true);
        ASystem.setIsLinear(false);
        Login l = new Login();
        l.Welcome_Speech = "welcome to login";
        l.setVisible(true);
        BLog.writeLog("Start");
    }
}
