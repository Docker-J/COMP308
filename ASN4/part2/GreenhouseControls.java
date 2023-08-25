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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tme3.Controller;
import tme3.Event;
import tme3.Fixable;
import tme3.events.Terminate;
import tme3.fixable.FixWindow;
import tme3.fixable.PowerOn;

/**
 * Class that represents controller of the greenhouse
 * 
 * @author: Junesung Lee
 * @date: Aug 3, 2023
 */
public class GreenhouseControls extends Controller {
  // private ArrayList<Tuple<String, Object>> variables = new ArrayList<>();
  private HashMap<String, Object> variables = new HashMap<>();
  private long lastEventDelayTime;

  private transient Greenhouse greenhouse;

  /**
   * Constructor. getting GUI object
   * 
   * @param greenhouse Greenhouse GUI object
   */
  public GreenhouseControls(Greenhouse greenhouse) {
    this.greenhouse = greenhouse;
  }

  /**
   * Method that set Greenhouse GUI object
   * 
   * @param greenhouse Greenhouse GUI object
   */
  public void setGUI(Greenhouse greenhouse) {
    this.greenhouse = greenhouse;
  }

  /**
   * Method that set Greenhouse GUI object
   * 
   * @param greenhouse Greenhouse GUI object
   */
  public JFrame getGUI() {
    return greenhouse;
  }

  /**
   * Method that calls the updateFlag method of the greenhouse
   * 
   * @param isRunning boolean value. true if is running false if not.
   */
  public void updateFlag(boolean isRunning) {
    greenhouse.updateFlag(isRunning);
  }

  @Override
  public void appendText(String message) {
    greenhouse.getTextArea().append(message + "\n");
  }

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
  public GreenhouseControls restore(String dump, Greenhouse greenhouse) {
    GreenhouseControls gc;
    try {
      FileInputStream file = new FileInputStream(new File(dump));
      ObjectInputStream ois = new ObjectInputStream(file);
      gc = (GreenhouseControls) ois.readObject();
      ois.close();
      file.close();
      gc.setGUI(greenhouse);

      greenhouse.updateFlag(true);

      gc.getFixable(gc.getError()).fix();
      List<Event> leftEvents = gc.getEvents();
      long lastEventDelayTime = gc.getLastEventTime();
      for (Event event : leftEvents) {
        event.start(lastEventDelayTime);
        gc.addEvent(event);
      }

      appendText("Restoring System");
      return gc;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(greenhouse, "The file is not a restorable Greenhouse", "Error",
          JOptionPane.ERROR_MESSAGE, null);
      return null;
    }
  }
}
