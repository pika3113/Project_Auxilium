package com.example.projectaux;

public class postshelper {

    String title, sec, subject, desc, userid, author;

    public postshelper() {
    }

    public postshelper(String title, String sec, String subject, String desc, String userid, String author) {
        this.title = title;
        this.sec = sec;
        this.subject = subject;
        this.desc = desc;
        this.userid = userid;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUserid() {return userid;}

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAuthor() {return author;}

    public void setAuthor(String author) {
        this.author = author;
    }
}
