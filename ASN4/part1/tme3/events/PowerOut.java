package tme3.events;

import tme3.Controller;
import tme3.ControllerException;
import tme3.Event;

public class PowerOut extends Event {

    public PowerOut(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    @Override
    public void action() throws ControllerException {
        this.controller.setVariable("errorcode", 2);
        this.controller.setVariable("PowerOn", false);

        throw new ControllerException("PowerOut");
    }

    public String toString() {
        return "Power Outage!";
    }
}