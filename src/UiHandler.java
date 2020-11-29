/*
    This class is for generating the UI
    The code was mostly taken from https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
    The class generates a window for the use to interact with EVE
*/

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.util.Random;

public class UiHandler extends Frame implements KeyListener {
    private TextField tfInput; // for input
    public JTextArea taDisplay; // for displaying in the window
    public String outputSave;
    Random random = new Random();

    public UiHandler() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { // for closing the window without crashing
                dispose();
                System.exit(0); // close the program
            }
        });

        setLayout(new FlowLayout()); //  frame sets to FlowLayout

        tfInput = new TextField(30); // input part of ui
        taDisplay = new JTextArea(30, 37); // the display
        taDisplay.setLineWrap(true); // so the line wraps around
        JScrollPane ta = new JScrollPane(taDisplay); // for scrolling i

        add(ta);
        add(new Label("Enter Text: "));
        add(tfInput);

        taDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // set the line border to black
        taDisplay.setEditable(false); // so the user cannot edit in the display window
        tfInput.addKeyListener(this); // for listening to input

        setTitle("E.V.E v0.1"); // sets title
        setSize(500, 600);  // sets initial size
        setVisible(true);          //  Frame shows
        setResizable(false); // non resizeable
        taDisplay.setEditable(false); // cannot edit the window
        taDisplay.append("Hello im Eve ! how are you ?" + "\n");
    }

    /**
     * The three different KeyEvent handlers
     */
    // Called back when a key has been typed (pressed and released)

    @Override
    public void keyPressed(KeyEvent e) { // get input from user and the display answer
        if (e.getKeyCode() == KeyEvent.VK_ENTER & (tfInput.getText().length() > 0)) {
            tfInput.setEditable(false); // while Eve is getting the answer the user cannot write anything
            boolean oneshot = true; // for the
            Timer timer = new Timer(); // for making a small delay
            TimerTask task = new Helper(); // for making Eve display . .. ... in the UI
            taDisplay.append("\n" + "User: " + tfInput.getText() + "\n"); // get the user input
            timer.schedule(task, 100, 500);
            outputSave = core.findAnswerToString(tfInput.getText()); // saving the reply from eve
            tfInput.setText(""); // reset the inputfield
        }
    }


    // Not Used, but we still need to declare them because a keyevent listener has to implement these three methods
    @Override
    public void keyTyped(KeyEvent evt) {
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }

    class Helper extends TimerTask {
        boolean oneshot = true; // to make sure the code only runs once
        boolean decider = random.nextBoolean(); // to switch between making 2 dots or 5 dots
        int stopdots; // amount of dots to be made

        int i = 0;

        public void run() { // for making the dots in the ui
            if (decider) { // make 5 or 2 dots
                stopdots = 5;
            } else {
                stopdots = 2;
            }
            if (i == stopdots) {
                taDisplay.replaceRange("Eve: " + outputSave, taDisplay.getText().length() - 7, taDisplay.getText().length());
                cancel();
                tfInput.setEditable(true);
            } else if (oneshot) { // to make sure it is at the first dot
                taDisplay.append("Eve: .");
                oneshot = false;
                i++;
            } else if (taDisplay.getText().contains("Eve: ...")) { // if it has made 3 dots make 1
                taDisplay.replaceRange("Eve: .", taDisplay.getText().length() - 8, taDisplay.getText().length());
                i++;
            } else {
                taDisplay.append(".");
                i++;
            }
        }
    }

}
