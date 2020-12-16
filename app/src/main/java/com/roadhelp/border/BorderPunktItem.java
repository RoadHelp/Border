package com.roadhelp.border;

public class BorderPunktItem {

    private int imageRosource;
    private String title;
    private String description;
    private String recipe;

    public BorderPunktItem(int imageRosource, String title, String description, String recipe) {
        this.imageRosource = imageRosource;
        this.title = title;
        this.description = description;
        this.recipe = recipe;
    }


    public int getImageRosource() {
        return imageRosource;
    }

    public String getTitle() {
        return title;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getDescription() {
        return description;
    }
}
