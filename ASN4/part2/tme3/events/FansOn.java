package tme3.events;

import tme3.Controller;
import tme3.Event;

/**
 * Class that represents FansOn event of the greenhouse.
 * 
 * @author: Junesung Lee
 * @date : Aug 23, 2023
 */
public class FansOn extends Event {

    /**
     * Constructor.
     * 
     * @param delayTime
     * @param controller
     */
    public FansOn(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    /**
     * Method that performs proper action for the Event.
     * This will call setVariable method of the controller with key of
     * "Fans" and value of true.
     * 
     */
    @Override
    public void action() {
        this.controller.setVariable("Fans", "true");
    }

    /**
     * Returns the String that represents the Event
     * 
     * @return "Greenhouse fans are on!"
     */
    public String toString() {
        return "Greenhouse fans are on";
    }
}
