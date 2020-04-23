/**
 * A simple GUI calculator that utilizes Java Swing Framework and
 * recursive-descent parsing in order to calulate operations the user enters.
 *
 * @author Dev Patel
 *
 */
public class GUICalculatorMain {

    /**
     * Main program that sets up GUI.
     *
     * @param args
     */
    public static void main(String[] args) {

        GUICalcModel model = new GUICalcModelClass();
        GUICalcView view = new GUICalcViewClass();
        GUICalcController controller = new GUICalcControllerClass(model, view);
        view.registerObserver(controller);
    }

}
