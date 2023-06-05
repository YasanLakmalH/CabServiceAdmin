package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static javafx.fxml.FXMLLoader.load;
public class DBUtils {
    static double x = 0;
    static double y = 0;
    public static void changeScene(ActionEvent event, String fxmlFile) {
        Parent root = null;
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        try {
            root = load(DBUtils.class.getResource(fxmlFile));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.centerOnScreen();
        root.setOnMousePressed(MouseEvent->{
            x = MouseEvent.getSceneX();
            y = MouseEvent.getSceneY();
        });
        root.setOnMouseClicked(MouseEvent->{
            stage.setX(MouseEvent.getScreenX()-x);
            stage.setY(MouseEvent.getScreenY()-y);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void driverRegister(ActionEvent event, Long nicText, String firstNameText, String lastNameText, String mobileText, String userEmail, String cityText, String userName, String userPassword) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM drivers WHERE driverName = ?");
            psCheckUserExist.setString(1, userName);
            resultSet = psCheckUserExist.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("User already exist!");
                alert.setContentText("you cannot use this username.");
                alert.show();
            } else {
                int newDriverId = 0;
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT driverId FROM drivers;");
                while (resultSet.next()) {
                    newDriverId = resultSet.getInt("driverId") + 1;
                }
                psInsert = connection.prepareStatement("INSERT INTO driverdetails VALUES(?, ?, ?, ?, ?, ?, ?)");
                psInsert.setInt(1, newDriverId);
                psInsert.setLong(2, nicText);
                psInsert.setString(3, firstNameText);
                psInsert.setString(4, lastNameText);
                psInsert.setString(5, mobileText);
                psInsert.setString(6, userEmail);
                psInsert.setString(7, cityText);
                psInsert.executeUpdate();

                psInsert = connection.prepareStatement("INSERT INTO drivers VALUES(?, ?, ?)");
                psInsert.setInt(1, newDriverId);
                psInsert.setString(2, userName);
                psInsert.setString(3, userPassword);
                psInsert.setInt(4,1);
                psInsert.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Successfully");
                alert.setContentText("Account created.");
                alert.show();

                changeScene(event, "customerLogin.fxml");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {

                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void vehicleRegister(ActionEvent event,String vehicleOwnerName,String vehicleType,String vehicleModel,String vehicleNo) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM vehicles WHERE vehicleNo = ?");
            psCheckUserExist.setString(1, vehicleNo);
            resultSet = psCheckUserExist.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("Vehicle already exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Vehicle already exist!");
                alert.setContentText("Already exist");
                alert.show();
            } else {
                int vehicleId = 0;
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT vehicleId FROM vehicles;");
                while (resultSet.next()) {
                    vehicleId = resultSet.getInt("vehicleId") + 1;
                }
                psInsert = connection.prepareStatement("INSERT INTO vehicles VALUES(?, ?, ?, ?, ?)");
                psInsert.setInt(1, vehicleId);
                psInsert.setString(2, vehicleOwnerName);
                psInsert.setString(3, vehicleType);
                psInsert.setString(4, vehicleModel);
                psInsert.setString(5, vehicleNo);
                psInsert.setInt(6,1);
                psInsert.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Successfully");
                alert.setContentText("Vehicle Upadated Successfully");
                alert.show();
                changeScene(event, "adminDashboard.fxml");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {

                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInVerify(ActionEvent event, String fxmlFile,String userName, String userPassword){
        Connection connection = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        String receivedPassword = null;
        int userId = 0;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice","root","");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM admins WHERE userName=?");
            psCheckUserExist.setString(1,userName);
            resultSet = psCheckUserExist.executeQuery();


            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Account does not exist");
                alert.show();
            }else{
                while(resultSet.next()){
                    receivedPassword = resultSet.getString("password");
                }
                assert receivedPassword != null;
                if(receivedPassword.equals(userPassword)){
                    System.out.println("Success");
                    changeScene(event,fxmlFile);

                }
                else{
                    System.out.println("Password doesn't match!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Password doesn't match");
                    alert.show();
                }
            }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        finally{
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psCheckUserExist != null){
                try{
                    psCheckUserExist.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void getAllDriversDetails(TableView<driverModel> tableName) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet=null;
        ObservableList<driverModel> list = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM driverdetails;");
            while (resultSet.next()) {
                int driverId = resultSet.getInt("driverId");
                Long nic = resultSet.getLong("nic");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String mobile = resultSet.getString("mobile");
                String email = resultSet.getString("email");
                String city = resultSet.getString("city");
                list.add(new driverModel(
                        driverId,
                        nic,
                        firstName,
                        lastName,
                        mobile,
                        email,
                        city
                ));
            }
            tableName.setItems(list);

        } catch (SQLException e) {
            System.out.println("SQL:" + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if(statement != null){
                try{
                    statement.close();
                }catch (SQLException e){
                e.printStackTrace();
                }
            }if (connection != null){
                    try{
                        connection.close();
                    }catch (SQLException e){
                        e.printStackTrace();

                    }
                }
        }
    }
    public static void getAllVehiclesDetails(TableView<vehicleModel> tableName){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ObservableList<vehicleModel> list = FXCollections.observableArrayList();
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM vehicles");
            while(resultSet.next()){
                int vehicleId = resultSet.getInt("vehicleId");
                String vehicleOwnerName = resultSet.getString("vehicleOwnerName");
                String vehicleType = resultSet.getString("vehicleType");
                String vehicleModel = resultSet.getString("vehicleModel");
                String vehicleNo = resultSet.getString("vehicleNo");
                int vehicleAvailability = resultSet.getInt("vehicleAvailability");
                list.add(new vehicleModel(
                        vehicleId,
                        vehicleOwnerName,
                        vehicleType,
                        vehicleModel,
                        vehicleNo,
                        vehicleAvailability
                ));
            }
            tableName.setItems(list);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if(statement != null){
                try{
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();

                }
            }
        }
    }

    public static void getPendingBookingsDetails(TableView<pendingBookings> tableName,ChoiceBox<Integer> bookingIdBox){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ObservableList<pendingBookings> list = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM userbookings WHERE status=\"pending\";");
            while(resultSet.next()){
                int bookingId = resultSet.getInt("bookingId");
                int userId = resultSet.getInt("userId");
                int contactNo = resultSet.getInt("contactNo");
                String pickupLocation = resultSet.getString("pickupLocation");
                String destination = resultSet.getString("destination");
                String vehicleType = resultSet.getString("vehicleType");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");

                list.add(new pendingBookings(
                        bookingId,
                        userId,
                        contactNo,
                        pickupLocation,
                        destination,
                        vehicleType,
                        date,
                        time
                ));
                bookingIdBox.getItems().add(bookingId);
            }
            tableName.setItems(list);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if(statement != null){
                try{
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();

                }
            }
        }

    }
    public static String getBookingDate(int bookingId){
        Connection connection = null;
        PreparedStatement psGetBookingDate = null;
        ResultSet resultSet = null;
        String bookingDate = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psGetBookingDate = connection.prepareStatement("SELECT date from userbookings WHERE bookingId=?");
            psGetBookingDate.setInt(1,bookingId);
            resultSet = psGetBookingDate.executeQuery();
            while(resultSet.next()){
                bookingDate = resultSet.getString("date");
            }

        }catch (SQLException e) {
            System.out.println("checkVehicleAvailability SQL Exception:" + e.getMessage());
        }finally {
            try{
                if(resultSet != null) {
                    resultSet.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(psGetBookingDate != null){
                    psGetBookingDate.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return bookingDate;

    }

    public static void getAvailableDriversId(int bookingId,ChoiceBox<Integer> driverIdBox) {
        Connection connection = null;
        PreparedStatement psCheckVehicleExist = null;
        ResultSet resultSet = null;
        ArrayList<Integer> driverIdList = new ArrayList<Integer>();
        String date = getBookingDate(bookingId);
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psCheckVehicleExist = connection.prepareStatement("SELECT DISTINCT driverId " +
                    "FROM bookingmaster " +
                    "INNER JOIN userbookings " +
                    "ON bookingmaster.bookingId = userbookings.bookingId " +
                    "WHERE userbookings.date <> ? " +
                    "UNION " +
                    "SELECT driverId " +
                    "FROM drivers " +
                    "WHERE driverAvailability <> 0 " +
                    "ORDER BY driverId ASC; ");
            psCheckVehicleExist.setString(1, date);
            resultSet = psCheckVehicleExist.executeQuery();
            while(resultSet.next()) {
                driverIdList.add(resultSet.getInt("driverId"));
            }
            driverIdBox.getItems().clear();
            driverIdBox.getItems().addAll(driverIdList);

        } catch (SQLException e) {
            System.out.println("getAvailableDriverId SQL Exception:" + e.getMessage());
        }finally {
            try{
                if(resultSet != null) {
                    resultSet.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(psCheckVehicleExist != null){
                    psCheckVehicleExist.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static String getVehicleType(int bookingId){
        Connection connection = null;
        PreparedStatement psGetVehicleCategory = null;
        ResultSet resultSet = null;
        String vehicleType = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psGetVehicleCategory = connection.prepareStatement("SELECT vehicleType " +
                    "FROM vehicles " +
                    "INNER JOIN bookingmaster " +
                    "ON bookingmaster.vehicleId = vehicles.vehicleId " +
                    "WHERE bookingmaster.bookingId = 1;");
            resultSet = psGetVehicleCategory.executeQuery();
            while(resultSet.next()){
                vehicleType = resultSet.getString("vehicleType");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(resultSet != null) {
                    resultSet.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(psGetVehicleCategory != null){
                    psGetVehicleCategory.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        } {

        }
        return vehicleType;
    }
    public static void getVehicleModels(int bookingId,ChoiceBox<String> vehicleModelBox){
        Connection connection = null;
        PreparedStatement psGetVehicleModel = null;
        ResultSet resultSet = null;
        ArrayList<String> vehicleModelList = new ArrayList<String>();
        String vehicleType = getVehicleType(bookingId);
        String bookingDate = getBookingDate(bookingId);
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psGetVehicleModel = connection.prepareStatement("SELECT vehicleModel " +
                    "FROM vehicles " +
                    "INNER JOIN bookingmaster " +
                    "ON bookingmaster.vehicleId = vehicles.vehicleId " +
                    "INNER JOIN userbookings " +
                    "ON bookingmaster.bookingId = userbookings.bookingId " +
                    "WHERE userbookings.date <>? " +
                    "AND userbookings.vehicleType=? " +
                    "UNION " +
                    "SELECT vehicleModel " +
                    "FROM vehicles " +
                    "WHERE vehicleAvailability <> 0 " +
                    "AND vehicleType=? ");
            psGetVehicleModel.setString(1, bookingDate);
            psGetVehicleModel.setString(2,vehicleType);
            psGetVehicleModel.setString(3,vehicleType);
            resultSet = psGetVehicleModel.executeQuery();
            while(resultSet.next()){
                vehicleModelList.add(resultSet.getString("vehicleModel"));
            }
            vehicleModelBox.getItems().clear();
            vehicleModelBox.getItems().addAll(vehicleModelList);
        }
        catch(SQLException e){
            System.out.println("checkVehicleAvailability SQL Exception:"+e.getMessage());
        }finally {
            try{
                if(resultSet != null) {
                    resultSet.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(psGetVehicleModel != null){
                    psGetVehicleModel.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void getActiveBookingsDetails(TableView<activeBookings> tableName){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ObservableList<activeBookings> list = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT " +
                    "bookingmaster.bookingId," +
                    "bookingmaster.userId," +
                    "bookingmaster.driverId," +
                    "bookingmaster.vehicleId," +
                    "userbookings.contactNo," +
                    "userbookings.pickupLocation," +
                    "userbookings.destination," +
                    "userbookings.vehicleType," +
                    "userbookings.date," +
                    "userbookings.time," +
                    "driverdetails.firstName," +
                    "vehicles.vehicleModel," +
                    "vehicles.vehicleNo " +
                    "FROM bookingmaster " +
                    "INNER JOIN userbookings " +
                    "ON bookingmaster.userId = userbookings.userId " +
                    "INNER JOIN driverdetails " +
                    "ON bookingmaster.driverId = driverdetails.driverId " +
                    "INNER JOIN vehicles " +
                    "ON bookingmaster.vehicleId = vehicles.vehicleId " +
                    "WHERE bookingmaster.status=\"active\";");
            while(resultSet.next()){
                int bookingId = resultSet.getInt("bookingId");
                int userId = resultSet.getInt("userId");
                int contactNo = resultSet.getInt("contactNo");
                String pickupLocation = resultSet.getString("pickupLocation");
                String destination = resultSet.getString("destination");
                String vehicleType = resultSet.getString("vehicleType");
                String vehicleNo = resultSet.getString("vehicleNo");
                int driverId = resultSet.getInt("driverId");
                String driverName = resultSet.getString("firstName");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");

                list.add(new activeBookings(
                        bookingId,
                        userId,
                        contactNo,
                        pickupLocation,
                        destination,
                        vehicleType,
                        vehicleNo,
                        driverId,
                        driverName,
                        date,
                        time
                ));
            }
            tableName.setItems(list);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if(statement != null){
                try{
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();

                }
            }
        }

    }
    public static void getCompletedBookingsDetails(TableView<completedBookings> tableName){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ObservableList<completedBookings> list = FXCollections.observableArrayList();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice","root","");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT " +
                            "bookingmaster.bookingId," +
                            "bookingmaster.userId," +
                            "bookingmaster.driverId," +
                            "bookingmaster.vehicleId," +
                            "userbookings.contactNo," +
                            "userbookings.pickupLocation," +
                            "userbookings.destination," +
                            "userbookings.vehicleType," +
                            "userbookings.date," +
                            "userbookings.time," +
                            "driverdetails.firstName," +
                            "vehicles.vehicleModel," +
                            "vehicles.vehicleNo " +
                            "FROM bookingmaster " +
                            "INNER JOIN userbookings " +
                            "ON bookingmaster.userId = userbookings.userId " +
                            "INNER JOIN driverdetails " +
                            "ON bookingmaster.driverId = driverdetails.driverId " +
                            "INNER JOIN vehicles " +
                            "ON bookingmaster.vehicleId = vehicles.vehicleId " +
                            "WHERE bookingmaster.status=\"completed\";");
            while(resultSet.next()){
                int bookingId = resultSet.getInt("bookingId");
                int userId = resultSet.getInt("userId");
                int contactNo = resultSet.getInt("contactNo");
                String pickupLocation = resultSet.getString("pickupLocation");
                String destination = resultSet.getString("destination");
                String vehicleType = resultSet.getString("vehicleType");
                String vehicleNo = resultSet.getString("vehicleNo");
                int driverId = resultSet.getInt("driverId");
                String driverName = resultSet.getString("firstName");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");

                list.add(new completedBookings(
                        bookingId,
                        userId,
                        contactNo,
                        pickupLocation,
                        destination,
                        vehicleType,
                        vehicleNo,
                        driverId,
                        driverName,
                        date,
                        time
                ));
            }
            tableName.setItems(list);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }if(statement != null){
                try{
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }if (connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();

                }
            }
        }

    }

public static void activatePendingBooking(int bookingId,String vehicleModel,int driverId){
        Connection connection = null;
        PreparedStatement psActiveateBooking = null;
        LocalDateTime createdDateTime = java.time.LocalDateTime.now();
        String bookingDate = getBookingDate(bookingId);
        int userId = getUserId(bookingId);
        int vehicleId = getVehicleId(bookingId,bookingDate,vehicleModel);

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psActiveateBooking = connection.prepareStatement("INSERT INTO bookingmaster VALUES(?,?,?,?,?,?)");
            psActiveateBooking.setInt(1,bookingId);
            psActiveateBooking.setInt(2,userId);
            psActiveateBooking.setInt(3,driverId);
            psActiveateBooking.setInt(4,vehicleId);
            psActiveateBooking.setString(5, String.valueOf(createdDateTime));
            psActiveateBooking.setString(6,"active");


        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(psActiveateBooking != null){
                    psActiveateBooking.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        updateBookingStatus(bookingId,"active");
        updateVehicleAvailability(vehicleId);
        updateDriverAvailability(driverId);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Successfully");
        alert.setContentText("Booking Activated Successfully");
}
        public static int getUserId(int bookingId){
            Connection connection = null;
            PreparedStatement psGetVehicleId = null;
            ResultSet resultSet = null;
            int vehicleId = 0;
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
                psGetVehicleId = connection.prepareStatement("SELECT userId FROM userbookings WHERE bookingId=?");
                psGetVehicleId.setInt(1,bookingId);
                resultSet = psGetVehicleId.executeQuery();
                while(resultSet.next()){
                    vehicleId = resultSet.getInt("userId");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }finally {
                try{
                    if(psGetVehicleId != null){
                        psGetVehicleId.close();
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
                try{
                    if(connection != null){
                        connection.close();
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            return vehicleId;
        }
    public static int getVehicleId(int bookingId,String bookingDate, String vehicleModel){
        Connection connection = null;
        PreparedStatement psGetVehicleId = null;
        ResultSet resultSet = null;
        int vehicleId = 0;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psGetVehicleId = connection.prepareStatement("SELECT bookingmaster.vehicleId " +
                    "FROM bookingmaster " +
                    "INNER JOIN vehicles " +
                    "ON bookingmaster.vehicleId = vehicles.vehicleId " +
                    "INNER JOIN userbookings " +
                    "ON bookingmaster.bookingId = userbookings.bookingId " +
                    "WHERE userbookings.date <>? " +
                    "AND vehicles.vehicleModel=>? " +
                    "UNION " +
                    "SELECT vehicleId " +
                    "FROM vehicles " +
                    "WHERE vehicleAvailability <> 0 " +
                    "AND vehicleModel=? " +
                    "ORDER BY vehicleId ASC " +
                    "LIMIT 1;");
            psGetVehicleId.setInt(1,bookingId);
            psGetVehicleId.setString(2,vehicleModel);
            psGetVehicleId.setString(3,vehicleModel);
            resultSet = psGetVehicleId.executeQuery();
            while(resultSet.next()){
                vehicleId = resultSet.getInt("vehicleId");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(psGetVehicleId != null){
                    psGetVehicleId.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return vehicleId;
    }
        public  static void updateBookingStatus(int bookingId,String status){
            Connection connection = null;
            PreparedStatement psBookingStatusUpdate = null;
            if (status.equals("active")){
                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
                    psBookingStatusUpdate = connection.prepareStatement("UPDATE userbookings SET status=? WHERE bookingId=?");
                    psBookingStatusUpdate.setString(1,status);
                    psBookingStatusUpdate.setInt(2,bookingId);
                }catch(SQLException e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(psBookingStatusUpdate != null){
                            psBookingStatusUpdate.close();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                    try{
                        if(connection != null){
                            connection.close();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }

            } else if (status.equals("complete")) {
                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
                    psBookingStatusUpdate = connection.prepareStatement("UPDATE userbookings SET status=? WHERE bookingId=?");
                    psBookingStatusUpdate.setString(1,status);
                    psBookingStatusUpdate.setInt(2,bookingId);
                }catch(SQLException e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(psBookingStatusUpdate != null){
                            psBookingStatusUpdate.close();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                    try{
                        if(connection != null){
                            connection.close();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
                    psBookingStatusUpdate = connection.prepareStatement("UPDATE bookingmaster SET status=? WHERE bookingId=?");
                    psBookingStatusUpdate.setString(1,status);
                    psBookingStatusUpdate.setInt(2,bookingId);
                }catch(SQLException e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(psBookingStatusUpdate != null){
                            psBookingStatusUpdate.close();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                    try{
                        if(connection != null){
                            connection.close();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }


            }
            else if (status.equals("cancel")) {
                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
                    psBookingStatusUpdate = connection.prepareStatement("UPDATE userbookings SET status=? WHERE bookingId=?");
                    psBookingStatusUpdate.setString(1,status);
                    psBookingStatusUpdate.setInt(2,bookingId);
                }catch(SQLException e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(psBookingStatusUpdate != null){
                            psBookingStatusUpdate.close();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                    try{
                        if(connection != null){
                            connection.close();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
                    psBookingStatusUpdate = connection.prepareStatement("UPDATE bookingmaster SET status=? WHERE bookingId=?");
                    psBookingStatusUpdate.setString(1,status);
                    psBookingStatusUpdate.setInt(2,bookingId);
                }catch(SQLException e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(psBookingStatusUpdate != null){
                            psBookingStatusUpdate.close();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                    try{
                        if(connection != null){
                            connection.close();
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            }

        }

    public static void updateVehicleAvailability(int vehicleId) {
        Connection connection = null;
        PreparedStatement psVehicleUpdate = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psVehicleUpdate = connection.prepareStatement("UPDATE vehicles SET vehicleAvailability=0 WHERE vehicleId=?");
            psVehicleUpdate.setInt(1, vehicleId);
            psVehicleUpdate.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateVehicleAvailability SQL Exception:" + e.getMessage());
        }
    }
    public static void updateDriverAvailability(int driverId) {
        Connection connection = null;
        PreparedStatement psVehicleUpdate = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice", "root", "");
            psVehicleUpdate = connection.prepareStatement("UPDATE drivers SET driverAvailability=0 WHERE driverId=?");
            psVehicleUpdate.setInt(1, driverId);
            psVehicleUpdate.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateDriverAvailability SQL Exception:" + e.getMessage());
        }
    }
    public static int getBookingId(){
        Connection connection;
        Statement getLatestBookingId;
        ResultSet resultSet;
        int bookingId = 0;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabservice","root","");
            getLatestBookingId = connection.createStatement();
            String sql = "SELECT bookingId FROM userBookings ORDER BY bookingId DESC LIMIT 1;";
            resultSet = getLatestBookingId.executeQuery(sql);
            while(resultSet.next()){
                bookingId = resultSet.getInt("bookingId") + 1;
            }
        } catch (SQLException e) {
            System.out.println("getBookingId "+e.getMessage());
        }
        return bookingId;
    }}


