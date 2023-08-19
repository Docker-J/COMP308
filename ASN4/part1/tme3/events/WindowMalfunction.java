package tme3.events;

import tme3.Controller;
import tme3.ControllerException;
import tme3.Event;

public class WindowMalfunction extends Event {
    int errorcode = 0;
    boolean windowok = false;

    public WindowMalfunction(long delayTime, Controller controller) {
        super(delayTime, controller);
    }

    @Override
    public void action() throws ControllerException {
        this.controller.setVariable("errorcode", 1);
        this.controller.setVariable("WindowOk", false);
        throw new ControllerException("WindowMalfunction");
    }

    public String toString() {
        return "Winwdow Malfunction!";
    }
}