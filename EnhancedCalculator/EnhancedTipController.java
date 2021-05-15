package EnhancedTipCalculator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class EnhancedTipController {
    private static final NumberFormat currency= NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent= NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage= new BigDecimal(0.15);
    private BigDecimal number = new BigDecimal(1);

    @FXML    private Label tipPercentageLabel;
    @FXML    private TextField amountTextField;
    @FXML    private TextField TipTextField;
    @FXML    private Slider tipPercentageSlider;
    @FXML    private TextField totalTextField;
    @FXML    private Button calculateButton;
    @FXML    private Slider numPeople;
    @FXML    private TextField individualTextField;
    @FXML    void calculateButtonPress(ActionEvent event) {

        try{
            BigDecimal amount=new BigDecimal(amountTextField.getText());
            BigDecimal tip= amount.multiply(tipPercentage);
            BigDecimal total=amount.add(tip);
            BigDecimal perPerson = total.divide(number);

            TipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
            individualTextField.setText(currency.format(perPerson));
        }
        catch(NumberFormatException ex){
            amountTextField.setText("Enter Amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();

        }

    }
    public void initialize(){

        currency.setRoundingMode(RoundingMode.HALF_UP);

        tipPercentageSlider.valueProperty().addListener(
                new ChangeListener<Number>(){
                    @Override
                    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue){
                        tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
                        tipPercentageLabel.setText(percent.format(tipPercentage));
                    }
                }
        );
        numPeople.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                        number = BigDecimal.valueOf(newValue.intValue());
                        individualTextField.setText(number.toString());
                    }
                }
        );


    }


}
