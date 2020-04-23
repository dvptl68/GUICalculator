import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * View class.
 *
 * @author Dev Patel
 *
 */
public class GUICalcViewClass extends JFrame implements GUICalcView {

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
         * Create scroll pane for display
         */
        JScrollPane displayScroll = new JScrollPane(this.display);

        /*
         * Create left button panel
         */
        JPanel leftButtonPanel = new JPanel(new GridLayout(4, 4));

        /*
         * Add buttons to left button panel
         */
        leftButtonPanel.add(this.numbers[7]);
        leftButtonPanel.add(this.numbers[8]);
        leftButtonPanel.add(this.numbers[9]);
        leftButtonPanel.add(this.add);
        leftButtonPanel.add(this.numbers[4]);
        leftButtonPanel.add(this.numbers[5]);
        leftButtonPanel.add(this.numbers[6]);
        leftButtonPanel.add(this.subtract);
        leftButtonPanel.add(this.numbers[1]);
        leftButtonPanel.add(this.numbers[2]);
        leftButtonPanel.add(this.numbers[3]);
        leftButtonPanel.add(this.multiply);
        leftButtonPanel.add(this.numbers[0]);
        leftButtonPanel.add(this.leftParen);
        leftButtonPanel.add(this.rightParen);
        leftButtonPanel.add(this.divide);

        /*
         * Create right button panel
         */
        JPanel rightButtonPanel = new JPanel(new GridLayout(3, 1));

        /*
         * Add buttons to right button panel
         */
        rightButtonPanel.add(this.backspace);
        rightButtonPanel.add(this.clear);
        rightButtonPanel.add(this.enter);

        /*
         * Create combined button panel
         */
        JPanel allButtons = new JPanel();

        /*
         * Add two button panels to combined button panel
         */
        allButtons.add(leftButtonPanel);
        allButtons.add(rightButtonPanel);

        /*
         * Organize main window
         */
        this.setLayout(new GridLayout(2, 1));

        /*
         * Add scroll pane and button panel to main window
         */
        this.add(displayScroll);
        this.add(allButtons);

        /*
         * Register this as observer for all events
         */
        this.clear.addActionListener(this);
        this.backspace.addActionListener(this);
        this.enter.addActionListener(this);
        this.add.addActionListener(this);
        this.subtract.addActionListener(this);
        this.multiply.addActionListener(this);
        this.divide.addActionListener(this);
        this.leftParen.addActionListener(this);
        this.rightParen.addActionListener(this);
        for (int i = 0; i < this.numbers.length; i++) {
            this.numbers[i].addActionListener(this);
        }

        /*
         * Make main window sized correctly, exits on close, and is visible
         */
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

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
