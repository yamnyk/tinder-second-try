package ua.danit.entity;

public class User {
    private String name;
    private String imgURL;
    private Boolean liked;

    public User(String name, String imgURL) {
        this.name = name;
        this.imgURL = imgURL;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

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
}
