package tme3.fixable;

import java.io.FileWriter;
import java.io.IOException;

import tme3.Controller;
import tme3.Fixable;

/**
 * Class that represents the Fixing Window activity of the greenhouse
 * 
 * @author: Junesung Lee
 * @date: Aug 3, 2023
 */
public class FixWindow implements Fixable {
    private Controller controller;

    /**
     * Constructor
     * 
     * @param controller
     */
    public FixWindow(Controller controller) {
        this.controller = controller;
    }

    /**
     * Method that is fixing window.
     * call controller's setVariable method with key of "windowok" and value of
     * true,
     * and key of "errorcode" and value of 0
     */
    @Override
    public void fix() {
        this.controller.setVariable("windowok", true);
        this.controller.setVariable("errorcode", 0);
        this.log();
    }

    /**
     * Method that logging the detail of the fix
     */
    @Override
    public void log() {
        try {
            FileWriter fixLog = new FileWriter("fix.log");
            fixLog.write(System.currentTimeMillis() + " Window Fixed!");

            fixLog.close();
        } catch (IOException ex) {
            System.out.println("Failed to log the fix");
        }

        this.controller.appendText("Window Fixed!");
    }
}