package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class pendingBookingsController implements Initializable {
    @FXML
    private TableView<com.company.pendingBookings> pendingBookings;
    @FXML
    private TableColumn<com.company.pendingBookings, Integer> bookingIdColumn,userIdColumn;
    @FXML
    private TableColumn<com.company.pendingBookings, String> contactNoColumn;
    @FXML
    private TableColumn<com.company.pendingBookings, String> pickupLocationColumn;
    @FXML
    private TableColumn<com.company.pendingBookings, String> destinationColumn;
    @FXML
    private TableColumn<com.company.pendingBookings, String> vehicleTypeColumn;
    @FXML
    private TableColumn<com.company.pendingBookings, String> dateColumn;
    @FXML
    private TableColumn<com.company.pendingBookings, String> timeColumn;
    @FXML
    private ChoiceBox<Integer> bookingIdBox,driverIdBox;
    @FXML
    private ChoiceBox<String> vehicleModelBox;
    @FXML
    private Button previousButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button proceedButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBUtils.getPendingBookingsDetails(pendingBookings,bookingIdBox);
        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<com.company.pendingBookings,Integer>("bookingId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<com.company.pendingBookings,Integer>("userId"));
        contactNoColumn.setCellValueFactory(new PropertyValueFactory<com.company.pendingBookings,String>("contactNo"));
        pickupLocationColumn.setCellValueFactory(new PropertyValueFactory<com.company.pendingBookings,String>("pickupLocation"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<com.company.pendingBookings,String>("destination"));
        vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<com.company.pendingBookings,String>("vehicleType"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<com.company.pendingBookings,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<com.company.pendingBookings,String>("time"));

        bookingIdBox.valueProperty().addListener((observable, oldValue, newValue)->{
            DBUtils.getAvailableDriversId(bookingIdBox.getValue(),driverIdBox);
            DBUtils.getVehicleModels(bookingIdBox.getValue(),vehicleModelBox);
        });
        proceedButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                DBUtils.activatePendingBooking(bookingIdBox.getValue(),vehicleModelBox.getValue(),driverIdBox.getValue());
                DBUtils.changeScene(event,"adminDashboard.fxml");
            }
        });

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
