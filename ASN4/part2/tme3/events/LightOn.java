package tme3.events;

import tme3.Controller;
import tme3.Event;

/**
 * Class that represents LightOn event of the greenhouse.
 * 
 * @author: Junesung Lee
 * @date : Aug 23, 2023
 */
public class LightOn extends Event {

    /**
     * Constructor.
     * 
     * @param delayTime
     * @param controller
     */
    public LightOn(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    /**
     * Method that performs proper action for the Event.
     * This will call setVariable method of the controller with key of
     * "Light" and value of true.
     * 
     */
    @Override
    public void action() {
        this.controller.setVariable("Light", "true");
    }

    /**
     * Returns the String that represents the Event
     * 
     * @return "Light is on!"
     */
    public String toString() {
        return "Light is on";
    }

}
