package com.example.beautyhub.servercommunication;


import android.graphics.Bitmap;

public class Service {

        private static final String DATA_URL = "http://192.168.150.1/beautyhub/service.php";



        // Member variables
        private String serviceId;
        private String serviceName;
        private Bitmap serviceImage;
        private String description;
        private String duration;
        private String price;

        // Constructor
        public Service(String serviceId, String serviceName, Bitmap serviceImage, String description, String duration, String price) {
                this.serviceId = serviceId;
                this.serviceName = serviceName;
                this.serviceImage = serviceImage;
                this.description = description;
                this.duration = duration;
                this.price = price;
        }

        // Getters
        public String getServiceId() {
                return serviceId;
        }

        public String getServiceName() {
                return serviceName;
        }

        public Bitmap getServiceImage() {
                return serviceImage;
        }

        public String getDescription() {
                return description;
        }


}
