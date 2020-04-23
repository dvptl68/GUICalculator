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

    /***
     * Number based used in calculations.
     */
    private static final int RADIX = 10;

    /**
     * Constructor.
     *
     * @param model
     * @param view
     */
    public GUICalcControllerClass(GUICalcModel model, GUICalcView view) {

        /*
         * Sets instance variables to parameters and updates initial GUI
         */
        this.model = model;
        this.view = view;

        update(this.model, this.view);
    }

    /*
     * Recursive-descent parsing methods - these follow the CFG
     */

    /**
     * Evaluates a digit and returns its value.
     *
     * @param source
     *            the {@code StringBuilder} that starts with a digit
     * @return value of the digit
     */
    private static int valueOfDigit(StringBuilder source) {

        /*
         * Parse digit to be returned
         */
        int digit = Character.digit(source.charAt(0), RADIX);

        /*
         * Consume digit from parameter
         */
        source.deleteCharAt(0);

        return digit;
    }

    /**
     * Evaluates a digit sequence and returns its value.
     *
     * @param source
     *            the {@code StringBuilder} that starts with a digit-seq string
     * @return value of the digit sequence
     */
    private static int valueOfDigitSeq(StringBuilder source) {

        /*
         * Add digits onto the value until a non-number character is found
         */
        int value = 0;

        while (Character.isDigit(source.charAt(0))) {

            value = (value * RADIX) + valueOfDigit(source);
        }

        return value;
    }

    /**
     * Evaluates a factor and returns its value.
     *
     * @param source
     *            the {@code StringBuilder} that starts with a factor string
     * @return value of the factor
     */
    private static int valueOfFactor(StringBuilder source) {

        int value;

        /*
         * If the first character is '(', the following is an expr. Otherwise,
         * it is a digit sequence
         */
        if (source.charAt(0) == '(') {

            /*
             * Consume '('
             */
            source.deleteCharAt(0);

            /*
             * Evaluate expressions
             */
            value = valueOfExpr(source);

            /*
             * Consume ')'
             */
            source.deleteCharAt(0);

        } else {

            /*
             * Find the number value of the digit sequence
             */
            value = valueOfDigitSeq(source);
        }

        return value;
    }

    /**
     * Evaluates a term and returns its value.
     *
     * @param source
     *            the {@code StringBuilder} that starts with a term string
     * @return value of the term
     */
    private static int valueOfTerm(StringBuilder source) {

        /*
         * First value of term is always a factor
         */
        int value = valueOfFactor(source);

        /*
         * Complete multiply or divide operations on value until a non-term
         * character is found
         */
        while (source.charAt(0) == '*' || source.charAt(0) == '/') {

            char op = source.charAt(0);

            /*
             * Consume '*' or '/'
             */
            source.deleteCharAt(0);

            /*
             * Find value of next factor
             */
            int nextFactor = valueOfFactor(source);

            /*
             * Complete operation based on token consumed earlier
             */
            if (op == '*') {

                value *= nextFactor;

            } else {

                value /= nextFactor;
            }
        }

        return value;
    }

    /**
     * Evaluates an expression and returns its value.
     *
     * @param source
     *            the {@code StringBuilder} that starts with an expr string
     * @return value of the expression
     */
    public static int valueOfExpr(StringBuilder source) {

        /*
         * First value of expression is always term
         */
        int value = valueOfTerm(source);

        while (source.charAt(0) == '+' || source.charAt(0) == '-') {

            char op = source.charAt(0);

            /*
             * Consume '+' or '-'
             */
            source.deleteCharAt(0);

            /*
             * Find value of next term
             */
            int nextFactor = valueOfTerm(source);

            /*
             * Complete operation based on token consumed earlier
             */
            if (op == '+') {

                value += nextFactor;

            } else {

                value -= nextFactor;
            }
        }

        return value;
    }

    /*
     * Event handler methods
     */

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

        /*
         * Calculate value of expression
         */
        StringBuilder expression = new StringBuilder(this.model.display());
        int value = valueOfExpr(expression);

        /*
         * Update model to concatenate solution onto next line
         */
        this.model.setDisplay(this.model.display() + "\n=" + value);

        update(this.model, this.view);
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
