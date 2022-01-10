package com.developerdepository.scout.HelperClasses.DashboardHelperClasses;

public class CategoriesModel {
    int background, image;
    int title;

    public CategoriesModel() {
    }

    public CategoriesModel(int background, int image, int title) {
        this.background = background;
        this.image = image;
        this.title = title;
    }

    public int getBackground() {
        return background;
    }

    public int getImage() {
        return image;
    }

    public int getTitle() {
        return title;
    }
}
