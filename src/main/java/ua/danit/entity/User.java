package ua.danit.entity;

public class User {
    private String name;
    private String imgURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public User(String name, String imgURL) {

        this.name = name;
        this.imgURL = imgURL;
    }
}
