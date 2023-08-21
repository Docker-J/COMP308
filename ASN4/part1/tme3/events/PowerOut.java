package tme3.events;

import tme3.Controller;
import tme3.ControllerException;
import tme3.Event;

/**
 * Class that represents PowerOut event of the greenhouse.
 */
public class PowerOut extends Event {

    /**
     * Constructor.
     * 
     * @param delayTime
     * @param controller
     */
    public PowerOut(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    /**
     * Method that performs proper action for the Event.
     * This will call setVariable method of the controller with key of
     * "errorcode" and value of 2, and "PowerOn" with false.
     * 
     * @throws ControllerException
     */
    @Override
    public void action() throws ControllerException {
        this.controller.setVariable("errorcode", 2);
        this.controller.setVariable("poweron", false);

        throw new ControllerException("PowerOut");
    }

    /**
     * Returns the String that represents the Event
     * 
     * @return "Power Outage!"
     */
    public String toString() {
        return "Power Outage!";
    }
}