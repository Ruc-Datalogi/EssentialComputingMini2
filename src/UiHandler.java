

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Timer;

public class UiHandler extends Frame implements KeyListener {
    private TextField tfInput;
    public TextArea taDisplay;
    private int delay;
    // THIS IS COPYPASTORINOET FROM https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
    // FROM 3.6 EXAMPLE 6 "KeyEvent and KeyListener Interface"

    public UiHandler() {
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setLayout(new FlowLayout()); // "super" frame sets to FlowLayout

        add(new Label("Enter Text: "));
        tfInput = new TextField(30);
        add(tfInput);
        taDisplay = new TextArea(30, 40); // 5 rows, 40 columns
        add(taDisplay);

        tfInput.addKeyListener(this);
        // tfInput TextField (source) fires KeyEvent.
        // tfInput adds "this" object as a KeyEvent listener.

        setTitle("E.V.E v0.1"); // "super" Frame sets title
        setSize(500, 600);         // "super" Frame sets initial size
        setVisible(true);          // "super" Frame shows

    }

    /**
     * KeyEvent handlers
     */
    // Called back when a key has been typed (pressed and released)
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER & (tfInput.getText().length()>0)) {
            taDisplay.append("User: " + tfInput.getText() + "\n");

            taDisplay.append("Eve: " + core.findAnswerToString(tfInput.getText()) + "\n");

            tfInput.setText ("");
        }
    }

    // Not Used, but need to provide an empty body for compilation
    @Override
    public void keyTyped(KeyEvent evt) {
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }

}
