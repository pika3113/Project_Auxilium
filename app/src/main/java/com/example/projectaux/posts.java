package com.example.projectaux;

public class posts {

    String title, sec, subject, desc, author;
    boolean visibility;

    public posts() {

        this.visibility = false;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getTitle() {
        return title;
    }

    public String getSec() {
        return sec;
    }

    public String getSubject() {
        return subject;
    }

    public String getDesc() {
        return desc;
    }

    public String getAuthor() {
        return author;
    }
}
