/**
 * Interface for model class.
 *
 * @author Dev Patel
 *
 */
public interface GUICalcModel {

    /**
     * Reports the contents of the display.
     *
     * @return display content
     */
    String display();

    /**
     * Sets the display value to given string.
     *
     * @param s
     *            the new display value
     */
    void setDisplay(String s);
}
