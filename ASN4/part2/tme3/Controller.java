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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tme3.events.Bell;
import tme3.events.Terminate;

/**
 * Abstract Class that represents controller.
 * 
 * @author: Junesung Lee
 * @date : Aug 23, 2023
 */
public abstract class Controller implements Serializable {
  // A class from java.util to hold Event objects:
  private List<Event> eventList = new ArrayList<Event>();

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
   * Method that pause all the events in the event list
   */
  public void pauseEvents() {
    for (Event event : eventList) {
      event.pause();
    }
    this.updateFlag(false);
  }

  /**
   * Method that resume all the event in the event lists
   */
  public void resumeEvents() {
    for (Event event : eventList) {
      event.resume();
    }
    this.updateFlag(true);
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
      JOptionPane.showMessageDialog(this.getGUI(), "The file contains wrong event", "Error",
          JOptionPane.ERROR_MESSAGE, null);
      addEvent(new Terminate(0, this));
    }
  }

  /**
   * Create Bell Event that has an extra parameter, ring and add Event to the
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
      JOptionPane.showMessageDialog(this.getGUI(), "The file contains wrong event", "Error",
          JOptionPane.ERROR_MESSAGE, null);
      addEvent(new Terminate(0, this));
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

  /**
   * Abstract method to implement that append text to the GUI
   * 
   * @param message String message to append
   */
  public abstract void appendText(String message);

  /**
   * Abstract method to implement that update the flag
   * 
   * @param isRunning Boolean value to update
   */
  public abstract void updateFlag(boolean isRunning);

  /**
   * Abstract method to implement that update the flag
   * 
   * @param isRunning Boolean value to update
   */
  public abstract JFrame getGUI();
} /// :~
