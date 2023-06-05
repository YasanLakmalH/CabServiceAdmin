package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class registeredDriversController implements Initializable{
    @FXML
    private TableView<driverModel> registeredDrivers;
    @FXML
    private TableColumn<driverModel,Integer> driverId;
    @FXML
    private TableColumn<driverModel,Long> nic;
    @FXML
    private TableColumn<driverModel,String> firstName, lastName, mobile, email, city;
    @FXML
    private Button previousButton;
    @FXML
    private Button closeButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        DBUtils.getAllDriversDetails(registeredDrivers);
        driverId.setCellValueFactory(new PropertyValueFactory<driverModel,Integer>("driverId"));
        nic.setCellValueFactory(new PropertyValueFactory<driverModel,Long>("nic"));
        firstName.setCellValueFactory(new PropertyValueFactory<driverModel,String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<driverModel,String>("lastName"));
        mobile.setCellValueFactory(new PropertyValueFactory<driverModel,String>("mobile"));
        email.setCellValueFactory(new PropertyValueFactory<driverModel,String>("email"));
        city.setCellValueFactory(new PropertyValueFactory<driverModel,String>("city"));

        previousButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event,"adminDashboard.fxml");
            }
        });
        closeButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                System.exit(0);
            }
        });
    }


}
