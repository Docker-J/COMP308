import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import tme3.events.Restart;
import tme3.events.Terminate;

public class Greenhouse extends JFrame implements ActionListener {
    String[] buttons_str = { "Start", "Restart", "Terminate", "Suspend", "Resume" };
    JButton[] buttons = new JButton[5];
    String[] menuItems_str = { "New Window", "Close Window", "Open Events", "Restore", "Exit" };
    int[] menuitems_key = { KeyEvent.VK_N, KeyEvent.VK_C, KeyEvent.VK_O, KeyEvent.VK_R, KeyEvent.VK_E };
    JMenuItem[] menuItems = new JMenuItem[5];
    JPanel p;
    JTextArea textArea;

    GreenhouseControls gc;
    String eventFile;
    private boolean isRunning = false;
    private boolean isSuspended = false;

    Greenhouse() {
        setTitle("Greenhosue");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        for (int i = 0; i < buttons.length; i++) {
            JMenuItem menuItem = new JMenuItem(menuItems_str[i], menuitems_key[i]);
            menuItem.addActionListener(this);
            menuItems[i] = menuItem;
            fileMenu.add(menuItem);
        }
        mb.add(fileMenu);
        setJMenuBar(mb);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scrollPane = new JScrollPane(textArea);

        c.add(scrollPane);

        p = new JPanel(new FlowLayout());

        for (int i = 0; i < buttons.length; i++) {
            JButton button = new JButton(buttons_str[i]);
            button.addActionListener(this);
            button.setEnabled(false);
            buttons[i] = button;
            p.add(button);
        }

        add(p);
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new Greenhouse();
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void updateComponents() {
        if (isRunning) {
            menuItems[3].setEnabled(false);

            buttons[0].setEnabled(false);
            buttons[1].setEnabled(false);
            buttons[2].setEnabled(true);
            buttons[3].setEnabled(true);
            buttons[4].setEnabled(false);
        } else { // not running
            menuItems[3].setEnabled(true);

            buttons[0].setEnabled(true);
            buttons[1].setEnabled(true);
            buttons[2].setEnabled(false);
            buttons[3].setEnabled(false);
            if (isSuspended) {
                buttons[4].setEnabled(true);
            }
        }
    }

    public void updateFlag(boolean running) {
        isRunning = running;
        updateComponents();
    }

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
                System.exit(0);
            }
        }
        // Open Events
        if (e.getSource() == menuItems[2]) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                gc = new GreenhouseControls(this);
                eventFile = chooser.getSelectedFile().getAbsolutePath();

                buttons[0].setEnabled(true);
            }

        }
        // Restore
        if (e.getSource() == menuItems[3]) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                buttons[0].setEnabled(true);
                gc = new GreenhouseControls(this);

                eventFile = chooser.getSelectedFile().getAbsolutePath();
            }

            gc = gc.restore(eventFile, this);
        }
        // Exit
        if (e.getSource() == menuItems[4]) {
            System.exit(0);
        }

        // Start
        if (e.getSource() == buttons[0]) {
            textArea.setText("Start\n");
            gc.addEvent(new Restart(0, gc, eventFile));
        }
        // Restart
        if (e.getSource() == buttons[1]) {
            textArea.setText("Restart\n");
            gc.addEvent(new Restart(0, gc, eventFile));
        }
        // Terminate
        if (e.getSource() == buttons[2]) {
            long delayTime = Long.parseLong(JOptionPane.showInputDialog("Enter the Delay Time"));
            if (delayTime >= 0) {
                gc.addEvent(new Terminate(delayTime, gc));
            } else {
                JOptionPane.showMessageDialog(this, "Please enter the proper delayTime", "Wrong Value!",
                        JOptionPane.ERROR_MESSAGE, null);
            }
        }
        // Suspend
        if (e.getSource() == buttons[3]) {
            textArea.append("Suspended\n");

            isSuspended = true;
            gc.pauseEvents();
        }
        // Resume
        if (e.getSource() == buttons[4]) {
            textArea.append("Resuming\n");

            isSuspended = false;
            gc.resumeEvents();
        }

    }
}