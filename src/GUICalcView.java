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
     */
    void registerObserver(GUICalcController controller);

    /**
     * Updates the number display with provided string.
     *
     * @param s
     */
    void updateDisplay(String s);

}
