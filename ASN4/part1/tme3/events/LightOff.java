package tme3.events;

import tme3.Controller;
import tme3.Event;

/**
 * Class that represents LightOff event of the greenhouse.
 */
public class LightOff extends Event {

    /**
     * Constructor.
     * 
     * @param delayTime
     * @param controller
     */
    public LightOff(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    /**
     * Method that performs proper action for the Event.
     * This will call setVariable method of the controller with key of
     * "Light" and value of false.
     * 
     */
    @Override
    public void action() {
        this.controller.setVariable("Light", false);
    }

    /**
     * Returns the String that represents the Event
     * 
     * @return "Light is off!"
     */
    public String toString() {
        return "Light is off";
    }

}
