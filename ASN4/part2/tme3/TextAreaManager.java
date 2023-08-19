package tme3;

import javax.swing.JTextArea;

public class TextAreaManager {
    private JTextArea textArea;

    public TextAreaManager(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void append(String message) {
        textArea.append(message);
    }
}
