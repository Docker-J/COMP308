package tme3.events;

import tme3.Controller;
import tme3.Event;

public class ThermostatDay extends Event {

  public ThermostatDay(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  @Override
  public void action() {
    this.controller.setVariable("Thermostat", "Day");

  }

  public String toString() {
    return "Thermostat on day setting";
  }
}
