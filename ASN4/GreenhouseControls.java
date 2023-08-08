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
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tme3.Event;
import tme3.fixable.FixWindow;
import tme3.fixable.PowerOn;
import tme3.Controller;
import tme3.Fixable;
import tme3.events.Restart;

public class GreenhouseControls extends Controller implements Serializable {
  // private ArrayList<Tuple<String, Object>> variables = new ArrayList<>();
  private HashMap<String, Object> variables = new HashMap<>();

  @Override
  public void setVariable(String key, Object value) {
    variables.put(key, value);
  }

  @Override
  public void shutdown() {
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

      FileOutputStream dumpOut = new FileOutputStream(new File("dump.out"));
      ObjectOutputStream out = new ObjectOutputStream(dumpOut);
      out.writeObject(this);
      out.close();
      dumpOut.close();

      System.exit(-1);
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  public static void printUsage() {
    System.out.println("Correct format: ");
    System.out.println("  java GreenhouseControls -f <filename>, or");
    System.out.println("  java GreenhouseControls -d dump.out");
  }

  int getError() {
    return (Integer) variables.get("errorcode");
  };

  Fixable getFixable(int errorcode) {
    if (errorcode == 1) {
      return new FixWindow(this);
    }

    return new PowerOn(this);
  }

  public void restore(String dump) {
    System.out.println("Restoring System");
    try {
      FileInputStream file = new FileInputStream(new File(dump));
      ObjectInputStream ois = new ObjectInputStream(file);
      GreenhouseControls gc = (GreenhouseControls) ois.readObject();
      ois.close();
      file.close();

      gc.getFixable(gc.getError()).fix();
      List<Event> leftEvents = gc.getEvents();
      for (Event event : leftEvents) {
        event.start();
        gc.addEvent(event);
      }
    } catch (Exception e) {
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

} /// :~
