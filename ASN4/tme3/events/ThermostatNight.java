package tme3.events;

import tme3.Controller;
import tme3.Event;

public class ThermostatNight extends Event {
  public ThermostatNight(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  @Override
  public void action() {
    this.controller.setVariable("Thermostat", "Night");

  }

  public String toString() {
    return "Thermostat on night setting";
  }
}
