import java.awt.event.ActionListener;

/**
 * Interface for view class.
 *
 * @author Dev Patel
 *
 */
public interface GUICalcView extends ActionListener {

    /**
     * Registers controller as an observer of this.
     *
     * @param controller
     *            controller object to be registered
     */
    void registerObserver(GUICalcController controller);

    /**
     * Updates the number display with provided string.
     *
     * @param s
     *            new string to update display with
     */
    void updateDisplay(String s);

    /**
     * Updates display of whether entering an operator is allowed.
     *
     * @param b
     *            true if it is allowed, false otherwise
     */
    void enableOps(boolean b);

    /**
     * Updates display of whether entering a '(' is allowed.
     *
     * @param b
     *            true if it is allowed, false otherwise
     */
    void enableLeftParen(boolean b);

    /**
     * Updates display of whether entering a ')' is allowed.
     *
     * @param b
     *            true if it is allowed, false otherwise
     */
    void enableRightParen(boolean b);

    /**
     * Updates display of whether entering a number is allowed.
     *
     * @param b
     *            true if it is allowed, false otherwise
     */
    void enableNums(boolean b);

    /**
     * Updates display of whether entering a zero is allowed.
     *
     * @param b
     *            true if it is allowed, false otherwise
     */
    void enableZero(boolean b);

    /**
     * Updates display of whether backspace is allowed.
     *
     * @param b
     *            true if it is allowed, false otherwise
     */
    void enableBackspace(boolean b);

    /**
     * Updates display of whether clear is allowed.
     *
     * @param b
     *            true if it is allowed, false otherwise
     */
    void enableClear(boolean b);

    /**
     * Updates display of whether enter allowed.
     *
     * @param b
     *            true if it is allowed, false otherwise
     */
    void enableEnter(boolean b);
}
