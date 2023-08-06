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

public abstract class Event implements Runnable {
  private long eventTime;
  protected final long delayTime;
  protected Controller controller;

  public Event(long delayTime, Controller controller) {
    this.delayTime = delayTime;
    this.controller = controller;
    start();
  }

  public void start() { // Allows restarting
    eventTime = System.currentTimeMillis() + delayTime;
  }

  public boolean ready() {
    return System.currentTimeMillis() >= eventTime;
  }

  @Override
  public void run() {
    synchronized (this.controller) {
      try {
        this.action();
      } catch (ControllerException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    // try {
    // Thread.sleep(delayTime);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
  }

  public abstract void action() throws ControllerException;
} /// :~
