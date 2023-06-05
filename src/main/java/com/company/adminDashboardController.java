package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class adminDashboardController implements Initializable {
    @FXML
    private Button driverRegisterButton;
    @FXML
    private Button vehicleRegisterButton;
    @FXML
    private Button registeredDriversButton;
    @FXML
    private Button registeredVehiclesButton;
    @FXML
    private Button pendingBookingsButton;
    @FXML
    private Button activeBookingsButton;
    @FXML
    private Button completedBookingsButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button closeButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        previousButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"adminLogin.fxml");
            }
        });
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        driverRegisterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event,"driverRegister.fxml");
            }
        });
        vehicleRegisterButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event,"vehicleRegister.fxml");
            }
        });
        registeredDriversButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                DBUtils.changeScene(event,"registeredDrivers.fxml");
            }
        });
        registeredVehiclesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"registeredVehicles.fxml");
            }
        });
        pendingBookingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"pendingBookings.fxml");
            }
        });
        activeBookingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"activeBookings.fxml");
            }
        });
        completedBookingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"completedBookings.fxml");
            }
        });
    }


}
