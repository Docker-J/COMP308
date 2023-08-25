package tme3.events;

import tme3.Controller;
import tme3.Event;

/**
 * Class that represents Terminate event of the greenhouse.
 */
public class Terminate extends Event {

    /**
     * Constructor.
     * 
     * @param delayTime
     * @param controller
     */
    public Terminate(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    /**
     * Method that performs proper action for the Event.
     * This will finish the program.
     * 
     */
    @Override
    public void action() {
        for (Event event : this.controller.getEvents()) {
            event.stop();
        }
        System.exit(0);
    }

    /**
     * Returns the String that represents the Event
     * 
     * @return "Terminating!"
     */
    public String toString() {
        return "Terminating";
    }
}
