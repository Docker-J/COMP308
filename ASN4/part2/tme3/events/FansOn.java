package tme3.events;

import tme3.Controller;
import tme3.Event;

public class FansOn extends Event {

    public FansOn(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    @Override
    public void action() {
        this.controller.setVariable("Fans", "true");
    }

    public String toString() {
        return "Greenhouse fans are on";
    }
}
