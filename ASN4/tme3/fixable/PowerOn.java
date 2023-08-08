package tme3.fixable;

import tme3.Controller;
import tme3.Fixable;

public class PowerOn implements Fixable {
    Controller controller;

    public PowerOn(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void fix() {
        this.controller.setVariable("poweron", true);
        this.controller.setVariable("errorcode", 0);
        this.log();
    }

    @Override
    public void log() {
        System.out.println("Power On!");
    }
}