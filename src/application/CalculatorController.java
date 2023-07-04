package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * A Controller class that handles the functionality of the calculator by
 * responding to events triggered within the GUI.
 * 
 * @author 0fficialNadeem (GitHub)
 * @since 1.0
 */
public class CalculatorController {
//Assigning components
	@FXML
	Button DEL;
	@FXML
	Button LOG;
	@FXML
	Button AC;
	@FXML
	Button DIVIDE;
	@FXML
	Button MULTIPLY;
	@FXML
	Button ADDITION;
	@FXML
	Button SUBTRACT;
	@FXML
	Button DECIMAL;
	@FXML
	Button one;
	@FXML
	Button two;
	@FXML
	Button three;
	@FXML
	Button four;
	@FXML
	Button five;
	@FXML
	Button six;
	@FXML
	Button seven;
	@FXML
	Button eight;
	@FXML
	Button nine;
	@FXML
	Button zero;
	@FXML
	Label screen;
	@FXML
	Label calculations;

	private String currentVal;
	private String displayCalculations;
	private String finalCalculation;
	private double result;
	private Double num1;
	private String lastNum = "";
	private boolean firstnum = false;
	private String lastOperator;
	private ArrayList<String> oldVals = new ArrayList<>();
	private String firstOperatorSet;

	/**
	 * Handles the initial set-up of the calculator,code block runs automatically as
	 * soon as GUI has loaded.
	 * 
	 * @since 1.0
	 */
	public void initialize() {
		calculations.setVisible(false); // calculations not visible at start
		currentVal = "";
		displayCalculations = "";
		result = 0;
		screen.setText("0");
		num1 = null;
		oldVals.clear();
		firstOperatorSet = null;
	}

	/**
	 * Handles the event when a number button is clicked. Updates the current value
	 * and performs calculations based on the last operator selected.
	 * 
	 * @param event the event representing the button click
	 * @since 1.0
	 */
	@FXML
	public void onNumClicked(ActionEvent event) {
		Button btn = (Button) event.getSource();
		String btnText = btn.getText();
		lastNum += btnText;

		// Take note of values pressed
		if (!currentVal.contains(".") || !btnText.equals(".")) {
			if (firstOperatorSet == ("-")) {
				currentVal += "-";
				firstOperatorSet = "";
			}
			currentVal += btnText;

		}

		// operations
		if (lastOperator == ("+")) {
			result += Double.parseDouble(lastNum);
			oldVals.add(lastNum);
		}
		if (lastOperator == ("-")) {
			result -= Double.parseDouble(lastNum);
			oldVals.add(lastNum);
		}
		if (lastOperator == ("×")) {
			result *= Double.parseDouble(lastNum);
			oldVals.add(lastNum);

		}
		if (lastOperator == ("/")) {
			result /= Double.parseDouble(lastNum);
			oldVals.add(lastNum);
		}
		if (lastOperator == ("Log")) {
			result = Math.log10(Double.parseDouble(lastNum));
			oldVals.add(lastNum);
		}

		// undo any changes until final/desired double value is made
		for (int i = 0; oldVals.size() > 1 && i < oldVals.size() - 1; i++) {

			if (lastOperator == ("+")) {
				result -= Double.parseDouble(oldVals.get(i));
				oldVals.remove(i);
			}
			if (lastOperator == ("-")) {
				result += Double.parseDouble(oldVals.get(i));
				oldVals.remove(i);
			}
			if (lastOperator == ("×")) {
				result /= Double.parseDouble(oldVals.get(i));
				oldVals.remove(i);
			}
			if (lastOperator == ("/")) {
				result *= Double.parseDouble(oldVals.get(i));
				oldVals.remove(i);
			}
		}

		screen.setText(currentVal);

	}

	/**
	 * Removes the last character from the displayed value by returning a substring
	 * with one character fewer then the previous.
	 * 
	 * @since 1.0
	 */
	@FXML
	public void onDelete() {
		currentVal = currentVal.substring(0, currentVal.length() - 1);
		lastNum = lastNum.substring(0, lastNum.length() - 1);
		screen.setText(currentVal);

	}

	/**
	 * Handles the event when an operator button is clicked. Updates the
	 * calculations label and takes note of the corresponding mathematical
	 * operation.
	 * 
	 * @param event the event representing the button click
	 * @since 1.0
	 */

	@FXML
	public void onOperatorClicked(ActionEvent event) {
		Button btn = (Button) event.getSource();
		String operator = btn.getText();
		if (num1 == null) {
			if (!currentVal.isEmpty()) {
				num1 = Double.parseDouble(currentVal);
				firstnum = true;
			}
		}

		switch (operator) {
		case "+": {
			if (firstnum) {
				result = num1;
				firstnum = false;
			}
			lastNum = "";
			lastOperator = "+";
			firstOperatorSet = "+";
			break;
		}
		case "-": {
			if (firstnum) {
				result = num1;
				firstnum = false;
			}
			lastNum = "";
			lastOperator = "-";
			if (firstOperatorSet == null) {
				firstOperatorSet = "-";
			}
			break;
		}
		case "×": {
			if (firstnum) {
				result = num1;
				firstnum = false;
			}
			lastNum = "";
			lastOperator = "×";
			firstOperatorSet = "×";
			break;
		}
		case "/": {
			if (firstnum) {
				result = num1;
				firstnum = false;
			}
			lastNum = "";
			lastOperator = "/";
			firstOperatorSet = "/";
			break;
		}

		}
		// displays result and operator
		displayCalculations = Double.toString(result) + operator;
		calculations.setText(displayCalculations);
		screen.setText(Double.toString(result));
		calculations.setVisible(true);
		// resets values
		currentVal = "";
		finalCalculation = displayCalculations;
		displayCalculations = "";
		oldVals.clear();
	}

	/**
	 * Handles the event when the log button is clicked. Updates the calculations
	 * label and sets the last operator to "Log".
	 * 
	 * @since 1.0
	 */
	@FXML
	public void onLogClicked() {
		lastNum = "";
		lastOperator = "Log";
		displayCalculations += "Log(";
		calculations.setText(displayCalculations);
		calculations.setVisible(true);
		// resets values
		currentVal = "";
		finalCalculation = displayCalculations;
		displayCalculations = "";
		oldVals.clear();
	}

	/**
	 * Displays the final calculation with an "=" sign and shows the result on the
	 * main screen.
	 * 
	 * @since 1.0
	 */
	@FXML
	public void displayResult() {
		calculations.setText(finalCalculation + lastNum + "=");
		screen.setText(Double.toString(result));
		oldVals.clear();

	}

	/**
	 * Resets the calculator to its default state (starting state).
	 * 
	 * @since 1.0
	 */
	@FXML
	public void reset() {
		initialize();
	}

}
