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

/*
* Edited By: Junesung Lee
* Student ID: 3643836
* Date: Aug 1st, 2023
* 
* Description:
* public void addEvent(Event c) - Add Event c to the List, eventList.
* public void run() - Reun through the List, eventList, and trigger the event's action as they are ready. Handling ControllerException.
* public List<Event> getEvents() - return the eventList List
* public abstract void shutdown() - abtract method named shutdown
*/

package tme3;

import java.io.Serializable;
import java.util.*;

public abstract class Controller implements Serializable {
  // A class from java.util to hold Event objects:
  private List<Event> eventList = new ArrayList<Event>();

  public void addEvent(Event c) {
    eventList.add(c);
  }

  public void run() {
    while (eventList.size() > 0)
      // Make a copy so you're not modifying the list
      // while you're selecting the elements in it:
      for (Event e : new ArrayList<Event>(eventList))
        if (e.ready()) {
          System.out.println(e);
          try {
            e.action();
            eventList.remove(e);
          } catch (ControllerException ex) {
            System.out.println(ex);
            eventList.remove(e);
            shutdown();
          }

        }
  }

  public List<Event> getEvents() {
    return eventList;
  }

  public abstract void shutdown(); /// :~

}