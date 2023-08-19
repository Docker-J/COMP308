package tme3.events;

import tme3.Controller;
import tme3.Event;

public class Terminate extends Event {
    public Terminate(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    @Override
    public void action() {
        // should set the button of the Jframe in stop stat
        for (Event event : this.controller.getEvents()) {
            event.stop();
        }
        this.controller.updateFlag(false);
    }

    public String toString() {
        return "Terminating";
    }
}
