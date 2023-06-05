package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class vehicleRegisterController implements Initializable {
    @FXML
    private TextField vehicleOwnerName;
    @FXML
    private TextField vehicleType;
    @FXML
    private TextField vehicleModel;
    @FXML
    private TextField vehicleNo;
    @FXML
    private Button registerButton;
    @FXML
    private Button cancelButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.vehicleRegister(event,vehicleOwnerName.getText(), vehicleType.getText(),vehicleModel.getText(),vehicleNo.getText());

            }
        });
        cancelButton.setOnAction(new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event,"adminDashboard.fxml");
            }
        });
    }
}
