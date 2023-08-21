package tme3.events;

import tme3.Controller;
import tme3.Event;

/**
 * Class that represents FansOff event of the greenhouse.
 */
public class FansOff extends Event {

    /**
     * Constructor.
     * 
     * @param delayTime
     * @param controller
     */
    public FansOff(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    /**
     * Method that performs proper action for the Event.
     * This method will call setVariable method of the controller with key of
     * "Fans" and value of false
     * 
     */
    @Override
    public void action() {
        this.controller.setVariable("Fans", false);
    }

    /**
     * Returns the String that represents the Event
     * 
     * @return "Greenhouse fans are off!"
     */
    public String toString() {
        return "Greenhouse fans are off";
    }
}
