package com.example.beautyhub.servercommunication;


import android.graphics.Bitmap;

public class SubService {

    private static final String DATA_URL = "http://192.168.150.1/beautyhub/subservice.php";




    // Member variables
    private String subserviceName;
    private Bitmap subserviceImage;
    private String subservicedescription;
    private String subserviceduration;
    private String subserviceprice;

    // Constructor
    public SubService(String subserviceName, Bitmap subserviceImage, String subservicedescription, String subserviceduration, String subserviceprice) {
        this.subserviceName = subserviceName;
        this.subserviceImage = subserviceImage;
        this.subservicedescription = subservicedescription;
        this.subserviceduration = subserviceduration;
        this.subserviceprice = subserviceprice;
    }

    // Getters

    public String getSubServiceName() {
        return subserviceName;
    }

    public Bitmap getSubServiceImage() {
        return subserviceImage;
    }

    public String getSubServiceDescription() {
        return subservicedescription;
    }

    public String getSubServiceDuration() {
        return subserviceduration;
    }

    public String getSubServicePrice() {
        return subserviceprice;
    }
}
