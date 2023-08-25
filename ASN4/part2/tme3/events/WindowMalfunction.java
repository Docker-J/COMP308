package tme3.events;

import tme3.Controller;
import tme3.ControllerException;
import tme3.Event;

/**
 * Class that represents WindowMalfunction event of the greenhouse.
 * 
 * @author: Junesung Lee
 * @date : Aug 23, 2023
 */
public class WindowMalfunction extends Event {

    /**
     * Constructor.
     * 
     * @param delayTime
     * @param controller
     */
    public WindowMalfunction(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    /**
     * Method that performs proper action for the Event.
     * This will call setVariable method of the controller with key of
     * "errorcode" and value of 1, and "WindowOk" with false.
     * 
     * @throws ControllerException
     */
    @Override
    public void action() throws ControllerException {
        this.controller.setVariable("errorcode", 1);
        this.controller.setVariable("WindowOk", false);
        throw new ControllerException("WindowMalfunction");
    }

    /**
     * Returns the String that represents the Event
     * 
     * @return "Window Malfunction!"
     */
    public String toString() {
        return "Winwdow Malfunction!";
    }
}