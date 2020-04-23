import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * View class.
 *
 * @author Dev Patel
 *
 */
public class GUICalcViewClass implements GUICalcView {

    /**
     * Controller object to observse interaction events.
     */
    private GUICalcController controller;

    /**
     * Test area.
     */
    private final JTextArea display;

    /**
     * Operator buttons.
     */
    private final JButton clear, backspace, enter, add, subtract, multiply,
            divide, leftParen, rightParen;

    /**
     * Number buttons.
     */
    private final JButton[] numbers;

    /**
     * Default Constructor.
     */
    public GUICalcViewClass() {

        /*
         * Create widgets
         */
        this.display = new JTextArea();
        this.clear = new JButton("Clear");
        this.backspace = new JButton("Backspace");
        this.enter = new JButton("Enter");
        this.add = new JButton("+");
        this.subtract = new JButton("-");
        this.multiply = new JButton("*");
        this.divide = new JButton("/");
        this.leftParen = new JButton("(");
        this.rightParen = new JButton(")");
        this.numbers = new JButton[10];
        for (int i = 0; i < this.numbers.length; i++) {
            this.numbers[i] = new JButton(i + "");
        }

        /*
         * Make display wrap lines and read-only
         */
        this.display.setEditable(false);
        this.display.setLineWrap(true);
        this.display.setWrapStyleWord(true);

        /*
         * Enter is initially disabled
         */
        this.enter.setEnabled(false);
    }

    @Override
    public void registerObserver(GUICalcController controller) {
        this.controller = controller;
    }

    @Override
    public void updateDisplay(String s) {

    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
