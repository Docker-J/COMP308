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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import tme3.Controller;
import tme3.Tuple;
import tme3.events.Restart;

public class GreenhouseControls extends Controller {
  private ArrayList<Tuple> variables = new ArrayList<>();

  @Override
  public void setVariable(String key, String value) {
    variables.put(key, value);
  }

  @Override
  public void shutdown() {
    try {
      FileWriter errorLog = new FileWriter("error.log");
      String errorcode = this.variables.get("errorcode");

      if (errorcode == "1") {
        System.out.println(System.currentTimeMillis() + " WindowMalfunction " + errorcode);
        errorLog.write(System.currentTimeMillis() + " WindowMalfunction " + errorcode);
      } else if (errorcode == "2") {
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
        gc.addEvent(new Restart(0, gc, filename));
      }

      gc.run();
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invalid number of parameters");
      printUsage();
    }
  }

} /// :~
