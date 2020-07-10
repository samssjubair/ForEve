package com.example.foreve;

public class UserData {
    private String user;
    private String useremail;
    private String usermobile;
    private String fr1Email;
    private String fr1Mobile;
    private String fr2Email;
    private String fr2Mobile;
    private String fr3Email;
    private String fr3Mobile;

    public UserData(String user, String useremail, String usermobile, String fr1Email, String fr1Mobile, String fr2Email, String fr2Mobile, String fr3Email, String fr3Mobile) {
        this.user = user;
        this.useremail = useremail;
        this.usermobile = usermobile;
        this.fr1Email = fr1Email;
        this.fr1Mobile = fr1Mobile;
        this.fr2Email = fr2Email;
        this.fr2Mobile = fr2Mobile;
        this.fr3Email = fr3Email;
        this.fr3Mobile = fr3Mobile;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsermobile() {
        return usermobile;
    }

    public void setUsermobile(String usermobile) {
        this.usermobile = usermobile;
    }

    public String getFr1Email() {
        return fr1Email;
    }

    public void setFr1Email(String fr1Email) {
        this.fr1Email = fr1Email;
    }

    public String getFr1Mobile() {
        return fr1Mobile;
    }

    public void setFr1Mobile(String fr1Mobile) {
        this.fr1Mobile = fr1Mobile;
    }

    public String getFr2Email() {
        return fr2Email;
    }

    public void setFr2Email(String fr2Email) {
        this.fr2Email = fr2Email;
    }

    public String getFr2Mobile() {
        return fr2Mobile;
    }

    public void setFr2Mobile(String fr2Mobile) {
        this.fr2Mobile = fr2Mobile;
    }

    public String getFr3Email() {
        return fr3Email;
    }

    public void setFr3Email(String fr3Email) {
        this.fr3Email = fr3Email;
    }

    public String getFr3Mobile() {
        return fr3Mobile;
    }

    public void setFr3Mobile(String fr3Mobile) {
        this.fr3Mobile = fr3Mobile;
    }
}
