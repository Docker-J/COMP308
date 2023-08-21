package tme3.events;

import tme3.Controller;
import tme3.Event;

/**
 * Class that represents Bell event of the greenhouse.
 */
public class Bell extends Event {

  /**
   * Constructor. It adds (ring -1) Bell events to the controller.
   * 
   * @param delayTime
   * @param ring
   * @param controller
   */
  public Bell(long delayTime, int ring, Controller controller) {
    super(delayTime, controller);
    for (int i = 1; i < ring; i++) {
      this.controller.addEvent("Bell", delayTime + 2000 * i, 0, controller);
    }
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