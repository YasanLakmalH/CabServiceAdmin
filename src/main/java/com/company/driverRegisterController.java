package com.company;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
public class driverRegisterController implements Initializable {
    @FXML
    private Button createAccountButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField nicText;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField mobileText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField cityText;
    @FXML
    private TextField userNameText;
    @FXML
    private TextField passwordText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.driverRegister(event, Long.parseLong(nicText.getText()),
                        firstNameText.getText(),
                        lastNameText.getText(),
                        mobileText.getText(),
                        emailText.getText(),
                        cityText.getText(),
                        userNameText.getText(),
                        passwordText.getText());
            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"adminDashboard.fxml");
            }
        });
    }
}
