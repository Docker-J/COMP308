package tme3.events;

import tme3.Controller;
import tme3.Event;

public class Terminate extends Event {
    public Terminate(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    @Override
    public void action() {
        System.exit(0);
    }

    public String toString() {
        return "Terminating";
    }
}
