package Painter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class painterModifiedController {
    @FXML    private Pane drawingAreaPane;
    @FXML    private Slider redSlider;
    @FXML    private Slider greenSlider;
    @FXML    private Slider blueSlider;
    @FXML    private Slider alphaSlider;
    @FXML    private TextField redTextField;
    @FXML    private TextField greenTextField;
    @FXML    private TextField blueTextField;
    @FXML    private TextField alphaTextField;
    @FXML    private Circle colorCircle;
    @FXML    private Rectangle colorRectangle;
    @FXML    private RadioButton smallRadioButton;
    @FXML    private ToggleGroup sizeToggleGroup;
    @FXML    private RadioButton mediumRadioButton;
    @FXML    private RadioButton largeRadioButton;
    @FXML    private Button undoButton;
    @FXML    private Button clearButton;

    private PenSize radius=PenSize.MEDIUM;
    private Paint colorBrush= Color.BLACK;

    private int red=0;
    private int green=0;
    private int blue=0;
    private double alpha=1.0;

    private enum PenSize{
        SMALL(2),
        MEDIUM(4),
        LARGE(6);
        private final int radius;
        PenSize(int radius){this.radius=radius;}
        public int getRadius(){return radius;}
    }


    public void initialize(){
        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);

        redTextField.textProperty().bind(redSlider.valueProperty().asString("%.0f"));
        greenTextField.textProperty().bind(greenSlider.valueProperty().asString("%.0f"));
        blueTextField.textProperty().bind(blueSlider.valueProperty().asString("%.0f"));
        alphaTextField.textProperty().bind(alphaSlider.valueProperty().asString("%.2f"));


        //listener that set Rectangle's fill base on Slider changes

        redSlider.valueProperty().addListener(
                new ChangeListener<Number>(){
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue){
                        red=newValue.intValue();
                        colorRectangle.setFill(Color.rgb(red,green,blue,alpha));
                        colorBrush = Color.rgb(red, green, blue);

                    }
                }
        );

        greenSlider.valueProperty().addListener(
                new ChangeListener<Number>(){
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue){
                        green=newValue.intValue();
                        colorRectangle.setFill(Color.rgb(red,green,blue,alpha));
                        colorBrush = Color.rgb(red, green, blue);
                    }
                }
        );

        blueSlider.valueProperty().addListener(
                new ChangeListener<Number>(){
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue){
                        blue=newValue.intValue();
                        colorRectangle.setFill(Color.rgb(red,green,blue,alpha));
                        colorBrush = Color.rgb(red, green, blue);
                    }
                }
        );

        alphaSlider.valueProperty().addListener(
                new ChangeListener<Number>(){
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue){
                        alpha=newValue.doubleValue();
                        colorRectangle.setFill(Color.rgb(red,green,blue,alpha));
                        colorBrush = Color.rgb(red, green, blue);
                    }
                }
        );
    }

    public void setColorRectangle(Rectangle colorRectangle) {
        this.colorRectangle = colorRectangle;
    }

    @FXML
    void undoButtonPressed(ActionEvent event) {
        int count = drawingAreaPane.getChildren().size();
        if(count>0){
            drawingAreaPane.getChildren().remove(count-1);
        }
    }
    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();

    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent e) {
        Circle newCircle= new Circle (e.getX(), e.getY(), radius.getRadius(), colorBrush);
        drawingAreaPane.getChildren().add(newCircle);
    }

    @FXML
    void sizeRadioButtonPressed(ActionEvent event) {
        radius=(PenSize) sizeToggleGroup.getSelectedToggle().getUserData();

    }

}
