/**
 * Model class.
 *
 * @author Dev Patel
 *
 */
public class GUICalcModelClass implements GUICalcModel {

    /**
     * Model variable.
     */
    private String display;

    /**
     * Default constructor.
     */
    public GUICalcModelClass() {
        this.display = "";
    }

    @Override
    public String display() {
        return this.display;
    }

    @Override
    public void setDisplay(String s) {
        this.display = s;
    }
}
