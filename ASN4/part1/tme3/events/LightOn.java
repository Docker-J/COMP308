package tme3.events;

import tme3.Controller;
import tme3.Event;

public class LightOn extends Event {

    public LightOn(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    @Override
    public void action() {
        this.controller.setVariable("Light", true);
    }

    public String toString() {
        return "Light is on";
    }

}
