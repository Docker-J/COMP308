//: innerclasses/controller/Event.java
// The common methods for any control event.
// From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

/***********************************************************************
 * Adapated for COMP308 Java for Programmer, 
 *		SCIS, Athabasca University
 *
 * Assignment: TME3
 * @author: Steve Leung
 * @date  : Oct. 21, 2006
 *
 * Description: Event abstract class
 *
 */

/*
* Edited By: Junesung Lee
* Student ID: 3643836
* Date: Aug 1st, 2023
* 
* Description:
* public Event(long delayTime) - constructor, set the delaytime attribute and call start() function
* public void start() - set the eventTime attribute to System.currentTimeMillis() + delayTime attribute
* public boolean ready() - return true if System.currentTimeMillis() is greater or equal than the eventTime attribute.
* public abstract void action() trhows ControllerException - abtract method named action and can throw ControllerException
*/

package tme3;

import java.io.Serializable;

public abstract class Event implements Serializable {
  private long eventTime;
  protected final long delayTime;

  public Event(long delayTime) {
    this.delayTime = delayTime;
    start();
  }

  public void start() { // Allows restarting
    eventTime = System.currentTimeMillis() + delayTime;
  }

  public boolean ready() {
    return System.currentTimeMillis() >= eventTime;
  }

  public abstract void action() throws ControllerException;
} /// :~
