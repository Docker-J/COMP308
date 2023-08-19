package tme3.events;

import tme3.Controller;
import tme3.Event;

public class Bell extends Event {
  public Bell(long delayTime, int ring, Controller controller) {
    super(delayTime, controller);
    for (int i = 1; i < ring; i++) {
      this.controller.addEvent("Bell", delayTime + 2000 * i, 0, controller);
    }
  }

  @Override
  public void action() {
    // nothing to do
  }

  public String toString() {
    return "Bing!";
  }
}