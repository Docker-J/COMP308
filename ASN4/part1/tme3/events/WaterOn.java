package tme3.events;

import tme3.Controller;
import tme3.Event;

public class WaterOn extends Event {

    public WaterOn(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    @Override
    public void action() {
        this.controller.setVariable("Water", "true");
    }

    public String toString() {
        return "Greenhouse water is on";
    }
}
