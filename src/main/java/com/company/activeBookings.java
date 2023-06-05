package com.company;

public class activeBookings {
    private int bookingId;
    private int userId;
    private int contactNo;
    private String pickupLocation;
    private String destination;
    private String vehicleType;
    private String vehicleNo;
    private int driverId;
    private String driverName;

    private String date;
    private String time;

    public activeBookings(int bookingId,
                          int userId,
                          int contactNo,
                          String pickupLocation,
                          String destination,
                          String vehicleType,
                          String vehicleNo,
                          int driverId,
                          String driverName,
                          String date,
                          String time){
      this.bookingId = bookingId;
      this.userId = userId;
      this.contactNo = contactNo;
      this.pickupLocation = pickupLocation;
      this.destination = destination;
      this.vehicleType = vehicleType;
      this.vehicleNo = vehicleNo;
      this.driverId = driverId;
      this.driverName = driverName;
      this.date = date;
      this.time = time;
    }
    public int getBookingId(){
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public int getContactNo() {
        return contactNo;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }

}
