package tme3.events;

import tme3.Controller;
import tme3.Event;

public class WaterOff extends Event {
    public WaterOff(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    @Override
    public void action() {
        this.controller.setVariable("Water", false);
    }

    public String toString() {
        return "Greenhouse water is off";
    }
}
