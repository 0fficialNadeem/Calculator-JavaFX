package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {
// Assigning components
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

	public void initialize() {
		calculations.setVisible(false);
		currentVal = "";
		displayCalculations = "";
		result = 0;
		screen.setText("0");
		num1 = null;
		oldVals.clear();
	}

	@FXML
	public void onNumClicked(ActionEvent event) {
		Button btn = (Button) event.getSource();
		String btnText = btn.getText();
		lastNum += btnText;
		System.out.println(currentVal);
		// Take note of values pressed
		if (!currentVal.contains(".") || !btnText.equals(".")) {
			currentVal += btnText;
		}

		// operations
		if (lastOperator == ("+")) {
			result += Double.parseDouble(lastNum);
			oldVals.add(lastNum);

			System.out.println(oldVals);
			System.out.println("result: " + result);
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
				System.out.println("dividing by:" + Double.parseDouble(oldVals.get(i)));
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

	// remove characters from displayed value
	@FXML
	public void onDelete() {
		currentVal = currentVal.substring(0, currentVal.length() - 1);
		screen.setText(currentVal);

	}

	@FXML
	public void onOperatorClicked(ActionEvent event) {
		Button btn = (Button) event.getSource();
		String operator = btn.getText();
		if (num1 == null) {
			num1 = Double.parseDouble(currentVal);
			firstnum = true;
		}

		switch (operator) {
		case "+": {
			if (firstnum) {
				result = num1;
				firstnum = false;
			}
			lastNum = "";
			lastOperator = "+";
			break;
		}
		case "-": {
			if (firstnum) {
				result = num1;
				firstnum = false;
			}
			lastNum = "";
			lastOperator = "-";
			break;
		}
		case "×": {
			if (firstnum) {
				result = num1;
				firstnum = false;
			}
			lastNum = "";
			lastOperator = "×";
			break;
		}
		case "/": {
			if (firstnum) {
				result = num1;
				firstnum = false;
			}
			lastNum = "";
			lastOperator = "/";
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

	@FXML
	public void onLogClicked() {
		if (firstnum) {
			result = num1;
			firstnum = false;
		}
		lastNum = "";
		lastOperator = "Log";
		displayCalculations += "Log(";
		calculations.setText(displayCalculations);
		screen.setText(Double.toString(result));
		calculations.setVisible(true);
		// resets values
		currentVal = "";
		finalCalculation = displayCalculations;
		displayCalculations = "";
		oldVals.clear();
	}

	// Displays result
	@FXML
	public void displayResult() {
		calculations.setText(finalCalculation + lastNum + "=");
		screen.setText(Double.toString(result));
		oldVals.clear();

	}

	// reset everything
	@FXML
	public void reset() {
		initialize();
	}

}
