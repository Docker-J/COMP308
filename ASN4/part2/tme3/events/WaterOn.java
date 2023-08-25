package tme3.events;

import tme3.Controller;
import tme3.Event;

/**
 * Class that represents WaterOn event of the greenhouse.
 * 
 * @author: Junesung Lee
 * @date : Aug 23, 2023
 */
public class WaterOn extends Event {

    /**
     * Constructor.
     * 
     * @param delayTime
     * @param controller
     */
    public WaterOn(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    /**
     * Method that performs proper action for the Event.
     * This will call setVariable method of the controller with key of
     * "Water" and value of true.
     * 
     */
    @Override
    public void action() {
        this.controller.setVariable("Water", "true");
    }

    /**
     * Returns the String that represents the Event
     * 
     * @return "Greenhouse water is on"
     */
    public String toString() {
        return "Greenhouse water is on";
    }
}
