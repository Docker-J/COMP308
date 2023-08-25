import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DefaultCaret;

import tme3.Event;
import tme3.events.Restart;
import tme3.events.Terminate;

/**
 * GUI for the Greenhouse
 * 
 * @author Junesung Lee
 * @date Aug 15, 2023
 * 
 *       Compile: javac *.java tme3/*.java tme3/fixable/*.java
 *       tme3/events/*.java
 * 
 *       Running: java Greenhouse
 */
public class Greenhouse extends JFrame implements ActionListener {
    private final String[] buttons_str = { "Start", "Restart", "Terminate", "Suspend", "Resume" };
    private final String[] menuItems_str = { "New Window", "Close Window", "Open Events", "Restore", "Exit" };
    private final int[] menuitems_key = { KeyEvent.VK_N, KeyEvent.VK_C, KeyEvent.VK_O, KeyEvent.VK_R, KeyEvent.VK_E };

    private JButton[] buttons = new JButton[5];
    private JMenuItem[] menuItems = new JMenuItem[5];
    private JMenuItem[] contextMenuItems = new JMenuItem[5];

    private JTextField eventFileTextField;
    private JTextArea textArea;

    private GreenhouseControls gc;
    private String eventFile;
    private Event initialEvent;
    private boolean isRunning = false;
    private boolean isSuspended = false;

    /**
     * Constructor. Initialize the GUI
     */
    public Greenhouse() {
        setTitle("Greenhosue");
        setSize(550, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        for (int i = 0; i < menuItems.length; i++) {
            JMenuItem menuItem = new JMenuItem(menuItems_str[i], menuitems_key[i]);
            menuItem.addActionListener(this);
            menuItems[i] = menuItem;
            fileMenu.add(menuItem);
        }
        mb.add(fileMenu);
        setJMenuBar(mb);

        JPanel p1 = new JPanel(new FlowLayout());
        JLabel eventFileLabel = new JLabel("Event File:");
        eventFileTextField = new JTextField(eventFile, 30);
        eventFileTextField.setEditable(false);
        eventFileTextField.setFocusable(false);
        p1.add(eventFileLabel);
        p1.add(eventFileTextField);
        add(p1, BorderLayout.NORTH);

        textArea = new JTextArea(10, 48);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel p3 = new JPanel(new FlowLayout());
        JPopupMenu pb = new JPopupMenu();
        for (int i = 0; i < buttons.length; i++) {
            JButton button = new JButton(buttons_str[i]);
            button.addActionListener(this);
            button.setEnabled(false);
            buttons[i] = button;
            p3.add(button);
        }

        for (int i = 0; i < contextMenuItems.length; i++) {
            JMenuItem contextMenuItem = new JMenuItem(buttons_str[i]);
            contextMenuItem.addActionListener(this);
            contextMenuItem.setEnabled(false);
            contextMenuItems[i] = contextMenuItem;
            pb.add(contextMenuItem);
            if (i == 1 || i == 2) {
                pb.addSeparator();
            }
        }
        add(p3, BorderLayout.SOUTH);

        p1.setComponentPopupMenu(pb);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    /**
     * Method that returns JTextArea of the GUI
     * 
     * @return textArea attribute that is the JTextArea object
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * Method that updates the components of the GUI depends on the current status
     */
    public void updateComponents() {
        if (isRunning) {
            menuItems[3].setEnabled(false);

            buttons[0].setEnabled(false);
            buttons[1].setEnabled(false);
            buttons[2].setEnabled(true);
            buttons[3].setEnabled(true);
            buttons[4].setEnabled(false);

            contextMenuItems[0].setEnabled(false);
            contextMenuItems[1].setEnabled(false);
            contextMenuItems[2].setEnabled(true);
            contextMenuItems[3].setEnabled(true);
            contextMenuItems[4].setEnabled(false);
        } else { // not running
            menuItems[3].setEnabled(true);

            if (eventFile != null) {
                buttons[0].setEnabled(true);
                buttons[1].setEnabled(true);

                contextMenuItems[0].setEnabled(true);
                contextMenuItems[1].setEnabled(true);

            }
            buttons[2].setEnabled(false);
            buttons[3].setEnabled(false);

            contextMenuItems[2].setEnabled(false);
            contextMenuItems[3].setEnabled(false);
            if (isSuspended) {
                buttons[4].setEnabled(true);
                contextMenuItems[4].setEnabled(true);
            }
        }
    }

    /**
     * Method that set isRunning attribute with running value.
     * Then call updateComponents method
     * 
     * @param running
     */
    public void updateFlag(boolean running) {
        isRunning = running;
        updateComponents();
    }

    /**
     * Method that to makesure if the user likes to close the window
     * Then call updateComponents method
     * If the greenhouse is running, the program will ask user to close the window,
     * if not it will close the window without confirmation
     */
    @Override
    public void dispose() {
        if (isRunning) {
            int options = JOptionPane.showConfirmDialog(this, "Greenhouse is still running.\nAre you wish to continue?",
                    "Warning", JOptionPane.OK_CANCEL_OPTION);
            if (options == JOptionPane.YES_OPTION) {
                gc.addEvent(new Terminate(0, gc)); // stop events from being running
                super.dispose(); // then close the window
            }
        } else {
            super.dispose(); // close the window if the greenhouse is not running
        }
    }

    /**
     * Method to check if there is any other window is presented
     * 
     * @param currentWindow
     * @return true if there are multiple windows, false if not
     */
    public boolean checkWindows(Window currentWindow) {
        Window[] allWindows = Window.getWindows();
        for (Window window : allWindows) {
            if (window != this && window.isVisible()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // New Window
        if (e.getSource() == menuItems[0]) {
            new Greenhouse();
        }
        // Close Window
        if (e.getSource() == menuItems[1]) {
            if (checkWindows(this)) {
                dispose();
            } else {
                dispose();
                System.exit(0);
            }
        }
        // Open Events
        if (e.getSource() == menuItems[2]) {
            JFileChooser chooser = new JFileChooser();
            FileFilter txtFilter = new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isDirectory() || file.getName().toLowerCase().endsWith(".txt");
                }

                @Override
                public String getDescription() {
                    return "Text Files (*.txt)";
                }
            };

            // Set the file filter to the file chooser
            chooser.setFileFilter(txtFilter);
            int returnVal = chooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                gc = new GreenhouseControls(this);
                eventFile = chooser.getSelectedFile().getAbsolutePath();

                try {
                    File file = new File(eventFile);
                    Scanner scanner = new Scanner(file);
                    Pattern pattern = Pattern.compile("Event=([^,]+),time=([^,]+)(?:,rings=([^,]+))?");

                    while (scanner.hasNextLine()) {
                        Matcher m = pattern.matcher(scanner.nextLine());

                        if (!m.matches()) {
                            scanner.close();
                            throw new FileNotFoundException("Wrong File Format");
                        }
                    }

                    scanner.close();

                    buttons[0].setEnabled(true);
                    contextMenuItems[0].setEnabled(true);

                    initialEvent = new Restart(0, gc, eventFile);
                    eventFileTextField.setText(eventFile);
                } catch (FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE,
                            null);
                }
            }
        }
        // Restore
        if (e.getSource() == menuItems[3]) {
            JFileChooser chooser = new JFileChooser();
            FileFilter dumpFilter = new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isDirectory() || file.getName().toLowerCase().endsWith(".out");
                }

                @Override
                public String getDescription() {
                    return "Dump Files (*.out)";
                }
            };

