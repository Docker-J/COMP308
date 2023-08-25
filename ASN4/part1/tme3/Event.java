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

package tme3;

import java.io.Serializable;

/**
 * Abstract class that represents events of the greenhouse.
 * 
 * @edited: Junesung Lee
 * @date : Aug 23, 2023
 */
public abstract class Event implements Runnable, Serializable {
  private long eventTime;
  protected final long delayTime;
  protected Controller controller;

  private volatile boolean running = true;

  /**
   * Constructor.
   * 
   * @param delayTime  delay time of the event
   * @param controller controller that the event belongs to
   */
  public Event(long delayTime, Controller controller) {
    this.delayTime = delayTime;
    this.controller = controller;
    start();
  }

  /**
   * Method that set the event time to current time + delay time
   */
  public void start() { // Allows restarting
    eventTime = System.currentTimeMillis() + delayTime;
  }

  /**
   * Method that set the event time to current time + delay time - last event
   * delay time
   * Use for the restored events
   * 
   * @param lastEventDelayTime the delay time of the event that ocurred error
   */
  public void start(long lastEventDelayTime) { // Adjust the delay time with the time that last event occured
    eventTime = System.currentTimeMillis() + delayTime - lastEventDelayTime;
  }

  /**
   * Method that check the event is ready to go
   * 
   * @return true if the current time is equal or greater than event time, false
   *         if not.
   */
  public boolean ready() {
    return System.currentTimeMillis() >= eventTime;
  }

  /**
   * Behaviour of the Runnable object.
   * Event will run its action when itself is ready and other's not using the
   * controller
   */
  @Override
  public void run() {
    while (running) {
      if (this.ready()) {
        this.controller.removeEvent(this);
        synchronized (this.controller) {
          try {
            this.action();
            System.out.println(this);
          } catch (ControllerException e) {
            this.controller.shutdown(this.delayTime);
          }
        }
        stop();
      }
    }
  }

  /**
   * Method that stop the event(thread)
   */
  public void stop() {
    this.running = false;
  }

  /**
   * Method that returns the delay time of the event
   * 
   * @return delayTime
   */
  public long getDelayTime() {
    return delayTime;
  }

  /**
   * Abstract method that performs proper action for the Event.
   * 
   * @throws ControllerException
   */
  public abstract void action() throws ControllerException;
} /// :~
