package BMICalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class BMIController {
    @FXML    private TextField weightTextField;
    @FXML    private TextField heightTextField;
    @FXML    private Text bmiText;
    @FXML    private ToggleButton metric;
    @FXML    private ToggleButton english;
    @FXML    private Text bmiRes;
    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            double weight = Double.parseDouble(weightTextField.getText());
            double height = Double.parseDouble(heightTextField.getText());
            if (weight < 0 || height < 0) {
                weightTextField.setText("Weight/height cannot be negative");
                heightTextField.setText("Weight/height cannot be negative");
            } else {
                double bmi = (metric.isSelected() ? 1 : 703) * weight / (height * height);
                bmiText.setText(String.format("%.2f", bmi));
                if (bmi < 18.5) bmiRes.setText("Underweight");
                else if (bmi < 25) bmiRes.setText("Normal");
                else if (bmi < 30) bmiRes.setText("Overweight");
                else bmiRes.setText("Obese");
            }
        } catch (NumberFormatException ex) {
            weightTextField.setText("Invalid input");
            weightTextField.selectAll();
            weightTextField.requestFocus();
        }
    }
    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null) oldVal.setSelected(true);
        });
        metric.setToggleGroup(toggleGroup);
        english.setToggleGroup(toggleGroup);
    }
}