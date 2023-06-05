package com.company;

public class driverModel {
    private int driverId;
    private Long nic;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String city;

    public driverModel(int driverId, Long nic, String firstName, String lastName, String mobile, String email, String city){
        this.driverId = driverId;
        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.city = city;
    }
    public int getDriverId(){
        return driverId;
    }

    public Long getNic() {
        return nic;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getMobile(){
        return mobile;
    }
    public String getEmail(){
        return email;
    }
    public String getCity(){
        return city;
    }
}
