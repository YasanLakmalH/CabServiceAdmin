package com.company;
public class activeBookings extends pendingBookings{
    private String vehicleNo;
    private int driverId;
    private String driverName;

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
        super(bookingId, userId, contactNo, pickupLocation, destination, vehicleType, date,time);

      this.vehicleNo = vehicleNo;
      this.driverId = driverId;
      this.driverName = driverName;
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

}
