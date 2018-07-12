package ua.danit.entity;

public class User {
    private int id;
    private String name;
    private String photo;
    private Boolean liked;

    public User(int id, String name, String photo, Boolean liked) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.liked = liked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
