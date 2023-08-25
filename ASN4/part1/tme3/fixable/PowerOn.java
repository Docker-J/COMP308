package tme3.fixable;

import java.io.FileWriter;
import java.io.IOException;

import tme3.Controller;
import tme3.Fixable;

/**
 * Class that represents the Power On activity of the greenhouse
 * 
 * @author: Junesung Lee
 * @date : Aug 3, 2023
 */
public class PowerOn implements Fixable {
    private Controller controller;

    /**
     * Constructor
     * 
     * @param controller
     */
    public PowerOn(Controller controller) {
        this.controller = controller;
    }

    /**
     * Method that is turning on the power.
     * call contorller's setVariable method with key of "poweron" and value of true,
     * and key of "errorcode" and value of 0
     */
    @Override
    public void fix() {
        this.controller.setVariable("poweron", true);
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
            fixLog.write(System.currentTimeMillis() + " Power On!");

            fixLog.close();
        } catch (IOException ex) {
            System.out.println("Failed to log the fix");
        }

        System.out.println("Power On!");
    }
}