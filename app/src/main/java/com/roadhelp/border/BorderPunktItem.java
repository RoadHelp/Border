package com.roadhelp.border;

public class BorderPunktItem {

    private int imageRosource;
    private String title;
    private String description;
    private String cameraUrl;

    public BorderPunktItem(int imageRosource, String title, String description, String cameraUrl) {
        this.imageRosource = imageRosource;
        this.title = title;
        this.description = description;
        this.cameraUrl = cameraUrl;
    }


    public int getImageRosource() {
        return imageRosource;
    }

    public String getTitle() {
        return title;
    }

    public String getCameraUrl() {
        return cameraUrl;
    }

    public String getDescription() {
        return description;
    }
}
