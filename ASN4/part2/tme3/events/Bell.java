package tme3.events;

import tme3.Controller;
import tme3.Event;

/**
 * Class that represents Bell event of the greenhouse.
 * 
 * @author: Junesung Lee
 * @date : Aug 23, 2023
 */
public class Bell extends Event {

  /**
   * Constructor. It adds (ring -1) Bell events to the controller.
   * 
   * @param delayTime  long type
   * @param controller
   */
  public Bell(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  /**
   * Method that performs proper action for the Event.
   * Bell Event does not have its action. Just a placeholder.
   * 
   */
  @Override
  public void action() {
    // nothing to do
  }

  /**
   * Returns the String that represents the Event
   * 
   * @return "Bing!"
   */
  public String toString() {
    return "Bing!";
  }
}