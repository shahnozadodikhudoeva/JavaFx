package TargetCalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TargetController {
    @FXML    private TextField ageTextField;
    @FXML    private Text maxText;
    @FXML    private Text targetMin;
    @FXML    private Text targetMax;

    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            int age = Integer.parseInt(ageTextField.getText());
            if (age < 0 || age > 220) ageTextField.setText("");
            else {
                maxText.setText(String.valueOf(220 - age));
                targetMin.setText(String.valueOf((int) ((220 - age) * 0.5)));
                targetMax.setText(String.valueOf((int) ((220 - age) * 0.85)));
            }
        } catch (NumberFormatException ex) {
            ageTextField.setText("Invalid input");
            ageTextField.selectAll();
            ageTextField.requestFocus();
        }
    }
}