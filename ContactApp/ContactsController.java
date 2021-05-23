package ContactApp;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ContactsController {
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();
    private Contact contactSelected;

    @FXML
    private ListView<Contact> ContactsListView;
    @FXML    private TextField firstNameField;
    @FXML    private TextField lastNameField;
    @FXML    private TextField emailField;
    @FXML    private TextField phoneNumberField;

    public void initialize(){
        contacts.add(new Contact("Shahnoza","Dodikhudoeva","sdodkhudoeva@gmail.com","1111222"));
        ContactsListView.setItems(contacts);
        ContactsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> arg0, Contact oldValue, Contact newValue) {
                contactSelected = newValue;
                firstNameField.setText(newValue.getFirstName());
                lastNameField.setText(newValue.getLastName());
                emailField.setText(newValue.getEmail());
                phoneNumberField.setText(newValue.getPhoneNumber());
            }
        });
    }

    public boolean validationFailed() {
        return (firstNameField.getText().trim().equals("") ||
                lastNameField.getText().trim().equals("") ||
                emailField.getText().trim().equals("") ||
                phoneNumberField.getText().trim().equals(""));
    }
    @FXML
    void addButtonPressed(ActionEvent event) {
        try{
            if (validationFailed()){
                throw new NumberFormatException();
            }
            else{
                Contact obj1 = new Contact();
                obj1.setFirstName(firstNameField.getText());
                obj1.setLastName(lastNameField.getText());
                obj1.setEmail(emailField.getText());
                obj1.setPhoneNumber(phoneNumberField.getText());
                contacts.add(obj1);
            }
        } catch (Exception ex){
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
            firstNameField.requestFocus();
        }

    }

    @FXML
    void deleteButtonPressed(ActionEvent event) {
        try {
            contacts.remove(contactSelected);
        }
        catch (Exception ex){
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
            firstNameField.requestFocus();
        }
    }

    @FXML
    void updateButtonPressed(ActionEvent event) {
        try{
            if (validationFailed()){
                throw new NumberFormatException();
            }
            else{
                Contact obj1 = new Contact();
                contactSelected.setFirstName(firstNameField.getText());
                contactSelected.setLastName(lastNameField.getText());
                contactSelected.setEmail(emailField.getText());
                contactSelected.setPhoneNumber(phoneNumberField.getText());

            }
        } catch (Exception ex){
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
            firstNameField.requestFocus();
        }
    }

}