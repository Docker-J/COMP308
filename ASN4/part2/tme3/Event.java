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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Event implements Runnable, Serializable {
  private long eventTime;
  protected final long delayTime;
  protected Controller controller;

  private final Lock lock = new ReentrantLock();
  private volatile boolean running = true;
  private volatile boolean paused = false;

  private long pausedTime;

  public Event(long delayTime, Controller controller) {
    this.delayTime = delayTime;
    this.controller = controller;
    // start();
  }

  public void start() { // Allows restarting
    eventTime = System.currentTimeMillis() + delayTime;
  }

  public boolean ready() {
    return System.currentTimeMillis() >= eventTime;
  }

  @Override
  public void run() {
    start();
    while (running) {
      synchronized (lock) {
        while (paused) {
          try {
            lock.wait();
          } catch (InterruptedException ex) {

          }
        }
      }

      if (this.ready()) {
        synchronized (this.controller) {
          this.controller.removeEvent(this);
          try {
            this.action();
            this.controller.appendText(this.toString());
          } catch (ControllerException e) {
            this.controller.appendText(this.toString());
            this.controller.shutdown();
          }
        }
        stop();
      }
    }
  }

  public void stop() {
    this.running = false;
  }

  public void pause() {
    this.paused = true;
    this.pausedTime = System.currentTimeMillis();
  }

  public void resume() {
    synchronized (lock) {
      paused = false;
      lock.notify();
      eventTime += System.currentTimeMillis() - pausedTime; // compensate the time while paused
    }
  }

  public abstract void action() throws ControllerException;
} /// :~
