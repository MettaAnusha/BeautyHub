package com.example.beautyhub.servercommunication;



public class Appointment {

    private static final String DATA_URL = "http://192.168.150.1/beautyhub/appointmentlist.php";


    // Member variables
    private String proName;
    private String subServiceName;
    private String date;
    private String time;
    private String subServicePrice;

    // Constructor
    public Appointment(String proName, String subServiceName, String date, String time, String subServicePrice) {
        this.proName = proName;
        this.subServiceName = subServiceName;
        this.date = date;
        this.time = time;
        this.subServicePrice = subServicePrice;
    }

    // Getters
    public String getProName() {
        return proName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSubServiceName() {
        return subServiceName;
    }

    public String getSubServicePrice() {
        return subServicePrice;
    }
}
