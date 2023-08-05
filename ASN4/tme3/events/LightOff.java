package tme3.events;

import tme3.Controller;
import tme3.Event;

public class LightOff extends Event {

    public LightOff(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    @Override
    public void action() {
        this.controller.setVariable("Light", "false");
    }

    public String toString() {
        return "Light is off";
    }

}
