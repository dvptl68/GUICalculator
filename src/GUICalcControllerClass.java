/**
 * Controller class.
 *
 * @author Dev Patel
 *
 */
public class GUICalcControllerClass implements GUICalcController {

    /**
     * Model object.
     */
    private final GUICalcModel model;

    /**
     * View object.
     */
    private final GUICalcView view;

    public GUICalcControllerClass(GUICalcModel model, GUICalcView view) {
        this.model = model;
        this.view = view;
    }
}
