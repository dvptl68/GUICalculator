/**
 * Interface for controller class.
 *
 * @author Dev Patel
 *
 */
public interface GUICalcController {

    /**
     * Processes event to calculate the entered operation.
     */
    void processEnter();

    /**
     * Processes event to clear the display.
     */
    void processClear();

    /**
     * Processes event to add + to display.
     */
    void processAdd();

    /**
     * Processes event to add - to display.
     */
    void processSubtract();

    /**
     * Processes event to add / to display.
     */
    void processDivide();

    /**
     * Processes event to add * to display.
     */
    void processMultiply();

    /**
     * Processes event to add ( to display.
     */
    void processLeftParen();

    /**
     * Processes event to add ) to display.
     */
    void processRightParen();

    /**
     * Processes event to add a number to display.
     *
     * @param num
     *            the number to be added to the display
     */
    void processNum(int num);
}