            // Set the file filter to the file chooser
            chooser.setFileFilter(dumpFilter);
            int returnVal = chooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                gc = new GreenhouseControls(this);

                eventFile = chooser.getSelectedFile().getAbsolutePath();

                gc = gc.restore(eventFile, this);
            }
        }
        // Exit
        if (e.getSource() == menuItems[4]) {
            System.exit(0);
        }

        // Start
        if (e.getSource() == buttons[0] || e.getSource() == contextMenuItems[0]) {
            textArea.setText("Start\n");
            gc.addEvent(initialEvent);
        }
        // Restart
        if (e.getSource() == buttons[1] || e.getSource() == contextMenuItems[1]) {
            textArea.setText("Restart\n");
            gc.addEvent(new Restart(0, gc, eventFile));
        }
        // Terminate
        if (e.getSource() == buttons[2] || e.getSource() == contextMenuItems[2]) {
            try {
                long delayTime = Long.parseLong(JOptionPane.showInputDialog("Enter the Delay Time"));
                if (delayTime >= 0) {
                    gc.addEvent(new Terminate(delayTime, gc));
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter the proper delayTime", "Wrong Value!",
                            JOptionPane.ERROR_MESSAGE, null);
                }
            } catch (Exception ex) {

            }
        }
        // Suspend
        if (e.getSource() == buttons[3] || e.getSource() == contextMenuItems[3]) {
            textArea.append("Suspended\n");

            isSuspended = true;
            gc.pauseEvents();
        }
        // Resume
        if (e.getSource() == buttons[4] || e.getSource() == contextMenuItems[4]) {
            textArea.append("Resuming\n");

            isSuspended = false;
            gc.resumeEvents();
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Greenhouse());
    }
}