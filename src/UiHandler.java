/*
    This class is for generating the UI
    The code was mostly taken from https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
    The class generates a window for the use to interact with EVE
*/
import java.awt.*;
import java.awt.event.*;

public class UiHandler extends Frame implements KeyListener {
    private TextField tfInput; // for input
    public TextArea taDisplay; // for displaying in the window

    public UiHandler() {
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) { // for closing the window without crashing
                dispose();
                System.exit(0);
            }
        });

        setLayout(new FlowLayout()); // "super" frame sets to FlowLayout

        tfInput   = new TextField(30);
        taDisplay = new TextArea(30, 60); // 5 rows, 40 columns

        add(taDisplay);
        add(new Label("Enter Text: "));
        add(tfInput);

        tfInput.addKeyListener(this); // for listening to input

        setTitle("E.V.E v0.1"); // sets title
        setSize(500, 600);  // sets initial size
        setVisible(true);          //  Frame shows
        setResizable(false); // non resizeable
        taDisplay.append("Hello im Eve ! how are you ?" + "\n");
    }

    /**
     * The three different KeyEvent handlers
     */
    // Called back when a key has been typed (pressed and released)
    @Override
    public void keyPressed(KeyEvent e) { // get input from user and the display answer
        if (e.getKeyCode() == KeyEvent.VK_ENTER & (tfInput.getText().length()>0)) {
            taDisplay.append("User: " + tfInput.getText() + "\n");

            taDisplay.append("Eve: " + core.findAnswerToString(tfInput.getText()) + "\n");

            tfInput.setText ("");
        }
    }


    // Not Used, but we still need to declare them because a keyevent listener has to implement these three methods
    @Override
    public void keyTyped(KeyEvent evt) {
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }



}
