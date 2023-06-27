package application;

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

	private double result = 0;
	String lastCalculation = "";
	String lastVal = "";
	String lastOperation;

	public void onClick(ActionEvent event) {
		Button clickedBtn = (Button) event.getSource();

		// update working-out screen
		if (!clickedBtn.getText().equals("DEL")
				&& !clickedBtn.getText().equals("AC") && !clickedBtn.getText().equals("RESULT")) {
			lastCalculation += clickedBtn.getText();
			calculations.setText(lastCalculation);
		}

		// deletes character for working-out screen
		if (clickedBtn.getText().equals("DEL") && lastCalculation.length() > 0) {
			lastCalculation = lastCalculation.substring(0, lastCalculation.length() - 1);
			calculations.setText(lastCalculation);
		}

		// update main Screen value
		if (!clickedBtn.getText().equals("DEL") && !clickedBtn.getText().equals("LOG")
				&& !clickedBtn.getText().equals("AC") && !clickedBtn.getText().equals("+")
				&& !clickedBtn.getText().equals("-") && !clickedBtn.getText().equals("/")
				&& !clickedBtn.getText().equals("×")
				&& !clickedBtn.getText().equals("RESULT")) {
			lastVal += clickedBtn.getText();
			screen.setText(lastVal);
		}

		// carry out operations
		if (clickedBtn.getText().equals("+")) {
			if (lastVal.length() > 0) {
				result += Double.parseDouble(lastVal);
				lastVal = "";
			}
			lastOperation = "+";
		}

		if (clickedBtn.getText().equals("-")) {
			if (lastVal.length() > 0) {
				result = Double.parseDouble(lastVal);
				lastVal = "";
			}
			lastOperation = "-";
		}

		if (clickedBtn.getText().equals("×")) {
			if (lastVal.length() > 0) {
				result = Double.parseDouble(lastVal);
				lastVal = "";

			}
			lastOperation = "*";
		}
		if (clickedBtn.getText().equals("/")) {
			if (lastVal.length() > 0) {
				result = Double.parseDouble(lastVal);
				;
				lastVal = "";
			}
			lastOperation = "/";
		}
		

		// display result
		if (clickedBtn.getText().equals("RESULT")) {
			if (lastOperation.equals("+")) {
				result += Double.parseDouble(lastVal);
			} else if (lastOperation.equals("-")) {
				result -= Double.parseDouble(lastVal);

			} else if (lastOperation.equals("*")) {
				result *= Double.parseDouble(lastVal);

			} else if (lastOperation.equals("/")) {
				result /= Double.parseDouble(lastVal);

			} 
			screen.setText(Double.toString(result));
			lastCalculation = Double.toString(result);
			lastVal = "";
			lastOperation = "";
		}

		// Reset Everything
		if (clickedBtn.getText().equals("AC")) {
			result = 0;
			lastVal = "";
			lastCalculation = "";
			screen.setText("0");
			calculations.setText("0");
		}

	}

}
