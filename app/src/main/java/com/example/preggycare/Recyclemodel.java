package com.example.preggycare;

public class Recyclemodel {

    public String title;
    public String description;
    boolean isvisible;

    public Recyclemodel(String title, String description, boolean isvisible) {
        this.title = title;
        this.description = description;
        this.isvisible = isvisible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsvisible() {
        return isvisible;
    }

    public void setIsvisible(boolean isvisible) {
        this.isvisible = isvisible;
    }

    @Override
    public String toString() {
        return "Recyclemodel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
