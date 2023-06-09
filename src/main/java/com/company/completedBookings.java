package com.company;

public class completedBookings extends activeBookings{
    public completedBookings(int bookingId,
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
        super(bookingId, userId, contactNo, pickupLocation, destination, vehicleType, vehicleNo, driverId, driverName, date, time);


    }


}
