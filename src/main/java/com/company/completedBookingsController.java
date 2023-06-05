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
public class completedBookingsController implements Initializable{
    @FXML
    private TableView<completedBookings> completedBookings;
    @FXML
    private TableColumn<completedBookings, Integer> bookingIdColumn,userIdColumn,contactNoColumn;
    @FXML
    private TableColumn<completedBookings, String> pickupLocationColumn,destinationColumn;
    @FXML
    private TableColumn<completedBookings, String> vehicleTypeColumn,vehicleNoColumn;
    @FXML
    private TableColumn<completedBookings,Integer> driverIdColumn;
    @FXML
    private TableColumn<completedBookings, String> driverNameColumn;
    @FXML
    private TableColumn<completedBookings, String> dateColumn;
    @FXML
    private TableColumn<completedBookings, String> timeColumn;
    @FXML
    private Button previousButton;
    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBUtils.getCompletedBookingsDetails(completedBookings);
        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,Integer>("bookingId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,Integer>("userId"));
        contactNoColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,Integer>("contactNo"));
        pickupLocationColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,String>("pickupLocation"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,String>("destination"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,String>("vehicleType"));
        vehicleNoColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,String>("vehicleNo"));
        driverIdColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,Integer>("driverId"));
        driverNameColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,String>("driverName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<completedBookings,String>("time"));

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
