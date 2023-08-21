package tme3.events;

import tme3.Controller;
import tme3.Event;

/**
 * Class that represents WaterOff event of the greenhouse.
 */
public class WaterOff extends Event {

    /**
     * Constructor.
     * 
     * @param delayTime
     * @param controller
     */
    public WaterOff(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    /**
     * Method that performs proper action for the Event.
     * This will call setVariable method of the controller with key of
     * "Water" and value of false.
     * 
     */
    @Override
    public void action() {
        this.controller.setVariable("Water", false);
    }

    /**
     * Returns the String that represents the Event
     * 
     * @return "Greenhouse water is off"
     */
    public String toString() {
        return "Greenhouse water is off";
    }
}
