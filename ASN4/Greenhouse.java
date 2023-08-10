import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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

    Greenhouse() {
        setTitle("Greenhosue");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    }

    public static void main(String[] args) {
        new Greenhouse();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // New Window
        if (e.getSource() == menuItems[0]) {
            new Greenhouse();
        }
        // Close Window
        if (e.getSource() == menuItems[1]) {
            dispose();
            System.exit(0);
        }
        // Open Events
        if (e.getSource() == menuItems[2]) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(null);

            if (returnVal == 0) {
                buttons[0].setEnabled(true);
                gc = new GreenhouseControls();
                eventFile = "ASN4/examples4.txt";
            }

        }

        if (e.getSource() == menuItems[4]) {
            System.exit(0);
        }

        // Start
        if (e.getSource() == buttons[0]) {
            textArea.append("Start\n");
            gc.addEvent(new Restart(0, gc, "ASN4/examples4.txt"));
            buttons[0].setEnabled(false);
            buttons[2].setEnabled(true);
            buttons[3].setEnabled(true);
        }
        // Restart
        if (e.getSource() == buttons[1]) {
            textArea.append("Restart\n");
        }
        // Terminate
        if (e.getSource() == buttons[2]) {
            textArea.append("Terminate\n");
            gc.addEvent(new Terminate(0, gc));
        }
        // Suspend
        if (e.getSource() == buttons[3]) {
            textArea.append("Suspend\n");
            buttons[3].setEnabled(false);
            buttons[4].setEnabled(true);
        }
        // Resume
        if (e.getSource() == buttons[4]) {
            textArea.append("Resume\n");
        }
    }
}