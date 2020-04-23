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

        /*
         * Sets instance variables to parameters and updates initial GUI
         */
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

        /*
         * Updates model to remove last character in display
         */
        String disp = this.model.display();
        this.model.setDisplay(disp.substring(0, disp.length() - 1));

        update(this.model, this.view);
    }

    @Override
    public void processClear() {

        /*
         * Updates model to clear display
         */
        this.model.setDisplay("");

        update(this.model, this.view);
    }

    @Override
    public void processEnter() {
        //tokenize and parse input
    }

    @Override
    public void processAdd() {

        /*
         * Updates model to concatenate + to end of display
         */
        this.model.setDisplay(this.model.display() + "+");

        update(this.model, this.view);
    }

    @Override
    public void processSubtract() {

        /*
         * Updates model to concatenate - to end of display
         */
        this.model.setDisplay(this.model.display() + "-");

        update(this.model, this.view);

    }

    @Override
    public void processMultiply() {

        /*
         * Updates model to concatenate * to end of display
         */
        this.model.setDisplay(this.model.display() + "*");

        update(this.model, this.view);
    }

    @Override
    public void processDivide() {

        /*
         * Updates model to concatenate / to end of display
         */
        this.model.setDisplay(this.model.display() + "/");

        update(this.model, this.view);
    }

    @Override
    public void processLeftParen() {

        /*
         * Updates model to concatenate ( to end of display
         */
        this.model.setDisplay(this.model.display() + "(");

        update(this.model, this.view);
    }

    @Override
    public void processRightParen() {

        /*
         * Updates model to concatenate ) to end of display
         */
        this.model.setDisplay(this.model.display() + ")");

        update(this.model, this.view);
    }

    @Override
    public void processNum(int num) {

        /*
         * Updates model to concatenate paramter num to end of display
         */
        this.model.setDisplay(this.model.display() + num);

        update(this.model, this.view);
    }

}
