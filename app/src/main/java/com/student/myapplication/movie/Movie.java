package com.student.myapplication.movie;

public class Movie {
    private String title;
    private String year;
    private String mark;
    private String image;
    private String description;

    public Movie(String title, String year, String mark, String image, String description) {
        this.title = title;
        this.year = year;
        this.mark = mark;
        this.image = image;
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
