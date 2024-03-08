package com.example.beautyhub.servercommunication;


import android.graphics.Bitmap;

public class Professionals {

    private static final String DATA_URL = "http://192.168.150.1/beautyhub/professionalslist.php";





    // Member variables
    private String proName;
    private Bitmap proImage;
    private String rating;
    private String profession;

    // Constructor
    public Professionals(String proName, Bitmap proImage, String rating, String profession) {
        this.proName = proName;
        this.proImage = proImage;
        this.rating = rating;
        this.profession = profession;
    }

    // Getters

    public String  getProName() {
        return proName;
    }

    public Bitmap getProImage() {
        return proImage;
    }

    public String getRating() {
        return rating;
    }

    public String getProfession() {
        return profession;
    }


}
