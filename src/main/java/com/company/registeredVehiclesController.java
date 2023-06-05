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

public class registeredVehiclesController implements Initializable {
    @FXML
    private TableView<vehicleModel> registeredVehicles;
    @FXML
    private TableColumn<com.company.vehicleModel, Integer> vehicleId,vehicleAvailability;
    @FXML
    private TableColumn<vehicleModel, String> vehicleOwnerName;
    @FXML
    private TableColumn<vehicleModel, String> vehicleType;
    @FXML
    private TableColumn<vehicleModel, String> vehicleModel;
    @FXML
    private TableColumn<vehicleModel, String> vehicleNo;
    @FXML
    private Button previousButton;
    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBUtils.getAllVehiclesDetails(registeredVehicles);
        vehicleId.setCellValueFactory(new PropertyValueFactory<vehicleModel,Integer>("vehicleId"));
        vehicleOwnerName.setCellValueFactory(new PropertyValueFactory<vehicleModel,String>("vehicleOwnerName"));
        vehicleType.setCellValueFactory(new PropertyValueFactory<vehicleModel,String>("vehicleType"));
        vehicleModel.setCellValueFactory(new PropertyValueFactory<vehicleModel,String>("vehicleModel"));
        vehicleNo.setCellValueFactory(new PropertyValueFactory<vehicleModel,String>("vehicleNo"));
        vehicleAvailability.setCellValueFactory(new PropertyValueFactory<vehicleModel,Integer>("vehicleAvailability"));

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
