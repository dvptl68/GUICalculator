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
        update(this.model, this.view);
    }

    /**
     * Updates the view object to reflect changes incurred by user.
     *
     * @param model
     *            reference to model object
     * @param view
     *            reference to view object
     */
    private static void update(GUICalcModel model, GUICalcView view) {

        view.updateDisplay(model.display());
    }

    @Override
    public void processBackspace() {

        String disp = this.model.display();
        this.model.setDisplay(disp.substring(0, disp.length() - 1));
    }

    @Override
    public void processClear() {

        this.model.setDisplay("");
    }

    @Override
    public void processEnter() {
        //tokenize and parse input
    }

    @Override
    public void processAdd() {

        this.model.setDisplay(this.model.display() + "+");
    }

    @Override
    public void processSubtract() {

        this.model.setDisplay(this.model.display() + "-");

    }

    @Override
    public void processMultiply() {

        this.model.setDisplay(this.model.display() + "*");
    }

    @Override
    public void processDivide() {

        this.model.setDisplay(this.model.display() + "/");
    }

    @Override
    public void processLeftParen() {

        this.model.setDisplay(this.model.display() + "(");
    }

    @Override
    public void processRightParen() {

        this.model.setDisplay(this.model.display() + ")");
    }

    @Override
    public void processNum(int num) {

        this.model.setDisplay(this.model.display() + num);
    }

}
