package ContactApp;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import javax.swing.*;

public class contact extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("Contact.fxml"));
        Scene scene =new Scene(root);
        stage.setTitle("Contact App");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }

}
