//: innerclasses/controller/Controller.java
// The reusable framework for control systems.
// From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

/***********************************************************************
 * Adapated for COMP308 Java for Programmer, 
 *		SCIS, Athabasca University
 *
 * Assignment: TME3
 * @author: Steve Leung
 * @date  : Oct 21, 2006
 *
 */

package tme3;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public abstract class Controller implements Serializable {
  // A class from java.util to hold Event objects:
  private List<Event> eventList = new ArrayList<Event>();

  public void addEvent(Event c) {
    Thread t = new Thread(c);
    t.start();
  }

  public void pauseEvents() {
    for (Event event : eventList) {
      event.pause();
    }
    this.updateFlag(false);
  }

  public void resumeEvents() {
    for (Event event : eventList) {
      event.resume();
    }
    this.updateFlag(true);
  }

  public void addEvent(String eventName, long delayTime, Controller controller) {
    try {
      Class<?> someEvent = Class.forName("tme3.events." + eventName);
      Constructor<?> constructor = someEvent.getConstructor(long.class, Controller.class);
      Event event = (Event) constructor.newInstance(delayTime, controller);
      eventList.add(event);
      this.addEvent(event);
    } catch (Exception ex) {
      System.out.println(ex);
    }
  }

  public List<Event> getEvents() {
    return eventList;
  }

  public void removeEvent(Event event) {
    eventList.remove(event);
  }

  public abstract void shutdown();

  public abstract void setVariable(String key, Object value);

  public abstract void appendText(String message);

  public abstract void updateFlag(boolean isRunning);
} /// :~
