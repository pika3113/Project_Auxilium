package com.example.projectaux;

public class UserHelper {

    String user,email,phoneno,userid,post,help;

    public UserHelper() {
    }

    public UserHelper(String user, String email, String phoneno, String userid,String post, String help) {
        this.user = user;
        this.email = email;
        this.phoneno = phoneno;

        this.post = post;
        this.help = help;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String userid) {this.post = post; }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {this.help = help; }

}
