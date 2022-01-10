package com.developerdepository.scout.HelperClasses.DashboardHelperClasses;

public class FeaturedModel {
    int image;
    int title, description;
    double ratingBar;
    String url;
    public FeaturedModel() {
    }

    public FeaturedModel(int image, int title, int description, double ratingBar, String url) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.ratingBar = ratingBar;
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public int getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }

    public double getRatingBar() {return ratingBar;}

    public String getUrl() {
        return url;
    }

}
