package com.company;

public class vehicleModel {
    private int vehicleId;
    private String vehicleOwnerName;
    private String vehicleType;
    private String vehicleModel;
    private String vehicleNo;
    private int vehicleAvailability;
    public vehicleModel(int vehicleId,
                        String vehicleOwnerName,
                        String vehicleType,
                        String vehicleModel,
                        String vehicleNo,
                        int vehicleAvailability
    ){
        this.vehicleId = vehicleId;
        this.vehicleOwnerName = vehicleOwnerName;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.vehicleNo = vehicleNo;
        this.vehicleAvailability = vehicleAvailability;
    }
    public int getVehicleId(){
        return vehicleId;
    }
    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public String getVehicleModel() {
        return vehicleModel;
    }
    public String getVehicleNo() {
        return vehicleNo;
    }
    public int getVehicleAvailability(){
        return vehicleAvailability;
    }
}
