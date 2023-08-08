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

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tme3.*;

interface Fixable {
  // turns Power on, fix window and zeros out error codes
  void fix();

  // logs to a text file in the current directory called fix.log
  // prints to the console, and identify time and nature of
  // the fix
  void log();
}

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

      System.exit(-1);
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  int getError() {
    return errorcode;
  };

  Fixable getFixable(int errorcode) {
    if (errorcode == 1) {
      return new FixWindow();
    }

    return new PowerOn();
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

    public Bell(long delayTime) {
      super(delayTime);
    }

    public void action() {
      // nothing to do
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
        File file = new File("./ASN3/" + eventsFile);
        Scanner scanner = new Scanner(file);
        Pattern pattern = Pattern.compile("Event=([^,]+),time=([^,]+)(?:,rings=([^,]+))?");

        while (scanner.hasNextLine()) {
          Matcher m = pattern.matcher(scanner.nextLine());

          while (m.find()) {
            String event = m.group(1);
            Long time = Long.parseLong(m.group(2));
            int ring = 1;
            if (m.group(3) != null) {
              ring = Integer.parseInt(m.group(3));
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
                for (int i = 0; i < ring; i++) {
                  addEvent(new Bell(time));
                  time += 2000;
                }
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
                System.out.println("Wrong Event");
            }
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
