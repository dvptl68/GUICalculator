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

    /**
     * Number based used in calculations.
     */
    private static final int RADIX = 10;

    /**
     * Numer of left and right parentheses in the expression.
     */
    private static int numLeft = 0, numRight = 0;

    /**
     * Types of last character of expression.
     */
    private static enum Kind {
        /**
         * Last is either number, operator, '(', ')', or empty
         */
        NUMBER, OPERATOR, LEFT_PAREN, RIGHT_PAREN, EMPTY
    }

    /**
     * Kind variable to determine what kind of character the last is.
     */
    private static Kind last;

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
     *            StringBuilder that starts with a digit
     * @return value of digit
     */
    private static int digit(StringBuilder source) {

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
     *            StringBuilder that starts with a digit-seq string
     * @return value of digit sequence
     */
    private static int digitSeq(StringBuilder source) {

        /*
         * Add digits onto the value until a non-number character is found
         */
        int value = 0;

        while (Character.isDigit(source.charAt(0))) {

            value = (value * RADIX) + digit(source);
        }

        return value;
    }

    /**
     * Evaluates a factor and returns its value.
     *
     * @param source
     *            StringBuilder that starts with a factor string
     * @return value of factor
     */
    private static int factor(StringBuilder source) {

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
            value = expr(source);

            /*
             * Consume ')'
             */
            source.deleteCharAt(0);

        } else {

            /*
             * Find the number value of the digit sequence
             */
            value = digitSeq(source);
        }

        return value;
    }

    /**
     * Evaluates a term and returns its value.
     *
     * @param source
     *            StringBuilder that starts with a term string
     * @return value of term
     */
    private static int term(StringBuilder source) {

        /*
         * First value of term is always a factor
         */
        int value = factor(source);

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
            int nextFactor = factor(source);

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
     *            StringBuilder that starts with an expr string
     * @return value of the expression
     */
    public static int expr(StringBuilder source) {

        /*
         * First value of expression is always term
         */
        int value = term(source);

        while (source.charAt(0) == '+' || source.charAt(0) == '-') {

            char op = source.charAt(0);

            /*
             * Consume '+' or '-'
             */
            source.deleteCharAt(0);

            /*
             * Find value of next term
             */
            int nextFactor = term(source);

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
     * Sets the static variable last to the kind of character it is.
     *
     * @param expr
     *            the expression
     */
    private static void setKind(String expr) {

        if (expr.length() > 0) {

            char end = expr.charAt(expr.length() - 1);

            if (Character.isDigit(end)) {

                last = Kind.NUMBER;

            } else if (end == '(') {

                last = Kind.LEFT_PAREN;

            } else if (end == ')') {

                last = Kind.RIGHT_PAREN;

            } else {

                last = Kind.OPERATOR;
            }

        } else {

            last = Kind.EMPTY;
        }
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

        /*
         * Current expression
         */
        String expr = model.display();

        /*
         * Update GUI display to current expression
         */
        view.updateDisplay(expr);

        /*
         * Sets last static variable to the kind of expression
         */
        setKind(expr);

        switch (last) {
            case NUMBER: {
                view.enableNums(true);
                view.enableOps(true);
                view.enableLeftParen(false);
                view.enableRightParen(numLeft > numRight);
                view.enableBackspace(true);
                view.enableClear(true);
                view.enableEnter(numLeft == numRight);
                break;
            }
            case OPERATOR: {
                view.enableNums(true);
                view.enableOps(false);
                view.enableLeftParen(true);
                view.enableRightParen(false);
                view.enableBackspace(true);
                view.enableClear(true);
                view.enableEnter(false);
                view.enableZero(expr.charAt(expr.length() - 1) != '/');
                break;
            }
            case LEFT_PAREN: {
                view.enableNums(true);
                view.enableOps(false);
                view.enableLeftParen(true);
                view.enableRightParen(false);
                view.enableBackspace(true);
                view.enableClear(true);
                view.enableEnter(false);
                break;
            }
            case RIGHT_PAREN: {
                view.enableNums(false);
                view.enableOps(true);
                view.enableLeftParen(false);
                view.enableRightParen(numLeft > numRight);
                view.enableBackspace(true);
                view.enableClear(true);
                view.enableEnter(numLeft == numRight);
                break;
            }
            case EMPTY: {
                view.enableNums(true);
                view.enableOps(false);
                view.enableLeftParen(true);
                view.enableRightParen(false);
                view.enableBackspace(false);
                view.enableClear(false);
                view.enableEnter(false);
                break;
            }
            default:
                break;
        }
    }

    /**
     * Updates to view object to reflect changes after enter is pressed.
     *
     * @param model
     *            reference to model object
     * @param view
     *            reference to view object
     */
    private static void updateEnter(GUICalcModel model, GUICalcView view) {

        /*
         * Current expression
         */
        String expr = model.display();

        /*
         * Update GUI display to current expression
         */
        view.updateDisplay(expr);

        /*
         * Disable everything except clear
         */
        view.enableNums(false);
        view.enableOps(false);
        view.enableLeftParen(false);
        view.enableRightParen(false);
        view.enableBackspace(false);
        view.enableClear(true);
        view.enableEnter(false);
    }

    @Override
    public void processBackspace() {

        /*
         * Update model to remove last character in display
         */
        String disp = this.model.display();
        this.model.setDisplay(disp.substring(0, disp.length() - 1));

        update(this.model, this.view);
    }

    @Override
    public void processClear() {

        /*
         * Update model to clear display
         */
        this.model.setDisplay("");

        update(this.model, this.view);
    }

    @Override
    public void processEnter() {

        /*
         * Calculate value of expression
         */
        int value = expr(new StringBuilder(this.model.display() + "!"));

        /*
         * Update model to concatenate solution onto next line
         */
        this.model.setDisplay(this.model.display() + "\n=" + value);

        updateEnter(this.model, this.view);
    }

    @Override
    public void processAdd() {

        /*
         * Update model to concatenate + to end of display
         */
        this.model.setDisplay(this.model.display() + "+");

        update(this.model, this.view);
    }

    @Override
    public void processSubtract() {

        /*
         * Update model to concatenate - to end of display
         */
        this.model.setDisplay(this.model.display() + "-");

        update(this.model, this.view);

    }

    @Override
    public void processMultiply() {

        /*
         * Update model to concatenate * to end of display
         */
        this.model.setDisplay(this.model.display() + "*");

        update(this.model, this.view);
    }

    @Override
    public void processDivide() {

        /*
         * Update model to concatenate / to end of display
         */
        this.model.setDisplay(this.model.display() + "/");

        update(this.model, this.view);
    }

    @Override
    public void processLeftParen() {

        /*
         * Update static variable accordingly
         */
        numLeft++;

        /*
         * Update model to concatenate ( to end of display
         */
        this.model.setDisplay(this.model.display() + "(");

        update(this.model, this.view);
    }

    @Override
    public void processRightParen() {

        /*
         * Update static variable accordingly
         */
        numRight++;

        /*
         * Update model to concatenate ) to end of display
         */
        this.model.setDisplay(this.model.display() + ")");

        update(this.model, this.view);
    }

    @Override
    public void processNum(int num) {

        /*
         * Update model to concatenate paramter num to end of display
         */
        this.model.setDisplay(this.model.display() + num);

        update(this.model, this.view);
    }

}
