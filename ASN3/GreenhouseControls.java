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

/*
 * Edited By: Junesung Lee
 * Student ID: 3643836
 * Date: Aug 1st, 2023
 * 
 * Description:
 * public GreenhouseControls() - Default Constructor
 * public void shutdown() - log the error and serialize the controller when the error occurs to restore the controller later
 * int getError() - return errorcode attribute
 * Fixable getFixable(int errorcode) - return Fixable obejct depends on the errorcode. For 1, return FixWindow obejct. For 2, return PowerOn object.
 * Events Inner Classes
 *  public class LightOn
 *    public LightOn(long delaytime) - constructor
 *    public void action() - set the light attribute of the GreenhouseControls obejct to true
 *    public String toString() - return "Light is on"
 *  public class LightOn
 *    public LightOff(long delaytime) - constructor
 *    public void action() - set the light attribute of the GreenhouseControls obejct to false
 *    public String toString() - return "Light is off"
 *  public class WaterOn
 *    public WaterOn(long delaytime) - constructor
 *    public void action() - set the water attribute of the GreenhouseControls obejct to true
 *    public String toString() - return "Greenhouse water is on"
 *  public class WaterOff
 *    public LightOn(long delaytime) - constructor
 *    public void action() - set the water attribute of the GreenhouseControls obejct to false
 *    public String toString() - return "Greenhouse water is off"
 *  public class FansOn
 *    public FansOn(long delaytime) - constructor
 *    public void action() - set the fans attribute of the GreenhouseControls obejct to true
 *    public String toString() - return "Greenhouse fans are on"
 *  public class FansOff
 *    public FansOff(long delaytime) - constructor
 *    public void action() - set the fans attribute of the GreenhouseControls obejct to false
 *    public String toString() - return "Greenhouse fans are off"
 *  public class ThermostatNight
 *    public ThermostatNight(long delaytime) - constructor
 *    public void action() - set the thermostat attribute of the GreenhouseControls obejct to "Night"
 *    public String toString() - return "Thermostat on night setting"
 *  public class ThermostatDay
 *    public ThermostatDay(long delaytime) - constructor
 *    public void action() - set the thermostat attribute of the GreenhouseControls obejct to "Day"
 *    public String toString() - return "Thermostat on day setting"
 *  public class Bell
 *    public Bell(long delayTime, int ring) - constructor, add (ring - 1) Bell event to the GrennhouseControls object with 2000msec of delay time between the each Bell event
 *    public String toString() - return "Bing!"
 *  public class WindowMalfunction
 *    public WindowMalfunction(long delaytime) - constructor
 *    public void action() - set the errorcode and windowok attributes of the GreenhouseControls object to 1 and false respectively and throw ControllerException
 *    public String toString() - return "Window Malfunction!"
 *  public class PowerOut
 *    public Powerout(long delaytime) - constructor
 *    public void action() - set the errocode and poweron attributes of the GreenhouseControls object to 2 and false respectively and throw ControllerException
 *    public String toString() - return "Power Outage"
 *  public class Restart
 *    public Restart(long delaytime, filename) - constructor, set the eventFile attribute of the GreenhouseControls object to filename
 *    public void action() - try to read the file, filename, add events listed in the file. If the format of the file is wrong or file does not exists, program will print out the problem and will be terminated.
 *    public String toString() - return "Restarting system"
 * Fixable Inner Classes
 *  public class PowerOn
 *    public PowerOn() - Default constructor
 *    public void fix() - set the errorcode and poweron attributes of the GreenhouseControls object to 0 and true respectively and call log() function
 *    public void log() - prints out "Power On!"
 *  public class FixWindow
 *    public FixWindow() - Default constructor
 *    public void fix() - set the errorcode and windowok attributes of the GreenhouseControls object to 0 and true respectively and call log() function
 *    public void log() - prints out "Window Fixed!"
 * 
 * public class Restore
 *  public Restore(String dump) - Constructor. Try to read the file, dump, serialized GreenhouseControls object, and fix the issue with proper Fixable object then restore and run the GreenhouseControls object from where it left
 * 
 * public void printUsage() - prints out the proper usage of the program
 * 
 * Compile: javac *.java
 * Run: java GreenhouseControls -f <filename>, or
 *      java GreenhouseControls -d dump.out
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tme3.Controller;
import tme3.ControllerException;
import tme3.Event;

public class GreenhouseControls extends Controller {
  private boolean light = false;
  private boolean water = false;
  private boolean fans = false;
  private boolean windowok = true;
  private boolean poweron = true;
  private String thermostat = "Day";
  private String eventsFile = "examples1.txt";
  private int errorcode = 0;

  @Override
  public void shutdown() {
    try {
      FileWriter errorLog = new FileWriter("error.log");
      if (errorcode == 1) {
        System.out.println(System.currentTimeMillis() + " WindowMalfunction " + errorcode);
        errorLog.write(System.currentTimeMillis() + " WindowMalfunction " + errorcode);
      } else if (errorcode == 2) {
        System.out.println(System.currentTimeMillis() + " PowerOut " + errorcode);
        errorLog.write(System.currentTimeMillis() + " PowerOut " + errorcode);
      }
      errorLog.close();

      FileOutputStream dumpOut = new FileOutputStream(new File("dump.out"));
      ObjectOutputStream out = new ObjectOutputStream(dumpOut);
      out.writeObject(this);
      out.close();
      dumpOut.close();
    } catch (IOException exception) {
      System.out.println(exception);
    } finally {
      this.addEvent(new Terminate(0));
    }
  }

  int getError() {
    return errorcode;
  };

  Fixable getFixable(int errorcode) {
    if (errorcode == 1) {
      return new FixWindow();
    } else if (errorcode == 2) {
      return new PowerOn();
    }
    return null;
  }

  public class LightOn extends Event {
    public LightOn(long delayTime) {
      super(delayTime);
    }

    public void action() {
      // Put hardware control code here to
      // physically turn on the light.
      light = true;
    }

    public String toString() {
      return "Light is on";
    }
  }

  public class LightOff extends Event {
    public LightOff(long delayTime) {
      super(delayTime);
    }

    public void action() {
      // Put hardware control code here to
      // physically turn off the light.
      light = false;
    }

    public String toString() {
      return "Light is off";
    }
  }

  public class WaterOn extends Event {
    public WaterOn(long delayTime) {
      super(delayTime);
    }

    public void action() {
      // Put hardware control code here.
      water = true;
    }

    public String toString() {
      return "Greenhouse water is on";
    }
  }

  public class WaterOff extends Event {
    public WaterOff(long delayTime) {
      super(delayTime);
    }

    public void action() {
      // Put hardware control code here.
      water = false;
    }

    public String toString() {
      return "Greenhouse water is off";
    }
  }

  public class FansOn extends Event {
    public FansOn(long delayTime) {
      super(delayTime);
    }

    public void action() {
      fans = true;
    }

    public String toString() {
      return "Greenhouse fans are on";
    }
  }

  public class FansOff extends Event {
    public FansOff(long delayTime) {
      super(delayTime);
    }

    public void action() {
      fans = false;
    }

    public String toString() {
      return "Greenhouse fans are off";
    }
  }

  public class ThermostatNight extends Event {
    public ThermostatNight(long delayTime) {
      super(delayTime);
    }

    public void action() {
      // Put hardware control code here.
      thermostat = "Night";
    }

    public String toString() {
      return "Thermostat on night setting";
    }
  }

  public class ThermostatDay extends Event {
    public ThermostatDay(long delayTime) {
      super(delayTime);
    }

    public void action() {
      // Put hardware control code here.
      thermostat = "Day";
    }

    public String toString() {
      return "Thermostat on day setting";
    }
  }

  // An example of an action() that inserts a
  // new one of itself into the event list:
  public class Bell extends Event {
    int ring;

    public Bell(long delayTime, int ring) {
      super(delayTime);
      this.ring = ring;
      // add Bell events if the number of the rings is greater than 0
      // when the first bell event actioned and spearated them by 2000 msec.
      for (int i = 1; i < ring; i++) {
        addEvent(new Bell(delayTime + 2000 * i, 0));
      }
    }

    public void action() {
    }

    public String toString() {
      return "Bing!";
    }
  }

  public class WindowMalfunction extends Event {

    public WindowMalfunction(long delayTime) {
      super(delayTime);
    }

    @Override
    public void action() throws ControllerException {
      errorcode = 1;
      windowok = false;
      throw new ControllerException("WindowMalfunction");
    }

    public String toString() {
      return "Winwdow Malfunction!";
    }
  }

  public class PowerOut extends Event {

    public PowerOut(long delayTime) {
      super(delayTime);
    }

    @Override
    public void action() throws ControllerException {
      errorcode = 2;
      poweron = false;
      throw new ControllerException("PowerOut");
    }

    public String toString() {
      return "Power Outage!";
    }
  }

  public class PowerOn implements Fixable {

    @Override
    public void fix() {
      poweron = true;
      errorcode = 0;
      this.log();
    }

    @Override
    public void log() {
      System.out.println("Power On!");
    }
  }

  public class FixWindow implements Fixable {

    @Override
    public void fix() {
      windowok = true;
      errorcode = 0;
      this.log();
    }

    @Override
    public void log() {
      System.out.println("Window Fixed!");
    }
  }

  public class Restart extends Event {
    public Restart(long delayTime, String filename) {
      super(delayTime);
      eventsFile = filename;
    }

    public void action() {
      try {
        File file = new File(eventsFile);
        Scanner scanner = new Scanner(file);
        Pattern pattern = Pattern.compile("Event=([^,]+),time=([0-9]+)(?:,rings=([0-9]+))?");

        while (scanner.hasNextLine()) {
          Matcher m = pattern.matcher(scanner.nextLine());

          if (!m.matches()) {
            System.out.println("Wrong Event File Format");
            System.exit(-1);
          }

          String event = m.group(1);
          Long time = Long.parseLong(m.group(2));
          int ring = 1;
          if (m.group(3) != null) {
            ring = Integer.parseInt(m.group(3));
          }

          if (ring <= 0) {
            System.out.println("Wrong Event File Format");
            System.exit(-1);
          }

          switch (event) {
            case "LightOn":
              addEvent(new LightOn(time));
              break;
            case "LightOff":
              addEvent(new LightOff(time));
              break;
            case "WaterOn":
              addEvent(new WaterOn(time));
              break;
            case "WaterOff":
              addEvent(new WaterOff(time));
              break;
            case "FansOn":
              addEvent(new FansOn(time));
              break;
            case "FansOff":
              addEvent(new FansOff(time));
              break;
            case "ThermostatNight":
              addEvent(new ThermostatNight(time));
              break;
            case "ThermostatDay":
              addEvent(new ThermostatDay(time));
              break;
            case "Bell":
              addEvent(new Bell(time, ring));
              break;
            case "WindowMalfunction":
              addEvent(new WindowMalfunction(time));
              break;
            case "PowerOut":
              addEvent(new PowerOut(time));
              break;
            case "Terminate":
              addEvent(new Terminate(time));
              break;
            default:
              System.out.println("Wrong Event Name");
              System.exit(-1);
          }

        }
        scanner.close();

      } catch (FileNotFoundException exception) {
        System.out.print("No file exists");
      }
    }

    public String toString() {
      return "Restarting system";
    }
  }

  public class Terminate extends Event {
    public Terminate(long delayTime) {
      super(delayTime);
    }

    public void action() {
      System.exit(0);
    }

    public String toString() {
      return "Terminating";
    }
  }

  public class Restore {

    public Restore(String dump) {
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
        }
        gc.run();
      } catch (Exception e) {
        System.out.println("No file found");
      }
    }
  }

  public static void printUsage() {
    System.out.println("Correct format: ");
    System.out.println("  java GreenhouseControls -f <filename>, or");
    System.out.println("  java GreenhouseControls -d dump.out");
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
        gc.addEvent(gc.new Restart(0, filename));
      } else if (option.equals("-d")) {
        gc.new Restore(filename);
      }

      gc.run();
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invalid number of parameters");
      printUsage();
    }
  }

} /// :~

interface Fixable {
  // turns Power on, fix window and zeros out error codes
  void fix();

  // logs to a text file in the current directory called fix.log
  // prints to the console, and identify time and nature of
  // the fix
  void log();
}
