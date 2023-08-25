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

import tme3.events.Bell;

/**
 * Abstract Class that act as a controller
 */
public abstract class Controller implements Serializable {
  // A class from java.util to hold Event objects:
  private List<Event> eventList = new ArrayList<>();

  /**
   * Create and start thread with the Event c
   * 
   * @param c Event
   */
  public void addEvent(Event c) {
    Thread t = new Thread(c);
    t.start();
  }

  /**
   * Create with the parameters and add Event to the eventList and call
   * addEvent(Event c) to start the event
   * 
   * @param eventName  Name of the event
   * @param delayTime  Delay time of the Event
   * @param controller Controller of the Event
   */
  public void addEvent(String eventName, long delayTime, Controller controller) {
    try {
      Class<?> someEvent = Class.forName("tme3.events." + eventName);
      Constructor<?> constructor = someEvent.getConstructor(long.class, Controller.class);
      Event event = (Event) constructor.newInstance(delayTime, controller);
      eventList.add(event);
      this.addEvent(event);
    } catch (Exception ex) {
      System.out.println("Wrong Event");
      System.exit(-1);
    }
  }

  /**
   * Create Bell Event that has an extra parameter, ringm and add Event to the
   * eventList and call
   * addEvent(Event c) to start the event
   * 
   * @param eventName  Name of the event
   * @param delayTime  Delay time of the Event
   * @param ring       Number of rings for Bell event
   * @param controller Controller of the Event
   */
  public void addEvent(String eventName, long delayTime, int ring, Controller controller) {
    try {
      Class<?> someEvent = Class.forName("tme3.events." + eventName);
      Constructor<?> constructor = someEvent.getConstructor(long.class, int.class, Controller.class);
      Event event = (Bell) constructor.newInstance(delayTime, ring, controller);
      eventList.add(event);
      this.addEvent(event);
    } catch (Exception ex) {
      System.out.println("Wrong Event");
      System.exit(-1);
    }
  }

  /**
   * Method that returns eventList which contains the list of the Events that are
   * waiting to be actioned
   * 
   * @return List of the Events that are waiting to action
   */
  public List<Event> getEvents() {
    return eventList;
  }

  /**
   * Remove the Event event from the eventList
   * 
   * @param event Targeted Event to remove from the list
   */
  public void removeEvent(Event event) {
    eventList.remove(event);
  }

  /**
   * Abstract method to implment that go through shutdown process
   * 
   * @param delayTime
   */
  public abstract void shutdown(long delayTime);

  /**
   * Abstract method to implement that set the variable with the key and value
   * pair
   * 
   * @param key
   * @param value
   */
  public abstract void setVariable(String key, Object value);

  // public abstract Object getVariable(String key);

} /// :~
