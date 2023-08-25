//: innerclasses/GreenhouseControls.java
// This produces a specific application of the
// control system, all in a single class. Inner
// classes allow you to encapsulate different
// functionality for each type of event.
// From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

/***********************************************************************
 * Adapated for COMP308 Java for Programmer, 
 *		SCIS, Athabasca University
 *
 * Assignment: TME3
 * @author: Steve Leung
 * @date  : Oct 21, 2005
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tme3.Controller;
import tme3.Event;
import tme3.Fixable;
import tme3.events.Restart;
import tme3.events.Terminate;
import tme3.fixable.FixWindow;
import tme3.fixable.PowerOn;

/**
 * Class that represents controller of the greenhouse
 * 
 * @author: Junesung Lee
 * @date: Aug 3, 2023
 * 
 *        Compile: javac *.java tme3/*.java tme3/fixable/*.java
 *        tme3/events/*.java
 * 
 *        Running: java GreenhouseControls -f <filename>
 *        java GreenhouseControls -d dump.out
 */
public class GreenhouseControls extends Controller {
  // private ArrayList<Tuple<String, Object>> variables = new ArrayList<>();
  private HashMap<String, Object> variables = new HashMap<>();
  private long lastEventDelayTime;

  @Override
  public void setVariable(String key, Object value) {
    variables.put(key, value);
  }

  @Override
  public void shutdown(long delayTime) {
    this.addEvent(new Terminate(0, this));
    try {
      FileWriter errorLog = new FileWriter("error.log");
      for (Map.Entry<String, Object> entry : variables.entrySet()) {
        if (entry.getKey().equals("errorcode")) {
          int errorcode = (int) entry.getValue();
          if (errorcode == 1) {
            System.out.println(System.currentTimeMillis() + " WindowMalfunction " + errorcode);
            errorLog.write(System.currentTimeMillis() + " WindowMalfunction " + errorcode);
          } else if (errorcode == 2) {
            System.out.println(System.currentTimeMillis() + " PowerOut " + errorcode);
            errorLog.write(System.currentTimeMillis() + " PowerOut " + errorcode);
          }
        }
      }
      errorLog.close();

      lastEventDelayTime = delayTime;

      FileOutputStream dumpOut = new FileOutputStream(new File("dump.out"));
      ObjectOutputStream out = new ObjectOutputStream(dumpOut);
      out.writeObject(this);
      out.close();
      dumpOut.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  /**
   * Method that prints out the usage of the program
   */
  public static void printUsage() {
    System.out.println("Correct format: ");
    System.out.println("  java GreenhouseControls -f <filename>, or");
    System.out.println("  java GreenhouseControls -d dump.out");
  }

  /**
   * Method that returns the errorcode of the greenhouse
   * 1 for window malfunction, 2 for power out
   * 
   * @return Integer value of the errorcode, getting from variables attribute with
   *         key of "errorcode"
   */
  int getError() {
    return (Integer) variables.get("errorcode");
  };

  /**
   * Method that returns the proper Fixable object depends on the errorcode to fix
   * the greenhouse
   * 
   * @param errorcode integer value of the errorcode
   * @return Proper Fixable object. errorcode 1 returns FixWindow, else returns
   *         PowerOn
   */
  Fixable getFixable(int errorcode) {
    if (errorcode == 1) {
      return new FixWindow(this);
    }

    return new PowerOn(this);
  }

  /**
   * Methods that returns the delay time of the malfunction event
   * 
   * @return long value
   */
  public long getLastEventTime() {
    return lastEventDelayTime;
  }

  /**
   * Method that restores the serialized greenhouse object from the dump file
   * 
   * @param dump Name of the dumpfile in String
   */
  public void restore(String dump) {
    try {
      FileInputStream file = new FileInputStream(new File(dump));
      ObjectInputStream ois = new ObjectInputStream(file);
      GreenhouseControls gc = (GreenhouseControls) ois.readObject();
      ois.close();
      file.close();

      gc.getFixable(gc.getError()).fix();
      List<Event> leftEvents = gc.getEvents();
      long lastEventDelayTime = gc.getLastEventTime();
      for (Event event : leftEvents) {
        event.start(lastEventDelayTime);
        gc.addEvent(event);
      }
    } catch (Exception e) {
      System.out.println(e);
      System.out.println("No file found");
    }
  }

  // ---------------------------------------------------------
  public static void main(String[] args) {
    try {
      String option = args[0];
      String filename = args[1];

      if (!(option.equals("-f")) && !(option.equals("-d"))) {
        System.out.println("Invalid option");
        printUsage();
      }

      GreenhouseControls gc = new GreenhouseControls();

      if (option.equals("-f")) {
        gc.addEvent(new Restart(0, gc, filename));
      }

      if (option.equals("-d")) {
        gc.restore(filename);
      }

    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invalid number of parameters");
      printUsage();
    }
  }
}

/// :~
