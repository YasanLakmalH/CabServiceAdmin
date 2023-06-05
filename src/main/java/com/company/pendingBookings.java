package com.company;

public class pendingBookings {
    private int userId;
    private int bookingId;
    private int contactNo;
    private String pickupLocation;
    private String destination;
    private String vehicleType;
    private String date;
    private String time;

    public pendingBookings(
                    int bookingId,
                    int userId,
                    int contactNo,
                    String pickupLocation,
                    String destination,
                    String vehicleType,
                    String date,
                    String time){
        this.userId = userId;
        this.bookingId = bookingId;
        this.contactNo = contactNo;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.vehicleType = vehicleType;
        this.date = date;
        this.time = time;


    }

    public int getUserId() {
        return userId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getContactNo() {
        return contactNo;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }
    public String getDestination(){
        return destination;
    }
    public String getVehicleType(){
        return vehicleType;
    }

    public String getDate(){
        return date;
    }
    public String getTime() {
        return time;
    }

}
