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

public class activeBookingsController implements Initializable {
    @FXML
    private TableView<activeBookings> activeBookings;
    @FXML
    private TableColumn<activeBookings, Integer> bookingIdColumn,userIdColumn,contactNoColumn;
    @FXML
    private TableColumn<activeBookings, String> pickupLocationColumn,destinationColumn;
    @FXML
    private TableColumn<activeBookings, String> vehicleTypeColumn,vehicleNoColumn;
    @FXML
    private TableColumn<activeBookings,Integer> driverIdColumn;
    @FXML
    private TableColumn<activeBookings, String> driverNameColumn;
    @FXML
    private TableColumn<activeBookings, String> dateColumn;
    @FXML
    private TableColumn<activeBookings, String> timeColumn;
    @FXML
    private Button previousButton;
    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBUtils.getActiveBookingsDetails(activeBookings);
        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,Integer>("bookingId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,Integer>("userId"));
        contactNoColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,Integer>("contactNo"));
        pickupLocationColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,String>("pickupLocation"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,String>("destination"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,String>("vehicleType"));
        vehicleNoColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,String>("vehicleNo"));
        driverIdColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,Integer>("driverId"));
        driverNameColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,String>("driverName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<activeBookings,String>("time"));

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
