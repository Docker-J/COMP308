package tme3.events;

import tme3.Controller;
import tme3.Event;

public class Bell extends Event {

  public Bell(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  @Override
  public void action() {
    // nothing to do
  }

  public String toString() {
    return "Bing!";
  }
}