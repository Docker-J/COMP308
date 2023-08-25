package tme3.events;

import tme3.Controller;
import tme3.Event;

/**
 * Class that represents ThermostatNight event of the greenhouse.
 * 
 * @author: Junesung Lee
 * @date : Aug 23, 2023
 */
public class ThermostatNight extends Event {

  /**
   * Constructor.
   * 
   * @param delayTime
   * @param controller
   */
  public ThermostatNight(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  /**
   * Method that performs proper action for the Event.
   * This will call setVariable method of the controller with key of
   * "Thermostat" and value of "Night".
   * 
   */
  @Override
  public void action() {
    this.controller.setVariable("Thermostat", "Night");

  }

  /**
   * Returns the String that represents the Event
   * 
   * @return "Thermostat on night setting"
   */
  public String toString() {
    return "Thermostat on night setting";
  }
}
