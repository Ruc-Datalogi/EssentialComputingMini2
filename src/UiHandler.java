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
    public String inputSave;
    Random random = new Random();

    public UiHandler() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { // for closing the window without crashing
                dispose();
                System.exit(0);
            }
        });

        setLayout(new FlowLayout()); // "super" frame sets to FlowLayout

        tfInput = new TextField(30);
        taDisplay = new JTextArea(30, 37); // 5 rows, 40 columns
        taDisplay.setLineWrap(true);
        JScrollPane ta = new JScrollPane(taDisplay);

        add(ta);
        add(new Label("Enter Text: "));
        add(tfInput);

        taDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taDisplay.setEditable(false);
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
            tfInput.setEditable(false);
            boolean oneshot = true;
            Timer timer = new Timer();
            TimerTask task = new Helper();
            taDisplay.append("\n" + "User: " + tfInput.getText() + "\n");
            timer.schedule(task, 100, 500);
            inputSave = tfInput.getText();

            tfInput.setText("");
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
        boolean oneshot = true;
        boolean decider = random.nextBoolean();
        int stopdots;

        int i = 0;

        public void run() {
            if (decider) {
                stopdots = 5;
            } else {
                stopdots = 2;
            }
            if (i == stopdots) {
                System.out.println(inputSave);
                taDisplay.replaceRange("Eve: " + core.findAnswerToString(inputSave), taDisplay.getText().length() - 7, taDisplay.getText().length());
                System.out.println(core.findAnswerToString(inputSave));
                cancel();
                tfInput.setEditable(true);
            } else if (oneshot) {
                taDisplay.append("Eve: .");
                oneshot = false;
                i++;
            } else if (taDisplay.getText().contains("Eve: ...")) {
                taDisplay.replaceRange("Eve: .", taDisplay.getText().length() - 8, taDisplay.getText().length());
                //taDisplay.setText("Eve: .");
                i++;
            } else {
                taDisplay.append(".");
                i++;
            }
        }
    }

}
