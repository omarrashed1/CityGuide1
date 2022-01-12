package com.developerdepository.scout.HelperClasses.DashboardHelperClasses;

public class MostViewedModel {
    int image;
    String title, description;
    double ratingBar;
    String url;
    public MostViewedModel() {
    }

    public MostViewedModel(int image, String title, String description, double ratingBar , String url) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.ratingBar = ratingBar;
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public double getRatingBar() {return ratingBar;}

    public String getUrl() {
        return url;
    }
}
