package tme3.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tme3.Controller;
import tme3.Event;

public class Restart extends Event {
    private String eventsFile;

    public Restart(long delayTime, Controller controller, String filename) {
        super(delayTime, controller);
        eventsFile = filename;
    }

    public void action() {
        try {
            File file = new File(eventsFile);
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

                    if (event.equals("Bell")) {
                        for (int i = 0; i < ring; i++) {
                            this.controller.addEvent("Bell", time, controller);
                            time += 2000;
                        }
                    } else {
                        this.controller.addEvent(event, time, controller);
                    }
                }
            }
            scanner.close();
            this.controller.updateFlag(true);
        } catch (FileNotFoundException exception) {
            System.out.print("No file exists");
        }
    }

    public String toString() {
        return "Restarting system";
    }
}
