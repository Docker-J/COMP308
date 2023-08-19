package tme3.events;

import tme3.Controller;
import tme3.Event;

public class FansOff extends Event {

    public FansOff(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    @Override
    public void action() {
        this.controller.setVariable("Fans", "false");
    }

    public String toString() {
        return "Greenhouse fans are off";
    }
}
